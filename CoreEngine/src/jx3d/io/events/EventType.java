package jx3d.io.events;

/**
 * All types of events.
 */
public enum EventType {
    None,
    WindowClose, WindowResize, WindowFocus, WindowLostFocus, WindowMoved,
    AppTick, AppUpdate, AppRender,
    KeyDown, KeyUp, KeyTyped,
    MousePressed, MouseReleased, MouseMoved, MouseScrolled
}
