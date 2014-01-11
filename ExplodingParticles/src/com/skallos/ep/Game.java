package com.skallos.ep;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Game implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private List<Particle> particles;
	private ShapeRenderer shapeRenderer;
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(1, 1);
		
		shapeRenderer = new ShapeRenderer(); 
		particles = new LinkedList<Particle>();
		
		for(int c = 0; c < 1500; c++){
			particles.add(new Particle(5, 
					new Vector2(MathUtils.random(w),MathUtils.random(h)), 
					new Vector2(MathUtils.random(-100, 100),MathUtils.random(-100, 100))));
		}
		
		explosions = new LinkedList<Explosion>();
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	private float delta = 0;
	private List<Explosion> explosions;
	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		delta = Gdx.graphics.getDeltaTime();
		
		for(Particle particle : particles){
			particle.update(delta);
			particle.checkCollsions(explosions);
			particle.draw(shapeRenderer);
		}
		
		
		//Get the user mouse input
		//If the user left clicks, create a new Explosion object at the cursor's location
		List<Explosion> removeExplosion = new LinkedList<Explosion>();
		for(Explosion explosion : explosions){
			if(explosion.isDead())
				removeExplosion.add(explosion);
		}
		
		explosions.removeAll(removeExplosion);
		for(Explosion explosion : explosions){
			explosion.update(delta);
			explosion.draw(shapeRenderer);
		}
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT)){
			explosions.add(new Explosion(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())));
		}
		
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
