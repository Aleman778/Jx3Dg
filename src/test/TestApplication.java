package test;

import jx3d.desktop.GlfwDisplay;
import jx3d.graphics.IndexBuffer;
import jx3d.graphics.VertexArray;
import jx3d.graphics.VertexBuffer;

import static jx3d.core.Constants.*;

/**
 * Test application.
 * @author Aleman778
 */
public class TestApplication {
	
	private GlfwDisplay display;
	private VertexBuffer vbo;
	private IndexBuffer ibo;
	private VertexArray vao;
	
	public TestApplication() {
		display = new GlfwDisplay("jx3D Application", 640, 480);
		display.setRenderer(OPENGL, GL20_PROFILE); 
		display.setVisible(true);
	}
	
	
	

	public static void main(String[] args) {
		new TestApplication();
		
	}

}
