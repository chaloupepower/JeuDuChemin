package com.jeuchemin.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jeuchemin.JeuChemin;
import com.jeuchemin.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.WINDOW_WIDTH;
		config.height = Constants.WINDOW_HEIGHT;
		config.title = Constants.WINDOW_TITLE + " " + Constants.GAME_VERSION;
		new LwjglApplication(new JeuChemin(), config);
	}
}