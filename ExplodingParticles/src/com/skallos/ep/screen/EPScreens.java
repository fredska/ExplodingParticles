package com.skallos.ep.screen;

public enum EPScreens {

	INTRO {
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance(){
			return new IntroScreen();
		}
	},
	MAIN_MENU{
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance(){
			return new MainMenuScreen();
		}
	},
	CREDITS{
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance(){
			return new CreditsScreen();
		}
	},
	GAME{
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance(){
			return new GameScreen();
		}
	},
	LEVEL_OVER{
		@Override
		protected com.badlogic.gdx.Screen getScreenInstance(){
			return new LevelOverScreen();
		}
	};
	
	protected abstract com.badlogic.gdx.Screen getScreenInstance();
}
