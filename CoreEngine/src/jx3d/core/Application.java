package jx3d.core;

import jx3d.graphics.Graphics;
import jx3d.io.Files;
import jx3d.io.Input;
import jx3d.io.event.Event;
import jx3d.io.event.EventAdapter;
import jx3d.io.event.EventType;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Application class is an abstract class that is used to define common methods and fields
 * to easily achieve platform independent application code. For each platform this class has to
 * be extended to setup the application for that particular platform.
 */
public abstract class Application {

    /**
     * Singleton application instance.
     */
    protected static Application instance = null;

    /**
     * The application listener attached to this application.
     */
    protected final ApplicationListener listener;

    /**
     * The application layer stack.
     */
    protected final LayerStack layerStack;

    /**
     * The running flag indicates whether or not the application is currently running.
     */
    protected boolean running = true;

    /**
     * Constructor creates a new Application.
     */
    public Application(ApplicationListener listener) {
        assert instance != null : "There already exists an application!";
        instance = this;

        this.listener = listener;
        this.layerStack = new LayerStack();
    }

    /**
     * The run method is used to start the application.
     */
    public abstract void run();

    /**
     * Push a layer onto the applications layer stack.
     * @param layer the layer to push
     */
    public final void pushLayer(Layer layer) {
        layerStack.pushLayer(layer);
        layer.onAttach();
    }

    /**
     * Push a layer as an overlay onto the applications layer stack.
     * @param layer the layer to push as overlay
     */
    public final void pushOverlay(Layer layer) {
        layerStack.pushOverlay(layer);
        layer.onAttach();
    }

    /**
     * On event method is called when an event is created and should be dispatched.
     * @param event the event to handle
     */
    public final void onEvent(Event event) {
        if (event.getType() == EventType.WindowClose) {
            onWindowClose();
        }

        listener.onEvent(event);

        for (Layer layer : layerStack) {
            if (event.isHandled())
                break;

            layer.onEvent(event);
        }
    }

    /**
     * On window close method is called before the window is closing and application is terminated.
     */
    private void onWindowClose() {
        running = false;
        layerStack.dispose();
    }

    /**
     * Get the current graphics context used by this application.
     * @return the graphics context
     */
    public abstract Graphics getGraphics();

    /**
     * Get the file manager used by this application.
     * @return the file manager
     */
    public abstract Files getFiles();

    /**
     * Get the input manager used by this application.
     * @return the input manager
     */
    public abstract Input getInput();

    /**
     * Get the static application instance.
     * @return the application in use
     */
    public static Application get() {
        return instance;
    }
}
