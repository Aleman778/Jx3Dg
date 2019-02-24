package jx3d.graphics;

/**
 * Camera with an orthographic projection.
 * The projection is two dimensional and
 * its view frustum is defined by box.
 * The camera can be moved and rotated
 * in 2d space.
 *
 * @author Aleman778
 */
public class OrthographicCamera extends Camera {

    /**
     * Viewing distance.
     */
    private float left, right, bottom, top;

    /**
     * Default constructor.
     * Create an orthographic camera that takes up the entire display screen.
     */
    public OrthographicCamera() {
        this(new Viewport());
    }

    /**
     * Constructor.
     * Create an orthographic camera with a provided viewport.
     *
     * @param viewport a custom viewport to use
     */
    public OrthographicCamera(Viewport viewport) {
        super(viewport);
    }

    /**
     * @see OrthographicCamera#setOrtho(float, float, float, float, float, float)
     */
    public void setOrtho(float left, float right, float bottom, float top) {
        setOrtho(left, right, bottom, top, near, far);
    }

    /**
     * Setup the orthographic projection parameters.
     * The parameters define the dimensions of a box and within
     * that box objects are drawn within.
     *
     * @param left   the left viewing distance
     * @param right  the right viewing distance
     * @param bottom the bottom viewing distance
     * @param top    the top viewing distance
     * @param near   the nearest viewing distance
     * @param far    the farthest viewing distance
     */
    public void setOrtho(float left, float right, float bottom, float top, float near, float far) {
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;
        this.near = near;
        this.far = far;
        this.validProj = false;
    }

    /**
     * Get the left viewing distance.
     *
     * @return the left viewing distance
     */
    public float getLeft() {
        return left;
    }

    /**
     * Set the left viewing distance.
     *
     * @param left the left viewing distance
     */
    public void setLeft(float left) {
        this.left = left;
        validProj = false;
    }

    /**
     * Get the right viewing distance.
     *
     * @return the right viewing distance
     */
    public float getRight() {
        return right;
    }

    /**
     * Set the right viewing distance.
     *
     * @param right the right viewing distance
     */
    public void setRight(float right) {
        this.right = right;
    }

    /**
     * Get the bottom viewing distance.
     *
     * @return the bottom viewing distance
     */
    public float getBottom() {
        return bottom;
    }

    /**
     * Set the bottom viewing distance.
     *
     * @param bottom the bottom viewing distance
     */
    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    /**
     * Get the top viewing distance.
     *
     * @return the top viewing distance
     */
    public float getTop() {
        return top;
    }

    /**
     * Set the top viewing distance.
     *
     * @param top the top viewing distance
     */
    public void setTop(float top) {
        this.top = top;
    }

    @Override
    protected void validateProjection() {
        projection.ortho(left, right, bottom, top, near, far);
    }

    @Override
    protected void validateView() {
    }
}
