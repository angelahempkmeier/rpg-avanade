package com.avanade.rpg.exceptions;

public class GameNotStartedException extends RuntimeException {
    public GameNotStartedException(String message) {
        super(message);
    }
}