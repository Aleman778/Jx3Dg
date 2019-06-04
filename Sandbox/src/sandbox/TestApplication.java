package sandbox;

import jx3d.core.Application;
import jx3d.core.ApplicationAdapter;
import jx3d.core.JX3D;
import jx3d.core.Module;
import jx3d.graphics.opengl.GL20;
import jx3d.io.event.Event;
import jx3d.io.event.EventType;
import jx3d.io.event.KeyEvent;
import sandbox.layers.GuiDebugLayer;
import sandbox.layers.ModelViewerLayer;

/**
 * Test application.
 *
 * @author Aleman778
 */
public class TestApplication extends ApplicationAdapter {

    private ModelViewerLayer layer;
    private GuiDebugLayer debugGuiLayer;

    @Override
    public void onStart() {
//        layer = new ModelViewerLayer();
//        Application.get().pushLayer(layer);
        debugGuiLayer = new GuiDebugLayer();
        Application.get().pushLayer(debugGuiLayer);
    }

    @Override
    public void onUpdate() {
        JX3D.gl20.clear(GL20.COLOR_BUFFER_BIT);
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType() == EventType.KeyDown) {
            System.out.println(((KeyEvent) event).getKey() == Module.KEY_ESCAPE && !((KeyEvent) event).isRepeated());
            if (((KeyEvent) event).getKey() == Module.KEY_ESCAPE && !((KeyEvent) event).isRepeated()) {
                if (layer != null) {
                    Application.get().popLayer(layer);
                    layer = null;
                } else {
                    layer = new ModelViewerLayer();
                    Application.get().pushLayer(layer);
                }
            }
        }
    }
}
