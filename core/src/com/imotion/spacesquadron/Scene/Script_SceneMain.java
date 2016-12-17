package com.imotion.spacesquadron.Scene;

import com.badlogic.ashley.core.Entity;
import com.imotion.spacesquadron.Env;
import com.imotion.spacesquadron.MusicMgr;
import com.imotion.spacesquadron.SceneHelper;
import com.imotion.spacesquadron.SceneMgr;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

/**
 * Created by Aditya Hadi on 11/30/2016.
 */

public class Script_SceneMain implements IScript {

    Entity entity;
    private ItemWrapper rootItem;
    private float scaleAnim = 1.2f;

    @Override
    public void init(Entity entity) {
        this.entity = entity;
        rootItem = new ItemWrapper(entity);
        MusicMgr.getInstance().play(Env.MUSIC_TITLE, true);

        ButtonComponent btnMenuComponent = rootItem.getChild("btnMenu").getEntity().getComponent(ButtonComponent.class);
        ButtonComponent btnInstantPlayComponent = rootItem.getChild("btnInstantPlay").getEntity().getComponent(ButtonComponent.class);
        ButtonComponent btnSpineComponent = rootItem.getChild("btnSpine").getEntity().getComponent(ButtonComponent.class);

        if (btnInstantPlayComponent != null) {
            btnInstantPlayComponent.addListener(new ButtonComponent.ButtonListener() {
                @Override
                public void touchUp() {
                    Env.getGame().changeScene(Env.O2D_SCENE_POP_UP, false);
                }

                @Override
                public void touchDown() {

                }

                @Override
                public void clicked() {

                }
            });
        }

        if (btnSpineComponent != null) {
            btnSpineComponent.addListener(new ButtonComponent.ButtonListener() {
                @Override
                public void touchUp() {
                    SceneHelper.setAnimSpineEntity("idle", true, rootItem.getChild("btnSpine").getChild("spine").getEntity());
                }

                @Override
                public void touchDown() {
                    SceneHelper.setAnimSpineEntity("press", false, rootItem.getChild("btnSpine").getChild("spine").getEntity());
                }

                @Override
                public void clicked() {

                }
            });
        }

        if (btnMenuComponent != null) {
            btnMenuComponent.addListener(new ButtonComponent.ButtonListener() {
                @Override
                public void touchUp() {
                    Env.getGame().changeScene(Env.O2D_SCENE_MENU);
                }

                @Override
                public void touchDown() {
                }

                @Override
                public void clicked() {
                }
            });
        }
    }

    @Override
    public void act(float delta) {
        SceneMgr.s_updateSceneTransition(rootItem);
    }

    @Override
    public void dispose() {

    }
}
