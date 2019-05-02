package sandbox;

import jx3d.core.Application;
import jx3d.core.ApplicationAdapter;
import sandbox.layers.ModelViewerLayer;

/**
 * Test application.
 *
 * @author Aleman778
 */
public class TestApplication extends ApplicationAdapter {

    @Override
    public void onStart() {
        ModelViewerLayer layer = new ModelViewerLayer();
        Application.get().pushLayer(layer);
    }
}
