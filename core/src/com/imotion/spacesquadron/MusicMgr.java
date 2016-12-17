package com.imotion.spacesquadron;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

import java.util.HashMap;

/**
 * Created by azakhary on 8/23/2015.
 */
public class MusicMgr {
    private static MusicMgr instance;
    private final AssetManager assetMgr;

    public HashMap<String, Music> musics = new HashMap<String, Music>();

    private MusicMgr() {
        assetMgr = Env.getGame().getAssetManager();
        loadMusic();
    }

    public static MusicMgr getInstance() {
        if (instance == null) {
            instance = new MusicMgr();
        }

        return instance;
    }

    private void loadMusic() {
        musics.put(Env.MUSIC_TITLE, assetMgr.get("sound/" + Env.MUSIC_TITLE, Music.class));
    }

    public void play(String name, boolean loop) {
        if (!musics.get(name).isPlaying()) {
            musics.get(name).play();
            if (loop) {
                musics.get(name).setLooping(true);
            }
        }
    }

    public void play(String name, float volume) {
        if (!musics.get(name).isPlaying()) {
            musics.get(name).play();
        }
        musics.get(name).setVolume(volume);
    }
}
