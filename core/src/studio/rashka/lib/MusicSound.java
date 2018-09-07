package studio.rashka.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class MusicSound {

    //private Music music, run, cave;
    private Sound click;

    public MusicSound() {
        click = Gdx.audio.newSound(Gdx.files.internal("sounds/click.ogg"));
    }

    /*public void startFon(){
        try {
            if (!music.isPlaying()) {
                music.setLooping(true);
                music.setVolume(1.0f);
                music.play();
            }
        } catch (NullPointerException e) {
            loadMusicFon();
            startRun();
        }
    }

    public void pauseFon(){
        if (music != null) {
            try {
                music.pause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopFon(){
        if (music != null) {
            try {
                music.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    public Sound getClick() {
        return click;
    }

    public void dispose() {
        try {
            click.dispose();
            /*music.dispose();
            run.dispose();
            cave.dispose();

            gameover.dispose();
            sounds.clear();*/
        } catch (NullPointerException e) {
            // предохраняемся
        }
    }
}