package tsc;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import tsc.engine.SceneController;
import tsc.engine.Scene;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private SceneController sceneController;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public Main() {
        super(new StatsAppState(), new DebugKeysAppState());
        System.out.println("OPERATING SYSTEM: " + System.getProperty("os.name"));
    }

    @Override
    public void simpleInitApp() {
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

        sceneController = new SceneController(stateManager, this, new Scene());

    }

    private float time = 0;

    @Override
    public void simpleUpdate(float tpf) {
        sceneController.update(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
