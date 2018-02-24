package jx3d.desktop;

import jx3d.core.Display;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Desktop window implementation using the GLFW.
 * <h1>What is GLFW?</h1>
 * <b>GLFW</b> is an Open Source, multi-platform library
 * for OpenGL, OpenGL ES and Vulkan development on
 * the desktop. It provides a simple API for creating
 * windows, contexts and surfaces, receiving input and events.<br>
 * @since 1.0
 * @author Aleman778
 * @see <a href="http://www.glfw.org/">http://www.glfw.org/</a>
 */
public class GlfwDisplay extends Display {
	
	private static boolean initialized = false;
	
	private int width, height;
	private String title;
	
	/**
	 * Default constructor.
	 */
	public GlfwDisplay() {
		this("");
	}
	
	/**
	 * Constructor.
	 * @param title the title of the window
	 */
	public GlfwDisplay(String title) {
		this(title, 640, 480);
	}
	
	/**
	 * Constructor.
	 * @param width the width of the window
	 * @param height the height of the window
	 */
	public GlfwDisplay(int width, int height) {
		this("", width, height);
	}
	
	/**
	 * Constructor.
	 * @param title the title of the window
	 * @param width the width of the window
	 * @param height the height of the window
	 */
	public GlfwDisplay(String title, int width, int height) {
		initialized();
		
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public String getTitle() {
		return title;
	} 
	
	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	private static final void initialized() {
		if (!initialized) {
			if (glfwInit()) {
				initialized = true;
			} else {
				throw new IllegalStateException("The GLFW library failed to initialize.");
			}
		}
	}
}
