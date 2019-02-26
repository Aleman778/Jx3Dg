package jx3d.lwjgl3;

import jx3d.core.Application;
import jx3d.core.ApplicationListener;
import jx3d.graphics.Graphics;
import jx3d.io.Files;
import jx3d.io.Input;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public final class Lwjgl3Application extends Application {

    private Lwjgl3Window mainWindow;

    private ArrayList<Lwjgl3Window> windows;

    public Lwjgl3Application(Lwjgl3Configurations config, ApplicationListener listener) {
        super(listener);

        initializeGlfw();
        if (config.title == null) {
            config.title = getClass().getSimpleName();
        }

        mainWindow = new Lwjgl3Window(config);
    }

    @Override
    public void run() {
        while (!mainWindow.shouldClose()) {
            mainWindow.pollEvents();
            mainWindow.swapBuffers();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Files getFiles() {
        return null;
    }

    @Override
    public Input getInput() {
        return null;
    }


    /**
     * Initialize the window, has to be called before calling GLFW and is only performed once.
     */
    private static void initializeGlfw() {
        if (glfwInit()) {
            glfwSetErrorCallback(GLFWErrorCallback.createThrow());
        } else {
            throw new IllegalStateException("The GLFW library failed to initialize.");
        }
    }
}
