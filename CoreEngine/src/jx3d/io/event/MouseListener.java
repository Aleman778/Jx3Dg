package jx3d.io.event;

/**
 * Mouse listener interface includes all events that are using the mouse event.
 */
public interface MouseListener {

    /**
     * Mouse pressed event is triggered when the user presses
     * a button on the mouse.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     *
     * @param event the mouse event
     */
    void mousePressed(MouseEvent event);

    /**
     * Mouse released event is triggered when the user releases
     * a button on the mouse.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     *
     * @param event the mouse event
     */
    void mouseReleased(MouseEvent event);

    /**
     * Mouse entered event is triggered when the user moves the
     * cursor on this node.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     *
     * @param event the mouse event
     */
    void mouseEntered(MouseEvent event);

    /**
     * Mouse exited event is triggered when the user moves the
     * cursor away from this node.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     *
     * @param event the mouse event
     */
    void mouseExited(MouseEvent event);

    /**
     * Mouse moved event is triggered when the user moves the cursor.
     * If the user presses and holds a button then the
     * {@link #mouseDragged(MouseEvent)} event is used instead.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     *
     * @param event the mouse event
     */
    void mouseMoved(MouseEvent event);

    /**
     * Mouse dragged event is triggered when the user moves the cursor
     * after pressing and holding a button. If the user releases the button
     * then the {@link #mouseMoved(MouseEvent)} event is used instead.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(MOUSE_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     *
     * @param event the mouse event
     */
    void mouseDragged(MouseEvent event);
}
