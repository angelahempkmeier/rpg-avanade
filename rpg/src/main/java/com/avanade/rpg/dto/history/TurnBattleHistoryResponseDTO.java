package com.avanade.rpg.dto.history;

public record TurnBattleHistoryResponseDTO(Integer turn,
                                           String attacker,
                                           Integer attack,
                                           String defender,

                                           Integer defense,
                                           Integer damage) {
}
