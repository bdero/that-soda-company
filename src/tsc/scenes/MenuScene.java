package tsc.scenes;

import com.jme3.scene.Spatial;
import tsc.engine.Scene;

/**
 *
 * @author bdero
 */
public class MenuScene extends Scene {

    @Override
    protected void sceneInit() {
        Spatial scene = app.getAssetManager().loadModel("Scenes/MenuScene.j3o");
        scene.setLocalTranslation(app.width/2, app.height/2, 0);
        app.getRootNode().attachChild(scene);
        //app.getRootNode().addLight(light);
    }

    @Override
    protected void sceneUpdate(float tpf) {
    }

}
