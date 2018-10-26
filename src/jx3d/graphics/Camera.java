package jx3d.graphics;

import org.joml.Matrix4f;

import jx3d.core.Node;
import jx3d.math.Frustum;
import jx3d.math.Transform;

/**
 * Abstract camera class defines basic fields and methods that
 * are implemented by a 2D-/ orthographic- and 3D/ perspective camera.
 * @author Aleman778
 */
public abstract class Camera extends Node {

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
	protected Matrix4f view;
	
	/**
	 * The projection matrix is defines how all objects are mapped onto a 2D screen.
	 */
	protected Matrix4f projection;
	
	/**
	 * The combined matrix is defined as <code>mapping = projection * view</code>.
	 */
	protected Matrix4f combined;
	
	/**
	 * The far clipping plane distance.
	 */
	protected float far;
	
	/**
	 * The near clipping plane distance.
	 */
	protected float near;
	
	/**
	 * Valid Projection flag is set if there are any changes specifically to the projection matrix. 
	 */
	protected boolean validProj;

	/**
	 * Valid Projection flag is set if there are any changes specifically to the view matrix. 
	 */
	protected boolean validView;
	
	/**
	 * Transformation object.
	 */
	protected Transform transform;
	
	/**
	 * Default constructor.
	 * The viewport will be set to <code>(x=0, y=0, w=1, h=1)</code>.
	 * @see #Camera(Viewport)
	 */
	public Camera() {
		this(new Viewport());
	}
	
	/**
	 * Constructor.
	 * @param viewport an area of the window where the camera draws on (in screen space coordinates)
	 */
	public Camera(Viewport viewport) {
		 this.viewport = viewport;
		 this.view = new Matrix4f();
		 this.projection = new Matrix4f();
		 this.combined = new Matrix4f();
		 this.validView = false;
		 this.validProj = false;
		 this.near = -100000;
		 this.far = 100000;
		 this.transform = new Transform();
	}
	
	@Override
	public void setup() {
		install(WINDOW_EVENTS);
	}
	
	@Override
	public void windowResized(int width, int height) {
		System.out.println("Whats up!");
		validProj = false;
	}
	
	/**
	 * Get the viewport used by this camera.
	 * @return a viewport
	 */
	public Viewport getViewport() {
		return new Viewport(viewport);
	}
	
	/**
	 * Set the viewport to be used by this camera.
	 * @param viewport the viewport to set
	 */
	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}
	
	/**
	 * Get the nearest viewing distance.
	 * @return the near distance
	 */
	public float getNear() {
		return near;
	}
	
	/**
	 * Set the nearest viewing distance
	 * @param near the near distance to set
	 */
	public void setNear(float near) {
		this.near = near;
		this.validProj = false;
	}
	
	/**
	 * Get the farthest viewing distance.
	 * @return the far
	 */
	public float getFar() {
		return far;
	}
	
	/**
	 * Set the farthest viewing distance
	 * @param far the far distance to set
	 */
	public void setFar(float far) {
		this.far = far;
		this.validProj = false;
	}
	
	/**
	 * Get the view matrix.
	 * @return a view matrix
	 */
	public Matrix4f getView() {
		return new Matrix4f(view);
	}
	
	/**
	 * Get the projection matrix.
	 * @return a projection matrix
	 */
	public Matrix4f getProjection() {
		return projection;
	}
	
	/**
	 * Get the mapping matrix.
	 * This is the view and projection matrices combined.
	 * @return a mapping matrix 
	 */
	public Matrix4f getMapping() {
		validate();
		return new Matrix4f(combined);
	}
	
	/**
	 * Validate the combined matrix.
	 */
	protected void validate() {
		if (!validProj)
			validateProjection();
		
		if (!validView)
			validateView();
		
		if (!validProj || !validView)
			projection.mul(view, combined);
		
		validProj = true;
		validView = true;
	}
	
	/**
	 * Validates the view matrix.
	 */
	protected abstract void validateProjection();
	
	/**
	 * Validates the projection matrix.
	 */
	protected abstract void validateView();
}