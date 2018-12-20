package jx3d.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;

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
	
	
	/*
	 * Maybe abstract this code below, some platforms might not support these methods.
	 */
	public BufferedReader createReader(String file) {
		InputStream inputStream = createInput(file);
		InputStreamReader inputReader = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(inputReader);
		return reader;
	}
	
	public InputStream createInput(String file) {
		if (file == null)
			return null;
		
		if (file.isEmpty())
			return null;
		
		//HTTP URL file
		InputStream input = null;
		if (file.contains("http://") || file.contains("https://"))
			input = inputFromURL(file);
		
		//Project resource
		if (input == null)
			input = inputFromResource(file);
		
		//Local directory
		if (input == null)
			input = local(file).toInputStream();
		
		//External directory
		if (input == null)
			input = external(file).toInputStream();
		
		return input;
	}
	
	public OutputStream createOutput(String file) {
		return null;
	}
	
	protected InputStream inputFromURL(String file) {
		try {
			URL url = new URL(file);
			URLConnection connection = url.openConnection();
			
			if (connection instanceof HttpURLConnection) {
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				httpConnection.setInstanceFollowRedirects(true);
				
				int response = httpConnection.getResponseCode();
				if (response >= 300 && response < 400) {
					inputFromURL(httpConnection.getHeaderField("Location"));
				}
				
				return httpConnection.getInputStream();
			} else if (connection instanceof JarURLConnection) {
				return url.openStream();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	protected InputStream inputFromResource(String file) {
		ClassLoader loader = getClass().getClassLoader();
		InputStream input = loader.getResourceAsStream(file);
		if (input != null) {
			String clss = input.getClass().getName();
			if (!clss.equals("sun.plugin.cache.EmptyInputStream")) {
				return input;
			}
		}
		return null;
	}

	protected OutputStream outputStream(String file) {
		if (file == null)
			return null;
		
		if (file.isEmpty())
			return null;
		
		OutputStream output = null;
		
		//Local directory
		if (output == null)
			output = local(file).toOutputStream();
		
		//External directory
		if (output == null)
			output = external(file).toOutputStream();
		
		return output;
	}
	
	public abstract FileHandle local(String file);
	
	public abstract FileHandle external(String file);
}
