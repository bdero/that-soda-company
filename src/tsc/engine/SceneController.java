/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsc.engine;

import com.jme3.app.Application;
import com.jme3.app.state.AppStateManager;

/**
 *
 * @author bdero
 */
public class SceneController {
    Scene currentScene;
    AppStateManager stateManager;
    Application app;

    public SceneController(AppStateManager stateManager, Application app) {
        this.stateManager = stateManager;
        this.app = app;
    }


}
