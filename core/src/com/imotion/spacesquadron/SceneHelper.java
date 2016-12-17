package com.imotion.spacesquadron;

import com.badlogic.ashley.core.Entity;
import com.overlap2d.extensions.spine.SpineObjectComponent;

/**
 * Created by Aditya Hadi on 12/7/2016.
 */

public class SceneHelper {
    /**
     * @param anim   : animation name
     * @param loop
     * @param entity : the Overlap2D entity containing the Spine object
     */
    public static void setAnimSpineEntity(String anim, boolean loop, Entity entity) {
        if (entity != null && entity.getComponent(SpineObjectComponent.class) != null)
            entity.getComponent(SpineObjectComponent.class).setAnimation(anim, loop);
    }
}
