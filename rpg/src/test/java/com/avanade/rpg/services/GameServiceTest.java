package com.avanade.rpg.services;

import com.avanade.rpg.dto.game.AttackDTO;
import com.avanade.rpg.dto.game.DefenseDTO;
import com.avanade.rpg.dto.game.StartGameDTO;
import com.avanade.rpg.entities.Character;
import com.avanade.rpg.entities.Game;
import com.avanade.rpg.entities.TurnBattle;
import com.avanade.rpg.entities.characters.heroes.Warrior;
import com.avanade.rpg.entities.characters.monsters.Orc;
import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.TurnStatus;
import com.avanade.rpg.repositories.CharacterRepository;
import com.avanade.rpg.repositories.GameRepository;
import com.avanade.rpg.repositories.TurnBattleRepository;
import com.avanade.rpg.utils.CharacterConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GameServiceTest {
    @Mock
    private CharacterService characterService;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private TurnBattleRepository turnBattleRepository;
    @Mock
    private CharacterRepository characterRepository;
    @Mock
    private CharacterConversionService conversionService;
    @InjectMocks
    private GameService gameService;
    Character player;
    Character opponent;

    public GameServiceTest(){}

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        player = new Warrior("Warrior",
                CharacterClass.WARRIOR);
        player.setId(1L);
        player.setLife(10);
        player.setStrength(7);
        player.setDefense(5);
        player.setAgility(6);
        player.setDiceQuantity(1);
        player.setDiceFaces(12);
        player.setInitialLife(20);

        opponent = new Orc(
                "Orc",
                CharacterClass.ORC
        );
        opponent.setId(2L);
        opponent.setLife(42);
        opponent.setStrength(7);
        opponent.setDefense(1);
        opponent.setAgility(2);
        opponent.setDiceQuantity(3);
        opponent.setDiceFaces(4);
        opponent.setInitialLife(42);
    }

    @Test
    public void testStartGameWithOpponent() {
        // Arrange
        StartGameDTO startGameDTO = new StartGameDTO(
                1L,
                2L
        );
        when(characterService.findCharacterById(1L)).thenReturn(player);
        when(characterService.findCharacterById(2L)).thenReturn(opponent);
        when(characterService.opponentIsAMonster(opponent)).thenReturn(true);

        // Act
        Map<String, Object> result = gameService.startGame(startGameDTO);

        // Assert
        verify(gameRepository, times(1)).save(any(Game.class));
        assertNotNull(result);
    }
}