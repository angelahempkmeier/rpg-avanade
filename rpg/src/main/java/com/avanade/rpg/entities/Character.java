package com.avanade.rpg.entities;

import com.avanade.rpg.dto.CharacterRequestDTO;
import com.avanade.rpg.dto.CharacterResponseDTO;
import com.avanade.rpg.entities.characters.heroes.Warrior;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO sera que preciso que seja uma entidade?

@Entity
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "character_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer health;
    private Integer strength;
    private Integer defense;
    private Integer agility;
    private Integer diceQuantity;
    private Integer diceFaces;

    public Character(CharacterRequestDTO dto){

    }

    public CharacterResponseDTO characterToResponseDTO(CharacterRequestDTO requestDTO){
        return new CharacterResponseDTO(
                this.id,
                requestDTO.name(),
                requestDTO.type(),
                requestDTO.characterClass(),
                this.health,
                this.strength,
                this.defense,
                this.agility,
                this.diceQuantity,
                this.diceFaces
        );
    }

    public CharacterResponseDTO characterResponseDTO(){
        return new CharacterResponseDTO(
                this.id,
                this.health,
                this.strength,
                this.defense,
                this.agility,
                this.diceQuantity,
                this.diceFaces
        );
    }

    public CharacterResponseDTO characterToResponseDTO(Character character){
        if(character instanceof Warrior){
            Warrior warrior = (Warrior) character;
            warrior.getName();
        }
        return new CharacterResponseDTO(
                this.id,
                this.health,
                this.strength,
                this.defense,
                this.agility,
                this.diceQuantity,
                this.diceFaces
        );
    }
}
