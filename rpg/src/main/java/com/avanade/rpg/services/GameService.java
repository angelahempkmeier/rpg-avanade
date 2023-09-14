package com.avanade.rpg.services;

import com.avanade.rpg.dto.game.AttackDTO;
import com.avanade.rpg.dto.game.DefenseDTO;
import com.avanade.rpg.dto.game.StartGameDTO;
import com.avanade.rpg.entities.BattleHistory;
import com.avanade.rpg.entities.Character;
import com.avanade.rpg.entities.Game;
import com.avanade.rpg.entities.TurnBattle;
import com.avanade.rpg.enums.GameStatus;
import com.avanade.rpg.enums.TurnStatus;
import com.avanade.rpg.exceptions.*;
import com.avanade.rpg.repositories.BattleHistoryRepository;
import com.avanade.rpg.repositories.GameRepository;
import com.avanade.rpg.repositories.TurnBattleRepository;
import com.avanade.rpg.utils.responses.AttackResponse;
import com.avanade.rpg.utils.responses.DamageResponse;
import com.avanade.rpg.utils.responses.DefenseResponse;
import com.avanade.rpg.utils.responses.StartGameResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class GameService {
    private CharacterService characterService;
    private TurnBattleService turnBattleService;
    private GameRepository gameRepository;
    private TurnBattleRepository turnBattleRepository;
    private BattleHistoryRepository battleHistoryRepository;
    private Game currentGame;
    private TurnBattle currentTurn;
    private BattleHistory battleHistory;
    private int turn = 1;

    public GameService(CharacterService characterService,
                       GameRepository gameRepository,
                       TurnBattleRepository turnBattleRepository,
                       BattleHistoryRepository battleHistoryRepository,
                       TurnBattleService turnBattleService) {
        this.characterService = characterService;
        this.gameRepository = gameRepository;
        this.turnBattleRepository = turnBattleRepository;
        this.battleHistoryRepository = battleHistoryRepository;
        this.turnBattleService = turnBattleService;
    }

    public Map<String, Object> startGame(StartGameDTO startGameDTO){
        Long playerId = startGameDTO.player();
        Character opponent;
        Long opponentId = startGameDTO.opponent();
        opponent = (opponentId == null) ? characterService.createRandomMonster() : characterService.findCharacterById(opponentId);
        if(IsPlayerTryingToPlayAgainstItself(playerId, opponentId)){
            throw new YouCannotPlayAgainstYourselfException();
        }
        if(!characterService.opponentIsAMonster(opponent)){
            throw new OpponentHasToBeAMonsterException("Opponent has to be a monster.");
        }
        try {
            Character player = characterService.findCharacterById(playerId);
            currentGame = new Game();
            battleHistory = new BattleHistory();
            currentGame.setPlayer(player);
            currentGame.setOpponent(opponent);
            player.setLife(player.getInitialLife());
            gameRepository.save(currentGame);
            startFirstTurn();
            return whoInitiated(player, opponent, currentGame);
        }catch (EntityNotFoundException e){
            throw new CharacterNotFoundException(playerId);
        }
    }

    public Map<String, Object> attack(AttackDTO attacker){
        Character character = characterService.findCharacterById(attacker.id());
        if(currentTurn == null) {
            throw new TurnNotStartedException("First, the turn must be started, and it occurs immediately after the game starts. Please start the game before attacking.");
        }
        if(currentTurn.getStatus().equals(TurnStatus.STARTED)){
            if (character.getId().equals(currentTurn.getAttacker().getId())) {
                Integer attack = character.attack();
                currentTurn.setStatus(TurnStatus.ATTACK);
                currentTurn.setAttack(attack);
            }
        }else{
            throw new GameNotStartedException("The game hasn't started yet. You cannot attack.");
        }
        turnBattleRepository.save(currentTurn);
        Map<String, Object> attackResponse = AttackResponse.attackResponse(character, currentTurn);
        return attackResponse;
    }

    public Map<String, Object> defense(DefenseDTO defender){
        Map<String, Object> responseDefense;
        Character character = characterService.findCharacterById(defender.id());
        if(currentTurn.getStatus().equals(TurnStatus.ATTACK)){
            if(character.characterResponseDTO().id().equals(currentTurn.getDefender().getId())){
                Integer defense = character.defense();
                currentTurn.setStatus(TurnStatus.DEFENSE);
                currentTurn.setDefense(defense);
                if(currentTurn.getAttack() > currentTurn.getDefense()){
                    responseDefense = DefenseResponse.defenderLost(defense, currentTurn);
                }else{
                    responseDefense = DefenseResponse.defenderWins(defense, currentTurn);
                    endTurn();
                }
            }else{
                throw new NotYourTurnToDefenseException(defender.id());
            }
        }else{
            throw new DefenseNotAllowedException("First you have to attack, after that, you'll be able to defend yourself.");
        }
        turnBattleRepository.save(currentTurn);
        return responseDefense;
    }

    public Map<String, Object> damage(){
        if(currentTurn == null || !(currentTurn.getStatus() == TurnStatus.DEFENSE)){
            throw new DamageNotAllowedException("To calculate the damage, the game must have been started, and both players must have completed the attack and defense phases.");
        }
        Integer damageValue;
        Character attacker = currentTurn.getAttacker();
        Character defender = currentTurn.getDefender();

        if(characterService.doDamage(currentTurn.getAttack(), currentTurn.getDefense())){
            damageValue = attacker.damage();
            characterService.updateLifeCharacter(defender, damageValue);
            currentTurn.setDamage(damageValue);
        }else{
            startNewTurn();
        }
        Map<String, Object> damageResponse = DamageResponse.damageResponse(currentTurn);
        if(characterService.endingGame(defender)){
            currentGame.setWinner(attacker.getName());
            gameRepository.save(currentGame);
            endGame();
        }else{
            endTurn();
        }
        turnBattleRepository.save(currentTurn);
        return damageResponse;
    }

    private void startFirstTurn(){
        currentTurn = new TurnBattle();
        currentTurn.setStatus(TurnStatus.STARTED);
        currentTurn.setGame(currentGame);
        currentTurn.setTurn(turn);
        turnBattleRepository.save(currentTurn);
    }

    private void startNewTurn(){
        Character attacker = currentTurn.getAttacker();
        Character defender = currentTurn.getDefender();
        currentTurn = new TurnBattle();
        currentTurn.setStatus(TurnStatus.STARTED);
        currentTurn.setGame(currentGame);
        currentTurn.setTurn(turn);
        currentTurn.setAttacker(defender);
        currentTurn.setDefender(attacker);
        turnBattleRepository.save(currentTurn);
    }

    private void endTurn(){
        currentTurn.setStatus(TurnStatus.FINISHED);
        turn++;
        currentGame.setTurnBattles(currentTurn);
        turnBattleRepository.save(currentTurn);
        if(!(currentGame.getGameStatus() == GameStatus.FINISHED)){
            startNewTurn();
        }
    }

    private void endGame() {
        currentGame.setGameStatus(GameStatus.FINISHED);
        turn = 1;
        battleHistory.setGame(currentGame);
        battleHistoryRepository.save(battleHistory);
        gameRepository.save(currentGame);
        currentGame = null;
    }

    private Boolean IsPlayerTryingToPlayAgainstItself(Long playerId, Long opponentId){
        return playerId == opponentId;
    }

    private Map<String, Object> whoInitiated(Character player, Character opponent, Game game) {
        Character winner = null;
        int winningDiceRoll = 0;
        Character loser = null;
        int losingDiceRoll = 0;

        Integer playerDice = player.rollingDice1d20();
        Integer opponentDice = opponent.rollingDice1d20();

        if (playerDice > opponentDice) {
            game.setPlayerInitiated(true);
            winner = player;
            loser = opponent;
            winningDiceRoll = playerDice;
            losingDiceRoll = opponentDice;
        } else if (playerDice < opponentDice) {
            game.setPlayerInitiated(false);
            winner = opponent;
            loser = player;
            winningDiceRoll = opponentDice;
            losingDiceRoll = playerDice;
        } else {
            whoInitiated(player, opponent, game);
        }
        currentTurn.setAttacker(winner);
        currentTurn.setDefender(loser);
        turnBattleRepository.save(currentTurn);
        return StartGameResponse.startGameResponse(winner, winningDiceRoll, loser, losingDiceRoll);
    }
}
