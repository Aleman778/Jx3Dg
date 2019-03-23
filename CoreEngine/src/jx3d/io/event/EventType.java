package jx3d.io.event;

/**
 * All types of event.
 */
public enum EventType {
    None,
    WindowClose, WindowResize, WindowFocus, WindowLostFocus, WindowMoved,
    Setup, Update, Draw,
    KeyDown, KeyUp, KeyTyped,
    MousePressed, MouseReleased, MouseMoved, MouseDragged, MouseScrolled
}
