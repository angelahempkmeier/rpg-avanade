package com.avanade.rpg.dto.characters;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CharacterRequestDTO(@NotNull @NotBlank
                                  String name,
                                  @NotNull @NotBlank
                                  String type,
                                  @NotNull @NotBlank
                                  String characterClass) {
}
