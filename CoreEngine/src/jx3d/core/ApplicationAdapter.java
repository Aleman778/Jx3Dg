package jx3d.core;

import jx3d.io.event.Event;

/**
 * Application adapter is a class implements application listener in order to
 * give the user the possibility to override only the event you desire.
 */
public abstract class ApplicationAdapter implements ApplicationListener {

    @Override
    public void onEvent(Event event) {

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
