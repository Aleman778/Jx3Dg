package jx3d.core;

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
	 * Mouse cursor position.
	 */
	private double mouseX, mouseY;
	
	/**
	 * Array of mouse buttons states, if the value is true
	 * then the specific button is being hold down. 
	 */
	private boolean[] mouseState;
	
	
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
		this.mouseState = new boolean[Module.MOUSE_BUTTON_LAST];
	}
	
	/**
	 * Add a new key listener node.
	 * @param node the key listener
	 */
	public void addKeyListener(Node node) {
		keyListeners.add(node);
	}

	/**
	 * Add a new mouse listener node.
	 * @param node the mouse listener
	 */
	public void addMouseListener(Node node) {
		mouseListeners.add(node);
	}

	/**
	 * Add a new window listener node.
	 * @param node the window listener
	 */
	public void addWindowListener(Node node) {
		windowListeners.add(node);
	}
}
