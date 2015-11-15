package tsc;

import tsc.engine.Scene;

import com.jme3.app.DebugKeysAppState;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private Scene testState;

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

        testState = new Scene();
        stateManager.attach(testState);
    }

    private float time = 0;

    @Override
    public void simpleUpdate(float tpf) {
        if (time >= 0) {
            time += tpf;
            if (time > 5) {
                time = -1;
                testState.transition(new Scene());
            }
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
