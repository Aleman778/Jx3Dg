package jx3d.io.event;

/**
 * Window listener interface includes all events that are using the window event.
 */
public interface WindowListener {

    /**
     * Window resized event is triggered when the user resize the window.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     * @param event the window event
     */
    void windowResized(WindowEvent event);

    /**
     * Window moved event is triggered when the user moves the window.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     * @param event the window event
     */
    void windowMoved(WindowEvent event);

    /**
     * Window focus event is triggered when then user interacts with the window and it regains the focus.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     * @param event the window event
     */
    void windowFocus(WindowEvent event);

    /**
     * Window focus event is triggered when the window has lost focus.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     * @param event the window event
     */
    void windowLostFocus(WindowEvent event);

    /**
     * Window iconify event is triggered when the user iconify (minimize) the window
     * or when the user restores the window.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     * @param event the window event
     */
    void windowIconify(WindowEvent event);

    /**
     * Window maximize event is triggered when the user maximizes the window
     * or when the user restores the window.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     * @param event the window event
     */
    void windowMaximize(WindowEvent event);

    /**
     * Window closed event is triggered when the user closes the window.
     * <p>
     * <i>Note:</i> in order for this event to be used you need to call
     * <code>install(WINDOW_EVENT);</code> in the {@link jx3d.core.Node#setup()} method.
     * </p>
     * @param event the window event
     */
    void windowClose(WindowEvent event);
}
