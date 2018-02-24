package jx3d.core;

/**
 * Abstract display interface provides required methods for working
 * with different display implementations.
 * 
 * @since 1.0
 * @author Aleman778
 */
public abstract class Display {
	
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
}
