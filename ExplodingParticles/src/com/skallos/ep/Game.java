package com.skallos.ep;

import java.util.LinkedList;
import java.util.List;

import javax.swing.text.TabExpander;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.skallos.ep.screen.EPScreens;
import com.skallos.ep.screen.ScreenManager;

public class Game extends com.badlogic.gdx.Game {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private List<Particle> particles;
	private ShapeRenderer shapeRenderer;

	private Stage stage;
	private Table resetButtonTable;
	private Button resetButton;
	private Label particleCount;

	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(EPScreens.INTRO);

		/*
		 * float w = Gdx.graphics.getWidth(); float h =
		 * Gdx.graphics.getHeight();
		 * 
		 * camera = new OrthographicCamera(1, 1);
		 * 
		 * shapeRenderer = new ShapeRenderer();
		 * 
		 * 
		 * 
		 * stage = new Stage(); Gdx.input.setInputProcessor(stage); Skin skin =
		 * new Skin(Gdx.files.internal("data/uiskin.json")); resetButtonTable =
		 * new Table(skin); resetButton = new TextButton("RESET", skin);
		 * resetButton.getStyle().checked = skin.newDrawable("white",
		 * Color.BLUE); resetButton.addListener(new InputListener() { float w =
		 * Gdx.graphics.getWidth(); float h = Gdx.graphics.getHeight();
		 * 
		 * @Override public boolean touchDown(InputEvent event, float x, float
		 * y, int pointer, int button) {
		 * 
		 * // Re-initialize everything! initialize(w, h); return
		 * super.touchDown(event, x, y, pointer, button); }
		 * 
		 * }); particleCount = new Label("Particle Count", skin);
		 * resetButtonTable.add(resetButton); resetButtonTable.setPosition(w -
		 * 50, h - 20); particleCount.setPosition(w - 150, h - 50);
		 * stage.addActor(resetButtonTable); stage.addActor(particleCount);
		 * 
		 * // Initialize the game initialize(w, h);
		 */
	}

	private void initialize(float w, float h) {
		particles = new LinkedList<Particle>();

		for (int c = 0; c < 250; c++) {
			particles.add(new Particle(5, new Vector2(MathUtils.random(w),
					MathUtils.random(h)), new Vector2(MathUtils.random(-100,
					100), MathUtils.random(-100, 100))));
		}
		explosions = new LinkedList<Explosion>();
	}

	@Override
	public void dispose() {
		// batch.dispose();
		// texture.dispose();
		super.dispose();
		ScreenManager.getInstance().dispose();
	}

	private float delta = 0;
	private List<Explosion> explosions;
	private List<Particle> deadParticles;

	@Override
	public void render() {
		super.render();
		/*
		 * Gdx.gl.glClearColor(0, 0, 0, 0);
		 * Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); delta =
		 * Gdx.graphics.getDeltaTime();
		 * 
		 * // Remove dead particles; deadParticles = new LinkedList<Particle>();
		 * for (Particle particle : particles) { if (particle.isDead()) {
		 * deadParticles.add(particle); } }
		 * 
		 * particles.removeAll(deadParticles);
		 * 
		 * for (Particle particle : particles) { particle.update(delta);
		 * particle.checkCollsions(explosions); particle.draw(shapeRenderer); }
		 * 
		 * // Get the user mouse input // If the user left clicks, create a new
		 * Explosion object at the // cursor's location List<Explosion>
		 * removeExplosion = new LinkedList<Explosion>(); for (Explosion
		 * explosion : explosions) { if (explosion.isDead())
		 * removeExplosion.add(explosion); }
		 * 
		 * explosions.removeAll(removeExplosion); for (Explosion explosion :
		 * explosions) { explosion.update(delta); explosion.draw(shapeRenderer);
		 * }
		 * 
		 * if (Gdx.input.isButtonPressed(Buttons.LEFT)) { explosions.add(new
		 * Explosion(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() -
		 * Gdx.input.getY()))); } if (Gdx.input.isButtonPressed(Buttons.RIGHT))
		 * { Vector2 newVelocity = new Vector2(MathUtils.random(-100, 100),
		 * MathUtils.random(-100, 100)); particles.add(new Particle(5, new
		 * Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() -
		 * Gdx.input.getY()), newVelocity)); }
		 * 
		 * particleCount.setText("Particle Count: " + particles.size());
		 * stage.act(delta); stage.draw();
		 */
	}
}
