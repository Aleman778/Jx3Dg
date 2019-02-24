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

    public void keyDownProc(int key, int mods) {
        if (key >= KEY_FIRST && key <= KEY_LAST) {
            keyboardState[key] = true;
        }
        window.keyDown(key);
        for (Node n : keyListeners) {
            n.keyDown(key);
        }
    }

    public void keyUpProc(int key, int mods) {
        if (key >= KEY_FIRST && key <= KEY_LAST) {
            keyboardState[key] = false;
        }
        window.keyUp(key);
        for (Node n : keyListeners) {
            n.keyUp(key);
        }
    }

    public void keyRepeatProc(int key, int mods) {
        window.keyRepeat(key);
        for (Node n : keyListeners) {
            n.keyDown(key);
        }
    }

    public void mousePressedProc(int button, int mods) {
        if (button >= MOUSE_BUTTON_FIRST && button <= MOUSE_BUTTON_LAST) {
            mouseState[button] = true;
            mouseButtons |= 0x1 << button;
        }
        window.mousePressed(button);
        for (Node n : mouseListeners) {
            n.mousePressed(button);
        }
    }

    public void mouseReleasedProc(int button, int mods) {
        if (button >= MOUSE_BUTTON_FIRST && button <= MOUSE_BUTTON_LAST) {
            mouseState[button] = false;
            mouseButtons &= ~(0x1 << button);
        }
        window.mouseReleased(button);
        for (Node n : mouseListeners) {
            n.mouseReleased(button);
        }
    }

    public void mouseEnteredProc() {
        window.mouseEntered();
        for (Node n : mouseListeners) {
            n.mouseEntered();
        }
    }

    public void mouseExitedProc() {
        window.mouseExited();
        for (Node n : mouseListeners) {
            n.mouseExited();
        }
    }

    public void mouseMovedProc(float xpos, float ypos) {
        float dx = xpos - mouseX;
        float dy = ypos - mouseY;
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

    public void mouseScrolledProc(float dx, float dy) {
        window.mouseScrolled(dx, dy);
        for (Node n : mouseListeners) {
            n.mouseScrolled(dx, dy);
        }
    }

    public void windowResizedProc(int width, int height) {
        window.windowResized(width, height);
        for (Node n : windowListeners) {
            n.windowResized(width, height);
        }
    }

    public void windowMovedProc(int x, int y) {
        window.windowMoved(x, y);
        for (Node n : windowListeners) {
            n.windowMoved(x, y);
        }
    }

    public void windowFocusProc(boolean focused) {
        window.windowFocus(focused);
        for (Node n : windowListeners) {
            n.windowFocus(focused);
        }
    }

    public void windowIconifyProc(boolean iconified) {
        window.windowIconify(iconified);
        for (Node n : windowListeners) {
            n.windowIconify(iconified);
        }
    }

    public void windowMaximizeProc(boolean maximized) {
        window.windowMaximize(maximized);
        for (Node n : windowListeners) {
            n.windowMaximize(maximized);
        }
    }

    public void windowClosedProc() {
        window.windowClosed();
        for (Node n : windowListeners) {
            n.windowClosed();
        }
    }

    /**
     * Add a key listener for a specific node.
     *
     * @param node the key listener
     */
    public void addKeyListener(Node node) {
        keyListeners.add(node);
    }

    /**
     * Remove the key listener of a specific node.
     *
     * @param node the node to remove its key listener
     * @return false if the provided node has no key listener, true otherwise
     */
    public boolean removeKeyListener(Node node) {
        return keyListeners.remove(node);
    }

    /**
     * Add a new mouse listener node.
     *
     * @param node the mouse listener
     */
    public void addMouseListener(Node node) {
        mouseListeners.add(node);
    }

    /**
     * Remove the mouse listener of a specific node.
     *
     * @param node the node to remove its mouse listener
     * @return false if the provided node has no mouse listener, true otherwise
     */
    public boolean removeMouseListener(Node node) {
        return mouseListeners.remove(node);
    }

    /**
     * Add a new window listener node.
     *
     * @param node the window listener
     */
    public void addWindowListener(Node node) {
        windowListeners.add(node);
    }

    /**
     * Remove the window listener of a specific node.
     *
     * @param node the node to remove its window listener
     * @return false if the provided node has no window listener, true otherwise
     */
    public boolean removeWindowListener(Node node) {
        return windowListeners.remove(node);
    }

    /**
     * The current mouse location in the x-axis.
     *
     * @return the mouseX
     */
    public float getMouseX() {
        return mouseX;
    }

    /**
     * The current mouse location in the y-axis.
     *
     * @return the mouseY
     */
    public float getMouseY() {
        return mouseY;
    }

    /**
     * The buttons that are currently being held down.
     * The each bit in the byte represents one of the 8 mouse buttons.
     *
     * @return the mouse buttons byte
     */
    public byte getMouseButtons() {
        return mouseButtons;
    }

    /**
     * Get the state of a specific key on the keyboard.
     *
     * @param key the key to check
     * @return true if the key is being held down, false otherwise
     */
    public boolean getKeyState(int key) {
        if (key < KEY_FIRST || key > KEY_LAST)
            throw new IllegalArgumentException("The provided key identifier (" + key + ") is out of the range of the defined standard keys.");

        return keyboardState[key];
    }

    /**
     * Get the state of a specific mouse button.
     *
     * @param button the button to check
     * @return true if the mouse button is being held down, false otherwise
     */
    public boolean getMouseState(int button) {
        if (button < MOUSE_BUTTON_FIRST || button > MOUSE_BUTTON_LAST)
            throw new IllegalArgumentException("The provided mouse button identifier (" + button + ") is out of the range of 0 through 8.");

        return mouseState[button];
    }
}
