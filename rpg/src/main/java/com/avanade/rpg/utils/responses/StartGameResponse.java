package com.avanade.rpg.utils.responses;

import com.avanade.rpg.entities.Character;

import java.util.LinkedHashMap;
import java.util.Map;

public class StartGameResponse {
    public static Map<String, Object> startGameResponse(Character winner, Integer winnerDiceRoll, Character loser, Integer loserDiceRoll){
        Map<String, Object> startGameResponse = new LinkedHashMap<>();
        startGameResponse.put("Attacker", winner.getName());
        startGameResponse.put("Attacker id", winner.getId().toString());
        startGameResponse.put("Attacker roll dice", winnerDiceRoll);
        startGameResponse.put("Defender", loser.getName());
        startGameResponse.put("Defender id", loser.getId().toString());
        startGameResponse.put("Defender roll dice", loserDiceRoll);
        startGameResponse.put("Next step:", "Send attack requisition using attacker id: " + winner.getId());
        return startGameResponse;
    }

}
