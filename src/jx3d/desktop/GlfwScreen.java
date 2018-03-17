package jx3d.desktop;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWVidMode;

import jx3d.core.Screen;

/**
 * Screen class designed specifically for use with GLFW displays.
 * @since 1.0
 * @author Aleman778
 * @see GlfwDisplay
 */
public class GlfwScreen implements Screen {

	private GLFWVidMode mode;
	private long monitor;
	
	/**
	 * Constructor.
	 * @param monitor the monitor reference
	 */
	public GlfwScreen(long monitor) {
		this.mode = glfwGetVideoMode(monitor);
		this.monitor = monitor;
	}

	@Override
	public int getWidth() {
		return mode.width();
	}

	@Override
	public int getHeight() {
		return mode.height();
	}

	@Override
	public int getRedBits() {
		return mode.redBits();
	}

	@Override
	public int getGreenBits() {
		return mode.greenBits();
	}

	@Override
	public int getBlueBits() {
		return mode.blueBits();
	}

	@Override
	public double getRefreshRate() {
		return mode.refreshRate();
	}
	
	/**
	 * Get the GLFW monitor reference.
	 * @return a GLFW long memory address pointer
	 */
	public long getMonitor() {
		return monitor;
	}
	
	@Override
	public String toString() {
		return glfwGetMonitorName(monitor);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GlfwScreen) {
			GlfwScreen screen = (GlfwScreen) obj;
			return (getMonitor() == screen.getMonitor());
		}
		
		return false; 
	}
}