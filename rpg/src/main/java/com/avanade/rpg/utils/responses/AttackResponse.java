package com.avanade.rpg.utils.responses;

import com.avanade.rpg.entities.Character;
import com.avanade.rpg.entities.Game;

public class AttackResponse {

    public String attackResponse(Game game, Character winner, Character loser){

        return "Attack made by " + winner.characterResponseDTO().name();
    }
}
