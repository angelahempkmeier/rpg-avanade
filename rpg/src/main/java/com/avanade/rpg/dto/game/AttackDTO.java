package com.avanade.rpg.dto.game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AttackDTO(@NotBlank @NotNull Long id) {
}
