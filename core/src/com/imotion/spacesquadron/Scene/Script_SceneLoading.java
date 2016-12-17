package com.imotion.spacesquadron.Scene;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.imotion.spacesquadron.Env;
import com.imotion.spacesquadron.MusicMgr;
import com.imotion.spacesquadron.SceneMgr;
import com.imotion.spacesquadron.SoundMgr;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

/**
 * Created by Aditya Hadi on 11/30/2016.
 */

public class Script_SceneLoading implements IScript {

    Entity entity;
    private ItemWrapper rootItem;
    private AssetManager assetMgr;

    @Override
    public void init(Entity entity) {
        this.entity = entity;
        this.rootItem = new ItemWrapper(entity);
        assetMgr = Env.getGame().getAssetManager();

        loadSound();
    }

    @Override
    public void act(float delta) {
        SceneMgr.s_updateSceneTransition(rootItem);
        if (assetMgr.update()) {
            Gdx.app.log("Loading", "All asset loaded, go to next screen");
            SoundMgr.getInstance();
            MusicMgr.getInstance();
            Env.getGame().changeScene(Env.O2D_SCENE_MAIN);
        }
    }

    @Override
    public void dispose() {

    }

    private void loadSound() {
        assetMgr.load("sound/" + Env.SFX_CLICK, Sound.class);
        assetMgr.load("sound/" + Env.SFX_JUMP, Sound.class);
        assetMgr.load("sound/" + Env.MUSIC_TITLE, Music.class);
    }
}
