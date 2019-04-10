package jx3d.io.event;

import jx3d.core.Module;
import jx3d.core.Window;

/**
 * Window event is a container class that contains information about the window properties of the related window.
 * The types of events that uses this class are {@link EventType#WindowMoved}, {@link EventType#WindowResize},
 * {@link EventType#WindowFocus}, {@link EventType#WindowLostFocus}, {@link EventType#WindowIconified},
 * {@link EventType#WindowMaximized} and {@link EventType#WindowClose}.
 */
public class WindowEvent extends Event {

    /**
     * The name of the event.
     */
    private String name;

    /**
     * The type of event.
     */
    private EventType type;

    /**
     * The related window,
     */
    private Window window;

    /**
     * The window positions.
     */
    private float x, y;

    /**
     * The window size
     */
    private float width, height;

    /**
     * Window is iconified.
     */
    private boolean iconified;

    /**
     * Window is maximized.
     */
    private boolean maximized;

    /**
     * Constructs a {@link WindowEvent} object with specific parameters.
     * @param name the name of the event
     * @param type the type of event
     * @param window the related window
     * @param x the window x position
     * @param y the window y position
     * @param width the window width
     * @param height the window height
     * @param iconified is window iconified
     * @param maximized is window maximized
     */
    public WindowEvent(String name, EventType type, Window window, float x, float y, float width, float height,
                       boolean iconified, boolean maximized) {
        this.name = name;
        this.type = type;
        this.window = window;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.iconified = iconified;
        this.maximized = maximized;
    }

    /**
     * Get the related window.
     * @return the related window
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Get the window x position.
     * @return the x position
     */
    public float getX() {
        return x;
    }

    /**
     * Get the window y position.
     * @return the y position
     */
    public float getY() {
        return y;
    }

    /**
     * Get the window width.
     * @return the width
     */
    public float getWidth() {
        return width;
    }

    /**
     * Get the window height
     * @return the height
     */
    public float getHeight() {
        return height;
    }

    /**
     * Check if the window is iconified
     * @return true if iconified, false otherwise
     */
    public boolean isIconified() {
        return iconified;
    }

    /**
     * Check if the window is maximized
     * @return true if maximized, false otherwise
     */
    public boolean isMaximized() {
        return maximized;
    }

    @Override
    public EventType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCategoryFlag() {
        return Module.WINDOW_EVENTS;
    }
}
