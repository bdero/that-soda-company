/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsc.engine;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.FadeFilter;

/**
 *
 * @author bdero
 */
public class Scene extends AbstractAppState {

    private Application app;
    private AppStateManager stateManager;
    private Scene nextScene;
    private FilterPostProcessor fpp;

    private FadeFilter fade;
    private boolean fadeIn;

    public Scene(FilterPostProcessor fpp) {
        super();

        this.fpp = fpp;
    }

    public void initialize(AppStateManager stateManager, Application app) {
        super.initialize(stateManager, app);

        this.app = app;
        this.stateManager = stateManager;

        fade = new FadeFilter(1.5f);
        fpp.addFilter(fade);
        fade.setValue(0);
        fade.fadeIn();

        fadeIn = true;
    }

    @Override
    public void update(float tpf) {
        float fadeValue = fade.getValue();

        if (fadeValue == 1) {
            fadeIn = false;
        }

        if (!fadeIn && fadeValue == 0) {
            stateManager.detach(this);
        }

        super.update(tpf);
    }

    public void transition(Scene nextScene) {
        this.nextScene = nextScene;
        fade.fadeOut();
        fadeIn = false;
    }

    @Override
    public void cleanup() {
        if (nextScene == null) {
            // Kill the application if no next state was specified.
            app.stop();
        } else {
            // Initialize the next scene
            stateManager.attach(nextScene);
            fpp.removeFilter(fade);
        }

        super.cleanup();
    }

}
