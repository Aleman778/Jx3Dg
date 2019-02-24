package jx3d.graphics;

/**
 * Viewport is a rectangle that defines drawing area of the
 * applications display, in screen-spaced coordinates.
 *
 * @author Aleman778
 * @since 1.0
 */
public class Viewport {

    /**
     * The viewport x position.
     */
    public float x;

    /**
     * The viewport y position.
     */
    public float y;

    /**
     * The width of the viewport rectangle.
     */
    public float width;

    /**
     * The height of the viewport rectangle.
     */
    public float height;

    /**
     * Default Constructor.
     * Create a viewport that covers the entire display.
     */
    public Viewport() {
        this(0, 0, 1, 1);
    }

    /**
     * Constructor.
     * Create a viewport with a defined screen-spaced rectangle.
     *
     * @param x      the viewport x position
     * @param y      the viewport y position
     * @param width  the viewport width
     * @param height the viewport height
     */
    public Viewport(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor.
     * Create a copy of the provided viewport
     *
     * @param copy the viewport object to copy
     */
    public Viewport(Viewport copy) {
        this.x = copy.x;
        this.y = copy.y;
        this.width = copy.width;
        this.height = copy.height;
    }
}
