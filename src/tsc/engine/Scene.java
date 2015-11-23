package tsc.engine;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import tsc.Main;

/**
 *
 * @author bdero
 */
abstract public class Scene extends AbstractAppState {

    protected Main app;
    protected AppStateManager stateManager;

    public Scene() {
        super();
    }

    abstract protected void sceneInit();
    abstract protected void sceneUpdate(float tpf);

    @Override
    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        this.app = (Main)app;
        this.stateManager = stateManager;

        sceneInit();
    }

    @Override
    public void update(float tpf) {
        sceneUpdate(tpf);

        super.update(tpf);
    }

}
