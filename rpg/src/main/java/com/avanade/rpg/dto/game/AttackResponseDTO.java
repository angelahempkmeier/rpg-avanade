package com.avanade.rpg.dto.game;

import com.avanade.rpg.entities.Character;

public record AttackResponseDTO(Character character, Integer attack) {
}
