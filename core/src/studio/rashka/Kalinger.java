package studio.rashka;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import studio.rashka.lib.FontTTF;
import studio.rashka.lib.Game;
import studio.rashka.lib.Language;
import studio.rashka.lib.Loading;
import studio.rashka.lib.Monitor;
import studio.rashka.lib.MusicSound;
import studio.rashka.lib.Preference;
import studio.rashka.lib.Textures;
import studio.rashka.screen.LoadScreen;

/**
 * Created by 4bos on 18.10.2017.
 */

public class Kalinger extends ApplicationAdapter {
	private Preferences setting;

	public static int WIDTH = 1080;
	public static final int HEIGHT = 1920;
	public static final String TITLE = "Kalinger";

	SpriteBatch batch;
	private static Game game;
	private static OrthographicCamera camera;
	private static Preference preference; // lib
	private static MusicSound musicSound;
	private static Language language;
	private static Textures textures;
	private static FontTTF fontTTF;
	private static Loading loading;
	private Monitor monitor;

	private static float ratioMonitorW, ratioMonitorH;
	
	@Override
	public void create () {
		setting = Gdx.app.getPreferences("Kalinger");
		setting.putString("Lang", "ru");
		//setting.putString("JSONLang", "en");
		setting.flush();

		preference = new Preference();
		musicSound = new MusicSound();
		textures = new Textures();
		fontTTF = new FontTTF();
		language = new Language();
		monitor = new Monitor();
		loading = new Loading();

		batch = new SpriteBatch();
		game = new Game();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);

		ratioMonitorW = monitor.getRatioMonitorW();
		ratioMonitorH = monitor.getRatioMonitorH();

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		game.push(new LoadScreen(game));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);

		game.update(Gdx.graphics.getDeltaTime());
		game.render(batch);

		/*
		if (loadMusic == 1) { // старт/стоп музыки
			if (preference.loadMusic() == 1) musicSound.startFon();
			else if (preference.loadMusic() == 0) musicSound.pauseFon();
			loadMusic = 0;
		}*/
	}

	public static void setWIDTH(int WIDTH) {
		Kalinger.WIDTH = WIDTH;
	}

	public static float getRatioMonitorW() {
		return ratioMonitorW;
	}

	public static float getRatioMonitorH() {
		return ratioMonitorH;
	}

	public static Preference getPreference() {
		return preference;
	}

	public static MusicSound getMusicSound() {
		return musicSound;
	}

	public static Language getLanguage() {
		return language;
	}

	public static Textures getTextures() {
		return textures;
	}

	public static FontTTF getFontTTF() {
		return fontTTF;
	}

	public static Loading getLoading() {
		return loading;
	}

	@Override
	public void dispose () {
		batch.dispose();
		musicSound.dispose();
		textures.dispose();
		fontTTF.dispose();
		language.dispose();
	}
}
