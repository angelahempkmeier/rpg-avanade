package com.avanade.rpg.services;

import com.avanade.rpg.dto.characters.CharacterEditRequestDTO;
import com.avanade.rpg.dto.characters.CharacterResponseDTO;
import com.avanade.rpg.entities.Character;
import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;
import com.avanade.rpg.repositories.CharacterRepository;
import com.avanade.rpg.utils.CharacterConversionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private CharacterConversionService conversionService;

    @InjectMocks
    private CharacterService characterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindCharacterById() {
        Long characterId = 1L;
        Character character = new Character();

        when(characterRepository.findById(characterId)).thenReturn(Optional.of(character));

        CharacterResponseDTO expectedResponseDTO = new CharacterResponseDTO(
                1L,
                "TestCharacter",
                CharacterType.HERO,
                CharacterClass.WARRIOR,
                100,
                10,
                20,
                30,
                2,
                6
        );

        when(conversionService.convertCharacterToDTO(character)).thenReturn(expectedResponseDTO);

        CharacterResponseDTO responseDTO = characterService.findById(characterId);

        assertNotNull(responseDTO);
        assertEquals(1L, responseDTO.id());
        assertEquals("TestCharacter", responseDTO.name());
        assertEquals(CharacterType.HERO, responseDTO.type());
        assertEquals(CharacterClass.WARRIOR, responseDTO.characterClass());
        assertEquals(100, responseDTO.health());

    }

    @Test
    public void testFindAllCharacters() {
        List<Character> characters = new ArrayList<>();
        characters.add(new Character());

        when(characterRepository.findAll()).thenReturn(characters);

        List<CharacterResponseDTO> expectedResponseDTOs = new ArrayList<>();
        expectedResponseDTOs.add(new CharacterResponseDTO(
                1L,
                "Character Find All",
                CharacterType.HERO,
                CharacterClass.WARRIOR,
                100,
                10,
                20,
                30,
                2,
                6
        ));

        when(conversionService.convertCharacterToDTO(any(Character.class)))
                .thenReturn(expectedResponseDTOs.get(0), expectedResponseDTOs.toArray(new CharacterResponseDTO[0]));

        List<CharacterResponseDTO> responseDTOs = characterService.findAll();

        assertNotNull(responseDTOs);
        assertFalse(responseDTOs.isEmpty());
    }
    @Test
    public void testUpdateCharacter() {
            Long characterId = 1L;
            CharacterEditRequestDTO editDTO = new CharacterEditRequestDTO("UpdatedName");

            Character existingCharacter = new Character();

            when(characterRepository.findById(characterId)).thenReturn(Optional.of(existingCharacter));
            when(characterRepository.save(any(Character.class))).thenReturn(existingCharacter);

            CharacterResponseDTO expectedResponseDTO = new CharacterResponseDTO(
                    1L,
                    "UpdatedName",
                    CharacterType.HERO,
                    CharacterClass.WARRIOR,
                    100,
                    10,
                    20,
                    30,
                    2,
                    6
            );

            when(conversionService.convertCharacterToDTO(existingCharacter)).thenReturn(expectedResponseDTO);

            CharacterResponseDTO responseDTO = characterService.update(editDTO, characterId);

            assertNotNull(responseDTO);
            assertEquals("UpdatedName", responseDTO.name());
        }

    @Test
    public void testDeleteCharacter() {
        Long characterId = 1L;
        Character existingCharacter = new Character();

        when(characterRepository.findById(characterId)).thenReturn(Optional.of(existingCharacter));

        assertDoesNotThrow(() -> characterService.delete(characterId));
    }


}