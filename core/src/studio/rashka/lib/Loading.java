package studio.rashka.lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import studio.rashka.Kalinger;

public class Loading {

    private Animation<TextureRegion> animation;
    float deltaTime;

    public Loading() {
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("loading/load.gif").read());
    }

    public void render(SpriteBatch batch) {
        deltaTime += Gdx.graphics.getDeltaTime();
        batch.begin();
        batch.draw(animation.getKeyFrame(deltaTime), Kalinger.WIDTH / 2 - 75, 256);
        batch.end();
    }
}