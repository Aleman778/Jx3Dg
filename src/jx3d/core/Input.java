package jx3d.core;

import static jx3d.core.Module.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @since 1.0
 * @author Aleman778
 */
public class Input {

	/**
	 * The window owner.
	 */
	protected final Window window;
	
	/**
	 * Map of all installed key listeners in this window.
	 */
	private Set<Node> keyListeners;

	/**
	 * Map of all installed mouse listeners in this window.
	 */
	private Set<Node> mouseListeners;

	/**
	 * Map of all installed window listeners in this window.
	 */
	private Set<Node> windowListeners;

	/**
	 * Set of nodes that are setup for being dragged.
	 */
	private Set<Node> dragging;
	
	/**
	 * Mouse buttons that are being held down, every bit represents
	 * one button where the first bit is button 1 and the last is button 8.
	 */
	private byte mouseButtons;
	
	/**
	 * Mouse cursor position.
	 */
	public double mouseX, mouseY;
	
	/**
	 * Array of mouse button states, if the value is true
	 * then the specific button is being held down. 
	 */
	public boolean[] mouseState;
	
	/**
	 * Array of keyboard button states if the value is true
	 * then the specific button is being held down.
	 */
	public boolean[] keyboardState;
	
	
	/**
	 * Constructor.
	 * Creates a new input handler object.
	 * @param window the window owner.
	 */
	public Input(Window window) {
		this.window = window;
		this.keyListeners = new HashSet<>();
		this.mouseListeners = new HashSet<>();
		this.windowListeners = new HashSet<>();
		this.mouseState = new boolean[Module.MOUSE_BUTTON_LAST + 1];
		this.keyboardState = new boolean[Module.KEY_LAST + 1];
	}
	
	public final void keyDownProc(int key, int mods) {
		if (key >= KEY_FIRST && key <= KEY_LAST) {
			keyboardState[key] = true;
		}
		window.keyDown(key);
		for (Node n : keyListeners) {
			n.keyDown(key);
		}
	}
	
	public final void keyUpProc(int key, int mods) {
		if (key >= KEY_FIRST && key <= KEY_LAST) {
			keyboardState[key] = false;
		}
		window.keyUp(key);
		for (Node n : keyListeners) {
			n.keyUp(key);
		}
	}
	
	public final void keyRepeatProc(int key, int mods) {
		window.keyRepeat(key);
		for (Node n : keyListeners) {
			n.keyDown(key);
		}
	}
	
	public final void mousePressedProc(int button, int mods) {
		if (button >= MOUSE_BUTTON_FIRST && button <= MOUSE_BUTTON_LAST) {
			mouseState[button] = true;
			mouseButtons |= 0x1 << button;
		}
		window.mousePressed(button);
		for (Node n : mouseListeners) {
			n.mousePressed(button);
		}
	}
	
	public final void mouseReleasedProc(int button, int mods) {
		if (button >= MOUSE_BUTTON_FIRST && button <= MOUSE_BUTTON_LAST) {
			mouseState[button] = false;
			mouseButtons &= ~(0x1 << button);
		}
		window.mouseReleased(button);
		for (Node n : mouseListeners) {
			n.mouseReleased(button);
		}
	}
	
	public final void mouseEnteredProc() {
		window.mouseEntered();
		for (Node n : mouseListeners) {
			n.mouseEntered();
		}
	}
	
	public final void mouseExitedProc() {
		window.mouseExited();
		for (Node n : mouseListeners) {
			n.mouseExited();
		}
	}
	
	public final void mouseMovedProc(double xpos, double ypos) {
		double dx = xpos - mouseX;
		double dy = ypos - mouseY;
		mouseX = xpos;
		mouseY = ypos;
		
		if (mouseButtons > 0) {
			window.mouseDragged(dx, dy);
		} else {
			window.mouseMoved(dx, dy);
		}
		for (Node n : mouseListeners) {
			if (mouseButtons > 0) {
				n.mouseDragged(dx, dy);
			} else {
				n.mouseMoved(dx, dy);
			}
		}
	}
	
	public final void mouseScrolledProc(double dx, double dy) {
		window.mouseScrolled(dx, dy);
		for (Node n : mouseListeners) {
			n.mouseScrolled(dx, dy);
		}
	}
	
	public final void windowResizedProc(int width, int height) {
		window.windowResized(width, height);
		for (Node n : windowListeners) {
			n.windowResized(width, height);
		}
	}
	
	public final void windowMovedProc(int x, int y) {
		window.windowMoved(x, y);
		for (Node n : windowListeners) {
			n.windowMoved(x, y);
		}
	}
	
	public final void windowFocusProc(boolean focused) {
		window.windowFocus(focused);
		for (Node n : windowListeners) {
			n.windowFocus(focused);
		}
	}
	
	public final void windowIconifyProc(boolean iconified) {
		window.windowIconify(iconified);
		for (Node n : windowListeners) {
			n.windowIconify(iconified);
		}
	}
	
	public final void windowMaximizeProc(boolean maximized) {
		window.windowMaximize(maximized);
		for (Node n : windowListeners) {
			n.windowMaximize(maximized);
		}
	}
	
	public final void windowClosedProc() {
		window.windowClosed();
		for (Node n : windowListeners) {
			n.windowClosed();
		}
	}
	
	/**
	 * Add a new key listener node.
	 * @param node the key listener
	 */
	public final void addKeyListener(Node node) {
		keyListeners.add(node);
	}

	/**
	 * Add a new mouse listener node.
	 * @param node the mouse listener
	 */
	public final void addMouseListener(Node node) {
		mouseListeners.add(node);
	}

	/**
	 * Add a new window listener node.
	 * @param node the window listener
	 */
	public final void addWindowListener(Node node) {
		windowListeners.add(node);
	}
}
