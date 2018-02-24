package jx3d.core;

import jx3d.util.Disposable;
import jx3d.core.Screen;

/**
 * Abstract display interface provides required methods for working
 * with different display implementations.
 * 
 * @since 1.0
 * @author Aleman778
 */
public abstract class Display implements Disposable {
	
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
}
