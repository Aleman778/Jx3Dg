package jx3d.io.event;

import jx3d.core.Module;

/**
 * The mouse scroll event is an extension of mouse event, this class provides with
 * the mouse scrolling offset in the vertical and horizontal direction.
 */
public class MouseScrollEvent extends MouseEvent {

    /**
     * The horizontal mouse scrolling offset.
     */
    private float scrollX;

    /**
     * The vertical mouse scrolling offset.
     */
    private float scrollY;

    /**
     * Constructs a {@link MouseScrollEvent} object with specific parameters.
     * @param name the name of the event
     * @param x the mouse x position
     * @param y the mouse y position
     * @param dx the change in mouse x position
     * @param dy the change in mouse y position
     * @param scrollX the horizontal mouse scroll
     * @param scrollY the vertical mouse scroll
     * @param clickCount the total click count
     */
    public MouseScrollEvent(String name, int mods, float x, float y, float dx, float dy,
                            float scrollX, float scrollY, int clickCount) {

        super(EventType.MouseScrolled, name, Module.MOUSE_NOBUTTON, mods, x, y, dx, dy, clickCount);

        this.scrollX = scrollX;
        this.scrollY = scrollY;
    }

    /**
     * Get the horizontal scrolling offset.
     * @return the scroll offset
     */
    public float getScrollX() {
        return scrollX;
    }

    /**
     * Get the vertical scrolling offset.
     * @return the scroll offset
     */
    public float getScrollY() {
        return scrollY;
    }

    @Override
    public String toString() {
        return super.toString() + " {scrollX=" + scrollX + ", scrollY=" + scrollY +"}";
    }
}
