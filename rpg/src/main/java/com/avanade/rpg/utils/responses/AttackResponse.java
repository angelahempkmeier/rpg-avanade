package com.avanade.rpg.utils.responses;

import com.avanade.rpg.entities.Character;
import com.avanade.rpg.entities.Game;
import com.avanade.rpg.entities.TurnBattle;
import com.avanade.rpg.enums.TurnStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class AttackResponse {

    public static Map<String, Object> attackResponse(Character character, TurnBattle currentTurn){
        Map<String, Object> responseAttack = new LinkedHashMap<>();
        if (character.getId().equals(currentTurn.getAttacker().getId())){
            responseAttack.put("Attacker hit with a damage of", currentTurn.getAttack());
            responseAttack.put("Now it's time to defender try to defende yourself!", "Go " + currentTurn.getDefender().getName());
            responseAttack.put("Send requisition with defender id:", currentTurn.getDefender().getId());
        }else{
            responseAttack.put("Ops!", "Sounds like you trying to attack but it's not your turn.");
            responseAttack.put("Attack with the id:", currentTurn.getAttacker().getId());
        }
        return responseAttack;
    }
}
