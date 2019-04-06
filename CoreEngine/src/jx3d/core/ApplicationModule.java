package jx3d.core;

import jx3d.io.event.Event;
import jx3d.io.event.EventDispatcher;

/**
 * Application module is an implementation of {@link ApplicationListener} and extends {@link Module}
 * for easily prototyping an application without setting up layers. For larger projects you are not
 * recommended to use this since it contains unnecessary code.
 */
public class ApplicationModule extends Module implements ApplicationListener {

    @Override
    public final void onEvent(Event event) {
        EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.addListener(Module.INPUT_EVENTS, this);
        dispatcher.dispatch(event);
    }

    @Override
    public void setup() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {

    }

    @Override
    public void dispose() {

    }
}
