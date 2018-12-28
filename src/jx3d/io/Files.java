package jx3d.io;

import java.io.File;

import jx3d.graphics.Mesh;

/**
 * Files functions are used to load or save data for a general file system.
 * Different operating systems may differ in how their file systems are
 * implemented and therefore this class should be overridden to 
 * properly access the file system.
 * @since 1.0
 * @author Aleman778
 */
public abstract class Files {
	
	/**
	 * Reference to the io system to use.
	 */
	public final IOSystem io;
	
	/**
	 * Constructor.
	 * @param io the io system to use
	 */
	public Files(IOSystem io) {
		this.io = io;
	}

	/**
	 * Load bytes from the specific file.
	 * @param file the file to load from
	 * @return a new byte array holding the result
	 */
	public abstract byte[] loadBytes(String file);
	
	/**
	 * Save bytes onto the specific file.
	 * @param file the file to save to
	 * @param bytes the byte array data to store
	 * @return true if the data was saved successfully, false otherwise
	 */
	public abstract boolean saveBytes(String file, byte[] bytes);
	
	/**
	 * Load text from the specified file.
	 * @param file the file to load from
	 * @return a new string holding the contents of the file
	 */
	public abstract String loadText(String file);
	
	/**
	 * Save text onto the specified file.
	 * @param file the file to save to
	 * @param text the text to store
	 * @return true if the data was saved successfully, false otherwise
	 */
	public abstract boolean saveText(String file, String text);
	
	/**
	 * Load strings from the specific file.
	 * @param file the file to load from
	 * @return a new string array holding the result
	 */
	public abstract String[] loadStrings(String file);
	
	/**
	 * Save strings onto the specific file.
	 * @param file the file to save to
	 * @param strings the string array to save
	 * @return true if the data was saved successfully, false otherwise
	 */
	public abstract boolean saveStrings(String file, String[] strings);
	
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
	
	public abstract File selectFolder(String title, String current, String filter);
	
	public abstract File selectFile(String title, String current, int action, String filter);

}
