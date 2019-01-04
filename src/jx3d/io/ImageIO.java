package jx3d.io;

import jx3d.graphics.Image;

/**
 * Image IO is an abstract class with functions
 * that are used to save and load images.
 * @since 1.0
 * @author Aleman778
 */
public abstract class ImageIO {
	
	/**
	 * Reference to the system io used.
	 */
	protected SystemIO sys;
	
	/**
	 * Load an image from the specific image file.
	 * @param file the image file to load from 
	 * @return a new image holding the result
	 */
	public abstract Image loadImage(String file);
	
	/**
	 * Save an image onto the specific image file
	 * @param file the image file to save to
	 * @param image the image to save
	 * @return true if the image was saved successfully, false otherwise
	 */
	public abstract boolean saveImage(String file, Image image);

}
