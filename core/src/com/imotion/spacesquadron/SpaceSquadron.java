package com.imotion.spacesquadron;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SpaceSquadron extends ApplicationAdapter {
    /*
 * SceneMgr will be holding both menu and main game
 */
    private SceneMgr sceneMgr;
    private BitmapFont font;
    private SpriteBatch batch;
    private Preferences preferences;
    private int currLevelNum;
    private Viewport viewport;
    private AssetManager assetManager;


    @Override
    public void create() {
        new Env(this);
        viewport = new FillViewport(480, 800); // this should be the size of camera in WORLD units. make sure you check that in editor first.

        sceneMgr = new SceneMgr();

        batch = new SpriteBatch();
        font = new BitmapFont();


        preferences = Gdx.app.getPreferences("spacesquadron_save_data");
        currLevelNum = preferences.getInteger("level", 1);

        assetManager = new AssetManager();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sceneMgr.sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime()); // getting the ashley engine and updating it (it will render things with it's own render system)
        sceneMgr.updateSceneChanges();
    }

    @Override
    public void dispose() {

    }

    public void changeScene(String sceneName) {
        sceneMgr.changeScene(sceneName, true);
    }

    public void changeScene(String sceneName, boolean cleanScreenFirst) {
        SoundMgr.getInstance().play(Env.SFX_CLICK);
        sceneMgr.changeScene(sceneName, cleanScreenFirst);
    }

    public Viewport getViewport() {
        return viewport;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public SceneMgr getSceneMgr() {
        return sceneMgr;
    }
}

