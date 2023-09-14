package com.avanade.rpg.utils.responses;

import com.avanade.rpg.entities.TurnBattle;

import java.util.LinkedHashMap;
import java.util.Map;

public class DamageResponse {

    public static Map<String, Object> damageResponse(TurnBattle turnBattle){
        Map<String, Object> startGameResponse = new LinkedHashMap<>();
        String attackerName = turnBattle.getAttacker().getName();
        String defenderName = turnBattle.getDefender().getName();
        Integer lifeDefender = turnBattle.getDefender().getLife();
        startGameResponse.put("Damage done by", attackerName);
        startGameResponse.put("Damage received by", defenderName);
        startGameResponse.put("Value of damage was", turnBattle.getDamage());
        startGameResponse.put("Life of " + defenderName + " is now", lifeDefender);
        if ((lifeDefender <= 0)) {
            startGameResponse.put("Game over", attackerName + " won!");
        } else {
            startGameResponse.put("Game status", "All aboard for the next turn!");
        }
        return startGameResponse;
    }
}
