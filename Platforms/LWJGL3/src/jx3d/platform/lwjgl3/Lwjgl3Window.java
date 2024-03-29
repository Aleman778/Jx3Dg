package jx3d.platform.lwjgl3;

import jx3d.core.Application;
import jx3d.core.Log;
import jx3d.core.Screen;
import jx3d.core.Window;
import jx3d.graphics.opengl.GL20;
import jx3d.io.event.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

import static jx3d.core.Module.*;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Desktop window implementation using the <i>GLFW</i>.
 * <h1>What is GLFW?</h1>
 * <i>GLFW</i> is an Open Source, multi-platform library
 * for OpenGL, OpenGL ES and Vulkan development on
 * the desktop. It provides a simple API for creating
 * windows, contexts and surfaces, receiving input and event.
 *
 * @author Aleman778
 * @see <a href="http://www.glfw.org/">http://www.glfw.org/</a>
 * @since 1.0
 */
public class Lwjgl3Window extends Window {

    private static final IntBuffer xpos = BufferUtils.createIntBuffer(1);
    private static final IntBuffer ypos = BufferUtils.createIntBuffer(1);
    private static final DoubleBuffer xcursor = BufferUtils.createDoubleBuffer(1);
    private static final DoubleBuffer ycursor = BufferUtils.createDoubleBuffer(1);

    private static final long NULL = 0L;

    private Lwjgl3Screen screen;
    private String title;

    private long object;
    private boolean visible = false;

    private boolean fullscreen = false;

    /**
     * Window is decorated i.e. has a title bar.
     */
    private boolean decorated = true;

    /**
     * Window is floating i.e. always on top.
     */
    private boolean floating = false;

    /**
     * Window is iconified i.e. minimized.
     */
    private boolean iconified = false;

    /**
     * Window is resizable
     */
    private boolean resizable = true;

    /**
     * The swap time interval.
     */
    private int swapInterval = 0;

    /**
     * Window minimum size
     */
    private int minWidth = GLFW_DONT_CARE, minHeight = GLFW_DONT_CARE;

    /**
     * Window maximum size
     */
    private int maxWidth = GLFW_DONT_CARE, maxHeight = GLFW_DONT_CARE;

    /**
     * Window size
     */
    private int width, height;

    /**
     * Window x position
     */
    private int x = DEFAULT, y = DEFAULT;

    /**
     * The first mouse button pressed of possible multiple simultaneous presses.
     */
    private int firstMouseButton = MOUSE_NOBUTTON;

    /**
     * Mouse cursor position.
     */
    private float mouseX, mouseY;

    /**
     * Previous mouse cursor position.
     */
    private float prevMouseX, prevMouseY;

    /**
     * Default constructor.
     */
    public Lwjgl3Window() {
        this("");
    }

    /**
     * Constructor.
     *
     * @param title the title of the window
     */
    public Lwjgl3Window(String title) {
        this(title, 640, 480);
    }

    /**
     * Constructor.
     *
     * @param width  the width of the window
     * @param height the height of the window
     */
    public Lwjgl3Window(int width, int height) {
        this("", width, height);
    }

    /**
     * Constructor.
     *
     * @param title  the title of the window
     * @param width  the width of the window
     * @param height the height of the window
     */
    public Lwjgl3Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        object = glfwCreateWindow(width, height, title, NULL, NULL);
    }

    public Lwjgl3Window(Lwjgl3Configurations config) {
        title = config.title;
        x = config.windowX;
        y = config.windowY;
        width = config.windowWidth;
        height = config.windowHeight;
        minWidth = config.windowMinWidth;
        maxWidth = config.windowMaxWidth;
        minHeight = config.windowMinHeight;
        maxHeight = config.windowMaxHeight;
        fullscreen = config.windowFullscreen;
        resizable = config.windowResizable;
        decorated = config.windowDecorated;
        floating = config.windowFloating;
        swapInterval = config.vSyncEnabled ? 1 : 0;
        screen = config.screen;
        if (screen == null) {
            screen = getScreen();
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        setupContextHints(config.renderer, config.profile);

        if (fullscreen) {
            glfwWindowHint(GLFW_REFRESH_RATE, (int) screen.getRefreshRate());
            object = glfwCreateWindow(screen.getWidth(), screen.getHeight(), title, screen.getMonitor(), NULL);
        } else {
            object = glfwCreateWindow(width, height, title, NULL, NULL);
        }

        setupAttributes();
        setupCallbacks();
    }

    /**
     * Poll events is used to process events that are stored in the internal event queue.
     */
    public void pollEvents() {
        glfwPollEvents();
    }

    /**
     * Swap buffer is used to change the front buffer to render to and display the other buffer.
     */
    public void swapBuffers() {
        glfwSwapBuffers(object);
    }

    /**
     * Dispose method destroys the GLFW window instance and free its memory.
     */
    @Override
    public void dispose() {
        glfwDestroyWindow(object);
    }

    @Override
    public boolean isKeyDown(int key) {
        int state = glfwGetKey(object, key);
        return state == GLFW_PRESS || state == GLFW_REPEAT;
    }

    @Override
    public boolean isMouseButtonDown(int button) {
        int state = glfwGetMouseButton(object, button);
        return state == GLFW_PRESS;
    }

    @Override
    public float getMouseX() {
        return mouseX;
    }

    @Override
    public float getMouseY() {
        return mouseY;
    }

    @Override
    public float getMouseDeltaX() {
        return mouseX - prevMouseX;
    }

    @Override
    public float getMouseDeltaY() {
        return mouseY - prevMouseY;
    }

    /**
     * Check if the current window is active.
     *
     * @return true if the window is active.
     */
    public boolean isActive() {
        return visible;
    }

    /**
     * Get the window title.
     *
     * @return title
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Set the window title.
     */
    public void setTitle(String title) {
        this.title = title;
        glfwSetWindowTitle(object, title);
    }

    /**
     * Get the width of the window.
     *
     * @return the window width
     */
    @Override
    public int getWidth() {
        if (screen != null) {
            return screen.getWidth();
        }

        return width;
    }

    /**
     * Get the height of the window.
     *
     * @return the window height
     */
    @Override
    public int getHeight() {
        if (screen != null) {
            return screen.getHeight();
        }

        return height;
    }

    /**
     * Set the size of the window.
     *
     * @param width  the new width of the window
     * @param height the new height of the window
     */
    public final void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        glfwSetWindowSize(object, width, height);
    }

    /**
     * Set the minimum size limit of the window.
     * <br><b>Note:</b> Setting the size limit to -1 will
     *
     * @param minWidth  the minimum width
     * @param minHeight the minimum height
     */
    public final void setSizeLimit(int minWidth, int minHeight) {
        setSizeLimit(minWidth, minHeight, GLFW_DONT_CARE, GLFW_DONT_CARE);
    }

    /**
     * Set the minimum and maximum size limit of the window.
     *
     * @param minWidth  the minimum width
     * @param minHeight the minimum height
     * @param maxWidth  the maximum width
     * @param maxHeight the maximum height
     */
    public final void setSizeLimit(int minWidth, int minHeight, int maxWidth, int maxHeight) {
        this.minWidth = minWidth;
        this.minHeight = minHeight;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        glfwSetWindowSizeLimits(object, minWidth, minHeight, maxWidth, maxHeight);
    }

    /**
     * Get the size of the window.
     */
    public final int[] getSize() {
        return new int[]{width, height};
    }

    /**
     * Get the x location of the window.
     *
     * @return the x location
     */
    public final int getX() {
        if (fullscreen) {
            return 0;
        }

        return x;
    }

    /**
     * Get the y location of the window
     *
     * @return the y location
     */
    public final int getY() {
        if (fullscreen) {
            return 0;
        }

        return y;
    }

    /**
     * Set the location of the window.
     *
     * @param x the x location
     * @param y the y location
     */
    public final void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
        glfwSetWindowPos(object, x, y);
    }

    /**
     * Sets the location of the window relative to the provided screen.
     *
     * @param screen the relative screen
     */
    public final void setLocation(Screen screen) {
        int x = screen.getWidth() / 2 - width / 2;
        int y = screen.getHeight() / 2 - height / 2;
        setLocation(x, y);
    }

    /**
     * Enable vertical synchronization
     *
     * @param enable enable vsync
     */
    public final void setVsync(boolean enable) {
        setSwapInterval(enable ? 1 : 0);
    }

    /**
     * <b>From GLFW Documentation:</b><br>
     * This function sets the swap interval for the current OpenGL or OpenGL ES context,
     * i.e. the number of screen updates to wait from the time glfwSwapBuffers was called
     * before swapping the buffers and returning. This is sometimes called vertical
     * synchronization, vertical retrace synchronization or just vsync.
     * <br><br>
     * Contexts that support either of the WGL_EXT_swap_control_tear and
     * GLX_EXT_swap_control_tear extensions also accept negative swap intervals, which
     * allow the driver to swap even if a frame arrives a little bit late. You can check
     * for the presence of these extensions using glfwExtensionSupported.
     * For more information about swap tearing, see the extension specifications.
     * <br><br>
     * A context must be current on the calling thread. Calling this function
     * without a current context will cause a GLFW_NO_CURRENT_CONTEXT error.
     * <br><br>
     * <b>Note:</b> This function does not apply to Vulkan.
     *
     * @param interval the minimum number of screen updates to wait for until the buffers are swapped by glfwSwapBuffers.
     * @see <a href="http://www.glfw.org/docs/latest/group__context.html#ga6d4e0cdf151b5e579bd67f13202994ed">GLFW 1.3.2 Documentation</a>
     */
    public final void setSwapInterval(int interval) {
        if (swapInterval != 0 && interval == 0)
            throw new IllegalStateException("Cannot set swap interval to be reset to zero once it has been set to a non-zero value");

        this.swapInterval = interval;
        glfwSwapInterval(interval);
    }

    /**
     * Check if the window is in fullscreen mode.
     *
     * @return true if the window is in fullscreen mode
     */
    public final boolean isFullscreen() {
        return fullscreen;
    }

    /**
     * Sets the window in fullscreen mode on the provided screen.
     *
     * @param screen the screen to use
     */
    public final void setFullscreenMode(Lwjgl3Screen screen) {
        if (this.fullscreen && this.screen.equals(screen)) {
            return;
        }
        this.fullscreen = true;
        this.screen = screen;
        glfwSetWindowMonitor(object, screen.getMonitor(), 0, 0,
                screen.getWidth(), screen.getHeight(), (int) screen.getRefreshRate());
    }

    /**
     * Sets the window in fullscreen mode on the primary screen.
     */
    public final void setFullscreenMode() {
        setFullscreenMode(getScreen());
    }

    /**
     * Sets the window in windowed mode.
     */
    public final void setWindowedMode() {
        if (!this.fullscreen) {
            return;
        }
        this.fullscreen = false;
        this.screen = null;
        glfwSetWindowMonitor(object, NULL, x, y, width, height, GLFW_DONT_CARE);
    }

    /**
     * Checks if the window is visible.
     *
     * @return true if the window is visible
     */
    public final boolean isVisible() {
        return visible;
    }

    /**
     * Set the visibility of the screen
     *
     * @param visible the visibility flag
     */
    public final void setVisible(boolean visible) {
        this.visible = visible;
        if (hasReference()) {
            if (visible) {
                glfwShowWindow(object);
            } else {
                glfwHideWindow(object);
            }
        }
    }

    /**
     * Checks if the window is decorated.
     *
     * @return floating attribute
     */
    public final boolean isDecorated() {
        return glfwGetWindowAttrib(object, GLFW_DECORATED) == GLFW_TRUE;
    }

    /**
     * Set the decorated attribute.
     *
     * @param decorated flag
     */
    public final void setDecorated(boolean decorated) {
        this.decorated = decorated;
        glfwSetWindowAttrib(object, GLFW_DECORATED, decorated ? GLFW_TRUE : GLFW_FALSE);
    }

    /**
     * Checks if the window is floating.
     *
     * @return floating attribute
     */
    public final boolean isFloating() {
        return glfwGetWindowAttrib(object, GLFW_FLOATING) == GLFW_TRUE;
    }

    /**
     * Set the floating attribute.
     *
     * @param floating flag
     */
    public final void setFloating(boolean floating) {
        this.floating = floating;
        glfwSetWindowAttrib(object, GLFW_FLOATING, floating ? GLFW_TRUE : GLFW_FALSE);
    }

    /**
     * Checks if the window is resizable.
     *
     * @return floating attribute
     */
    public final boolean isResizable() {
        return glfwGetWindowAttrib(object, GLFW_RESIZABLE) == GLFW_TRUE;
    }

    /**
     * Set the resizable attribute.
     *
     * @param resizable flag
     */
    public final void setResizeable(boolean resizable) {
        this.resizable = resizable;
        glfwSetWindowAttrib(object, GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
    }

    /**
     * Checks if the window is iconified
     *
     * @return iconified attribute
     */
    public final boolean isIconified() {
        return glfwGetWindowAttrib(object, GLFW_ICONIFIED) == GLFW_TRUE;
    }

    /**
     * Set the minimized (iconified) attribute.
     */
    public final void setIconified() {
        glfwIconifyWindow(object);
    }

    /**
     * Checks if the window is maximized.
     *
     * @return floating attribute
     */
    public final boolean isMaximized() {
        return glfwGetWindowAttrib(object, GLFW_MAXIMIZED) == GLFW_TRUE;
    }

    /**
     * Set the maximized attribute.
     */
    public final void setMaximized() {
        glfwMaximizeWindow(object);
    }

    @Override
    public final Lwjgl3Screen getScreen() {
        return new Lwjgl3Screen(glfwGetPrimaryMonitor());
    }

    @Override
    public final Lwjgl3Screen[] getAllScreens() {
        PointerBuffer buff = glfwGetMonitors();
        Lwjgl3Screen[] result = new Lwjgl3Screen[buff.limit()];
        for (int i = 0; i < result.length; i++) {
            result[i] = new Lwjgl3Screen(buff.get(i));
        }
        return result;
    }

    @Override
    public boolean shouldClose() {
        return glfwWindowShouldClose(object);
    }

    private boolean hasReference() {
        return object != NULL;
    }

    protected long getObject() {
        return object;
    }


    private void setupContextHints(int renderer, int profile) {
        switch (renderer) {
            case OPENGL_DEBUG:
                glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GL20.TRUE);
            case OPENGL:
                switch (profile) {
                    case GL20_PROFILE:
                        Log.CORE.warning("GLFW does not support GL20_PROFILE, chaning to PREFERRED");
                        break;
                    case GL30_CORE_PROFILE:
                        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
                        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
                        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
                        break;
                    case GL30_COMPAT_PROFILE:
                        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
                        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
                        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE);
                        break;
                    case GL40_CORE_PROFILE:
                        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
                        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
                        break;
                    case GL40_COMPAT_PROFILE:
                        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
                        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
                        break;
                }
                break;
        }
    }

    private void setupCallbacks() {
        glfwSetWindowRefreshCallback(object, (long window) -> {
            //glfwSwapBuffers(window);
        });

        glfwSetMouseButtonCallback(object, (long window, int button, int action, int mods) -> {
            EventType type;
            String name = "glfw_mouse";
            if (action == GLFW_PRESS) {
                type = EventType.MousePressed;
                name += "_pressed";
                if (firstMouseButton == MOUSE_NOBUTTON) {
                    firstMouseButton = button;
                }
            } else {
                type = EventType.MouseReleased;
                name += "_released";
                if (firstMouseButton == button) {
                    firstMouseButton = MOUSE_NOBUTTON;
                }
            }

            Event event = new MouseEvent(type, name, button, mods, getMouseX(), getMouseY(),
                    getMouseDeltaX(), getMouseDeltaY(), 0);
            Application.get().onEvent(event);
        });

        glfwSetCursorPosCallback(object, (long window, double xpos, double ypos) -> {
            prevMouseX = mouseX;
            prevMouseY = mouseY;
            mouseX = (float) xpos;
            mouseY = (float) ypos;

            EventType type = EventType.MouseMoved;
            String name = "glfw_mouse";
            if (firstMouseButton != MOUSE_NOBUTTON) {
                type = EventType.MouseDragged;
                name += "_dragged";
            } else {
                name += "_moved";
            }

            Event event = new MouseEvent(type, name, firstMouseButton, 0, (float) xpos, (float) ypos,
                    getMouseDeltaX(), getMouseDeltaY(), 0);
            Application.get().onEvent(event);
        });

        glfwSetCursorEnterCallback(object, (long window, boolean entered) -> {
            EventType type;
            String name = "glfw_mouse";
            if (entered) {
                type = EventType.MouseEntered;
                name += "_entered";
            } else {
                type = EventType.MouseExited;
                name += "_exited";
            }

            Event event = new MouseEvent(type, name, MOUSE_NOBUTTON, 0, getMouseX(), getMouseY(),
                    getMouseDeltaX(), getMouseDeltaY(), 0);
            Application.get().onEvent(event);
        });

        glfwSetScrollCallback(object, (long window, double xoffset, double yoffset) -> {
            Event event = new MouseScrollEvent("glfw_mouse_scroll", 0, getMouseX(), getMouseY(), getMouseDeltaX(),
                    getMouseDeltaY(), (float) xoffset, (float) yoffset, 0);
            Application.get().onEvent(event);
        });

        glfwSetKeyCallback(object, (long window, int key, int scancode, int action, int mods) -> {
            EventType type = EventType.None;
            String name = "glfw_key";
            char keyChar = 0;

            boolean repeat = false;
            if (action == GLFW_PRESS) {
                type = EventType.KeyDown;
                name += "_pressed";
            } else if (action == GLFW_RELEASE) {
                type = EventType.KeyUp;
                name += "_released";
            } else if (action == GLFW_REPEAT) {
                type = EventType.KeyDown;
                name += "_repeat";
                repeat = true;
            }

            if (key >= KEY_SPACE && key < KEY_WORLD_2) {
                String keyName = glfwGetKeyName(key, scancode);
                System.err.println(keyName);
                if (keyName.length() > 0) {
                    keyChar = keyName.charAt(0);
                }
            }

            Event event = new KeyEvent(name, type, key, scancode, mods, keyChar, repeat);
            Application.get().onEvent(event);
        });


        glfwSetWindowSizeCallback(object, (long window, int width, int height) -> {
            this.width = width;
            this.height = height;
            Event event = new WindowEvent("glfw_window_resize", EventType.WindowResize, this,
                    x, y, width, height, isIconified(), isMaximized());
            Application.get().onEvent(event);
        });

        glfwSetWindowPosCallback(object, (long window, int xpos, int ypos) -> {
            this.x = xpos;
            this.y = ypos;
            Event event = new WindowEvent("glfw_window_moved", EventType.WindowMoved, this,
                    x, y, width, height, isIconified(), isMaximized());
            Application.get().onEvent(event);
        });

        glfwSetWindowFocusCallback(object, (long window, boolean focused) -> {
            EventType type = focused ? EventType.WindowFocus : EventType.WindowLostFocus;
            Event event = new WindowEvent("glfw_window_focus", type, this,
                    x, y, width, height, isIconified(), isMaximized());
            Application.get().onEvent(event);
        });

        glfwSetWindowIconifyCallback(object, (long window, boolean iconified) -> {
            Event event = new WindowEvent("glfw_window_iconify", EventType.WindowIconified, this,
                    x, y, width, height, iconified, isMaximized());
            Application.get().onEvent(event);
        });

        glfwSetWindowMaximizeCallback(object, (long window, boolean maximized) -> {
            Event event = new WindowEvent("glfw_window_maximize", EventType.WindowMaximized, this,
                    x, y, width, height, isIconified(), maximized);
            Application.get().onEvent(event);
        });

        glfwSetWindowCloseCallback(object, (long window) -> {
            Event event = new WindowEvent("glfw_window_close", EventType.WindowClose, this,
                    x, y, width, height, isIconified(), isMaximized());
            Application.get().onEvent(event);
        });
    }

    private void setupAttributes() {
        if (x == DEFAULT || y == DEFAULT) {
            glfwGetWindowPos(object, xpos, ypos);
            int tempX = xpos.get(0);
            int tempY = ypos.get(0);
            if (x != DEFAULT) {
                glfwSetWindowPos(object, x, tempY);
                y = tempY;
            } else if (y != -1) {
                glfwSetWindowPos(object, tempX, y);
                x = tempX;
            } else {
                x = tempX; y = tempY;
            }
        } else {
            glfwSetWindowPos(object, x, y);
        }

        glfwSetWindowSizeLimits(object, minWidth, minHeight, maxWidth, maxHeight);
        glfwSetWindowAttrib(object, GLFW_DECORATED, decorated ? 1 : 0);
        glfwSetWindowAttrib(object, GLFW_FLOATING, floating ? 1 : 0);
        glfwSetWindowAttrib(object, GLFW_RESIZABLE, resizable ? 1 : 0);

        if (iconified)
            glfwIconifyWindow(object);
    }
}
