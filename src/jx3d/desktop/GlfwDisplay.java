package jx3d.desktop;

import static org.lwjgl.glfw.GLFW.*;

import jx3d.core.Display;
import jx3d.core.Screen;
import jx3d.desktop.GlfwScreen;

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
public class GlfwDisplay extends Display implements Runnable {

	private static final IntBuffer xpos = BufferUtils.createIntBuffer(1);
	private static final IntBuffer ypos = BufferUtils.createIntBuffer(1);
	
	private static final long NULL = 0L;
	
	private static boolean initialized = false;

	private Object lock = new Object();
	private Thread mainThread;
	private GlfwScreen screen;
	private String title;
	
	private long window = NULL;
	private boolean visible = false;
	private boolean disposed = false;
	
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
	public GlfwDisplay() {
		this("");
	}
	
	/**
	 * Constructor.
	 * @param title the title of the window
	 */
	public GlfwDisplay(String title) {
		this(title, 640, 480);
	}
	
	/**
	 * Constructor.
	 * @param width the width of the window
	 * @param height the height of the window
	 */
	public GlfwDisplay(int width, int height) {
		this("", width, height);
	}
	
	/**
	 * Constructor.
	 * @param title the title of the window
	 * @param width the width of the window
	 * @param height the height of the window
	 */
	public GlfwDisplay(String title, int width, int height) {
		initialized();
		
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void run() {
		createWindow();
		
		synchronized (lock) {
			lock.notify();
		}
		
		while (!shouldClose()) {
			glfwWaitEvents();
		}
	}
	
	@Override
	public void dispose() {
		glfwDestroyWindow(window);
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
			glfwSetWindowTitle(window, title);
		}
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
			glfwSetWindowSize(window, width, height);
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
			glfwSetWindowSizeLimits(window, minWidth, minHeight, maxWidth, maxHeight);
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
			glfwSetWindowPos(window, x, y);
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
	public final void setFullscreenMode(GlfwScreen screen) {
		if (this.fullscreen && this.screen.equals(screen)) {
			return;
		}
		
		this.fullscreen = true;
		this.screen = screen;

		if (isCreated()) {
			glfwSetWindowMonitor(window, screen.getMonitor(), 0, 0,
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
			glfwSetWindowMonitor(window, NULL, x, y, width, height, GLFW_DONT_CARE);
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
				glfwShowWindow(window);
			} else {
				glfwHideWindow(window);
			}
		}
	}
	
	/**
	 * Checks if the window is decorated.
	 * @return floating attribute
	 */
	public final boolean isDecorated() {
		return glfwGetWindowAttrib(window, GLFW_DECORATED) == GLFW_TRUE;
	}

	/**
	 * Set the decorated attribute.
	 * @param iconified flag
	 */
	public final void setDecorated(boolean decorated) {
		this.decorated = decorated;
		if (isCreated()) {
			glfwSetWindowAttrib(window, GLFW_DECORATED, decorated ? GLFW_TRUE : GLFW_FALSE);
		}
	}
	
	/**
	 * Checks if the window is floating.
	 * @return floating attribute
	 */
	public final boolean isFloating() {
		return glfwGetWindowAttrib(window, GLFW_FLOATING) == GLFW_TRUE;
	}
	
	/**
	 * Set the floating attribute.
	 * @param floating flag
	 */
	public final void setFloating(boolean floating) {
		this.floating = floating;
		if (isCreated()) {
			glfwSetWindowAttrib(window, GLFW_FLOATING, floating ? GLFW_TRUE : GLFW_FALSE);
		}
	}

	/**
	 * Checks if the window is resizable.
	 * @return floating attribute
	 */
	public final boolean isResizable() {
		return glfwGetWindowAttrib(window, GLFW_RESIZABLE) == GLFW_TRUE;
	}
	
	/**
	 * Set the resizable attribute.
	 * @param resizeable flag
	 */
	public final void setResizeable(boolean resizable) {
		this.resizable = resizable;
		if (isCreated()) {
			glfwSetWindowAttrib(window, GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
		}
	}

	/**
	 * Checks if the window is iconified
	 * @return iconified attribute
	 */
	public final boolean isIconified() {
		return glfwGetWindowAttrib(window, GLFW_ICONIFIED) == GLFW_TRUE;
	}
	
	/**
	 * Set the minimized (iconified) attribute.
	 * @param iconified flag
	 */
	public final void setIconified() {
		glfwIconifyWindow(window);
	}
	
	/**
	 * Checks if the window is maximized.
	 * @return floating attribute
	 */
	public final boolean isMaximized() {
		return glfwGetWindowAttrib(window, GLFW_MAXIMIZED) == GLFW_TRUE;
	}

	/**
	 * Set the maximized attribute.
	 * @param maximized flag
	 */
	public final void setMaximized() {
		glfwMaximizeWindow(window);
	}

	@Override
	public final GlfwScreen getScreen() {
		return new GlfwScreen(glfwGetPrimaryMonitor());
	}

	@Override
	public final GlfwScreen[] getScreens() {
		PointerBuffer buff = glfwGetMonitors();
		GlfwScreen[] result = new GlfwScreen[buff.limit()];
		for (int i = 0; i < result.length; i++) {
			result[i] = new GlfwScreen(buff.get(i));
		}
		return result;
	}
	
	@Override
	public boolean shouldClose() {
		if (!isCreated()) {
			return true;
		}
		
		return glfwWindowShouldClose(window);
	}
	
	private boolean hasReference() {
		return window != NULL;
	}
	
	private boolean isCreated() {
		return (window != NULL) && (!disposed);
	}

	private void createWindow() {
		if (fullscreen) {
			glfwWindowHint(GLFW_REFRESH_RATE, (int) screen.getRefreshRate());
			window = glfwCreateWindow(screen.getWidth(), screen.getHeight(), title, screen.getMonitor(), NULL);
		} else {
			window = glfwCreateWindow(width, height, title, NULL, NULL);
		}
		
		setupAttributes();
		setupCallbacks();
	}
	
	private void setupCallbacks() {
		glfwSetWindowRefreshCallback(window, (long window) -> {
			//glfwSwapBuffers(window);
		});
	}
	
	private void setupAttributes() {
		if (x == -1 && y == -1) {
			glfwGetWindowPos(window, xpos, ypos);
			x = xpos.get(0);
			y = ypos.get(0);	
		} else {
			glfwSetWindowPos(window, x, y);
		}

		glfwSetWindowSizeLimits(window, minWidth, minHeight, maxWidth, maxHeight);
		glfwSetWindowAttrib(window, GLFW_DECORATED, decorated ? 1 : 0);
		glfwSetWindowAttrib(window, GLFW_FLOATING, floating ? 1 : 0);
		glfwSetWindowAttrib(window, GLFW_RESIZABLE, resizable? 1 : 0);
		
		if (iconified)
			glfwIconifyWindow(window);
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
