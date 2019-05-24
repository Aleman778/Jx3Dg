package jx3d.core;

import jx3d.graphics.debug.gui.GuiDebug;
import jx3d.io.event.Event;

/**
 * Application adapter is a class implements application listener in order to
 * give the user the possibility to override only the event you desire.
 */
public abstract class ApplicationAdapter implements ApplicationListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void onStart() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEvent(Event event) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onUpdate() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onDebugGuiRender(GuiDebug gui) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {

    }
}
