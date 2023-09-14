package com.avanade.rpg.exceptions;

public class NoSuchTypeException extends RuntimeException{

    public NoSuchTypeException(String type){
        super(type);
    }
}
