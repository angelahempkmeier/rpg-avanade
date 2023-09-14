package com.avanade.rpg.entities;

import com.avanade.rpg.dto.characters.CharacterRequestDTO;
import com.avanade.rpg.dto.characters.CharacterResponseDTO;
import com.avanade.rpg.dto.history.CharacterHistoryDTO;
import com.avanade.rpg.strategy.CharacterBattle;
import com.avanade.rpg.utils.RollingDice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Random;

@Entity
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "character_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "characters")
public class Character implements CharacterBattle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer life;
    private Integer strength;
    private Integer defense;
    private Integer agility;
    private Integer diceQuantity;
    private Integer diceFaces;
    private Integer initialLife;

    public CharacterResponseDTO characterToResponseDTO(CharacterRequestDTO requestDTO){
        return new CharacterResponseDTO(
                this.id,
                requestDTO.name(),
                requestDTO.type(),
                requestDTO.characterClass(),
                this.life,
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
                this.life,
                this.strength,
                this.defense,
                this.agility,
                this.diceQuantity,
                this.diceFaces
        );
    }

    public CharacterHistoryDTO toCharacterHistoryDTO(){
        return new CharacterHistoryDTO(
                this.id,
                this.getName(),
                this.getType(),
                this.getCharClass()
        );
    }

    public String getType(){
        return "";
    }

    public String getCharClass(){
        return "";
    }

    public Integer rollingDice1d20(){
        return RollingDice.rollingDice1d20();
    }

    public Integer rollingDice1d12(){
        return RollingDice.rollingDice1d12();
    }

    @Override
    public Integer attack() {
        return rollingDice1d12() + this.strength + this.agility;
    }

    @Override
    public Integer defense() {
        return rollingDice1d12() + this.defense + this.agility;
    }

    @Override
    public Integer damage() {
        Random random = new Random();
        int minRoll = 1;
        int maxRoll = this.diceFaces;

        int totalDamage = 0;

        for (int i = 0; i < this.diceQuantity; i++) {
            int diceRoll = random.nextInt(maxRoll - minRoll + 1) + minRoll;
            totalDamage += diceRoll;
        }

        totalDamage += this.strength;
        return totalDamage;
    }

    @Override
    public Integer updateLife(Integer damage) {
        return this.life - damage;
    }

    public String getName(){
        return "Character";
    }

    public void setName(String name){
    }
}
