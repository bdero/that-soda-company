package tsc.scenes;

import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import tsc.engine.Scene;

/**
 *
 * @author bdero
 */
public class MenuScene extends Scene {

    @Override
    protected void sceneInit() {
        Spatial bottle = app.getAssetManager().loadModel("Models/Bottle/Bottle.j3o");
        bottle.setLocalTranslation(50, 25, 0);
        bottle.scale(5);
        app.getRootNode().attachChild(bottle);
        PointLight light = new PointLight();
        light.setPosition(new Vector3f(50, 50, 200));
        light.setRadius(1000);
        light.setColor(ColorRGBA.White);
        app.getRootNode().addLight(light);
    }

    @Override
    protected void sceneUpdate(float tpf) {
    }

}
