package com.avanade.rpg.exceptions;

public class CharacterNotFoundException extends RuntimeException {
    public CharacterNotFoundException(Long id){
        super("Character not found. Id: " + id);
    }
}
