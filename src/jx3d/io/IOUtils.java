package jx3d.io;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import jx3d.graphics.Image;

/**
 * IO Utils class has simple static utility functions that
 * are implemented using the standard edition of Java.
 * @since 1.0
 * @author Aleman778
 */
public class IOUtils {

	/**
	 * Static class.
	 */
	private IOUtils() { }

	public static byte[] loadBytes(final InputStream input) {
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			
			int count = input.read(buffer);
			while (count != -1) {
				output.write(buffer, 0, count);
				count = input.read(buffer);
			}
			
			output.flush();
			
			byte[] result = output.toByteArray();
			
			output.close();
			input.close();
			
			return result;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean saveBytes(final OutputStream output, byte[] bytes) {
		try {
			output.write(bytes);
			output.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static String loadText(final InputStream input) {
		try {
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader reader = new BufferedReader(isr);

			String line = reader.readLine();
			String result = "";
			while (line != null) {
				result += line;
				line = reader.readLine();
				if (line != null)
					result += "\n";
			}
			
			reader.close();
			isr.close();
			input.close();
			
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static boolean saveText(final OutputStream output, String text) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(output);
			BufferedWriter writer = new BufferedWriter(osw);
			
			writer.write(text);
			
			writer.close();
			osw.close();
			output.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static String[] loadStrings(final InputStream input) {
		try {
			InputStreamReader isr = new InputStreamReader(input);
			BufferedReader reader = new BufferedReader(isr);
			ArrayList<String> output = new ArrayList<>();
			
			String line;
			while ((line = reader.readLine()) != null) {
				output.add(line);
			}
			
			reader.close();
			isr.close();
			input.close();
			
			String[] result = new String[output.size()];
			output.toArray(result);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static boolean saveStrings(final OutputStream output, String[] strings) {
		try {
			OutputStreamWriter osw = new OutputStreamWriter(output);
			BufferedWriter writer = new BufferedWriter(osw);
			
			for (int i = 0; i < strings.length; i++) {
				writer.write(strings[i]);
				if (i < strings.length - 1)
					writer.newLine();
			}
			
			writer.close();
			osw.close();
			output.close();
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static Image loadImage(final InputStream input) {
		try {
			BufferedImage bufimg = javax.imageio.ImageIO.read(input);
			Image image = new Image(bufimg.getWidth(), bufimg.getHeight());
			
			int[] pixels = image.getPixels();
			bufimg.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
			
	    	for (int i = 0; i < image.getWidth() * image.getHeight(); i++) {
	            int a = (pixels[i] & 0xFF000000) >> 24;
	            int r = (pixels[i] & 0xFF0000) >> 16;
	            int g = (pixels[i] & 0xFF00) >> 8;
	            int b = (pixels[i] & 0xFF);
	
	            pixels[i] = a << 24 | b << 16 | g << 8 | r;
	        }
	    	return image;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
