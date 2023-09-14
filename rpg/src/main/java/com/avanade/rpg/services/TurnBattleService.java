package com.avanade.rpg.services;

import com.avanade.rpg.entities.Character;
import com.avanade.rpg.entities.Game;
import com.avanade.rpg.entities.TurnBattle;
import com.avanade.rpg.enums.GameStatus;
import com.avanade.rpg.enums.TurnStatus;
import com.avanade.rpg.repositories.TurnBattleRepository;
import org.springframework.stereotype.Service;

@Service
public class TurnBattleService {
    private TurnBattleRepository turnBattleRepository;
    protected TurnBattle currentTurn;
    private int turn = 1;

    public TurnBattleService(TurnBattleRepository turnBattleRepository) {
        this.turnBattleRepository = turnBattleRepository;
    }

    public int turn(){
        int turn = 1;
        return turn;
    }

    public TurnBattle startFirstTurn(Game currentGame){
        currentTurn = new TurnBattle();
        currentTurn.setStatus(TurnStatus.STARTED);
        currentTurn.setGame(currentGame);
        currentTurn.setTurn(turn);
        turnBattleRepository.save(currentTurn);
        return currentTurn;
    }

    public TurnBattle startNewTurn(Game currentGame){
        Character attacker = currentTurn.getAttacker();
        Character defender = currentTurn.getDefender();
        currentTurn = new TurnBattle();
        currentTurn.setStatus(TurnStatus.STARTED);
        currentTurn.setGame(currentGame);
        currentTurn.setTurn(turn);
        currentTurn.setAttacker(defender);
        currentTurn.setDefender(attacker);
        turnBattleRepository.save(currentTurn);
        return currentTurn;
    }

    public void endTurn(Game currentGame){
        currentTurn.setStatus(TurnStatus.FINISHED);
        turn++;
        currentGame.setTurnBattles(currentTurn);
        turnBattleRepository.save(currentTurn);
        if(!(currentGame.getGameStatus() == GameStatus.FINISHED)){
            startNewTurn(currentGame);
        }
    }
}
