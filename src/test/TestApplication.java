package test;

import org.lwjgl.opengl.GL11;

import jx3d.desktop.GlfwDisplay;
import jx3d.graphics.IndexBuffer;
import jx3d.graphics.VertexArray;
import jx3d.graphics.VertexBuffer;

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
		display = new GlfwDisplay("Test Application", 640, 480);
		display.setVisible(true);
	}
	
	
	

	public static void main(String[] args) {
		new TestApplication();
		
	}

}
