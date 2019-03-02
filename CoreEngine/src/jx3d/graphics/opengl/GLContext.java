package jx3d.graphics.opengl;

import jx3d.graphics.Context;
import jx3d.lwjgl3.Lwjgl3Configurations;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;

/**
 * OpenGL Context.
 */
public class GLContext extends Context {

    /**
     * OpenGL major version.
     */
    private int majorVersion;

    /**
     * OpenGL minor version.
     */
    private int minorVersion;

    /**
     * Check if context forward compatible.
     * Forward compatible removes deprecated OpenGL functions in 3.0.
     */
    private boolean forwardCompat;

    /**
     * GLCapabilities instance.
     */
    private GLCapabilities glc;

    /**
     * Constructor create a GLContext.
     * This class should only be constructed using the static function
     * {@link Context#Create(RenderAPI, Object, Object)}.
     * @param config the application configurations
     * @param deviceContext the device context
     */
    public GLContext(Object config, Object deviceContext) {
        if (config instanceof Lwjgl3Configurations) {
            setupLwjgl3Context((Lwjgl3Configurations) config, (Long) deviceContext);
        } else {
            throw new IllegalArgumentException("Invalid GLContext parameters.");
        }
    }

    /**
     * Get the OpenGL major version
     * @return the major version
     */
    public static int getMajorVersion() {
        return get().majorVersion;
    }

    /**
     * Get the OpenGL minor version
     * @return the minor version
     */
    public static int getMinorVersion() {
        return get().minorVersion;
    }

    /**
     * Check if context has at least OpenGL version 3.0.
     * @return true if OpenGL is running version 3.0 or above, false if below 3.0
     */
    public static boolean hasGL30() {
        return get().majorVersion >= 3;
    }

    /**
     * Check if the OpenGL context is forwards compatible.
     * @return true if forward compatible, false otherwise
     */
    public static boolean forwardCompat() {
        return get().forwardCompat;
    }

    /**
     * Get the GLContext instance.
     * @return the GLContext
     * @throws IllegalStateException if context is not an OpenGL context
     */
    public static GLContext get() {
        Context context = Context.getInstance();
        if (context instanceof GLContext) {
            return (GLContext) Context.getInstance();
        } else {
            throw new IllegalStateException("Current context is not an OpenGL context.");
        }
    }

    /**
     * Setup lwjgl3 OpenGL context.
     * @param config the lwjgl3 configurations
     * @param window the window long pointer (glfw)
     */
    private void setupLwjgl3Context(Lwjgl3Configurations config, long window) {
        glfwMakeContextCurrent(window);
        glc = GL.createCapabilities();
        forwardCompat = glc.forwardCompatible;
        if (glc.OpenGL11) { minorVersion = 1; majorVersion = 1; }
        if (glc.OpenGL12) { minorVersion = 2; }
        if (glc.OpenGL13) { minorVersion = 3; }
        if (glc.OpenGL14) { minorVersion = 4; }
        if (glc.OpenGL15) { minorVersion = 5; }
        if (glc.OpenGL20) { minorVersion = 0; majorVersion = 2;}
        if (glc.OpenGL21) { minorVersion = 1; }
        if (glc.OpenGL30) { minorVersion = 0; majorVersion = 3; }
        if (glc.OpenGL31) { minorVersion = 1; }
        if (glc.OpenGL32) { minorVersion = 2; }
        if (glc.OpenGL33) { minorVersion = 3; }
        if (glc.OpenGL40) { minorVersion = 0; majorVersion = 4; }
        if (glc.OpenGL41) { minorVersion = 1; }
        if (glc.OpenGL42) { minorVersion = 2; }
        if (glc.OpenGL43) { minorVersion = 3; }
        if (glc.OpenGL44) { minorVersion = 4; }
        if (glc.OpenGL45) { minorVersion = 5; }
        if (glc.OpenGL46) { minorVersion = 6; }
    }
}
