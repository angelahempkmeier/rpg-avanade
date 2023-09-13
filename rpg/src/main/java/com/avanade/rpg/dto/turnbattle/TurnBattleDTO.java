package com.avanade.rpg.dto.turnbattle;

import com.avanade.rpg.entities.Character;
import com.avanade.rpg.enums.TurnStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record TurnBattleDTO(Long id,
                            Character attacker,
                            Character defender,
                            Integer turn,
                            Integer attack,
                            Integer defense,
                            Integer damage,
                            TurnStatus status) {
}
