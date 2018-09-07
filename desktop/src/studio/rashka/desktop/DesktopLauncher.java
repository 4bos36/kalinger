package studio.rashka.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import studio.rashka.Kalinger;

public class DesktopLauncher {
	private static DesktopLauncher application;
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		if (application == null) {
			application = new DesktopLauncher();
		}

		config.title = Kalinger.TITLE;
		config.width = Kalinger.WIDTH / 3;
		config.height = Kalinger.HEIGHT / 3;
		new LwjglApplication(new Kalinger(), config);
	}
}
