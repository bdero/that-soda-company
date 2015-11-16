/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsc.engine;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;

/**
 * The glue between scenes.
 * @author bdero
 */
public class SceneController {
    private AppStateManager stateManager;
    private Application app;

    private Scene currentScene;
    private Scene nextScene;

    public SceneController(AppStateManager stateManager, Application app, Scene initialScene) {
        this.stateManager = stateManager;
        this.app = app;
        this.nextScene = initialScene;
        this.swapScene();
    }

    public void update(float tpf) {
        currentScene.update(tpf);
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
        }
    }

    public void transition(Scene nextScene) {
        this.nextScene = nextScene;
    }
}
