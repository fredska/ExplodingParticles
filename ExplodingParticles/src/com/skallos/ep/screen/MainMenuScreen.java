package com.skallos.ep.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

class MainMenuScreen implements Screen {

	private Stage stage;

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();

		Table.drawDebug(stage); // This is optional, but enables debug lines for
								// tables.

	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		stage = new Stage();
		Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		Gdx.input.setInputProcessor(stage);

		Table table = new Table(skin);
		table.setFillParent(true);

		Label gameTitleLabel = new Label("Screen Interaction Example", skin);

		TextButton gameScreenButton = new TextButton("New Game", skin);
		gameScreenButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				ScreenManager.getInstance().show(EPScreens.GAME);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		TextButton creditScreenButton = new TextButton("Credits", skin);
		creditScreenButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				ScreenManager.getInstance().show(EPScreens.CREDITS);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		TextButton exitGameButton = new TextButton("Exit", skin);
		exitGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.exit(0);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		table.add(gameTitleLabel);
		table.row();
		table.add(gameScreenButton);
		table.row();
		table.add(creditScreenButton);
		table.row();
		table.add(exitGameButton);
		Table.drawDebug(stage);
		stage.addActor(table);
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
		stage.dispose();
	}

}
