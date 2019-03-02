package jx3d.core;

import jx3d.graphics.Graphics;
import jx3d.graphics.opengl.GL20;
import jx3d.graphics.opengl.GL30;
import jx3d.io.Files;
import jx3d.io.Input;

/**
 * JX3D class has all modules that are used to build an Application.
 * This class is just a convenience class.
 */
public class JX3D {

    /**
     * Static class should not be instantiated.
     */
    private JX3D() {

    }

    /**
     * The graphics API used by the application.
     */
    public static Graphics graphics;

    /**
     * The inputs taken from the main application window.
     */
    public static Input input;

    /**
     * The file system used by the application platform.
     */
    public static Files files;

    /**
     * OpenGL 2.X implementation.
     * Is null if graphics is not GLGraphics.
     */
    public static GL20 gl20;

    /**
     * OpenGL 3.X implementation.
     * Is null if graphics is not GLGraphics or GL version 3 not supported.
     */
    public static GL30 gl30;
}
