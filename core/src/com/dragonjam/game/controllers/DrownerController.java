package com.dragonjam.game.controllers;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.creatures.Drowner;

import java.util.Random;

public class DrownerController {

    // TODO: See if Stage.act() is a better way to implement updating.
    public static final Animation WALK = new Animation(
            1f / 5f,
            new TextureAtlas("images/sprites/drowner.atlas").getRegions()
    );
    private static Array<Array<Vector2>> hitboxes;

    private Random rand;
    private Array<Drowner> drowners;
    
    public DrownerController() {
        rand = GameManager.getInstance().rand();
        drowners = new Array<Drowner>();
        if (hitboxes == null) {
            hitboxesInit();
        }
    }

    private void hitboxesInit() {
        hitboxes = new Array<Array<Vector2>>();
        // Animation has 6 frames + idle
        for (int i = 0; i < 6; i++) {
            hitboxes.add(new Array<Vector2>(){

            });
        }
    }

    public Drowner newDrowner() {
        Drowner d = new Drowner(rand.nextBoolean());
        drowners.add(d);
        return d;
    }

    public void updateDrowners() {
        for (Drowner d : drowners) {
            d.updatePosition();
        }
    }
}
