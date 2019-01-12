package jx3d.io;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import jx3d.graphics.Image;
import jx3d.graphics.Mesh;

/**
 * Files functions are used to load or save data for a general file system.
 * Different operating systems may differ in how their file systems are
 * implemented and therefore this class should be overridden to 
 * properly access the file system.
 * @since 1.0
 * @author Aleman778
 */
public interface Files {

	/**
	 * Load bytes from the specific file.
	 * @param file the file to load from
	 * @return a new byte array holding the result
	 */
	public byte[] loadBytes(String file);
	
	/**
	 * Save bytes onto the specific file.
	 * @param file the file to save to
	 * @param bytes the byte array data to store
	 * @return true if the data was saved successfully, false otherwise
	 */
	public boolean saveBytes(String file, byte[] bytes);
	
	/**
	 * Load text from the specified file.
	 * @param file the file to load from
	 * @return a new string holding the contents of the file
	 */
	public String loadText(String file);
	
	/**
	 * Save text onto the specified file.
	 * @param file the file to save to
	 * @param text the text to store
	 * @return true if the data was saved successfully, false otherwise
	 */
	public boolean saveText(String file, String text);
	
	/**
	 * Load strings from the specific file.
	 * @param file the file to load from
	 * @return a new string array holding the result
	 */
	public String[] loadStrings(String file);
	
	/**
	 * Save strings onto the specific file.
	 * @param file the file to save to
	 * @param strings the string array to save
	 * @return true if the data was saved successfully, false otherwise
	 */
	public boolean saveStrings(String file, String[] strings);

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
	
	/**
	 * Select a folder using the operating systems internal window UI.
	 * <i>Note:</i> this function is not supported on all platforms.
	 * @param title the title of the folder select window
	 * @param current the folder to start with
	 * @param filter filters
	 * @return a new file object holding the path to the requested folder
	 */
	public File selectFolder(String title, String current, String filter);
	
	/**
	 * Select a file using the operating systems internal window UI.
	 * <i>Note:</i> this function is not supported on all platforms.
	 * @param title the title of the file select window
	 * @param current the file to start with
	 * @param action the type of action e.g. saving, loading
	 * @param filter filter which files to select from
	 * @return a new file object holding the path to the requested file
	 */
	public File selectFile(String title, String current, int action, String filter);
	
	/**
	 * Create an input stream to a specific file.
	 * @param file the file locator string, can be an URL,
	 * 			   relative or absolute file path
	 * @return a new input stream
	 */
	public abstract InputStream createInput(String file);

	/**
	 * Create an output stream to a specific file.
	 * @param file the file locator string, cannot be an URL,
	 * 			   but can be either a relative or absolute file path
	 * @return a new input stream
	 */
	public abstract OutputStream createOutput(String file);
	
	/**
	 * Get a file handle from a local directory.
	 * Local files are located in you project folder,
	 * e.g. <br><code>res/images/test.png</code>.
	 * @param file the local file path
	 * @return a new file handle to the requested file
	 */
	public abstract FileHandle local(String file);

	/**
	 * Get a file handle from a external directory.
	 * External files are located outside the project directory.
	 * e.g. <br><code>C:/Users/name/Documents/test.png</code>.<br>
	 * <i>Note:</i> an external file path is may not be
	 * a valid path on different machines.
	 * @param file the external file path
	 * @return a new file handle to the requested file
	 */
	public abstract FileHandle external(String file);
}
