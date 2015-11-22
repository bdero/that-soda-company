package tsc.engine;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Quad;

/**
 * The glue between scenes.
 * @author bdero
 */
public class SceneController {
    private final AppStateManager stateManager;
    private final SimpleApplication app;

    private Scene currentScene;
    private Scene nextScene;

    private ColorRGBA fadeColor;

    private boolean transitioning = false;

    public SceneController(AppStateManager stateManager, Application app, Scene initialScene) {
        this.stateManager = stateManager;
        this.app = (SimpleApplication)app;
        this.nextScene = initialScene;
        swapScene();
        initFade();
    }

    public void update(float tpf) {
        int sign = transitioning ? 1 : -1;
        fadeColor.a = Math.min(1, Math.max(0, fadeColor.a + 1*sign*tpf));

        if (transitioning && fadeColor.a == 1) {
            swapScene();
        }
    }

    public void render(RenderManager rm) {
    }

    private void initFade() {
        Camera guiCam = app.getGuiViewPort().getCamera();

        Quad fadeQuad = new Quad(guiCam.getWidth(), guiCam.getHeight());
        Geometry fadeLayer = new Geometry("TransitionFade", fadeQuad);

        Material fadeMat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        fadeMat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        fadeLayer.setMaterial(fadeMat);

        fadeColor = new ColorRGBA(0, 0, 0, 1);
        fadeMat.setColor("Color", fadeColor);

        app.getGuiNode().attachChild(fadeLayer);
    }

    private void swapScene() {
        if (currentScene != null) {
            stateManager.detach(currentScene);
        }

        if (nextScene == null) {
            // Kill the application if the next scene is set to null.
            app.stop();
        } else {
            currentScene = nextScene;
            nextScene = null;

            stateManager.attach(currentScene);

            transitioning = false;
        }
    }

    public void transition(Scene nextScene) {
        this.nextScene = nextScene;
        transitioning = true;
    }
}
