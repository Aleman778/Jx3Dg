package jx3d.io;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import jx3d.graphics.Image;

/**
 * 
 * @since 1.0
 * @author Aleman778
 */
public class JavaImageIO extends ImageIO {

	@Override
	public Image loadImage(String file) {
		try {
			InputStream input = sys.createInput(file);
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

	@Override
	public boolean saveImage(String file, Image image) {
		return false;
	}

}
