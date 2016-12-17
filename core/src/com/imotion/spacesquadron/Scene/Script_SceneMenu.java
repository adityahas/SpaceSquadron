package com.imotion.spacesquadron.Scene;

import com.badlogic.ashley.core.Entity;
import com.imotion.spacesquadron.Env;
import com.imotion.spacesquadron.SceneMgr;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

/**
 * Created by Aditya Hadi on 11/30/2016.
 */

public class Script_SceneMenu implements IScript {

    Entity entity;
    private ItemWrapper rootItem;

    @Override
    public void init(Entity entity) {
        this.entity = entity;
        this.rootItem = new ItemWrapper(entity);

        ButtonComponent btnBackComponent = rootItem.getChild("btnBack").getEntity().getComponent(ButtonComponent.class);
        ButtonComponent btnSettingComponent = rootItem.getChild("btnSetting").getEntity().getComponent(ButtonComponent.class);
        ButtonComponent btnSelectLevelComponent = rootItem.getChild("btnSelectLevel").getEntity().getComponent(ButtonComponent.class);

        if (btnBackComponent != null) {
            btnBackComponent.addListener(new ButtonComponent.ButtonListener() {
                @Override
                public void touchUp() {
                    Env.getGame().changeScene(Env.O2D_SCENE_MAIN);
                }

                @Override
                public void touchDown() {
                }

                @Override
                public void clicked() {
                }
            });
        }

        if (btnSelectLevelComponent != null) {
            btnSelectLevelComponent.addListener(new ButtonComponent.ButtonListener() {
                @Override
                public void touchUp() {

                }

                @Override
                public void touchDown() {

                }

                @Override
                public void clicked() {

                }
            });
        }

        if (btnSettingComponent != null) {
            btnSettingComponent.addListener(new ButtonComponent.ButtonListener() {
                @Override
                public void touchUp() {
                    Env.getGame().changeScene(Env.O2D_SCENE_SETTING);
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
