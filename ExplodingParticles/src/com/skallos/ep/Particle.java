package com.skallos.ep;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Particle {

	private float radius;
	private Vector2 position;
	private Vector2 velocity;
	private float SCREEN_HEIGHT;
	private float SCREEN_WIDTH;
	private boolean isDead;

	/**
	 * Default particle. Radius and velocity are pre-determined
	 * 
	 * @param position
	 *            Defines the starting position
	 */
	public Particle(Vector2 position) {
		this.radius = Configuration.PARTICLE_STD_RADIUS.getValue();
		this.position = position;
		this.velocity = new Vector2(MathUtils.random(
				Configuration.PARTICLE_MIN_VELOCITY.getValue(),
				Configuration.PARTICLE_MAX_VELOCITY.getValue()),
				MathUtils.random(
						Configuration.PARTICLE_MIN_VELOCITY.getValue(),
						Configuration.PARTICLE_MAX_VELOCITY.getValue()));
		
		SCREEN_HEIGHT = Gdx.graphics.getHeight() - this.radius;
		SCREEN_WIDTH = Gdx.graphics.getWidth() - this.radius;
	}

	public Particle(float radius, Vector2 position, Vector2 velocity) {
		this.radius = radius;
		this.position = position;
		this.velocity = velocity;
		SCREEN_HEIGHT = Gdx.graphics.getHeight() - radius;
		SCREEN_WIDTH = Gdx.graphics.getWidth() - radius;
		this.isDead = false;
	}

	public void checkCollsions(List<Explosion> explosions) {
		if (isDead())
			return;
		if (explosions.isEmpty())
			return;
		// Vector2 explosionPosition = new Vector2();
		for (Explosion explosion : explosions) {
			if (explosion.getPosition().dst2(this.position) <= ((explosion
					.getCurrentRadius() + this.radius) * (explosion
					.getCurrentRadius() + this.radius))) {
				this.isDead = true;
				// explosionPosition = explosion.getPosition();
				break;
			}
		}

		if (isDead()) {
			// Get the slope between the particle position & explosion position
			// float slope = (explosionPosition.y - this.position.y) /
			// (explosionPosition.x - this.position.x);
			explosions.add(new Explosion(position));
		}
	}

	public void update(float delta) {
		if (isDead())
			return;
		Vector2 tmpVelocity = this.velocity.cpy().scl(delta);
		Vector2 tmpPosition = this.position.cpy().add(tmpVelocity);

		if (tmpPosition.x <= radius || tmpPosition.x >= SCREEN_WIDTH) {
			this.velocity.x = -this.velocity.x;
		}
		if (tmpPosition.y <= radius || tmpPosition.y >= SCREEN_HEIGHT) {
			this.velocity.y = -this.velocity.y;
		}

		this.position.add(this.velocity.cpy().scl(delta));
	}

	public void draw(ShapeRenderer shapeRenderer) {
		if (isDead())
			return;
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.BLUE);
		shapeRenderer.circle(this.position.x, this.position.y, this.radius);
		shapeRenderer.end();
	}

	public boolean isDead() {
		return this.isDead;
	}
}
