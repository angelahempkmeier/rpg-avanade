package com.avanade.rpg.dto.turnbattle;

import com.avanade.rpg.entities.Character;
import com.avanade.rpg.enums.TurnStatus;

public record TurnBattleDTO(Long id,
                            Character attacker,
                            Character defender,
                            Integer turn,
                            Integer attack,
                            Integer defense,
                            Integer damage,
                            TurnStatus status) {
}
