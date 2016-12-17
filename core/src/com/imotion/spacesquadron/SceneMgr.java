package com.imotion.spacesquadron;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.imotion.spacesquadron.Scene.Script_SceneCredit;
import com.imotion.spacesquadron.Scene.Script_SceneLoading;
import com.imotion.spacesquadron.Scene.Script_SceneMain;
import com.imotion.spacesquadron.Scene.Script_SceneMenu;
import com.imotion.spacesquadron.Scene.Script_ScenePopUp;
import com.imotion.spacesquadron.Scene.Script_SceneSetting;
import com.overlap2d.extensions.spine.SpineItemType;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.commons.IExternalItemType;
import com.uwsoft.editor.renderer.components.ActionComponent;
import com.uwsoft.editor.renderer.components.ShaderComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.additional.ButtonComponent;
import com.uwsoft.editor.renderer.data.CompositeItemVO;
import com.uwsoft.editor.renderer.data.CompositeVO;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

/**
 * Created by Aditya Hadi on 11/1/2016.
 */

public class SceneMgr {
    private static float scaleAnim;
    SceneLoader sceneLoader;
    private boolean isChangingScene;
    private boolean isChangingSceneClean;
    private String sceneName;

    SceneMgr() {
        sceneLoader = new SceneLoader(); // default scene loader loads all resources from default RM as usual.
        sceneLoader.injectExternalItemType((IExternalItemType) new SpineItemType());

        isChangingScene = false;

        sceneLoader.loadScene(Env.O2D_SCENE_EMPTY, Env.getGame().getViewport());

        changeScene(Env.O2D_SCENE_LOADING);
    }

    public static void s_initSceneTransition() {
//        scaleAnim = 1.2f;
    }

    public static void s_updateSceneTransition(ItemWrapper rootItem) {
        if (scaleAnim > 1f) {
            Entity rootEntity = rootItem.getEntity();
            TransformComponent transformComponent = ComponentRetriever.get(rootEntity, TransformComponent.class);
            ShaderComponent shaderComponent = ComponentRetriever.get(rootEntity, ShaderComponent.class);
            ActionComponent actionComponent = ComponentRetriever.get(rootEntity, ActionComponent.class);

            scaleAnim -= Gdx.graphics.getDeltaTime();
            transformComponent.x = (480 - (480 * scaleAnim)) / 2;
            transformComponent.y = (800 - (800 * scaleAnim)) / 2;
            transformComponent.scaleX = scaleAnim;
            transformComponent.scaleY = scaleAnim;

            if (transformComponent.scaleX < 1f || transformComponent.scaleY < 1f) {
                transformComponent.x = 0;
                transformComponent.scaleX = 1f;
                transformComponent.scaleY = 1f;
            }
        }

    }

    void updateSceneChanges() {
//        if (isChangingScene && sceneLoader.getEngine().getEntities().size() == 0)
        if (isChangingScene) {

            Gdx.app.log("UI", "Change scene to " + sceneName + " clean:" + isChangingSceneClean);

            if (isChangingSceneClean) {
                sceneLoader.loadScene(sceneName, Env.getGame().getViewport());
                isChangingSceneClean = false;
            } else {
                CompositeItemVO vo = new CompositeItemVO();
                vo.composite = sceneLoader.getRm().getSceneVO(sceneName).composite;

                sceneLoader.getEntityFactory().createEntity(sceneLoader.getRoot(), vo);

                if (sceneLoader.getRm().getSceneVO(sceneName).composite == null) {
                    sceneLoader.getRm().getSceneVO(sceneName).composite = new CompositeVO();
                }

                if (sceneLoader.getRm().getSceneVO(sceneName).composite != null) {
                    sceneLoader.getEntityFactory().initAllChildren(sceneLoader.getEngine(), sceneLoader.getRoot(), sceneLoader.getRm().getSceneVO(sceneName).composite);
                }
            }

            // all entities with button tag now have ButtonComponent
            sceneLoader.addComponentsByTagName("button", ButtonComponent.class);
            // get the root entity of the scene
            ItemWrapper rootItem = new ItemWrapper(sceneLoader.getRoot());

            IScript sceneScript;
            if (sceneName.equals(Env.O2D_SCENE_MAIN)) {
                sceneScript = new Script_SceneMain();
            } else if (sceneName.equals(Env.O2D_SCENE_MENU)) {
                sceneScript = new Script_SceneMenu();
            } else if (sceneName.equals(Env.O2D_SCENE_SETTING)) {
                sceneScript = new Script_SceneSetting();
            } else if (sceneName.equals(Env.O2D_SCENE_CREDIT)) {
                sceneScript = new Script_SceneCredit();
            } else if (sceneName.equals(Env.O2D_SCENE_LOADING)) {
                sceneScript = new Script_SceneLoading();
            } else if (sceneName.equals(Env.O2D_SCENE_LOADING)) {
                sceneScript = new Script_SceneLoading();
            } else if (sceneName.equals(Env.O2D_SCENE_POP_UP)) {
                sceneScript = new Script_ScenePopUp();
            } else {
                sceneScript = new Script_SceneMain();
                Gdx.app.error("UI", "Scene not found");
            }

            // attach the script to scene entity
            rootItem.addScript(sceneScript);

            isChangingScene = false;
        }
    }

    void changeScene(String sceneName) {
        changeScene(sceneName, true);
    }

    void changeScene(String sceneName, boolean cleanScreenFirst) {
        SceneMgr.s_initSceneTransition();
        isChangingScene = true;
        isChangingSceneClean = cleanScreenFirst;
        this.sceneName = sceneName;

        if (cleanScreenFirst) {
            sceneLoader.getEngine().removeAllEntities();
        }
    }
}
