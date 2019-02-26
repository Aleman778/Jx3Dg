package jx3d.io;

import jx3d.core.Node;
import jx3d.core.Window;

import java.util.HashSet;
import java.util.Set;

import static jx3d.core.Module.*;

/**
 * <p>
 * Input class is an abstract class that contains functions for handling input
 * events from the windowing system. The window and nodes have their own input
 * handling callback functions but only callbacks from the window will be
 * automatically triggered.
 * </p>
 * <p>
 * In order to receive callbacks for a specific node
 * you have to manually add it the set of listeners using the add listeners functions
 * e.g. {@link Node#install(int)} where the <code>int</code> is the type of event listener.
 * However your nodes will always have access to the current input state using functions
 * like {@link Node#mouseX()} to get the location of the mouse in the x-axis.
 * </p>
 * <p>
 * This basic class has a general implementation
 * but because some platform have specific input handlers you should always implement
 * this class for each windowing system. This class also contains states for
 * some input devices such as the mouse, keyboard etc.
 * </p>
 *
 * @author Aleman778
 * @since 1.0
 */
public abstract class Input {

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
    private float mouseX, mouseY;

    /**
     * Array of mouse button states, if the value is true
     * then the specific button is being held down.
     */
    private boolean[] mouseState;

    /**
     * Array of keyboard button states if the value is true
     * then the specific button is being held down.
     */
    private boolean[] keyboardState;


    /**
     * Constructor.
     * Creates a new input handler object.
     *
     * @param window the window owner.
     */
    public Input(Window window) {
        this.window = window;
        this.keyListeners = new HashSet<>();
        this.mouseListeners = new HashSet<>();
        this.windowListeners = new HashSet<>();
        this.mouseState = new boolean[MOUSE_BUTTON_LAST + 1];
        this.keyboardState = new boolean[KEY_LAST + 1];
    }

}
