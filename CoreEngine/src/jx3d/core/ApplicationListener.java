package jx3d.core;

import jx3d.util.Disposable;

/**
 * Application listener is used to create an implementation
 * of an application in the client code. The listener
 * provides some basic events that are triggered by the application.
 */
public interface ApplicationListener extends Disposable {

    /**
     * The setup event is triggered when the {@link Application} is .
     * This event is only called once and should not be invoked after or before
     * this event is triggered.
     */
    void setup();

    /**
     * The update event is triggered for each {@link Application} tick.
     * The frequency of the event can be changed in some configurations.
     * By default the update method is approximately called 30 times per second.
     */
    void update();

    /**
     * The draw event is triggered for each time the {@link Application} needs to be redrawn.
     * The frequency of the event can be changed in some configurations.
     * By defailt the draw method is approximately called 30 times per second
     * giving you 30 frames per second by above definition.
     */
    void draw();

    /**
     * The dispose event is triggered at the end of the {@link Application} lifecycle e.g.
     * when user closes the {@link Application}. This event should clean every thing up
     * i.e. close opened files, clear memory etc.
     */
    void dispose();
}
