package com.skallos.ep;

import com.skallos.ep.screen.EPScreens;
import com.skallos.ep.screen.ScreenManager;

public class Game extends com.badlogic.gdx.Game {
	@Override
	public void create() {
		ScreenManager.getInstance().initialize(this);
		ScreenManager.getInstance().show(EPScreens.INTRO);
	}

	@Override
	public void dispose() {
		super.dispose();
		ScreenManager.getInstance().dispose();
	}
}
