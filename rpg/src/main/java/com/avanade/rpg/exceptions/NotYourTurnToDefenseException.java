package com.avanade.rpg.exceptions;

public class NotYourTurnToDefenseException extends RuntimeException{
    public NotYourTurnToDefenseException(String message){
        super(message);
    }
}
