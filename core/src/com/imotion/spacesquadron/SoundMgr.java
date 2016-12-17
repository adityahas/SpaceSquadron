package com.imotion.spacesquadron;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

/**
 * Created by azakhary on 8/23/2015.
 */
public class SoundMgr {

    private static SoundMgr instance;
    private final AssetManager assetMgr;

    public HashMap<String, Sound> fx = new HashMap<String, Sound>();

    private SoundMgr() {
        assetMgr = Env.getGame().getAssetManager();
        loadSound();
    }

    public static SoundMgr getInstance() {
        if (instance == null) {
            instance = new SoundMgr();
        }

        return instance;
    }

    private void loadSound() {
        fx.put(Env.SFX_CLICK, assetMgr.get("sound/" + Env.SFX_CLICK, Sound.class));
        fx.put(Env.SFX_JUMP, assetMgr.get("sound/" + Env.SFX_JUMP, Sound.class));
    }

    public void play(String name) {
        fx.get(name).play();
    }

    public void play(String name, float volume) {
        long id = fx.get(name).play();
        fx.get(name).setVolume(id, volume);
    }
}
