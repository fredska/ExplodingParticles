package com.skallos.ep.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.skallos.ep.GameState;

public class LevelOverScreen implements Screen {

	private Stage stage;
	
	@Override
	public void render(float delta) {
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		Table table = new Table(skin);
		table.setFillParent(true);
		Button backToMainMenuButton = new TextButton("Back to Main Menu", skin);
		backToMainMenuButton.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				ScreenManager.getInstance().dispose(EPScreens.GAME);
				ScreenManager.getInstance().show(EPScreens.MAIN_MENU);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		Button restartLevel = new TextButton("Restart",skin);
		restartLevel.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				ScreenManager.getInstance().dispose(EPScreens.GAME);
				ScreenManager.getInstance().show(EPScreens.GAME);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		if(GameState.currentState == GameState.LEVEL_FINISHED_COMPLETE)
			table.add("Enough particles have been destroyed to move on!");
		else
			table.add("The air has become too contaminated for further development.");
		table.row();
		table.add(restartLevel);
		table.row();
		table.add(backToMainMenuButton);
		stage.addActor(table);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		if(stage != null)
			stage.dispose();
	}

}
