package com.avanade.rpg.entities.characters.monsters;

import com.avanade.rpg.dto.characters.CharacterResponseDTO;
import com.avanade.rpg.entities.Character;
import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("werewolf")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Werewolf extends Character {
    @NotNull @NotBlank
    private String name;
    private CharacterClass characterClass = CharacterClass.WEREWOLF;
    public Werewolf(String name) {
        super(null, 34, 7, 4, 7, 2, 4,34);
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
    @Override
    public void setName(String name){
        this.name = name;
    }
    @Override
    public String getType() {
        return CharacterType.MONSTER.name();
    }
    @Override
    public String getCharClass(){
        return this.characterClass.name();
    }
}
