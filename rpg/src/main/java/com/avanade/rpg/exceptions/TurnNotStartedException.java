package com.avanade.rpg.exceptions;

public class TurnNotStartedException extends RuntimeException{
    public TurnNotStartedException(String message) {
        super(message);
    }
}
