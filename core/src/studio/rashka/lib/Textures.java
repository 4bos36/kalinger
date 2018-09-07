package studio.rashka.lib;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.HashMap;
import java.util.Map;

public class Textures {

    private TextureAtlas textureButton, textureCheckBox;
    private Skin textureButtonSkin, textureCheckBoxSkin;

    public Map<String, Texture> textures;
    public Map<String, TextureRegion> textureRegion; //массив регионов

    public Textures() {
        textureButton = new TextureAtlas("buttons.texture");
        textureButtonSkin = new Skin(textureButton);

        textureCheckBox = new TextureAtlas("login/checkbox.texture");
        textureCheckBoxSkin = new Skin(textureCheckBox);

        textures = new HashMap<String, Texture>();
        textureRegion = new HashMap<String, TextureRegion>();
    }

    public void loginScreen() {
        textures.put("Fon", new Texture("fon.jpg"));
        textures.put("Textures", new Texture("login/textures.png"));
        textureRegion.put("LogIn", new TextureRegion(textures.get("Textures"), 0, 0, 192, 192));
        textureRegion.put("Password", new TextureRegion(textures.get("Textures"), 192, 0, 192, 192));
        textureRegion.put("Button", new TextureRegion(textures.get("Textures"), 0, 192, 448, 64));
        textureRegion.put("CheckBoxOn", new TextureRegion(textures.get("Textures"), 0, 256, 128, 128));
        textureRegion.put("CheckBoxOff", new TextureRegion(textures.get("Textures"), 128, 256, 128, 128));
        textureRegion.put("White", new TextureRegion(textures.get("Textures"), 384, 0, 64, 64));
        textureRegion.put("Close", new TextureRegion(textures.get("Textures"), 384, 64, 128, 128));
    }

    public Skin getTextureButtonSkin() {
        return textureButtonSkin;
    }

    public Skin getTextureCheckBoxSkin() {
        return textureCheckBoxSkin;
    }

    public void textureDispose() {
        textures.clear();
        textureRegion.clear();
    }

    public void dispose() {
        try {
            textureButton.dispose();
            textureButtonSkin.dispose();
            textureCheckBox.dispose();
            textureCheckBoxSkin.dispose();
            textureDispose();
        } catch (NullPointerException e) {
            // если вдруг
        }
    }
}