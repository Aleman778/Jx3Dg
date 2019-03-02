package jx3d.lwjgl3;

import jx3d.core.Module;
import jx3d.graphics.Color;

public class Lwjgl3Configurations {

    /**
     * The title of the main window.
     * If set to null the application class name is used instead.
     */
    public String title = null;

    /**
     * The position of the window in the x direction.
     * If set to -1 the window is put in the center of the screen.
     */
    public int windowX = -1;

    /**
     * The position of the window in the y direction.
     * If set to -1 the window is put in the center of the screen.
     */
    public int windowY = -1;

    /**
     * The width of the window.
     */
    public int windowWidth = 640;

    /**
     * The height of the window.
     */
    public int windowHeight = 480;

    /**
     * Whether to allow the window to be resizable by the user.
     */
    public boolean windowResizable = true;

    /**
     * Whether to use a decorated window
     * i.e. window with a title bar.
     */
    public boolean windowDecorated = true;

    /**
     * Whether to use a floating window
     * i.e. window that is always on top of other windows.
     */
    public boolean windowFloating = false;

    /**
     * Whether to set the window to be in fullscreen mode.
     * The {@link Lwjgl3Configurations#windowWidth} and {@link Lwjgl3Configurations#windowHeight} is
     * ignored if this is set to true.
     */
    public boolean windowFullscreen = false;

    /**
     * Whether to use vertical synchronization.
     */
    public boolean vSyncEnabled = true;

    /**
     * The initial background color of window.
     */
    public Color initialBackground = Color.BLACK;

    /**
     * Number of bits per color channel.
     */
    public int r = 8, g = 8, b = 8, a = 8;

    /**
     * Number of bits for depth buffer.
     */
    public int depth = 16;

    /**
     * Number of bits for stencil buffer.
     */
    public int stencil = 0;

    /**
     * Number of samples for antialiasing (CSAA/MSAA).
     */
    public int samples = 0;

    /**
     * The renderer to use.
     * Default is OpenGL.
     */
    public int renderer = Module.DEFAULT;

    /**
     * The renderer profile to use.
     * Preferred uses the highest version supported.
     */
    public int profile = Module.PREFERRED;

    /**
     * Forward compatible removes deprecated functions.
     */
    public boolean forwardCompatible = false;
}
