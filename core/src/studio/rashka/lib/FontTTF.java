package studio.rashka.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.StringBuilder;

import java.util.HashMap;
import java.util.Map;

import studio.rashka.Kalinger;

public class FontTTF {

    private static final String FONT_NAME = "fonts/HelveticaLight.ttf"; // расположение шрифта
    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;
    private Map<String, BitmapFont> typeFont;

    private StringBuilder FONT_CHARS;
    private float RatioMonitor;

    public FontTTF() {
        typeFont = new HashMap<String, BitmapFont>();

        generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        RatioMonitor = (float) Gdx.graphics.getWidth() / (float) Kalinger.WIDTH; // коэффициент масштаба графики

        FONT_CHARS = new StringBuilder("");

        for (int i = 32; i < 127; i++) FONT_CHARS.append((char)i);
        for (int i = 1024; i < 1104; i++) FONT_CHARS.append((char)i); // русские символы

        parameter.size = (int)(54 * RatioMonitor);
        parameter.characters = FONT_CHARS.toString(); // заполняем массив символами рус и остальные
        typeFont.put("font54", generator.generateFont(parameter));

        parameter.size = (int)(48 * RatioMonitor);
        typeFont.put("font48", generator.generateFont(parameter));

        parameter.size = (int)(40 * RatioMonitor);
        typeFont.put("font40", generator.generateFont(parameter));

        parameter.size = (int)(28 * RatioMonitor);
        typeFont.put("font28", generator.generateFont(parameter));
    }

    public BitmapFont getFont54() {
        return typeFont.get("font54");
    }

    public BitmapFont getFont48() {
        return typeFont.get("font48");
    }

    public BitmapFont getFont40() {
        return typeFont.get("font40");
    }

    public BitmapFont getFont28() {
        return typeFont.get("font28");
    }

    public void dispose() {
        typeFont.clear();
        generator.dispose();
        parameter = null;
    }
}