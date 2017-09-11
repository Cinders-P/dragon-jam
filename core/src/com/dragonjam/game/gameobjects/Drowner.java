package com.dragonjam.game.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.dragonjam.game.helpers.AssetLoader;

import java.util.Random;

/**
 * Created by Tc2r on 9/9/2017.
 * <p>
 * Description:
 */

public class Drowner extends Monster {

	private Random r;
	private int hp;
	private boolean isAlive = true;


	public Drowner(float x, float y, int width, int height, float baseSpeed, float speedMod, boolean startLeft, int hp) {
		super(x, y, width, height, baseSpeed, speedMod, startLeft, hp);

		isAlive = true;
		this.hp = hp;

		r = new Random();
	}


	@Override
	public void update(float delta) {
		super.update(delta);

	}

	public void die() {
		switch(r.nextInt(3)) {
			case 0:
				AssetLoader.zombiedie01.play();
				break;
			case 1:
				AssetLoader.zombiedie02.play();
				break;
			case 2:
				AssetLoader.zombiedie03.play();
				break;
		}
		isAlive = false;
		velocity.y = 0;
		velocity.x = 0;
		acceleration.x = 0;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean collides(Boy boy) {
		// check if collision occurs with boy.
		if (startLeft) {
			if (position.x < boy.getX() + boy.getWidth()) {


				// Boy Hit!
				// push monster back, play sound effect. lower boy hp

				return (Intersector.overlaps(boy.getCollisionBox(), collisionBox));
			}
		}else{
			if (position.x > boy.getX()) {
				return (Intersector.overlaps(boy.getCollisionBox(), collisionBox));

				// Boy Hit!
			}
		}
		return false;
	}

	public void onCollision() {
		position.add(-100, 0);
		setVelocity(new Vector2(0,0));

	}
	public boolean collides(Girl girl) {
		// check if collision occurs with boy.
		if (position.x < girl.getX() + girl.getWidth()) {
			return (Intersector.overlaps(girl.getCollisionBox(), collisionBox));

			// Girl Hit!
		}
		return false;
	}

	public void onClick() {
		super.onClick();

		// Subtract HP
		if (this.hp > 0 && isAlive) {
			this.hp -= 10;

			// Play Sound Effect
			if (r.nextInt(2) == 0) {
				AssetLoader.zombieHit01.play();
			} else {
				AssetLoader.zombieHit02.play();
			}

		}


	}


}