package com.avanade.rpg.dto;

import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

//TODO se é um guerreiro, é um HERO
//TODO mudar de String pra CharacterClass
public record CharacterRequestDTO(String name,
                                  @Enumerated(EnumType.STRING)
                                  CharacterType type,
                                  @Enumerated(EnumType.STRING)
                                  CharacterClass characterClass) {
}
