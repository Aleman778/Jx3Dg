package jx3d.platform.lwjgl3;

import jx3d.core.*;
import jx3d.core.Module;
import jx3d.graphics.Context;
import jx3d.graphics.Context.RenderAPI;
import jx3d.graphics.Graphics;
import jx3d.graphics.opengl.GLContext;
import jx3d.graphics.opengl.GLGraphics;
import jx3d.io.Files;
import jx3d.io.Input;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GLUtil;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Lightweight Java Game Library (LWJGL3) Application is an implementation that supports most common desktop
 * platform operating systems such as <code>Windows</code>, <code>macOS</code> and <code>Linux</code>. LWJGL3 is a collection of different open
 * source projects and libraries, is has support for rendering with <code>OpenGL</code> and <code>Vulkan</code>.
 * The window library used by LWJGL3 is <code>GLFW</code> and supports all the above mentioned platforms and rendering APIs.
 */
public final class Lwjgl3Application extends Application {

    private Lwjgl3Window mainWindow;

    private ArrayList<Lwjgl3Window> windows;

    private Graphics graphics;
    private Files files;
    private Input input;

    public Lwjgl3Application(Lwjgl3Configurations config, ApplicationListener listener) {
        super(listener);

        initializeGlfw();
        if (config.title == null) {
            config.title = listener.getClass().getSimpleName();
        }

        mainWindow = new Lwjgl3Window(config);
        files = new Lwjgl3Files();

        setupGraphics(config);

        JX3D.graphics = graphics;
        JX3D.files = files;

    }

    private void setupGraphics(Lwjgl3Configurations config) {
        boolean debug = false;
        switch (config.renderer) {
            case Module.OPENGL_DEBUG:
                debug = true;
            case Module.OPENGL: case Module.DEFAULT:
                new Lwjgl3Context(RenderAPI.OPENGL, config.forwardCompatible, mainWindow.getObject());

                if (GLContext.hasGL30()) {
                    JX3D.gl30 = new Lwjgl3GL30();
                    JX3D.gl20 = JX3D.gl30;
                } else {
                    JX3D.gl30 = null;
                    JX3D.gl20 = new Lwjgl3GL20();
                }

                graphics = new GLGraphics(mainWindow);

                if (debug) {
                    GLUtil.setupDebugMessageCallback(System.err);
                    Log.CORE.info("OpenGL running in debug mode.");
                }
                break;
        }

        graphics.init();
    }

    @Override
    public void run() {
        listener.onStart();

        mainWindow.setVisible(true);
        while (!mainWindow.shouldClose()) {

            listener.onUpdate();
            for (Layer layer : layerStack) {
                layer.onUpdate();
            }

            mainWindow.swapBuffers();
            mainWindow.pollEvents();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Files getFiles() {
        return files;
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
