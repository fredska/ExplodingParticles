package com.skallos.ep.screen;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.skallos.ep.Explosion;
import com.skallos.ep.GameState;
import com.skallos.ep.Particle;

public class GameScreen implements Screen {

	private List<Particle> particles;
	private List<Particle> deadParticles;
	private List<Explosion> explosions;
	private ShapeRenderer shapeRenderer;
	
	private Stage stage;
	private Table resetButtonTable;
	private Button resetButton;
	private Label particleCount;
	
	private boolean isLeftButtonPushedOnStart;
	private boolean hasChainReactionStarted;
	
	private final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
	private final int SCREEN_WIDTH = Gdx.graphics.getWidth();
	
//	Map<Integer, Zone> zoneMap;
	private final int numOfZoneColumns = 15;
	private final int numOfZoneRows = 15;
	
	private void initialize(){
//		zoneMap = new HashMap<Integer, Zone>();
		initializeStage();
		initializeGameAssets();
	}
	
	private void initializeGameAssets(){
		shapeRenderer = new ShapeRenderer();
		
		particles = new LinkedList<Particle>();

		//Create n number of particles;  Randomly placed inside screen with random velocity
		for (int c = 0; c < 250; c++) {
			Vector2 position = new Vector2(MathUtils.random(SCREEN_WIDTH),
					MathUtils.random(SCREEN_HEIGHT));
			Particle newParticle = new Particle(position);
			//Original code work
			particles.add(newParticle);
			
			/*
			 * This section is the beginning work to break particles into their own zones.
			 * The goal is during the explosion phase, an explosion only needs to check 
			 * particle collision in nearby zones instead of the entire scene
			 */
//			int zoneId = Zone.getZoneId(SCREEN_HEIGHT, SCREEN_WIDTH, numOfZoneColumns, numOfZoneRows, position);
//			Zone zone = zoneMap.get(zoneId);
//			
//			if(zone == null){
//				zone = new Zone();
//				zone.setZoneId(zoneId);
//				zone.addParticle(newParticle);
//				zoneMap.put(zoneId, zone);
//			}
		}
		explosions = new LinkedList<Explosion>();
		
		//Set the boolean value if the left mouse button is pressed on initialization
		isLeftButtonPushedOnStart = Gdx.input.isButtonPressed(Buttons.LEFT);
		hasChainReactionStarted = false;
		
	}
	
	private void initializeStage(){
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		resetButtonTable = new Table(skin);
		resetButton = new TextButton("RESET", skin);
		resetButton.getStyle().checked = skin.newDrawable("white", Color.BLUE);
		resetButton.addListener(new InputListener() {
			float w = Gdx.graphics.getWidth();
			float h = Gdx.graphics.getHeight();

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				// Re-initialize everything!
				initializeGameAssets();
				return super.touchDown(event, x, y, pointer, button);
			}

		});
		particleCount = new Label("Particle Count", skin);
		resetButtonTable.add(resetButton);
		resetButtonTable.setPosition(SCREEN_WIDTH - 50, SCREEN_HEIGHT - 20);
		particleCount.setPosition(SCREEN_WIDTH - 150, SCREEN_HEIGHT- 50);
		stage.addActor(resetButtonTable);
		stage.addActor(particleCount);
	}
	
	private void cleanUpDeadParticles(){
		// Remove dead particles;
		deadParticles = new LinkedList<Particle>();
		for (Particle particle : particles) {
			if (particle.isDead()) {
				deadParticles.add(particle);
			}
		}

		particles.removeAll(deadParticles);
	}
	
	private void cleanUpExplosions(){
		List<Explosion> removeExplosion = new LinkedList<Explosion>();
		for (Explosion explosion : explosions) {
			if (explosion.isDead())
				removeExplosion.add(explosion);
		}
		explosions.removeAll(removeExplosion);
	}
	
	private void simulateExplosions(float delta){
		for (Explosion explosion : explosions) {
			explosion.update(delta);
			explosion.draw(shapeRenderer);
		}
	}
	
	private void runSimulation(float delta){
		cleanUpDeadParticles();
		cleanUpExplosions();
		simulateParticles(delta);
		simulateExplosions(delta);
	}
	
	private void simulateParticles(float delta){
		for (Particle particle : particles) {
			particle.update(delta);
			particle.checkCollsions(explosions);
			particle.draw(shapeRenderer);
		}
	}
	
	
	private void getPlayerInput(){
		if (Gdx.input.isButtonPressed(Buttons.LEFT) && !isLeftButtonPushedOnStart && !hasChainReactionStarted) {
			explosions.add(new Explosion(new Vector2(Gdx.input.getX(),
					Gdx.graphics.getHeight() - Gdx.input.getY())));
			hasChainReactionStarted = true;
		}
		
		if(!Gdx.input.isButtonPressed(Buttons.LEFT)){
			isLeftButtonPushedOnStart = false;
		}
		
		//Generate more particles in the middle of a game if on the desktop
//		if (Gdx.input.isButtonPressed(Buttons.RIGHT)) {
//			Vector2 newVelocity = new Vector2(MathUtils.random(-100, 100),
//					MathUtils.random(-100, 100));
//			particles.add(new Particle(5, new Vector2(Gdx.input.getX(),
//					Gdx.graphics.getHeight() - Gdx.input.getY()), newVelocity));
//		}
	}
	
	@Override
	public void render(float delta) {
		//Clear out the screen with a black background
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		//Get the delta time determined by libGDX
		delta = Gdx.graphics.getDeltaTime();
		
		//Run the simulation of particles
		runSimulation(delta);
		
		//Get any player input
		getPlayerInput();
		
		//Check if the explosions are finished
		if(hasChainReactionStarted && explosions.isEmpty()){
			ScreenManager.getInstance().dispose(EPScreens.GAME);
			if(((float)particles.size() / 250f) < 0.1f)
				GameState.currentState = GameState.LEVEL_FINISHED_COMPLETE;
			else
				GameState.currentState = GameState.LEVEL_FINISHED_FAILED;
			ScreenManager.getInstance().show(EPScreens.LEVEL_OVER);
		}
		//Update the Particle Count Label
		particleCount.setText("Particle Count: " + particles.size());
		
		//And finally, draw out the stage with the reset button & label!
		stage.act(delta);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		//Initialize the screen
		initialize();

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		//Clear the screen of any extra stuffs
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.dispose();
		particles.clear();
		explosions.clear();
	}

}
