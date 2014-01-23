package com.skallos.ep.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class IntroScreen implements Screen {
	private Texture texture;
	private TextureRegion textureRegion;
	private SpriteBatch spriteBatch;
	
	private float timer = 0f;
	@Override
	public void render(float delta) {
		if(timer >= 2f){
			ScreenManager.getInstance().show(EPScreens.MAIN_MENU);
			ScreenManager.getInstance().dispose(EPScreens.INTRO);
			return;
		}
		timer += delta;
		spriteBatch.begin();
		spriteBatch.draw(textureRegion, 0, 0);
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		timer = 0f;
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		textureRegion = new TextureRegion(texture);
		spriteBatch = new SpriteBatch();
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
		spriteBatch.dispose();
		texture.dispose();
	}

}
