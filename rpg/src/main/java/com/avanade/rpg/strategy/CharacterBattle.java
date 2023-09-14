package com.avanade.rpg.strategy;

public interface CharacterBattle {

    Integer attack();
    Integer defense();
    Integer damage();
    Integer updateLife(Integer damage);
}
