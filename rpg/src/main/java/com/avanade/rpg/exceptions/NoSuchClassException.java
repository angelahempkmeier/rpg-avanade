package com.avanade.rpg.exceptions;

public class NoSuchClassException extends RuntimeException{

    public NoSuchClassException(String characterClass){
        super(characterClass);
    }
}
