package jx3d.io.events;

/**
 * Event listener contains callback functions to all evens that the core engine uses.
 */
public interface EventListener {

    /**
     * The setup event is triggered once this object is ready to be used.
     * Use this method for initializing variables and other settings.
     */
    void setup();

    /**
     * The update event is triggered for each time the window requests an update from the running program,
     * this is normally performed via a loop that continuously triggers this event 30 times every second.
     * The rate in which this is executed can be customized.
     */
    void update();

    /**
     * The draw event is triggered for each time the window requests a new frame from the running program,
     * this is normally performed via a loop that continuously requests new frames 30 times every second.
     * The rate at which new frames are requested (frame rate) can be customized.
     */
    void draw();

    /**
     * Key down event is triggered when the user presses
     * a key on the keyboard.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(KEY_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param key the pressed key
     */
    void keyDown(int key);

    /**
     * Key up event is triggered when the user releases
     * a key on the keyboard.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(KEY_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param key the released key
     */
    void keyUp(int key);

    /**
     * Key repeat event is triggered repeatedly when the user holds
     * down a key on the keyboard for some time.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(KEY_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param key the repeating key
     */
    void keyRepeat(int key);

    /**
     * Mouse pressed event is triggered when the user presses
     * a button on the mouse.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param button the pressed mouse button
     */
    void mousePressed(int button);

    /**
     * Mouse released event is triggered when the user releases
     * a button on the mouse.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param button the released mouse button
     */
    void mouseReleased(int button);

    /**
     * Mouse clicked event is triggered when the user clicks
     * a button on the mouse.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param button the clicked mouse button
     */
    void mouseClicked(int button);

    /**
     * Mouse entered event is triggered when the user moves the
     * cursor on this node.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
     * </p>
     */
    void mouseEntered();

    /**
     * Mouse exited event is triggered when the user moves the
     * cursor away from this node.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
     * </p>
     */
    void mouseExited();

    /**
     * Mouse moved event is triggered when the user moves the cursor.
     * If the user presses and holds a button then the
     * {@link #mouseDragged(float, float)} event is used instead.
     * The change in position is provided as argument.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param dx the movement in the x direction
     * @param dy the movement in the y direction
     */
    void mouseMoved(float dx, float dy);

    /**
     * Mouse dragged event is triggered when the user moves the cursor
     * after pressing and holding a button. If the user releases the button
     * then the {@link #mouseMoved(float, float)} event is used instead.
     * The mouse button and the change in position is provided as argument.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param dx the movement in the x direction
     * @param dy the movement in the y direction
     */
    void mouseDragged(float dx, float dy);

    /**
     * <p>
     * Mouse scrolled event is triggered when the user scrolls on the
     * mouse wheel or using touch gesture on a trackpad.
     * The change in the scroll position is provided as argument.
     * </p>
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param dx the scrolling in the x direction
     * @param dy the scrolling in the y direction
     */
    void mouseScrolled(float dx, float dy);

    /**
     * Window resized event is triggered when the user resizes the window.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param width  the new width of the window
     * @param height the new height of the window
     */
    void windowResized(int width, int height);

    /**
     * Window moved event is triggered when the user moves the window.
     *
     * @param x the new x location of the window
     * @param y the new y location of the window
     *          <p>
     *          <i>Note:</i> in order for this event to be used you need to call
     *          <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
     *          </p>
     */
    void windowMoved(int x, int y);

    /**
     * Window focus event is triggered when the window has lost focus
     * or when then user interacts with the window and it regains the focus.
     * The focused state is provided as argument.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param focused true if the window is focused, false otherwise
     */
    void windowFocus(boolean focused);

    /**
     * Window iconify event is triggered when the user iconify (minimize) the window
     * or when the user restores the window.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param iconified true if the window is iconified, false otherwise
     */
    void windowIconify(boolean iconified);

    /**
     * Window maximize event is triggered when the user maximizes the window
     * or when the user restores the window.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
     * </p>
     *
     * @param maximized true if the window is maximized, false otherwise
     */
    void windowMaximize(boolean maximized);

    /**
     * Window closed event is triggered when the user closes the window.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
     * </p>
     */
    void windowClosed();
}
