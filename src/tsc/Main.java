package tsc;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.FlyCamAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private FilterPostProcessor fpp;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public Main() {
        super(new StatsAppState(), new FlyCamAppState(), new DebugKeysAppState());
    }

    @Override
    public void simpleInitApp() {
        // Create the global post processing filter.
        fpp = new FilterPostProcessor(assetManager);
        viewPort.addProcessor(fpp);

        // Set the background color
        viewPort.setBackgroundColor(ColorRGBA.White);

        // Setup the camera to be orthographic and encapsulate a playing field with width 0 to 100, and scale the
        // height accordingly.

        float fieldWidth = 100;
        float fieldHeight = fieldWidth*((float)settings.getHeight()/settings.getWidth());
        float hFieldWidth = fieldWidth/2;
        float hFieldHeight = fieldHeight/2;

        Camera camera = getCamera();
        camera.setParallelProjection(true);
        camera.setFrustum(1, 1000, -hFieldWidth, hFieldWidth, hFieldHeight, -hFieldHeight);
        camera.update();
        camera.setLocation(new Vector3f(hFieldWidth, hFieldHeight, 500));

        Box b = new Box(10, 10, 10);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
