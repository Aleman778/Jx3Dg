package jx3d.io;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

	public static byte[] toByteArray(final InputStream input) {
		try {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			
			int count = input.read(buffer);
			while (count != -1) {
				output.write(buffer, 0, count);
				count = input.read(buffer);
			}
			output.flush();
			return output.toByteArray();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Image toImage(final InputStream input) {
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
