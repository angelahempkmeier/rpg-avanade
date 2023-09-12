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
@DiscriminatorValue("giant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Giant extends Character {
    private String name;
    private CharacterClass characterClass = CharacterClass.GIANT;
    public Giant(String name) {
        super(null, 34, 10, 4,4, 2, 6);
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

    @Override
    public String getName() {
        return this.name;
    }
}
