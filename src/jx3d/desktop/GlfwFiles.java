package jx3d.desktop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import jx3d.core.Display;
import jx3d.core.Files;

public class GlfwFiles extends Files {

	public GlfwFiles(Display display) {
		super(display);
	}

	@Override
	public String load(String file) {
		String contents = "";
		try {
			InputStreamReader isr = new InputStreamReader(inputStream(file));
			BufferedReader br = new BufferedReader(isr);
			String line;
			while ((line = br.readLine()) != null) {
				contents += line + "\n";
			}			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return contents;
	}

	@Override
	public InputStream inputStream(String file) {
		if (file == null)
			return null;
		if (file.isEmpty())
			return null;
		
		if (filenme.contains("http//:"))
			
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
					inputFromHTTP(httpConnection.getHeaderField("Location"));
				}
				
				return httpConnection.getInputStream();
			} else if (connection instanceof JarURLConnection) {
				return url.openStream();
			}
		} catch (MalformedURLException | FileNotFoundException ex) { }
		
		return null;
	}
	
	private InputStream inputFromProject() {
		
	}

}
