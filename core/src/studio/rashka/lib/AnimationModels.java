package studio.rashka.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class AnimationModels {

    private ArrayList<TextureRegion> frames; // массив хранения кадров анимации
    private float maxFrameTime; // время отображения одного кадра
    private float currentFrameTime; // время отображения текущего кадра
    private int frameCount; // количество кадров анимации
    private int frame; // кадр анимации
    private int frameWidth, frameHeight; // размеры кадра анимации

    public AnimationModels(TextureRegion region, int frameCount, float cycleTime) {
        frames = new ArrayList<TextureRegion>();
        frameWidth = region.getRegionWidth() / frameCount;
        frameHeight = region.getRegionHeight();
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, region.getRegionY(), frameWidth, frameHeight));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float deltaTime) {
        currentFrameTime += deltaTime;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount) frame = 0;
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }
}