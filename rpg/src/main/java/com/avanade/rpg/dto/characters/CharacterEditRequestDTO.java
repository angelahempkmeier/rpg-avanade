package com.avanade.rpg.dto.characters;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CharacterEditRequestDTO(@NotNull @NotBlank String name) {
}
