package com.skallos.ep.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class CreditsScreen implements Screen {

	private Stage stage;
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        Table.drawDebug(stage); // This is optional, but enables debug lines for tables.

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
		
		Label creditsTitleLabel = new Label("Credits", skin);
		Label authorLabel = new Label("fredska (Fred Skallos)", skin);
		Label basedOnLabel = new Label("Based on the work by Robert Komorovsky", skin);
		Label blogTutorialLinkLabel = new Label("http://bioboblog.blogspot.com/2012/08/libgdx-screen-management.html", skin);
		Label libGDXVersion = new Label("LibGDX Version: 0.9.9", skin);
		TextButton backToMainMenuScreenButton = new TextButton("Back to Main Menu", skin);
		backToMainMenuScreenButton.addListener(new InputListener(){
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				System.out.println("Touch DOWN Event Fucking Happened ya'll!");
				ScreenManager.getInstance().dispose(
						EPScreens.CREDITS);
				ScreenManager.getInstance().show(EPScreens.MAIN_MENU);
				return super.touchDown(event, x, y, pointer, button);
			}
		});
		table.add(creditsTitleLabel);
		table.row();
		table.add(authorLabel);
		table.row();
		table.add(basedOnLabel);
		table.row();
		table.add(blogTutorialLinkLabel);
		table.row();
		table.add(libGDXVersion);
		table.row();
		table.add(backToMainMenuScreenButton);
		
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
