package com.avanade.rpg.strategy;

import com.avanade.rpg.entities.Character;
import com.avanade.rpg.entities.characters.heroes.Barbarian;
import com.avanade.rpg.entities.characters.heroes.Knight;
import com.avanade.rpg.entities.characters.heroes.Warrior;
import com.avanade.rpg.entities.characters.monsters.Giant;
import com.avanade.rpg.entities.characters.monsters.Orc;
import com.avanade.rpg.entities.characters.monsters.Werewolf;
import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;
import com.avanade.rpg.exceptions.NoSuchClassException;
import com.avanade.rpg.exceptions.NoSuchTypeException;

public class CharacterFactoryStrategy {
    public static Character createCharacter(String name, CharacterType type, CharacterClass characterClass){
        switch (type) {
            case HERO -> {
                return createHero(name, characterClass);
            }
            case MONSTER -> {
                return createMonster(name, characterClass);
            }
            default -> throw new NoSuchTypeException("Character type is not valid.");
        }
    }

    private static Character createHero(String name, CharacterClass characterClass){
        switch (characterClass){
            case WARRIOR -> {
                return new Warrior(name);
            }
            case BARBARIAN -> {
                return new Barbarian(name);
            }
            case KNIGHT -> {
                return new Knight(name);
            }
            default -> throw new NoSuchClassException("This hero doesn't exists.");
        }
    }

    private static Character createMonster(String name, CharacterClass characterClass){
        switch (characterClass){
            case ORC -> {
                return new Orc(name);
            }
            case GIANT -> {
                return new Giant(name);
            }
            case WEREWOLF -> {
                return new Werewolf(name);
            }
            default -> throw new NoSuchClassException("This monster doesn't exists.");
        }
    }
}
