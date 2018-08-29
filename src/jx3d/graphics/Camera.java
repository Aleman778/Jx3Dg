package jx3d.graphics;

import jx3d.math.Frustum;
import jx3d.math.Matrix44;

/**
 * Abstract camera class defines basic fields and methods that
 * are implemented by a 2D-/ orthographic- and 3D/ perspective camera.
 * @author Aleman778
 */
public abstract class Camera {

	/**
	 * The cameras view frustum.
	 */
	protected Frustum frustum;
	
	/**
	 * The viewport is a rectangle on the display in which the
	 * graphics are drawn onto. 
	 */
	protected Viewport viewport;
	
	/**
	 * The view matrix is the cameras movement or rotation.
	 */
	protected Matrix44 view;
	
	/**
	 * The projection matrix is defines how all objects are mapped onto a 2D screen.
	 */
	protected Matrix44 projection;
	
	/**
	 * The combined matrix is defined as <code>mapping = projection * view</code>.
	 */
	protected Matrix44 combined;
	
	/**
	 * The far clipping plane distance.
	 */
	protected float far;
	
	/**
	 * The near clipping plane distance.
	 */
	protected float near;
	
	/**
	 * Valid flag is set if there are any changes to the view or projection matrices.
	 */
	protected boolean valid;
	
	/**
	 * Default constructor.
	 */
	public Camera() {
		this(new Viewport());
	}
	
	/**
	 * Constructor.
	 * @param viewport the 
	 */
	public Camera(Viewport viewport) {
		 this.viewport = viewport;
		 this.view = new Matrix44();
		 this.projection = new Matrix44();
		 this.combined = new Matrix44();
		 this.valid = true;
	}
	
	public Viewport getViewport() {
		return viewport;
	}
	
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}
	
	public Matrix44 getView() {
		return view;
	}
	
	public Matrix44 getProjection() {
		return projection;
	}
	
	public Matrix44 getMapping() {
		validate();
		return combined;
	}
	
	public void validate() {
		if (valid)
			return;
		
		combined = projection.mul(view);
	}
}