package com.avanade.rpg.dto.characters;

import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;

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
}
