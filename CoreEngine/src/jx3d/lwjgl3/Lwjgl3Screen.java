package jx3d.lwjgl3;

import jx3d.core.Screen;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.glfwGetMonitorName;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;

/**
 * Screen class designed specifically for use with GLFW displays.
 *
 * @author Aleman778
 * @see Lwjgl3Window
 * @since 1.0
 */
public class Lwjgl3Screen implements Screen {

    private GLFWVidMode mode;
    private long monitor;

    /**
     * Constructor.
     *
     * @param monitor the monitor reference
     */
    public Lwjgl3Screen(long monitor) {
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
     *
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
        if (obj instanceof Lwjgl3Screen) {
            Lwjgl3Screen screen = (Lwjgl3Screen) obj;
            return (getMonitor() == screen.getMonitor());
        }

        return false;
    }
}