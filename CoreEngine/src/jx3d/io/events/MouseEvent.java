package jx3d.io.events;

import jx3d.core.Module;

public class MouseEvent extends Event {

    /**
     * The name of the event.
     */
    String name;

    float x, y;

    int button;

    int clickCount;

    boolean popupTrigger = false;

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
        return null;
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
