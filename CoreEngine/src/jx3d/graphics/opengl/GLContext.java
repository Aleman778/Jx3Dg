package jx3d.graphics.opengl;

import jx3d.graphics.Context;

/**
 * OpenGL Context.
 */
public abstract class GLContext extends Context {

    /**
     * OpenGL major version.
     */
    protected int majorVersion;

    /**
     * OpenGL minor version.
     */
    protected int minorVersion;

    /**
     * Check if context forward compatible.
     * Forward compatible removes deprecated OpenGL functions in 3.0.
     */
    protected boolean forwardCompat;

    public GLContext(RenderAPI renderAPI) {
        super(renderAPI);
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
}
