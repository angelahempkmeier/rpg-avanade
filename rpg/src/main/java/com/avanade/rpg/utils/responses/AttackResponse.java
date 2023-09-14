package com.avanade.rpg.utils.responses;

import com.avanade.rpg.entities.Character;
import com.avanade.rpg.entities.TurnBattle;
import com.avanade.rpg.exceptions.NotYourTurnToAttackException;

import java.util.LinkedHashMap;
import java.util.Map;

public class AttackResponse {

    public static Map<String, Object> attackResponse(Character character, TurnBattle currentTurn){
        Map<String, Object> responseAttack = new LinkedHashMap<>();
        if (character.getId().equals(currentTurn.getAttacker().getId())){
            responseAttack.put("Attacker hit with a damage of", currentTurn.getAttack());
            responseAttack.put("Now it's time for the defender to try to defend itself!", "Go, " + currentTurn.getDefender().getName() + "!");
            responseAttack.put("Send a request with the defender's id:", currentTurn.getDefender().getId());
        }else{
            throw new NotYourTurnToAttackException(currentTurn.getAttacker().getId());
        }
        return responseAttack;
    }
}
