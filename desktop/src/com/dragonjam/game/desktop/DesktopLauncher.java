package com.dragonjam.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.dragonjam.game.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(50 * 9, 50 * 16);
		config.setWindowSizeLimits(350, 350, (int) (1080 * 9f / 16f), 1080);
		new Lwjgl3Application(new GameMain(), config);
	}
}
