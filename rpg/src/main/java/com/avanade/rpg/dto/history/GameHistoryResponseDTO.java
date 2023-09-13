package com.avanade.rpg.dto.history;

public record GameHistoryResponseDTO(Long gameId,
                                     CharacterHistoryDTO player,
                                     CharacterHistoryDTO opponent,
                                     Boolean playerInitiated) {
}
