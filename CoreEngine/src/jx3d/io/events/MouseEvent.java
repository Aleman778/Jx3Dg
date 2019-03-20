package jx3d.io.events;

import jx3d.core.Module;

/**
 * Mouse event
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
     * The relative mouse position.
     */
    private float x, y;

    /**
     * The change in mouse position.
     */
    private float dx, dy;

    /**
     * The mouse scrolling offset.
     */
    private float scrollX, scrollY;

    private int button;

    private int clickCount;

    public MouseEvent(EventType type, String name, int button) {
        this.name = name;
        this.button = button;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getButton() {
        return button;
    }

    @Override
    public EventType getType() {
        return EventType.MousePressed;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getCategoryFlag() {
        return Module.MOUSE_EVENTS;
    }
}
