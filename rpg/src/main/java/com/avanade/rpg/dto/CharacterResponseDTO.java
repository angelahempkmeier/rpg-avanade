package com.avanade.rpg.dto;

import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;

//TODO mudar characterClass pro enum
public record CharacterResponseDTO(Long id,
                                   String name,
                                   CharacterType type,
                                   CharacterClass characterClass,
                                   Integer health,
                                   Integer strength,
                                   Integer defense,
                                   Integer agility,
                                   Integer diceQuantity,
                                   Integer diceFaces) {

    public CharacterResponseDTO(Long id, Integer health, Integer strength, Integer defense, Integer agility, Integer diceQuantity, Integer diceFaces) {
        this(id, null, null, null, health, strength, defense, agility, diceQuantity, diceFaces);
    }
}
