package test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jx3d.desktop.GlfwDisplay;
import jx3d.desktop.LwjglGL30;
import jx3d.graphics.*;
import jx3d.graphics.opengl.*;
/**
 * Test application.
 * @author Aleman778
 */
public class TestApplication extends GlfwDisplay {
	
	private VertexBuffer vbo;
	private IndexBuffer ibo;
	private VertexArray vao;
	private Shader shader;
	private Image image;
	private Texture2D tex;
	
	public TestApplication(String title, int width, int height) {
		super(title, width, height);
	}
	
	@Override
	public void setup() {
//		float[] vertices = {
//				-0.5f, -0.5f, 0.0f, 0.0f,
//				 0.5f, -0.5f, 1.0f, 0.0f,
//				 0.5f,  0.5f, 1.0f, 1.0f,
//				-0.5f,  0.5f, 0.0f, 1.0f
//		};
		float[] vertices = {
					-1.0f, -1.0f, 0.0f, 0.0f,
					 1.0f, -1.0f, 4.0f, 0.0f,
					 1.0f,  1.0f, 4.0f, 3.0f,
					-1.0f,  1.0f, 0.0f, 3.0f
				};
		
		short[] indices = {
			0, 1, 3,
			1, 2, 3
		};
		vbo = graphics.createVBO(STATIC_DRAW);
		vbo.bind();
		vbo.insert(vertices, 0);
		
		ibo = graphics.createIBO(6, STATIC_DRAW);
		ibo.bind();
		ibo.insert(indices, 0);
		
		vao = graphics.createVAO();
		
		AttributeMap attribs = new AttributeMap();
		attribs.putPosition(2);
		attribs.putTexcoord(2);
		vao.put(vbo, attribs);

		shader = graphics.loadShader("test/shaders/basic_fragment.glsl",
									 "test/shaders/basic_vertex.glsl");
		shader.setup();
		shader.enable();
		String filename = "test/textures/tex_brick.jpg";
		InputStream input = display.files.inputStream(filename);
		
		
		
		
		if (input == null)
			throw new RuntimeException("Image file: " + filename + " could not be found.");
		
		try {
			BufferedImage bufimg = ImageIO.read(input);
			image = new Image(bufimg.getWidth(), bufimg.getHeight());
			
			int[] pixels = image.getPixels();
			bufimg.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
			
	    	for (int i = 0; i < image.getWidth() * image.getHeight(); i++) {
	            int a = (pixels[i] & 0xFF000000) >> 24;
	            int r = (pixels[i] & 0xFF0000) >> 16;
	            int g = (pixels[i] & 0xFF00) >> 8;
	            int b = (pixels[i] & 0xFF);
	
	            pixels[i] = a << 24 | b << 16 | g << 8 | r;
	            
	        }
		} catch (IOException e) {
			throw new IllegalArgumentException("image " + filename + " is not found.");
		}

		GL30 gl = new LwjglGL30();

		gl.activeTexture(0);
	
		shader.set("sampler", 0);

		tex = new GLTexture2D(gl, false, false);
		tex.image(image);
		tex.setSample(POINT);
		//tex.setWrapMode(REPEAT, S|T);
		//tex.generateMipmaps();
	}

	@Override
	public void draw() {
		graphics.background(0.0f, 0.5f, 1.0f, 1.0f);
		shader.enable();
		tex.bind();
		graphics.render(TRIANGLES, vao, ibo);

	}
	
	public static void main(String[] args) {
		TestApplication display = new TestApplication("jx3D Application", 1280, 720);
		display.setRenderer(OPENGL_DEBUG); 
		display.setVisible(true);
	}

}
