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

public class Script_SceneSetting implements IScript {

    Entity entity;
    private ItemWrapper rootItem;

    @Override
    public void init(Entity entity) {
        this.entity = entity;
        rootItem = new ItemWrapper(entity);

        ButtonComponent btnBackComponent = rootItem.getChild("btnBack").getEntity().getComponent(ButtonComponent.class);
        ButtonComponent btnCreditComponent = rootItem.getChild("btnCredit").getEntity().getComponent(ButtonComponent.class);
        ButtonComponent btnMusicComponent = rootItem.getChild("btnMusic").getEntity().getComponent(ButtonComponent.class);
        ButtonComponent btnSFXComponent = rootItem.getChild("btnSFX").getEntity().getComponent(ButtonComponent.class);

        if (btnBackComponent != null) {
            btnBackComponent.addListener(new ButtonComponent.ButtonListener() {
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

        if (btnCreditComponent != null) {
            btnCreditComponent.addListener(new ButtonComponent.ButtonListener() {
                @Override
                public void touchUp() {
                    Env.getGame().changeScene(Env.O2D_SCENE_CREDIT);
                }

                @Override
                public void touchDown() {

                }

                @Override
                public void clicked() {

                }
            });
        }

        if (btnMusicComponent != null) {
            btnMusicComponent.addListener(new ButtonComponent.ButtonListener() {
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

        if (btnSFXComponent != null) {
            btnSFXComponent.addListener(new ButtonComponent.ButtonListener() {
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
    }

    @Override
    public void act(float delta) {
        SceneMgr.s_updateSceneTransition(rootItem);
    }

    @Override
    public void dispose() {

    }
}
