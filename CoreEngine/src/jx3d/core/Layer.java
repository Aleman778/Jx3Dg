package jx3d.core;

import jx3d.io.event.Event;
import jx3d.util.Disposable;

/**
 * Abstract layer class provides with basic definition of what a layer behaves.
 * A layer is not necessarily related to graphics that can be drawn on screen,
 * it can also be used to handle inputs and outputs from other devices.
 */
public abstract class Layer implements Disposable {

    /**
     * The name of the layer
     */
    private final String name;

    /**
     * Create a layer without a specific name.
     */
    public Layer() {
        this("");
    }

    /**
     * Create a layer with a specific name.
     * @param name specify the name
     */
    public Layer(String name) {
        this.name = name + getClass().getSimpleName();
    }

    /**
     * On attach method is called when the layer is attached to the layer stack.
     */
    public void onAttach() {

    }

    /**
     * On detach method is called when the layer is detached from the layer stack.
     */
    public void onDetach() {

    }

    /**
     * On update method is called when the application requests an update.
     */
    public void onUpdate() {

    }

    /**
     * On event method is called when an event was triggered and needs to be handled by the layer.
     * Events are processed first on overlay layers.
     * @param event the event that was triggered
     */
    public void onEvent(Event event) {

    }

    @Override
    public void dispose() {

    }

    /**
     * Get the name of the layer.
     * @return the layer name
     */
    public String getName() {
        return name;
    }
}
