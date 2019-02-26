package jx3d.core;

import jx3d.graphics.Graphics;
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

    public static Graphics graphics;

    public static Input input;

    public static Files files;
}
