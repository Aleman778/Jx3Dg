package jx3d.core;

import jx3d.core.Screen;
import jx3d.graphics.Graphics;
import jx3d.io.Files;
import jx3d.io.Input;
import jx3d.util.Disposable;

/**
 * Abstract display interface provides required methods for working
 * with different display implementations. The window is always the root
 * of the scene graph.
 * @since 1.0
 * @author Aleman778
 */
public abstract class Window extends Module implements Disposable {

	/**
	 * The handle to the rendering engine that is being used by this window.
	 * @see #window
	 */
	public Graphics graphics;
	
	/**
	 * The handle to the file I/O handler that is being used by this window.
	 * @see #window
	 */
	public Files files;
	
	/**
	 * The handle to the input handler that is being used by this window.
	 * @see #window
	 */
	public Input input;
	
	/**
	 * The root node that this window uses.
	 */
	private Node root = new Node() { };
	
	/**
	 * Constructor.
	 */
	public Window() {
		root.setName("Root Node");
		root.window = this;
	}
	
	
	public final void add(Node node) {
		root.add(node);
	}
	
	public final void remove(Node node) {
		
	}
	
	
	/**
	 * Get the title of the display.
	 * @return the title of the display
	 */
	public abstract String getTitle();
	
	/**
	 * Get the width (in pixels) of the display.
	 * @return the width of the display
	 */
	public abstract int getWidth();
	
	/**
	 * Get the height (in pixels) of the display.
	 * @return the height of the display
	 */
	public abstract int getHeight();
	
	/**
	 * Set the rendering engine of the display.
	 * This method can also accept an optional profile
	 * parameter, see {@link #setRenderer(int, int)}.
	 * @param renderer the rendering engine constant
	 */
	public abstract void setRenderer(int renderer);
	
	/**
	 * Set the rendering engine of the display with a
	 * specific profile. By using this function you are
	 * explicitly setting the graphics requirements, so
	 * if a user does not support the requirements then 
	 * the application will be either glitchy or will not run at all.
	 * Thus you are recommended to use this function
	 * {@link #setRenderer(int)} instead as it automatically sets
	 * the appropriate requirements for the users systems.
	 * @param renderer the rendering engine constant
	 * @param profile an optional parameter that specifies
	 * 		a version number and/ or other settings for the
	 * 		provided renderer
	 * @see #setRenderer(int)
	 */
	public abstract void setRenderer(int renderer, int profile);
	
	/**
	 * Get the aspect ratio (width / height) of the display
	 * @return the aspect ratio of the display
	 */
	public final float getAspectRatio() {
		return (float) getWidth() / (float) getHeight();
	}

	/**
	 * Get the primary screen information object.
	 * @return primary screen
	 */
	public abstract Screen getScreen();
	
	/**
	 * Get an array of all the active screens information object.
	 * @return all active screens
	 */
	public abstract Screen[] getScreens();
	
	/**
	 * Checks if the display should close.
	 * @return true if the display should close
	 */
	public abstract boolean shouldClose();
}
