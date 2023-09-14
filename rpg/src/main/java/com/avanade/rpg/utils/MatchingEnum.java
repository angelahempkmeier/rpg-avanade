package com.avanade.rpg.utils;

import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;
import com.avanade.rpg.exceptions.NoSuchTypeException;

public class MatchingEnum {

    public static CharacterType getEnumType(String type) {
        for (CharacterType characterType : CharacterType.values()) {
            if (characterType.name().equalsIgnoreCase(type)) {
                return characterType;
            }
        }
        throw new NoSuchTypeException(type);
    }

    public static CharacterClass getEnumClass(String charClass) {
        for (CharacterClass characterClass : CharacterClass.values()) {
            if (characterClass.name().equalsIgnoreCase(charClass)) {
                return characterClass;
            }
        }
        throw new NoSuchTypeException(charClass);
    }
}
