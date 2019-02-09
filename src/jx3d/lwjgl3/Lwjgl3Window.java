package jx3d.lwjgl3;

import static org.lwjgl.glfw.GLFW.*;

import jx3d.core.Log;
import jx3d.core.Window;
import jx3d.core.Screen;
import jx3d.graphics.opengl.GL20;
import jx3d.graphics.opengl.GLGraphics;
import jx3d.lwjgl3.Lwjgl3Screen;

import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWErrorCallback;

/**
 * Desktop window implementation using the <i>GLFW</i>.
 * <h1>What is GLFW?</h1>
 * <i>GLFW</i> is an Open Source, multi-platform library
 * for OpenGL, OpenGL ES and Vulkan development on
 * the desktop. It provides a simple API for creating
 * windows, contexts and surfaces, receiving input and events.
 * @since 1.0
 * @author Aleman778
 * @see <a href="http://www.glfw.org/">http://www.glfw.org/</a>
 */
public class Lwjgl3Window extends Window implements Runnable {

	private static final IntBuffer xpos = BufferUtils.createIntBuffer(1);
	private static final IntBuffer ypos = BufferUtils.createIntBuffer(1);
	
	private static final long NULL = 0L;
	
	private static boolean initialized = false;

	private final Object lock = new Object();
	private Thread mainThread;
	private Lwjgl3Screen screen;
	private String title;
	
	private long object = NULL;
	private boolean visible = false;
	private boolean disposed = false;
	private boolean gldebug = false;
	
	private boolean fullscreen = false;
	private boolean decorated = true;
	private boolean floating = false;
	private boolean iconified = false;
	private boolean resizable = true;
	private int swapInterval = 0;
	
	private int minWidth = GLFW_DONT_CARE, minHeight = GLFW_DONT_CARE;
	private int maxWidth = GLFW_DONT_CARE, maxHeight = GLFW_DONT_CARE;
	private int width, height;
	private int x = GLFW_DONT_CARE, y = GLFW_DONT_CARE;
	
	/**
	 * Default constructor.
	 */
	public Lwjgl3Window() {
		this("");
	}
	
	/**
	 * Constructor.
	 * @param title the title of the window
	 */
	public Lwjgl3Window(String title) {
		this(title, 640, 480);
	}
	
	/**
	 * Constructor.
	 * @param width the width of the window
	 * @param height the height of the window
	 */
	public Lwjgl3Window(int width, int height) {
		this("", width, height);
	}
	
	/**
	 * Constructor.
	 * @param title the title of the window
	 * @param width the width of the window
	 * @param height the height of the window
	 */
	public Lwjgl3Window(String title, int width, int height) {
		initialized();

		this.files = new Lwjgl3Files();
		this.input = new Lwjgl3Input(this);
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void run() {
		createWindow();
		window = this;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				glfwMakeContextCurrent(object);
				graphics.init();

				setup();
				
				while (!shouldClose()) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					//glfwPollEvents();
					glfwSwapBuffers(object);
					draw();
				}
			}
		}).start();

		synchronized (lock) {
			lock.notify();
		}

		while (!shouldClose()) {
			glfwWaitEvents();
		}
	}
	
	@Override
	public void dispose() {
		glfwDestroyWindow(object);
	}
	
	/**
	 * Check if the current window is active.
	 * @return true if the window is active.
	 */
	public boolean isActive() {
		return visible && isCreated();
	}
	
	/**
	 * Get the window title.
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
		if (isCreated()) {
			glfwSetWindowTitle(object, title);
		}
	}
	
	/**
	 * Set the rendering engine of the display. 
	 * This method can also accept an optional profile
	 * parameter if applicable, see {@link #setRenderer(int, int)}.
	 * @param renderer the rendering engine constant,
	 * 		supported renderer of GlfwDisplay is <code>OPENGL</code>.
	 */
	public void setRenderer(int renderer) {
		if (isCreated()) {
			throw new IllegalStateException("The renderer cannot be set after the window has been initialized.");
		}
		setRendererImpl(renderer);
	}

	/**
	 * Set the rendering engine of the display with a
	 * specific profile. By using this function you are
	 * explicitly setting the graphics requirements, so
	 * if a user does not support the requirements then 
	 * the application will be either glitchy or will not run at all.
	 * Thus you are recommended to use this function
	 * {@link #setRenderer(int)} instead as it automatically sets
	 * the appropriate requirements for the users systems.
	 * @param renderer the rendering engine constant
	 * @param profile an optional parameter that specifies
	 * 		a version number and/ or other settings for the
	 * 		provided renderer
	 * @see #setRenderer(int)
	 */
	public void setRenderer(int renderer, int profile) {
		if (isCreated()) {
			throw new IllegalStateException("The renderer cannot be set after the window has been initialized.");
		}
		
		setRendererImpl(renderer);
		
		switch (renderer) {
		case OPENGL: case OPENGL_DEBUG:
			setGLProfile(profile);
			break;
		default:
			throw new IllegalArgumentException("Renderer does not support different profiles.");
		}
	}
	
	
	/**
	 * Set the renderer.
	 * @param renderer the renderer to use
	 */
	private void setRendererImpl(int renderer) {
		switch (renderer) {
			case OPENGL:
				graphics = new GLGraphics(this);
				break;
			case OPENGL_DEBUG:
				graphics = new GLGraphics(this);
				gldebug = true;
				break;
			default:
				throw new IllegalArgumentException("Invalid or unsupported renderer provided.");
		}
	}
	
	/**
	 * Set the opengl profile.
	 * @param profile the profile to use
	 */
	private void setGLProfile(int profile) {
		switch (profile) {
			case GL20_PROFILE:
				setProfileImpl(2, false);
				break;
			case GL30_COMPAT_PROFILE:
				setProfileImpl(3, true);
				break;
			case GL30_CORE_PROFILE:
				setProfileImpl(3, false);
				break;
		}
	}
	
	/**
	 * Setup glfw context creation profile window hints.
	 * @param major major version
	 * @param compat compatibility profile? 
	 */
	private void setProfileImpl(int major, boolean compat) {
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, major);
		if (compat && major >= 3)
			glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE);
		else
			glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
	}
	
	/**
	 * Get the width of the window.
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
	 * @param width the new width of the window
	 * @param height the new height of the window
	 */
	public final void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		
		if (isCreated()) {
			glfwSetWindowSize(object, width, height);
		}
	}
	
	/**
	 * Set the minimum size limit of the window.
	 * <br><b>Note:</b> Setting the size limit to -1 will 
	 * @param minWidth the minimum width
	 * @param minHeight the minimum height
	 */
	public final void setSizeLimit(int minWidth, int minHeight) {
		setSizeLimit(minWidth, minHeight, GLFW_DONT_CARE, GLFW_DONT_CARE);
	}
	
	/**
	 * Set the minimum and maximum size limit of the window.
	 * @param minWidth the minimum width
	 * @param minHeight the minimum height
	 * @param maxWidth the maximum width
	 * @param maxHeight the maximum height
	 */
	public final void setSizeLimit(int minWidth, int minHeight, int maxWidth, int maxHeight) {
		this.minWidth = minWidth;
		this.minHeight = minHeight;
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		
		if (isCreated()) {
			glfwSetWindowSizeLimits(object, minWidth, minHeight, maxWidth, maxHeight);
		}
	}

	/**
	 * Get the size of the window.
	 */
	public final int[] getSize() {	
		return new int[] {width, height};
	}
	
	/**
	 * Get the x location of the window.
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
	 * @param x the x location 
	 * @param y the y location
	 */
	public final void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		
		if (isCreated()) {
			glfwSetWindowPos(object, x, y);
		}
	}
	
	/**
	 * Sets the location of the window relative to the provided screen.
	 * @param screen the relative screen
	 */
	public final void setLocation(Screen screen) {
		int x = screen.getWidth() / 2 - width / 2;
		int y = screen.getHeight() / 2 - height / 2;
		setLocation(x, y);
	}

	/**
	 * Enable vertical synchronization 
	 * @param enable
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
	 * @param interval the minimum number of screen updates to wait for until the buffers are swapped by glfwSwapBuffers.
	 * @see <a href="http://www.glfw.org/docs/latest/group__context.html#ga6d4e0cdf151b5e579bd67f13202994ed">GLFW 1.3.2 Documentation</a>
	 */
	public final void setSwapInterval(int interval) {
		if (swapInterval != 0 && interval == 0)
			throw new IllegalStateException("Cannot set swap interval to be reset to zero once it has been set to a non-zero value");
		
		this.swapInterval = interval;
		if (isCreated()) {
			glfwSwapInterval(interval);
		}
	}
	
	/**
	 * Check if the window is in fullscreen mode.
	 * @return true if the window is in fullscreen mode
	 */
	public final boolean isFullscreen() {
		return fullscreen;
	}

	/**
	 * Sets the window in fullscreen mode on the provided screen.
	 * @param screen the screen to use
	 */
	public final void setFullscreenMode(Lwjgl3Screen screen) {
		if (this.fullscreen && this.screen.equals(screen)) {
			return;
		}
		
		this.fullscreen = true;
		this.screen = screen;

		if (isCreated()) {
			glfwSetWindowMonitor(object, screen.getMonitor(), 0, 0,
					screen.getWidth(), screen.getHeight(), (int) screen.getRefreshRate());
		}
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
		
		if (isCreated()) {
			glfwSetWindowMonitor(object, NULL, x, y, width, height, GLFW_DONT_CARE);
		}
	}
	
	/**
	 * Checks if the window is visible.
	 * @return true if the window is visible
	 */
	public final boolean isVisible() {
		return visible;
	}
	
	/**
	 * Set the visibility of the screen
	 * @param visible the visibility flag
	 */
	public final void setVisible(boolean visible) {
		this.visible = visible;
		 
		if (visible && !isCreated()) {
			mainThread = new Thread(this, title);
			mainThread.start();
			synchronized(lock) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			return;
		}
		
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
	 * @return floating attribute
	 */
	public final boolean isDecorated() {
		return glfwGetWindowAttrib(object, GLFW_DECORATED) == GLFW_TRUE;
	}

	/**
	 * Set the decorated attribute.
	 * @param decorated flag
	 */
	public final void setDecorated(boolean decorated) {
		this.decorated = decorated;
		if (isCreated()) {
			glfwSetWindowAttrib(object, GLFW_DECORATED, decorated ? GLFW_TRUE : GLFW_FALSE);
		}
	}
	
	/**
	 * Checks if the window is floating.
	 * @return floating attribute
	 */
	public final boolean isFloating() {
		return glfwGetWindowAttrib(object, GLFW_FLOATING) == GLFW_TRUE;
	}
	
	/**
	 * Set the floating attribute.
	 * @param floating flag
	 */
	public final void setFloating(boolean floating) {
		this.floating = floating;
		if (isCreated()) {
			glfwSetWindowAttrib(object, GLFW_FLOATING, floating ? GLFW_TRUE : GLFW_FALSE);
		}
	}

	/**
	 * Checks if the window is resizable.
	 * @return floating attribute
	 */
	public final boolean isResizable() {
		return glfwGetWindowAttrib(object, GLFW_RESIZABLE) == GLFW_TRUE;
	}
	
	/**
	 * Set the resizable attribute.
	 * @param resizable flag
	 */
	public final void setResizeable(boolean resizable) {
		this.resizable = resizable;
		if (isCreated()) {
			glfwSetWindowAttrib(object, GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
		}
	}

	/**
	 * Checks if the window is iconified
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
	public final Lwjgl3Screen[] getScreens() {
		PointerBuffer buff = glfwGetMonitors();
		Lwjgl3Screen[] result = new Lwjgl3Screen[buff.limit()];
		for (int i = 0; i < result.length; i++) {
			result[i] = new Lwjgl3Screen(buff.get(i));
		}
		return result;
	}
	
	@Override
	public boolean shouldClose() {
		if (!isCreated()) {
			return true;
		}
		
		return glfwWindowShouldClose(object);
	}
	
	private boolean hasReference() {
		return object != NULL;
	}
	
	private boolean isCreated() {
		return (object != NULL) && (!disposed);
	}

	private void createWindow() {
		if (fullscreen) {
			glfwWindowHint(GLFW_REFRESH_RATE, (int) screen.getRefreshRate());
			object = glfwCreateWindow(screen.getWidth(), screen.getHeight(), title, screen.getMonitor(), NULL);
		} else {
			object = glfwCreateWindow(width, height, title, NULL, NULL);
		}
		
		setupAttributes();
		setupCallbacks();
	}
	
	private void setupCallbacks() {
		glfwSetWindowRefreshCallback(object, (long window) -> {
			//glfwSwapBuffers(window);
		});
		
		glfwSetKeyCallback(object, (long window, int key, int scancode, int action, int mods) -> {
			if (action == GLFW_PRESS)
				input.keyDownProc(key, mods);
			else if (action == GLFW_RELEASE)
				input.keyUpProc(key, mods);
			else if (action == GLFW_REPEAT)
				input.keyRepeatProc(key, mods);
		});
		
		glfwSetMouseButtonCallback(object, (long window, int button, int action, int mods) -> {
			if (action == GLFW_PRESS)
				input.mousePressedProc(button, mods);
			else if (action == GLFW_RELEASE)
				input.mouseReleasedProc(button, mods);
		});
		
		glfwSetScrollCallback(object, (long window, double xoffset, double yoffset) -> {
			input.mouseScrolledProc((float) xoffset, (float) yoffset);
		});
		
		glfwSetCursorPosCallback(object, (long window, double xpos, double ypos) -> {
			input.mouseMovedProc((float) xpos, (float) ypos);
		});
		
		glfwSetCursorEnterCallback(object, (long window, boolean entered) -> {
			if (entered)
				input.mouseEnteredProc();
			else
				input.mouseExitedProc();
		});
		
		glfwSetWindowSizeCallback(object, (long window, int width, int height) -> {
			this.width = width;
			this.height = height;
			input.windowResizedProc(width, height);
		});
		
		glfwSetWindowPosCallback(object, (long window, int xpos, int ypos) -> {
			this.x = xpos;
			this.y = ypos;
			input.windowMovedProc(xpos, ypos);
		});
		
		glfwSetWindowFocusCallback(object, (long window, boolean focused) -> {
			input.windowFocusProc(focused);
		});

		glfwSetWindowIconifyCallback(object, (long window, boolean iconified) -> {
			input.windowIconifyProc(iconified);
		});

		glfwSetWindowMaximizeCallback(object, (long window, boolean maximized) -> {
			input.windowMaximizeProc(maximized);
		});
		
		glfwSetWindowCloseCallback(object, (long window) -> {
			input.windowClosedProc();
		});
	}

	private void setupAttributes() {
		if (x == -1 && y == -1) {
			glfwGetWindowPos(object, xpos, ypos);
			x = xpos.get(0);
			y = ypos.get(0);	
		} else {
			glfwSetWindowPos(object, x, y);
		}

		glfwSetWindowSizeLimits(object, minWidth, minHeight, maxWidth, maxHeight);
		glfwSetWindowAttrib(object, GLFW_DECORATED, decorated ? 1 : 0);
		glfwSetWindowAttrib(object, GLFW_FLOATING, floating ? 1 : 0);
		glfwSetWindowAttrib(object, GLFW_RESIZABLE, resizable? 1 : 0);

		if (iconified)
			glfwIconifyWindow(object);
		
		//OpenGL debug mode
		if (gldebug)
			glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GL20.TRUE);	
	}
	
	/**
	 * Initialize the window, has to be called before calling GLFW and is only performed once.
	 */
	private static final void initialized() {
		if (!initialized) {
			if (glfwInit()) {
				initialized = true;
				glfwSetErrorCallback(GLFWErrorCallback.createThrow());
			} else {
				throw new IllegalStateException("The GLFW library failed to initialize.");
			}
		}
	}
}
