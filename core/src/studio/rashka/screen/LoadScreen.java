package studio.rashka.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import studio.rashka.Kalinger;
import studio.rashka.json.JSONLang;
import studio.rashka.lib.Game;
import studio.rashka.lib.JSON;
import studio.rashka.lib.State;

public class LoadScreen extends State {

    private Texture logo;
    private JSONLang jsonJSONLang;
    private int i = 0;

    public LoadScreen(Game game) {
        super(game);

        logo = new Texture("loading/logo.png");

        jsonJSONLang = new JSONLang();
        jsonJSONLang.lang = Kalinger.getPreference().language();
        JSON json = new JSON();
        json.sendRequest(jsonJSONLang, "POST", "http://kalinger.net/api/connect/", false);
    }

    @Override
    public void update(float deltaTime) {
        i++;
        if (i == 50) {
            Kalinger.getTextures().loginScreen();
            Kalinger.getLanguage().loginScreen();
            game.set(new LoginScreen(game));
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(logo, Kalinger.WIDTH / 2 - logo.getWidth() / 2, Kalinger.HEIGHT / 2 - 256);
        batch.end();
        Kalinger.getLoading().render(batch);
    }

    @Override
    public void dispose() {
        try {
            logo.dispose();
        } catch (NullPointerException e) {
            // защита от вылета
        }
    }
}