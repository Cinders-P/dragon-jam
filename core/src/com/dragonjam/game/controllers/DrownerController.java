package com.dragonjam.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.creatures.Drowner;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class DrownerController {

    // TODO: See if Stage.act() is a better way to implement updating.
    public static final Animation WALK = new Animation(
            1f / 5f,
            new TextureAtlas("img/sprites/drowner/walk.atlas").getRegions()
    );
    public static Polygon eating;
    public static Polygon eatingRight;
    public static Polygon walking;
    public static Polygon walkingRight;

    private static final int SPRITE_WIDTH = 286:
    private Random rand;
    private Array<Drowner> drowners;

    public DrownerController() {
        rand = GameManager.getInstance().rand();
        drowners = new Array<Drowner>();
        if (eating == null || walking ==  null) {
            eating = polygonFromFile("eating.txt");
            flipPolygon(eating, eatingRight);
            walking = polygonFromFile("walking.txt");
            flipPolygon(walking, walkingRight);
        }
    }

    private void flipPolygon(Polygon src, Polygon dst) {
        float[] flipped = src.getVertices();
        for (int i = 0; i < flipped.length; i += 2) {
            flipped[i] = SPRITE_WIDTH - flipped[i];
        }
        dst = new Polygon(flipped);
    }

    private Polygon polygonFromFile(String fileName) {
        Scanner s = null;
        try {
            s = new Scanner(Gdx.files.internal("data/vertices/" + fileName)
                                     .file());

            String[] vertices = s.toString().split(" ");
            float[] verticesFloat = new float[vertices.length];
            for (int i = 0; i < vertices.length; i++) {
                verticesFloat[i] = Float.parseFloat(vertices[i]);
            }
            return new Polygon(verticesFloat);
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.err);
        } catch (NumberFormatException e) {
            e.printStackTrace(System.err);
        } finally {
            if (s != null) s.close();
        }
        return null;
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
