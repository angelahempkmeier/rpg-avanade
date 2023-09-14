package com.avanade.rpg.utils;

import com.avanade.rpg.dto.characters.CharacterResponseDTO;
import com.avanade.rpg.entities.Character;
import com.avanade.rpg.entities.characters.heroes.Barbarian;
import com.avanade.rpg.entities.characters.heroes.Knight;
import com.avanade.rpg.entities.characters.heroes.Warrior;
import com.avanade.rpg.entities.characters.monsters.Giant;
import com.avanade.rpg.entities.characters.monsters.Orc;
import com.avanade.rpg.entities.characters.monsters.Werewolf;
import org.springframework.stereotype.Service;

@Service
public class CharacterConversionService {

    public CharacterResponseDTO convertCharacterToDTO(Character character) {
        if (character == null) {
            return null;
        }
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
}