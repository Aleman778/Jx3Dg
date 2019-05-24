package jx3d.core;

import jx3d.graphics.debug.gui.GuiDebug;
import jx3d.io.event.Event;

/**
 * Application listener is used to create an implementation
 * of an application in the client code. The listener
 * provides some basic event that are triggered by the application.
 */
public interface ApplicationListener {

    /**
     * The <code>onStart</code> function is triggered when the {@link Application} has started running.
     * This event is only called once and should not be invoked after or before
     * this event is triggered.
     */
    void onStart();

    /**
     * The <code>onEvent</code> function is triggered when an event is created either by
     * the user interacting with an input device or via a custom event.
     * @param event the event that has to be handled
     */
    void onEvent(Event event);

    /**
     * The <code>onUpdate</code> function is triggered for each {@link Application} tick.
     * The frequency of the event can be changed in some configurations.
     * By default the update method is approximately called 30 times per second.
     */
    void onUpdate();

    /**
     * The <code>onDebugGuiRender</code> function is triggered after the update and is
     * used to setup a simple debug graphical user interface.
     */
    void onDebugGuiRender(GuiDebug gui);

    /**
     * The dispose event is triggered at the end of the {@link Application} lifecycle e.g.
     * when user closes the {@link Application}. This event should clean every thing up
     * i.e. close opened files, clear memory etc.
     */
    void dispose();
}
