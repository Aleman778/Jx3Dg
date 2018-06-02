package jx3d.core;

import java.io.InputStream;

/**
 * Abstrac class that
 * @author alexa
 *
 */
public abstract class Files {

	protected final Display display;
	
	public Files(Display display) {
		this.display = display;
	}
	
	/**
	 * Load file from the provided file source,
	 * the file can be located in the project
	 * folder, local folder or on the Internet.<br>
	 * Supported syntax:
	 * <ul>
	 * 	<li>
	 * 		File from within the project folder,<br>
	 * 		e.g. <code>load("res/example/image.jpg");</code>.
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
	 *  </li>
	 * 
	 * </ul>
	 * @param file the name/ address of the file
	 * @return a new string containing the result.
	 */
	public abstract String load(String file);
	
	/**
	 * Craate a new input stream to the file
	 * accessed by the provided source name.
	 * @param file the name/ address of the file
	 * @return a new input stream to the file
	 * @see Files#load(String)
	 */
	public abstract InputStream inputStream(String file);
	
	/**
	 * Write text to file via the provided file destination,
	 * the file can only be written from either the project
	 * project directory or local folder.
	 * @param file the name of the file
	 * @return true if the write was successful, false otherwise
	 */
	public abstract boolean write(String file);
}
