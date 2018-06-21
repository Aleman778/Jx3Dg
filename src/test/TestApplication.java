package test;

import org.lwjgl.opengl.GL11;

import jx3d.desktop.GlfwDisplay;
import jx3d.graphics.AttributeMap;
import jx3d.graphics.IndexBuffer;
import jx3d.graphics.Shader;
import jx3d.graphics.VertexArray;
import jx3d.graphics.VertexBuffer;
import jx3d.graphics.opengl.GLException;
/**
 * Test application.
 * @author Aleman778
 */
public class TestApplication extends GlfwDisplay {
	
	private VertexBuffer vbo;
	private IndexBuffer ibo;
	private VertexArray vao;
	private Shader shader;
	
	public TestApplication(String title, int width, int height) {
		super(title, width, height);
	}
	
	@Override
	public void setup() {
		float[] vertices = {
			-0.5f, -0.5f,
			 0.5f, -0.5f,
			 0.5f,  0.5f,
			-0.5f,  0.5f
		};
		short[] indices = {
			0, 1, 2,
			2, 3, 0
		};
		vbo = graphics.createVBO(vertices, STATIC_DRAW);
		ibo = graphics.createIBO(indices, STATIC_DRAW);
		vao = graphics.createVAO();
		
		AttributeMap attribs = new AttributeMap();
		attribs.putPosition(2);
		vao.put(vbo, attribs);
		

		shader = graphics.loadShader("test/shaders/basic_fragment.glsl",
									 "test/shaders/basic_vertex.glsl");
		shader.setup();
	}

	@Override
	public void draw() {
		graphics.background(0.0f, 0.5f, 1.0f, 1.0f);
		shader.enable();
		graphics.render(TRIANGLES, vao, ibo);

//		int err = GL11.glGetError();
//		if (err > 0) {
//			throw new GLException(err);
//		}
	}
	
	public static void main(String[] args) {
		TestApplication display = new TestApplication("jx3D Application", 640, 480);
		display.setRenderer(OPENGL_DEBUG); 
		display.setVisible(true);
	}

}
