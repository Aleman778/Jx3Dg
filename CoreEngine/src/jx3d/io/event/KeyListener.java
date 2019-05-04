package jx3d.io.event;

/**
 * Key listener interface includes all events that are using the key event.
 */
public interface KeyListener {


    /**
     * Key down event is triggered when the user presses
     * a key on the keyboard.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(KEY_EVENTS);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     *
     * @param event the key down event
     */
    void keyDown(KeyEvent event);

    /**
     * Key up event is triggered when the user releases
     * a key on the keyboard.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(KEY_EVENTS);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     *
     * @param event the key up event
     */
    void keyUp(KeyEvent event);

    /**
     * The character event is triggered for each time the user presses any printable keys on the keyboard.
     * This is typically used for Graphical User Interfaces for retrieving text from user to display in a text box.
     * @param event the event that encapsulates the unicode character
     */
    void character(CharacterEvent event);
}
