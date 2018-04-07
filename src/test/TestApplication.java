package test;

import org.lwjgl.opengl.GL11;

import jx3d.desktop.GlfwDisplay;

/**
 * Test application.
 * @author Aleman778
 */
public class TestApplication {
	
	private GlfwDisplay display;
	
	public TestApplication() {
		display = new GlfwDisplay("Test Application", 640, 480);
		display.setVisible(true);

		System.out.println(GL11.glGetString(GL11.GL_VERSION));
	}

	public static void main(String[] args) {
		new TestApplication();
		
	}

}
