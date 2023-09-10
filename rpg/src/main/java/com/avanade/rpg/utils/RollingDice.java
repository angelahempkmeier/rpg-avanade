package com.avanade.rpg.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

public class RollingDice {
    public static Integer rollingDice1d20() {
        Random random = new Random();
        return random.nextInt(20) + 1;
    }

    public static Integer rollingDice1d12() {
        Random random = new Random();
        return random.nextInt(12) + 1;
    }



}
