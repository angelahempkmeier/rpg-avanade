package com.avanade.rpg.dto.history;

import com.avanade.rpg.dto.turnbattle.TurnBattleDTO;
import com.avanade.rpg.entities.Game;

import java.util.List;

public record BattleHistoryResponseDTO(Long historyId,
                                       GameHistoryResponseDTO game,
                                       List<TurnBattleHistoryResponseDTO> turnBattles,
                                       String winner) {
}
