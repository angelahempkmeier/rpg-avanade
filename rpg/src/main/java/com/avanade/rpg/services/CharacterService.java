package com.avanade.rpg.services;

import com.avanade.rpg.dto.characters.CharacterEditRequestDTO;
import com.avanade.rpg.dto.characters.CharacterRequestDTO;
import com.avanade.rpg.dto.characters.CharacterResponseDTO;
import com.avanade.rpg.entities.Character;
import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;
import com.avanade.rpg.exceptions.CharacterNotFoundException;
import com.avanade.rpg.exceptions.OnlyNameCanBeUpdatedException;
import com.avanade.rpg.repositories.CharacterRepository;
import com.avanade.rpg.strategy.CharacterFactoryStrategy;
import com.avanade.rpg.utils.CharacterConversionService;
import com.avanade.rpg.utils.MatchingEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CharacterService {
    private CharacterRepository characterRepository;
    private CharacterConversionService conversionService;

    public CharacterService(CharacterRepository characterRepository, CharacterConversionService conversionService) {
        this.characterRepository = characterRepository;
        this.conversionService = conversionService;
    }

    public CharacterResponseDTO create(CharacterRequestDTO dto){
        CharacterType type = MatchingEnum.getEnumType(dto.type());
        CharacterClass characterClass = MatchingEnum.getEnumClass(dto.characterClass());
        Character character =
                CharacterFactoryStrategy.createCharacter(dto.name(), type, characterClass);
        character = characterRepository.save(character);
        return character.characterToResponseDTO(dto);
    }

    public CharacterResponseDTO findById(Long id){
        Character character = characterRepository.findById(id).orElseThrow(
                () -> new CharacterNotFoundException(id)
        );
        return conversionService.convertCharacterToDTO(character);
    }

    public List<CharacterResponseDTO> findAll(){
        List<Character> characters = characterRepository.findAll();
        List<CharacterResponseDTO> dtoList = characters.stream()
                .map(character -> conversionService.convertCharacterToDTO(character))
                .collect(Collectors.toList());
        return dtoList;
    }

    public CharacterResponseDTO update(CharacterEditRequestDTO dto, Long id){
        Character existingCharacter = characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(id));

        if (dto != null && (dto.name() == null || dto.getClass().getDeclaredFields().length > 1)) {
            throw new OnlyNameCanBeUpdatedException("Only 'name' field is allowed to be updated.");
        }
        //Changes allowed only for name
        if(dto.name() != null){
            existingCharacter.setName(dto.name());
        }
        characterRepository.save(existingCharacter);
        return conversionService.convertCharacterToDTO(existingCharacter);
    }

    public void delete(Long id){
        characterRepository.findById(id).orElseThrow(
                () -> new CharacterNotFoundException(id));
        characterRepository.deleteById(id);
    }

    protected Character findCharacterById(Long id){
        return characterRepository.findById(id).orElseThrow(
                () -> new CharacterNotFoundException(id)
        );
    }

    protected void updateLifeCharacter(Character character, Integer damage){
        Integer life = character.updateLife(damage);
        character.setLife(life);
        characterRepository.save(character);
    }

    public Boolean opponentIsAMonster(Character opponent){
        return opponent.getType() == CharacterType.MONSTER.name();
    }

    protected Boolean endingGame(Character character){
        return character.getLife() <= 0;
    }

    protected Boolean doDamage(Integer attack, Integer defense){
        return attack > defense;
    }

    private String generateRandomName() {
        String[] possibleNames = {
                "Sauron",
                "Smeagol",
                "Goblin",
                "Lord Voldemort",
                "Dolores Umbridge",
                "Lucius Malfoy",
                "Darth Vader",
                "Joker",
                "Hannibal Lecter",
                "Norman Bates",
                "Scar"
        };
        return possibleNames[new Random().nextInt(possibleNames.length)];
    }

    public Character createRandomMonster() {
        List<CharacterClass> monsterClasses = new ArrayList<>();
        monsterClasses.add(CharacterClass.ORC);
        monsterClasses.add(CharacterClass.GIANT);
        monsterClasses.add(CharacterClass.WEREWOLF);
        CharacterClass randomMonsterClass = monsterClasses.get(new Random().nextInt(monsterClasses.size()));
        Character randomCharacter =
                CharacterFactoryStrategy.createCharacter(generateRandomName(), CharacterType.MONSTER, randomMonsterClass);
        return characterRepository.save(randomCharacter);
    }
}
