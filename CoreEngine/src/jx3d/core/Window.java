package jx3d.core;

import jx3d.io.Input;
import jx3d.util.Disposable;

/**
 * Abstract window interface provides required methods for working
 * with different windowing implementations.
 *
 * @author Aleman778
 * @since 1.0
 */
public abstract class Window implements Disposable, Input {

    /**
     * Constructor.
     * Creates an empty window.
     */
    public Window() {
    }

    /**
     * Get the title of the display.
     *
     * @return the title of the display
     */
    public abstract String getTitle();

    /**
     * Get the width (in pixels) of the display.
     *
     * @return the width of the display
     */
    public abstract int getWidth();

    /**
     * Get the height (in pixels) of the display.
     *
     * @return the height of the display
     */
    public abstract int getHeight();

    /**
     * Get the aspect ratio (width / height) of the display
     *
     * @return the aspect ratio of the display
     */
    public final float getAspectRatio() {
        return (float) getWidth() / (float) getHeight();
    }

    /**
     * Get the primary screen information object.
     *
     * @return primary screen
     */
    public abstract Screen getScreen();

    /**
     * Get an array of all the active screens information object.
     *
     * @return all active screens
     */
    public abstract Screen[] getAllScreens();

    /**
     * Checks if the display should close.
     *
     * @return true if the display should close
     */
    public abstract boolean shouldClose();
}
