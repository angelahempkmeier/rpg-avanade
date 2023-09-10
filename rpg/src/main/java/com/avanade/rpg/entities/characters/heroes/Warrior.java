package com.avanade.rpg.entities.characters.heroes;

import com.avanade.rpg.dto.characters.CharacterRequestDTO;
import com.avanade.rpg.dto.characters.CharacterResponseDTO;
import com.avanade.rpg.entities.Character;
import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("warrior")
public class Warrior extends Character {
    private String name;
    private CharacterClass characterClass = CharacterClass.WARRIOR;
    public Warrior(String name){
        super(null, 20, 7, 5, 6, 1, 12);
        this.name = name;
    }

    @Override
    public CharacterResponseDTO characterToResponseDTO(CharacterRequestDTO requestDTO) {
        return super.characterToResponseDTO(requestDTO);
    }

    @Override
    public CharacterResponseDTO characterResponseDTO() {
        CharacterResponseDTO parentDTO = super.characterResponseDTO();
        CharacterResponseDTO responseDTO = new CharacterResponseDTO(
                parentDTO.id(),
                this.name,
                CharacterType.HERO,
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

    //    public CharacterResponseDTO characterResponseDTO(){
//        return new CharacterResponseDTO(
//                this.getId(),
//                this.name,
//                CharacterType.HERO,
//                characterClass,
//                this.getHealth(),
//                this.getStrength(),
//                this.getDefense(),
//                this.getAgility(),
//                this.getDiceQuantity(),
//                this.getDiceFaces()
//        );
//    }
}
