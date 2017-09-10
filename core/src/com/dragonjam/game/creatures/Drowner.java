package com.dragonjam.game.creatures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dragonjam.game.controllers.DrownerController;
import com.dragonjam.game.controllers.GameManager;
import com.dragonjam.game.utility.View;

public class Drowner extends Mob {

    private float runningTime;
    private float stopPos;
    private float speed;

    /**
     * True if the drowner should spawn to the right of the camera and walk left.
     */
    private boolean right;
    private boolean stopped;

    public Drowner(boolean rightSide) {
        sprite.setTexture(new Texture("images/sprites/eating.png"));
        resizeBounds();
        speed = 0.4f + GameManager.getInstance().rand().nextInt(21) / 100f;
        stopPos = View.WIDTH.val() / 2;


        if (rightSide) {
            right = true;
            speed = -speed;
            stopPos -=  0.8f;
            sprite.flip(true, false);
            sprite.setPosition(View.WIDTH.val(), View.HEIGHT.val() / 2 - 2.5f);
        } else {
            stopPos -= 3.1f;
            sprite.setPosition(-sprite.getWidth(), View.HEIGHT.val() / 2 - 2.5f);
        }
    }

    public void updatePosition() {
        if (stopped) return;

        if ((!right && sprite.getX() > stopPos) || (right && (sprite.getX() < stopPos)))
            stopped = true;
        else
            sprite.translateX(speed * Gdx.graphics.getDeltaTime());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (stopped) {
            super.draw(batch, parentAlpha);
        } else {
            runningTime += Gdx.graphics.getDeltaTime();
            TextureRegion next = (TextureRegion) DrownerController.WALK
                    .getKeyFrame(runningTime, true);
            if (right) {
                batch.draw(next, sprite.getX() + sprite.getWidth(), sprite.getY(), -sprite
                        .getWidth(), sprite.getHeight());
            } else {
                batch.draw(next, sprite.getX(), sprite.getY(), sprite
                        .getWidth(), sprite.getHeight());
            }
        }
    }

    @Override
    public void place(float worldWidth, float worldHeight) {
        sprite.setPosition(sprite.getX(), worldHeight / 2 - 2.5f);
    }
}
