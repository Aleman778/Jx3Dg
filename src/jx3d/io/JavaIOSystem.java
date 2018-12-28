package jx3d.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class JavaIOSystem extends IOSystem {

	public static final String LOCAL_DIR = new File("").getAbsolutePath() + File.separator;
	public static final String EXTERNAL_DIR = new File("").getPath() + File.separator;
	
	public BufferedReader createReader(String file) {
		InputStream inputStream = createInput(file);
		InputStreamReader inputReader = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(inputReader);
		return reader;
	}

	@Override
	public BufferedWriter createWriter(String file) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
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
	
	@Override
	public OutputStream createOutput(String file) {
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
	public FileHandle local(String file) {
		return new FileHandle(LOCAL_DIR + file);
	}
	
	@Override
	public FileHandle external(String file) {
		return new FileHandle(EXTERNAL_DIR + file);
	}
}
