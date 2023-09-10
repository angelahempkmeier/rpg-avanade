package com.avanade.rpg.services;

import com.avanade.rpg.dto.CharacterRequestDTO;
import com.avanade.rpg.dto.CharacterResponseDTO;
import com.avanade.rpg.dto.CharactersAvailableDTO;
import com.avanade.rpg.entities.Character;
import com.avanade.rpg.entities.characters.heroes.Barbarian;
import com.avanade.rpg.entities.characters.heroes.Knight;
import com.avanade.rpg.entities.characters.heroes.Warrior;
import com.avanade.rpg.entities.characters.monsters.Giant;
import com.avanade.rpg.entities.characters.monsters.Orc;
import com.avanade.rpg.entities.characters.monsters.Werewolf;
import com.avanade.rpg.repositories.CharacterRepository;
import com.avanade.rpg.strategy.CharacterFactoryStrategy;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public CharacterResponseDTO create(CharacterRequestDTO dto){

        //TODO arrumar pra poder receber letra minuscula ou maiuscula

        Character character = CharacterFactoryStrategy.createCharacter(dto.name(), dto.type(), dto.characterClass());
        character = characterRepository.save(character);
        return character.characterToResponseDTO(dto);
    }

    public CharacterResponseDTO findById(Long id){
        Character character = characterRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Character not found.")
        );

        if(character instanceof Warrior){
            Warrior warrior = (Warrior) character;
            return warrior.characterResponseDTO();
        }else if(character instanceof Barbarian){
            Barbarian barbarian = (Barbarian) character;
            return barbarian.characterResponseDTO();
        } else if (character instanceof Knight) {
            Knight knight = (Knight) character;
            return knight.characterResponseDTO();
        } else if (character instanceof Giant) {
            Giant giant = (Giant) character;
            return giant.characterResponseDTO();
        } else if (character instanceof Orc) {
            Orc orc = (Orc) character;
            return orc.characterResponseDTO();
        } else if (character instanceof Werewolf) {
            Werewolf werewolf = (Werewolf) character;
            return werewolf.characterResponseDTO();
        }

        return null;
    }

    public CharactersAvailableDTO getCharactersAvailable(){
        return null;
        //return characterRepository.findAll();
    }


}
