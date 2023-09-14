package com.avanade.rpg.exceptions;

public class NotYourTurnToDefenseException extends RuntimeException{
    public NotYourTurnToDefenseException(Long id){
        super("Defense with id: " + id);
    }
}
