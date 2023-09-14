package com.avanade.rpg.utils.responses;

import com.avanade.rpg.entities.TurnBattle;

import java.util.LinkedHashMap;
import java.util.Map;

public class DefenseResponse {

    public static Map<String, Object> defenderWins(Integer defense, TurnBattle currentTurn){
        Map<String, Object> responseDefense = new LinkedHashMap<>();
        responseDefense.put("The defender defended itself with:", defense);
        responseDefense.put("Defender wins!", " The defender managed to fend off the attack.");
        responseDefense.put("Attacker x Defender:", currentTurn.getAttack() + " < " + currentTurn.getDefense());
        responseDefense.put("Next step: ", "start another turn.");
        return responseDefense;
    }
    public static Map<String, Object> defenderLost(Integer defense, TurnBattle currentTurn){
        Map<String, Object> responseDefense = new LinkedHashMap<>();
        responseDefense.put("The defender defended itself with:", defense);
        responseDefense.put("Attacker wins!", " The attacker struck with more power than the defender could defend against.");
        responseDefense.put("Attacker x Defender:", currentTurn.getAttack() + " > " + currentTurn.getDefense());
        responseDefense.put("Next step: ", "calculate the damage.");
        return responseDefense;
    }
}
