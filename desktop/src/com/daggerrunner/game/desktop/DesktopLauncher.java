package com.daggerrunner.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.daggerrunner.game.DaggerJumper;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DaggerJumper.WIDTH;
		config.height = DaggerJumper.HEIGHT;
		config.title = DaggerJumper.TITLE;
		new LwjglApplication(new DaggerJumper(), config);
	}
}
