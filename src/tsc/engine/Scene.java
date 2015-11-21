/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsc.engine;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;

/**
 *
 * @author bdero
 */
public class Scene extends AbstractAppState {

    protected Application app;
    protected AppStateManager stateManager;

    public Scene() {
        super();
    }

    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        this.app = app;
        this.stateManager = stateManager;
    }

    @Override
    public void update(float tpf) {
        super.update(tpf);
    }

}
