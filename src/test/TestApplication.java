package test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import jx3d.desktop.GlfwDisplay;
import jx3d.desktop.LwjglGL30;
import jx3d.graphics.*;
import jx3d.graphics.opengl.*;
import jx3d.math.Matrix33;
import jx3d.math.Matrix44;
import jx3d.math.Quaternion;
import jx3d.math.Transform;
import jx3d.math.Transform2D;
import jx3d.math.Vector2D;
import jx3d.math.Vector3D;

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
	private Transform t;
	private Transform2D t2d;
	
	public TestApplication(String title, int width, int height) {
		super(title, width, height);
	}
	
	@Override
	public void setup() {
		float[] vertices = {
				0,   0,   0, 0,
				418, 0,   1, 0,
				418, 418, 1, 1,
				0,   418, 0, 1
		};
//		float[] vertices = {
//					-1.0f, -1.0f, 0.0f, 0.0f,
//					 1.0f, -1.0f, 4.0f, 0.0f,
//					 1.0f,  1.0f, 4.0f, 3.0f,
//					-1.0f,  1.0f, 0.0f, 3.0f
//		};
		
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
		
		
		//Transformation in 2D
		t2d = new Transform2D();
		//t2d.translate(new Vector2D(618, 418));
		t2d.setFix(new Vector2D(209, 209));
		t2d.rotate(45);
		System.out.println(t2d.getMapping());
		
		
		//Transformation
		t = new Transform();
		t.translate(new Vector3D(120, 120, 0));
		
		//Perspective matrix
		Matrix44 perspective = new Matrix44();
		perspective = perspective.perspective(90, getAspectRatio(), -1, 1000);
		
		//Orthographic matrix
		Matrix44 ortho = new Matrix44();
		ortho = ortho.orthographic(0, getWidth(), getHeight(), 0, -1000, 1000);
		
		//shader.set("projection", perspective);
		shader.set("projection", ortho);
		graphics.viewport(0, 0, getWidth(), getHeight());
		
		//APPLY TRANSFORMATION
//		shader.set("transform", t.getMapping());
		shader.set("transform", toMatrix44(t2d.getMapping())); //2d mapping
		
		
	}
	
	public Matrix44 toMatrix44(Matrix33 mat) {
		Matrix44 result = new Matrix44();
		result.m00 = mat.m00;
		result.m01 = mat.m01;
		result.m10 = mat.m10;
		result.m11 = mat.m11;
		result.m20 = mat.m20;
		result.m21 = mat.m21;
		result.m22 = mat.m22;
		result.m03 = mat.m02;
		result.m13 = mat.m12;
		return result;
	}
	
	float x = 0;

	@Override
	public void draw() {
		float scl = (float) Math.sin(x) * 2.0f;

		Matrix44 ortho = new Matrix44().orthographic(0, getWidth(), getHeight(), 0, -1000, 1000);
				
	
		x += 0.01f;
		t.setScale(new Vector3D(scl, scl / 2.0f, 1));
		t.rotate(Quaternion.euler(0.03f, 0.01f, 0.04f));
		t.setPos(new Vector3D(getWidth() / 2, getHeight() / 2, 0));
		//t.validate();
		//shader.set("transform", t.getMapping());
		//shader.set("transform", new Matrix44().translate(100, 200, 0));
		
		t2d.rotate(0.01f);
		
		shader.set("projection", ortho);
		shader.set("transform", t.getMapping());


		graphics.background(0.0f, 0.0f, 0.0f, 1.0f);
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
