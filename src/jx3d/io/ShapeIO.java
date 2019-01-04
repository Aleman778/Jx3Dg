package jx3d.io;

import jx3d.graphics.Mesh;

/**
 * Shape IO is an abstract class with functions
 * that are used to save and load shapes.
 * @since 1.0
 * @author Aleman778
 */
public abstract class ShapeIO {

	/**
	 * Reference to the system io used.
	 */
	protected SystemIO sys;
	
	/**
	 * Load shape from the specific file.
	 * @param file the file to load from.
	 * @return a new shape holding the result
	 */
	public abstract Mesh loadShape(String file);
	
	/**
	 * Save shape from the specified file.
	 * @param file the file to save to
	 * @param shape the shape to save
	 * @return true if the data was saved successfully, false otherwise
	 */
	public abstract boolean saveShape(String file, Mesh shape);
}
