package jx3d.io.event;

import jx3d.core.Module;
import jx3d.core.Window;

public class WindowEvent extends Event {

    /**
     * The name of the event.
     */
    private String name;

    /**
     * The type of event.
     */
    private EventType type;

    private Window window;



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
