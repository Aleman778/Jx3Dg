package jx3d.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class IOSystem {

	/**
	 * Creates a buffered reader object that can pull data
	 * from the specified file.
	 * The method can throw an {@link IOException} if something fails,
	 * e.g. A {@link FileNotFoundException} should be thrown if the
	 * file was not found.
	 * @param file the file to create a reader buffer from
	 * @returna new buffered reader that is linked to the specified file
	 */
	public abstract BufferedReader createReader(String file) throws IOException;

	/**
	 * Creates a buffered writer object that can modify the specified file.
	 * The method can throw an {@link IOException} if something fails,
	 * e.g. A {@link FileNotFoundException} should be thrown if the
	 * file was not found.
	 * @param file the file to create a reader buffer from
	 * @returna a new buffered reader that is linked to the specified file
	 */
	public abstract BufferedWriter createWriter(String file) throws IOException;
	
	/**
	 * Create an input stream to a specific file.
	 * @param file the file locator string, can be an URL,
	 * 			   relative or absolute file path
	 * @return a new input stream
	 */
	public abstract InputStream createInput(String file) throws IOException;

	/**
	 * Create an output stream to a specific file.
	 * @param file the file locator string, cannot be an URL,
	 * 			   but can be either a relative or absolute file path
	 * @return a new input stream
	 */
	public abstract OutputStream createOutput(String file) throws IOException;
	
	/**
	 * Get a file handle from a local directory.
	 * Local files are located in you project folder,
	 * e.g. <br><code>res/images/test.png</code>.
	 * @param file the local file path
	 * @return a new file handle to the requested file
	 */
	public abstract FileHandle local(String file) throws IOException;

	/**
	 * Get a file handle from a external directory.
	 * External files are located outside the project directory.
	 * e.g. <br><code>C:/Users/name/Documents/test.png</code>.<br>
	 * <i>Note:</i> an external file path is may not be
	 * a valid path on different machines.
	 * @param file the external file path
	 * @return a new file handle to the requested file
	 */
	public abstract FileHandle external(String file) throws IOException;
}
