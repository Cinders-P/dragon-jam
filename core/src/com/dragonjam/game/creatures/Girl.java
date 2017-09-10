package com.dragonjam.game.creatures;

import com.badlogic.gdx.graphics.Texture;

public class Girl extends Mob {

    public Girl() {
        sprite.setTexture(new Texture("img/sprites/girl/idle.png"));
        resizeBounds();
    }

    public void place(float worldWidth, float worldHeight) {
        sprite.setPosition(worldWidth / 2 - 0.8f, worldHeight / 2 - 2.6f);
    }

}
