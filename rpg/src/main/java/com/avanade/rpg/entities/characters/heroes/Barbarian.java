package com.avanade.rpg.entities.characters.heroes;

import com.avanade.rpg.dto.CharacterRequestDTO;
import com.avanade.rpg.dto.CharacterResponseDTO;
import com.avanade.rpg.entities.Character;
import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("barbarian")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Barbarian extends Character {
    private String name;
    private CharacterClass characterClass = CharacterClass.BARBARIAN;

    public Barbarian(String name){
        super(null, 21, 10, 2, 5, 2, 8);
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



}
