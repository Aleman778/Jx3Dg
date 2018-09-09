package jx3d.graphics;

import org.joml.Matrix4f;
import org.joml.Vector2f;

/**
 * Camera with an orthographic projection.
 * The projection is two dimensional and
 * its view frustum is defined by box.
 * The camera can be moved and rotated
 * in 2d space.
 * @author Aleman778
 */
public class OrthographicCamera extends Camera {
	
	/**
	 * The cameras position in 2d space.
	 */
	private Vector2f position;
	
	/**
	 * The cameras orientation in 2d space.
	 */
	private float rotation;
	
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
	 * @param viewport
	 */
	public OrthographicCamera(Viewport viewport) {
		super(viewport);
		position = new Vector2f();
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
	 * @param left the left viewing distance
	 * @param right the right viewing distance
	 * @param bottom the bottom viewing distance
	 * @param top the top viewing distance
	 * @param near the nearest viewing distance
	 * @param far the farthest viewing distance
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
	 * Set the cameras position.
	 * @param x the x position
	 * @param y the y position
	 */
	public void setPos(float x, float y) {
		position = new Vector2f(x, y);
		validView = false;
	}
	
	/**
	 * Get the cameras position
	 * @return a vector holding the position of the camera
	 */
	public Vector2f getPos() {
		return new Vector2f(position);
	}
	
	/**
	 * Move the cameras position.
	 * @param x the horizontal translation
	 * @param y the vertical translation
	 */
	public void translate(float x, float y) {
		position.add(x, y);
		validView = false;
	}
	
	/**
	 * Set the cameras rotation to the provided angle.
	 * @param angle the angle to set
	 */
	public void setRotation(float angle) {
		rotation = angle;
		validView = false;
	}
	
	/**
	 * Get the cameras rotation.
	 * @return the rotation of the camera
	 */
	public float getRotation() {
		return rotation;
	}
	
	/**
	 * Rotate the camera by a provided angle
	 * @param angle the angle to rotate
	 */
	public void rotate(float angle) {
		rotation += angle;
		validView = false;
	}
	
	/**
	 * Get the left viewing distance.
	 * @return the left viewing distance
	 */
	public float getLeft() {
		return left;
	}
	
	/**
	 * Get the left viewing distance.
	 * @return the left viewing distance
	 */
	public void setLeft(float left) {
		this.left = left;
		validProj = false;
	}
	
	/**
	 * Get the left viewing distance.
	 * @return the left viewing distance
	 */
	public float getRight() {
		return right;
	}
	
	/**
	 * Get the left viewing distance.
	 * @return the left viewing distance
	 */
	public void setRight(float right) {
		this.right = right;
	}
	
	/**
	 * Get the left viewing distance.
	 * @return the left viewing distance
	 */
	public float getBottom() {
		return bottom;
	}
	
	/**
	 * Get the left viewing distance.
	 * @return the left viewing distance
	 */
	public void setBottom(float bottom) {
		this.bottom = bottom;
	}
	
	/**
	 * Get the left viewing distance.
	 * @return the left viewing distance
	 */
	public float getTop() {
		return top;
	}
	
	/**
	 * Get the left viewing distance.
	 * @return the left viewing distance
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
		view = new Matrix4f().rotateZ(-rotation)
							 .translate(-position.x, -position.y, 0);
	}
}
