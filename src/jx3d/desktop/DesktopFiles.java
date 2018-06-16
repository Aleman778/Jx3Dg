package jx3d.desktop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;

import jx3d.core.Constants;
import jx3d.core.Display;
import jx3d.core.FileHandle;
import jx3d.core.Files;

public class DesktopFiles extends Files {

	//public static final String EXTERNAL_DIR = new File("").getPath() + File.separator;
	public static final String LOCAL_DIR = new File("").getAbsolutePath() + File.separator;

	private BufferedReader reader;
	private BufferedWriter writer;
	
	public DesktopFiles(Display display) {
		super(display);
	}

	@Override
	public boolean open(int access, String file) {
		close();
		reader = null;
		writer = null;
		
		//Reader
		if (access == Constants.READ) {
			InputStream input = inputStream(file);
			if (input == null)
				return false;

			InputStreamReader isr = new InputStreamReader(input);
			reader = new BufferedReader(isr);
			try {
				reader.mark(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (access == Constants.WRITE) {
			OutputStream output = outputStream(file);
			if (output == null)
				return false;
			
			OutputStreamWriter osw = new OutputStreamWriter(output);
			writer = new BufferedWriter(osw);
		}
		
		return true;
	}
	
	@Override
	public void close() {
		try {
			if (reader != null)
				reader.close();
			if (writer != null)
				writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String read() {
		if (reader == null)
			throw new IllegalStateException("There is no opened reader to use.");

		try {
			reader.reset();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String contents = "";
		try {
			String line;
			while ((line = reader.readLine()) != null) {
				contents += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return contents;
	}
	
	@Override
	public String readln() {
		return null;
	}
	
	@Override
	public boolean write(String data) {
		if (writer == null)
			throw new IllegalStateException("There is no opened writer to use.");
		
		try {
			writer.write(data);
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public InputStream inputStream(String file) {
		if (file == null)
			return null;
		
		if (file.isEmpty())
			return null;
		
		//HTTP URL file
		InputStream input = null;
		if (file.contains("http//:") || file.contains("https//:"))
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
	
	private InputStream inputFromURL(String file) {
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
	
	private InputStream inputFromResource(String file) {
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

	@Override
	public OutputStream outputStream(String file) {
		if (file == null)
			return null;
		
		if (file.isEmpty())
			return null;
		
		//HTTP URL file
		OutputStream output = null;
		
		//Local directory
		if (output == null)
			output = local(file).toOutputStream();
		
		//External directory
		if (output == null)
			output = external(file).toOutputStream();
		
		return output;
	}
	
	@Override
	public FileHandle external(String file) {
		return new FileHandle(file);
	}

	@Override
	public FileHandle local(String file) {
		return new FileHandle(LOCAL_DIR + file);
	}
}
