package com.avanade.rpg.entities.characters.monsters;

import com.avanade.rpg.dto.characters.CharacterResponseDTO;
import com.avanade.rpg.entities.Character;
import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("orc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orc extends Character {
    private String name;
    private CharacterClass characterClass = CharacterClass.ORC;

    public Orc(String name){
        super(null, 42, 7, 1, 2, 3, 4);
        this.name = name;
    }

    @Override
    public CharacterResponseDTO characterResponseDTO() {
        CharacterResponseDTO parentDTO = super.characterResponseDTO();
        CharacterResponseDTO responseDTO = new CharacterResponseDTO(
                parentDTO.id(),
                this.name,
                CharacterType.MONSTER,
                this.characterClass,
                parentDTO.health(),
                parentDTO.strength(),
                parentDTO.defense(),
                parentDTO.agility(),
                parentDTO.diceQuantity(),
                parentDTO.diceFaces()
        );
        return responseDTO;
    }
}
