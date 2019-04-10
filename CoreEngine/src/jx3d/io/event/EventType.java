package jx3d.io.event;

/**
 * All types of event.
 */
public enum EventType {
    None,
    WindowMoved, WindowResize, WindowFocus, WindowLostFocus, WindowIconified, WindowMaximized, WindowClose,
    AppSetup, AppUpdate, AppDraw,
    KeyDown, KeyUp,
    MousePressed, MouseReleased, MouseMoved, MouseDragged, MouseScrolled, MouseEntered, MouseExited
}
