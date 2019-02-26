package jx3d.lwjgl3;

import jx3d.core.Module;
import jx3d.graphics.Color;

public class Lwjgl3Configurations {

    /**
     * The title of the main window.
     * If set to null the application class name is used instead.
     */
    String title = null;

    /**
     * The position of the window in the x direction.
     * If set to -1 the window is put in the center of the screen.
     */
    int windowX = -1;

    /**
     * The position of the window in the y direction.
     * If set to -1 the window is put in the center of the screen.
     */
    int windowY = -1;

    /**
     * The width of the window.
     */
    int windowWidth = 640;

    /**
     * The height of the window.
     */
    int windowHeight = 480;

    /**
     * Whether to allow the window to be resizable by the user.
     */
    boolean windowResizable = true;

    /**
     * Whether to use a decorated window
     * i.e. window with a title bar.
     */
    boolean windowDecorated = true;

    /**
     * Whether to use a floating window
     * i.e. window that is always on top of other windows.
     */
    boolean windowFloating = false;

    /**
     * Whether to set the window to be in fullscreen mode.
     * The {@link Lwjgl3Configurations#windowWidth} and {@link Lwjgl3Configurations#windowHeight} is
     * ignored if this is set to true.
     */
    boolean windowFullscreen = false;

    /**
     * Whether to use vertical synchronization.
     */
    boolean vSyncEnabled = true;

    /**
     * The initial background color of window.
     */
    Color initialBackground = Color.BLACK;

    /**
     * Number of bits per color channel.
     */
    int r = 8, g = 8, b = 8, a = 8;

    /**
     * Number of bits for depth buffer.
     */
    int depth = 16;

    /**
     * Number of bits for stencil buffer.
     */
    int stencil = 0;

    /**
     * Number of samples for antialiasing (CSAA/MSAA).
     */
    int samples = 0;

    /**
     * The renderer to use can at the moment only be set to <code>OPENGL</code>.
     */
    int renderer = Module.OPENGL;

    /**
     * The renderer profile to set.
     */
    int profile = Module.GL30_CORE_PROFILE;
}
