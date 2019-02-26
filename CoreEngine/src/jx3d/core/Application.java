package jx3d.core;

import jx3d.graphics.Graphics;
import jx3d.io.Files;
import jx3d.io.Input;

/**
 * Basic abstract Application class should be implemented
 * for each platform to better
 */
public abstract class Application {

    /**
     * Singleton application instance.
     */
    protected static Application instance = null;

    /**
     * The the application listener attached to this application.
     */
    protected final ApplicationListener listener;

    /**
     * Constructor creates a new Application.
     */
    public Application(ApplicationListener listener) {
        assert instance != null : "There already exists an application!";
        this.instance = this;
        this.listener = listener;
    }

    /**
     * The run method is used to start the application
     */
    public abstract void run();

    /**
     * Get the current graphics context used by this application.
     * @return the graphics context
     */
    public abstract Graphics getGraphics();

    /**
     * Get the file manager used by this application.
     * @return the file manager
     */
    public abstract Files getFiles();

    /**
     * Get the input manager used by this application.
     * @return the input manager
     */
    public abstract Input getInput();

    /**
     * Get the static application instance.
     * @return the application in use
     */
    public static Application get() {
        return instance;
    }
}
