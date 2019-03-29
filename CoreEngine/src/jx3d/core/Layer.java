package jx3d.core;

import jx3d.io.event.Event;

/**
 * Abstract layer class provides with basic definition of what a layer behaves.
 * A layer is not necessarily related to graphics that can be drawn on screen,
 * it can also be used to handle inputs and outputs from other devices.
 */
public abstract class Layer {

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
    public abstract void onAttach();

    /**
     * On detach method is called when the layer is detached from the layer stack.
     */
    public abstract void onDetach();

    /**
     * On update method is called when the application requests an update.
     */
    public abstract void onUpdate();

    /**
     * On event method is called when an event was triggered and needs to be handled by the layer.
     * Events are processed first on overlay layers.
     * @param event the event that was triggered
     */
    public abstract void onEvent(Event event);

    /**
     * Get the name of the layer.
     * @return the layer name
     */
    public String getName() {
        return name;
    }
}
