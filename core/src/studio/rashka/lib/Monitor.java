package studio.rashka.lib;

import com.badlogic.gdx.Gdx;

import studio.rashka.Kalinger;

public class Monitor { // для portrait ориентации

    private float ratioMonitorW, ratioMonitorH;
    private static int mWIDTH = Kalinger.WIDTH, mHEIGHT = Kalinger.HEIGHT;

    public Monitor () {
        onWidth();
        onHeight();
        if (ratioMonitorW != ratioMonitorH) {
            Kalinger.setWIDTH(1200);
            mWIDTH = Kalinger.WIDTH;
            onWidth();
            onHeight();
        }
    }

    private void onWidth() {
        ratioMonitorW = (float) Gdx.graphics.getWidth() / (float) mWIDTH;
    }

    private void onHeight() {
        if (Gdx.graphics.getHeight() == 2560 || Gdx.graphics.getHeight() == 1920 || Gdx.graphics.getHeight() == 1280 ||
                Gdx.graphics.getHeight() == 1024 || Gdx.graphics.getHeight() == 960 || Gdx.graphics.getHeight() == 854 ||
                Gdx.graphics.getHeight() == 800 || Gdx.graphics.getHeight() == 640 || Gdx.graphics.getHeight() == 480)
            ratioMonitorH = (float) Gdx.graphics.getHeight() / (float) mHEIGHT;
        else if (Gdx.graphics.getHeight() == 2416 || Gdx.graphics.getHeight() == 1776 || Gdx.graphics.getHeight() == 1136 ||
                Gdx.graphics.getHeight() == 880 || Gdx.graphics.getHeight() == 816 || Gdx.graphics.getHeight() ==  710)
            ratioMonitorH = (float) (Gdx.graphics.getHeight() + 144) / (float) mHEIGHT;
        else if (Gdx.graphics.getHeight() == 2434 || Gdx.graphics.getHeight() == 1794 || Gdx.graphics.getHeight() == 1154 ||
                Gdx.graphics.getHeight() == 898 || Gdx.graphics.getHeight() == 834 || Gdx.graphics.getHeight() ==  728)
            ratioMonitorH = (float) (Gdx.graphics.getHeight() + 126) / (float) mHEIGHT;
        else if (Gdx.graphics.getHeight() == 2464 || Gdx.graphics.getHeight() == 1824 || Gdx.graphics.getHeight() == 1184 ||
                Gdx.graphics.getHeight() == 928 || Gdx.graphics.getHeight() == 864 || Gdx.graphics.getHeight() == 758)
            ratioMonitorH = (float) (Gdx.graphics.getHeight() + 96) / (float) mHEIGHT;
        else if (Gdx.graphics.getHeight() == 2467 || Gdx.graphics.getHeight() == 1827 || Gdx.graphics.getHeight() == 1187 ||
                Gdx.graphics.getHeight() == 931 || Gdx.graphics.getHeight() == 867 || Gdx.graphics.getHeight() == 761)
            ratioMonitorH = (float) (Gdx.graphics.getHeight() + 93) / (float) mHEIGHT;
        else if (Gdx.graphics.getHeight() == 2476 || Gdx.graphics.getHeight() == 1836 || Gdx.graphics.getHeight() == 1196 ||
                Gdx.graphics.getHeight() == 940 || Gdx.graphics.getHeight() == 876 || Gdx.graphics.getHeight() == 770)
            ratioMonitorH = (float) (Gdx.graphics.getHeight() + 84) / (float) mHEIGHT;
        else if (Gdx.graphics.getHeight() == 2480 || Gdx.graphics.getHeight() == 1840 || Gdx.graphics.getHeight() == 1200 ||
                Gdx.graphics.getHeight() == 944 || Gdx.graphics.getHeight() == 880 || Gdx.graphics.getHeight() == 774)
            ratioMonitorH = (float) (Gdx.graphics.getHeight() + 80) / (float) mHEIGHT;
        else if (Gdx.graphics.getHeight() == 2488 || Gdx.graphics.getHeight() == 1848 || Gdx.graphics.getHeight() == 1208 ||
                Gdx.graphics.getHeight() == 952 || Gdx.graphics.getHeight() == 888 || Gdx.graphics.getHeight() == 782)
            ratioMonitorH = (float) (Gdx.graphics.getHeight() + 72) / (float) mHEIGHT;
        else if (Gdx.graphics.getHeight() == 2496 || Gdx.graphics.getHeight() == 1856 || Gdx.graphics.getHeight() == 1216 ||
                Gdx.graphics.getHeight() == 960 || Gdx.graphics.getHeight() == 896 || Gdx.graphics.getHeight() == 790)
            ratioMonitorH = (float) (Gdx.graphics.getHeight() + 64) / (float) mHEIGHT;
        else if (Gdx.graphics.getHeight() == 2500 || Gdx.graphics.getHeight() == 1860 || Gdx.graphics.getHeight() == 1220 ||
                Gdx.graphics.getHeight() == 964 || Gdx.graphics.getHeight() == 900 || Gdx.graphics.getHeight() == 794)
            ratioMonitorH = (float) (Gdx.graphics.getHeight() + 60) / (float) mHEIGHT;
        else if (Gdx.graphics.getHeight() == 2512 || Gdx.graphics.getHeight() == 1872 || Gdx.graphics.getHeight() == 1232 ||
                Gdx.graphics.getHeight() == 976 || Gdx.graphics.getHeight() == 912 || Gdx.graphics.getHeight() == 806)
            ratioMonitorH = (float) (Gdx.graphics.getHeight() + 48) / (float) mHEIGHT;
    }

    public float getRatioMonitorW() {
        return ratioMonitorW;
    }

    public float getRatioMonitorH() {
        return ratioMonitorH;
    }
}