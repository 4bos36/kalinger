package studio.rashka.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Preference {

    private Preferences setting;

    public Preference() {
        setting = Gdx.app.getPreferences("Kalinger"); // настройки приложения
        //setting.clear(); // если изменили тип данных, то разкомментироветь, запускаем и комментируем обратно, т.е. обнуляем всё

        Boolean start_pref = setting.getBoolean("Start_Pref");
        if (!start_pref) { // если запускается в первые, то запускаем параметры по умолчанию
            runNewSettings();
        }
    }

    public void runNewSettings() {
        setting.putInteger("Sound", 1);
        setting.putInteger("Music", 1);
        setting.putInteger("Vibro", 1);
        setting.putBoolean("Start_Pref", true); // сообщаем, что приложение уже было запущено
        setting.flush();
    }

    public void saveSound(int on_off) { // сохраняем настройки звуков
        setting.putInteger("Sound", on_off);
        setting.flush();
    }

    public int loadSound() { // загружаем настройки звуков
        return setting.getInteger("Sound");
    }

    public void saveMusic(int on_off) { // сохраняем настройки музыки
        setting.putInteger("Music", on_off);
        setting.flush();
    }

    public int loadMusic() { // загружаем настройки музыки
        return setting.getInteger("Music");
    }

    public int loadVibro() { // загружаем настройки музыки
        return setting.getInteger("Vibro");
    }

    public void saveVibro(int vibro) { // устанавливаем вибрацию телефона
        setting.putInteger("Vibro", vibro);
        setting.flush();
    }

    public String language() {
        return setting.getString("Lang");
    }
}