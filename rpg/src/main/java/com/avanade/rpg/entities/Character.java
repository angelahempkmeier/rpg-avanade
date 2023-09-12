package com.avanade.rpg.entities;

import com.avanade.rpg.dto.characters.CharacterRequestDTO;
import com.avanade.rpg.dto.characters.CharacterResponseDTO;
import com.avanade.rpg.dto.game.AttackResponseDTO;
import com.avanade.rpg.dto.game.DefenseResponseDTO;
import com.avanade.rpg.entities.characters.heroes.Warrior;
import com.avanade.rpg.strategy.CharacterBattle;
import com.avanade.rpg.utils.RollingDice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

//TODO sera que preciso que seja uma entidade?

@Entity
//@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "character_type")
@Data
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

    public AttackResponseDTO characterToAttackResponse(){
        return new AttackResponseDTO(
                this,
                attack()
        );
    }

    public DefenseResponseDTO characterToDefenseResponse(){
        return new DefenseResponseDTO(
                this,
                attack()
        );
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
        return "";
    }
}
