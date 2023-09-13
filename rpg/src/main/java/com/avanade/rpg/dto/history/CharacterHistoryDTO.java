package com.avanade.rpg.dto.history;

import com.avanade.rpg.enums.CharacterClass;
import com.avanade.rpg.enums.CharacterType;

public record CharacterHistoryDTO(Long id,
                                  String name,
                                  String type,
                                  String characterClass) {

}
