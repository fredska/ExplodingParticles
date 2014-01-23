package com.skallos.ep;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ExplodingParticles";
		cfg.useGL20 = false;
		cfg.width = 400;
		cfg.height = 400;
		
		new LwjglApplication(new Game(), cfg);
	}
}
