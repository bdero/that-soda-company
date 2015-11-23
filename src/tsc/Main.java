package tsc;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import tsc.engine.SceneController;
import tsc.scenes.MenuScene;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private SceneController sceneController;

    public float width;
    public float height;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public Main() {
        super(new StatsAppState(), new DebugKeysAppState());
    }

    @Override
    public void simpleInitApp() {
        // Set the background color
        viewPort.setBackgroundColor(ColorRGBA.White);

        // Setup the camera to be orthographic and encapsulate a playing field with width 0 to 100, and scale the
        // height accordingly.

        width = 100;
        height = width*((float)settings.getHeight()/settings.getWidth());
        float hFieldWidth = width/2;
        float hFieldHeight = height/2;

        Camera camera = getCamera();
        camera.setParallelProjection(true);
        camera.setFrustum(1, 1000, -hFieldWidth, hFieldWidth, hFieldHeight, -hFieldHeight);
        camera.update();
        camera.setLocation(new Vector3f(hFieldWidth, hFieldHeight, 500));

        sceneController = new SceneController(stateManager, this, new MenuScene());

    }

    @Override
    public void simpleUpdate(float tpf) {
        sceneController.update(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        sceneController.render(rm);
    }
}
