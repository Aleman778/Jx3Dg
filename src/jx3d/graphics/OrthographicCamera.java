package jx3d.graphics;

import jx3d.math.Frustum;
import jx3d.math.Transform2D;

/**
 * Camera with an orthographic projection.
 * The projection is two dimensional and
 * its view frustum is defined by box.
 * The camera can be moved and rotated.
 * @author Aleman778
 */
public class OrthographicCamera extends Camera {
	
	private Frustum frustum;
	
	private Transform2D t;
	
	public OrthographicCamera() {
		this(new Viewport());
	}
	
	public OrthographicCamera(Viewport viewport) {
		super(viewport);
	}
	
	public OrthographicCamera(float left, float right, float bottom,
							 float top, float near, float far) {
		this(new Viewport(), left, right, bottom, top, near, far);
	}
	
	public OrthographicCamera(Viewport viewport, float left, float right, float bottom,
							 float top, float near, float far) {
		super(viewport);
		this.far = far;
		this.near = near;
		this.projection = projection.orthographic(left, right, bottom, top, near, far);
		this.valid = false;
	}
}
