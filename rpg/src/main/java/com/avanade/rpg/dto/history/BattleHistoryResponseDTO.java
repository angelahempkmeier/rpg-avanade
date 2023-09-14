package com.avanade.rpg.dto.history;

import java.util.List;

public record BattleHistoryResponseDTO(Long historyId,
                                       GameHistoryResponseDTO game,
                                       List<TurnBattleHistoryResponseDTO> turnBattles,
                                       String winner) {
}
