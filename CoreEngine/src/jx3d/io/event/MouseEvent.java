package jx3d.io.event;

import jx3d.core.Module;

/**
 * Mouse events is a container class that contains information about the mouse position and the button that caused
 * this event to be triggered. The types of events that uses this class are {@link EventType#MousePressed},
 * {@link EventType#MouseReleased}, {@link EventType#MouseMoved} and {@link EventType#MouseDragged}.
 */
public class MouseEvent extends Event {

    /**
     * The name of the event.
     */
    private String name;

    /**
     * The type of event.
     */
    private EventType type;

    /**
     * The mouse position relative to the window.
     */
    private float x, y;

    //TODO: Add x, y mouse position based on the coordinate system the screen uses.

    /**
     * The change in mouse position.
     */
    private float dx, dy;

    /**
     * The mouse button that triggered this event.
     */
    private int button;

    /**
     * The total number of clicks since the start of the Application.
     */
    private int clickCount;

    public MouseEvent(EventType type, String name, int button, float x, float y, float dx, float dy, int clickCount) {
        this.type = type;
        this.name = name;
        this.button = button;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.clickCount = clickCount;
    }

    /**
     * Get the x position of the mouse.
     * @return the x position
     */
    public float getX() {
        return x;
    }

    /**
     * Get the y position of the mouse.
     * @return the y position
     */
    public float getY() {
        return y;
    }

    /**
     * Get the change in the x position.
     * @return the delta x position
     */
    public float getDx() {
        return dx;
    }

    /**
     * Get the change in the y position.
     * @return the delta y position
     */
    public float getDy() {
        return dy;
    }

    /**
     * Get the button that triggered this event.
     * @return the related mouse button
     */
    public int getButton() {
        return button;
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
        return Module.MOUSE_EVENTS;
    }

    @Override
    public String toString() {
        return "Mouse" + super.toString() + " {button=" + button + ", x=" + x + ", y=" + y +"}";
    }
}
