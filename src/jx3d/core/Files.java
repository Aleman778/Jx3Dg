package jx3d.core;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Abstract class Files includes methods that will
 * help with reading and writing to files.
 * There are a few basic functions to use:
 * <ul>
 * 		<li>
 * 			<code>open(access, file)</code> is used to grant
 * 			access to read, write or both to the file.
 * 		</li>
 * </ul>
 * @since 1.0
 * @author Aleman778
 * 
 */
public abstract class Files {

	/**
	 * The window owner.
	 */
	protected final Window window;
	
	/**
	 * Constructor.
	 * Creates a new file handler object.
	 * @param window the window owner.
	 */
	public Files(Window window) {
		this.window = window;
	}
	
	/**
	 * Opens file from the provided file source,
	 * the file can be located in the project
	 * folder, local folder or on the Internet.<br>
	 * Supported syntax:
	 * <ul>
	 * 	<li>
	 * 		File from within the project folder,<br>
	 * 		e.g. <code>load("res/example/image.jpg");</code>.
	 * 		<i>Note:</i> files in project folder is generally
	 * 		read-only.
	 * 	</li>
	 *  <li>
	 *  	File from local folder,<br>
	 *  	e.g. <code>load("user/documents/image.jpg"</code><br>
	 *  	<i>Note:</i> this method of loading files may not
	 *  	work properly for other users since they could have
	 *  	different file systems.
	 *  </li>
	 *  <li>
	 *  	File accessed from the Internet using the following
	 * 		protocols, <code>http</code> or <code>https</code>,<br>
	 *  	e.g. <code>load("http://example.com/image.jpg");</code>.
	 *  	<i>Note:</i> files in project folder is generally
	 * 		read-only.
	 *  </li>
	 * </ul>
	 * @param access read or write access, use constants <code>READ</code> or <code>WRITE</code>
	 * @param file the name/ address of the file
	 * @return true if the opening is successful, false otherwise
	 */
	public abstract boolean open(int access, String file);

	/**
	 * Closes the currently opened file.
	 * <i>Note:<i> this may be necessary for write operations on
	 * some file I/O systems, e.g. javas built in BufferWriter 
	 * requires the file to be closed after writing.
	 */
	public abstract void close();
	
	/**
	 * Reads the contents of the currently opened file.
	 * <i>Note:</i> the file has to been opened with <code>READ</code> access.
	 * @throws IllegalStateException if the file is not opened with read access
	 * @return the contents of the file
	 */
	public abstract String read();

	/**
	 * Reads a line of the currently opened file. When reaching the
	 * end of the file, null is returned.
	 * <i>Note:</i> the file has to been opened with <code>READ</code> access.
	 * @throws IllegalStateException if the file is not opened with read access
	 * @return the contents of the file
	 */
	public abstract String readln();
	
	/**
	 * Writes text to file via the provided file destination,
	 * the file can only be written from either the project
	 * project directory or local folder.
	 * @param data the data to write
	 * @return true if the write was successful, false otherwise
	 */
	public abstract boolean write(String data);
	
	/**
	 * Create a new input stream to the file
	 * accessed by the provided source name.
	 * @param file the name/ address of the file
	 * @return a new input stream to the file
	 * @see Files#load(String)
	 */
	public abstract InputStream inputStream(String file);

	/**
	 * Create a new output stream to the file
	 * accessed by the provided source name.
	 * @param file the name/ address of the file
	 * @return a new input stream to the file
	 * @see Files#write(String)
	 */
	public abstract OutputStream outputStream(String file);
	
	/**
	 * Gives the file handle to the provided filename
	 * in the external directory.
	 * @param file the file name to 
	 * @return the file handle of the file
	 */
	public abstract FileHandle external(String file);

	/**
	 * Gives the file handle to the provided filename
	 * in the local directory.
	 * @param file the file name to 
	 * @return the file handle of the file
	 */
	public abstract FileHandle local(String file);
}
