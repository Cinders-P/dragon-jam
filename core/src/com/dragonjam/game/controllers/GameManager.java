package com.dragonjam.game.controllers;


import java.util.Random;

public class GameManager {

    private static GameManager manager = new GameManager();

    private Random random;

    private GameManager() {
        random = new Random();
    }

    public Random rand() {
        return random;
    }

    public static GameManager getInstance() {
        return manager;
    }
}
