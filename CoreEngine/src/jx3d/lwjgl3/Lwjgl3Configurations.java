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
     * If set to {@link Module#DEFAULT}, then the window will keep its starting x position.
     */
    public int windowX = Module.DEFAULT;

    /**
     * The position of the window in the y direction.
     * If set to {@link Module#DEFAULT}, then the window will keep its starting y position.
     */
    public int windowY = Module.DEFAULT;

    /**
     * The width of the window.
     */
    public int windowWidth = 640;

    /**
     * The height of the window.
     */
    public int windowHeight = 480;

    /**
     * The minimum window width.
     * If set to {@link Module#DEFAULT}, no minimum width is used.
     */
    public int windowMinWidth = Module.DEFAULT;

    /**
     * The maximum window width.
     * If set to {@link Module#DEFAULT}, no maximum width is used.
     */
    public int windowMaxWidth = Module.DEFAULT;

    /**
     * The minimum window height.
     * If set to {@link Module#DEFAULT}, no minimum height is used.
     */
    public int windowMinHeight = Module.DEFAULT;

    /**
     * The maximum window height.
     * If set to {@link Module#DEFAULT}, no maximum height is used.
     */
    public int windowMaxHeight = Module.DEFAULT;

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
     * Set the screen to use in fullscreen mode, this has no effect outside fullscreen mode.
     * Default is null i.e. use the primary screen.
     * @see Lwjgl3Configurations#windowFullscreen
     */
    public Lwjgl3Screen screen = null;

    /**
     * Whether to use vertical synchronization.
     */
    public boolean vSyncEnabled = true;

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
