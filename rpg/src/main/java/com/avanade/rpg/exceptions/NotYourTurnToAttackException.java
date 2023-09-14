package com.avanade.rpg.exceptions;

public class NotYourTurnToAttackException extends RuntimeException{
    public NotYourTurnToAttackException(Long id){
        super("Attack with id: " + id);
    }
}
