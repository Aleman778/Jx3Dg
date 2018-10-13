package jx3d.graphics;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import jx3d.core.Module;

/**
 * Camera with a perspective projection
 * The projection is two dimensional and
 * its view frustum shape is defined by a 
 * truncated square pyramid. The camera
 * can be moved and rotated in 3d space.
 * @since 1.0
 * @author Aleman778
 */
public class PerspectiveCamera extends Camera {

	/**
	 * The cameras position in 3d space.
	 */
	private Vector3f position;
	
	/**
	 * The cameras orientation in 3d space.
	 */
	private Quaternionf rotation;
	
	/**
	 * The cameras field of view.
	 */
	private float fov;
	
	/**
	 * The cameras aspect ratio.
	 * If this value is set to anything less than 0
	 * then the aspect ratio of the display will be used.
	 */
	private float aspect;
	
	/**
	 * Default constructor.
	 * Create an orthographic camera that takes up the entire display screen.
	 */
	public PerspectiveCamera() {
		this(new Viewport());
	}
	
	/**
	 * Constructor.
	 * Create an orthographic camera with a provided viewport.
	 * @param viewport
	 */
	public PerspectiveCamera(Viewport viewport) {
		super(viewport);
		position = new Vector3f();
		rotation = new Quaternionf();
		aspect = -1;
		near = 0.1f;
		fov = Module.HALF_PI;
		validProj = false;
		validView = false;
	}
	
	/**
	 * Get the cameras field of view.
	 * @return the cameras fov
	 */
	public float getFov() {
		return fov;
	}
	
	/**
	 * Set the cameras field of view.
	 * @param fov the fov to set
	 */
	public void setFov(float fov) {
		this.fov = fov;
		this.validProj = false;
	}
	
	/**
	 * Set the aspect ratio used by this camera
	 * @return the aspect ratio
	 */
	public float getAspectRatio() {
		return aspect;
	}
	
	/**
	 * @param aspect the aspect to set
	 */
	public void setAspectRatio(float aspect) {
		this.aspect = aspect;
		this.validProj = false;
	}
	
	public void useDisplayAspectRatio() {
		this.aspect = -1;
		this.validProj = false;
	}
	
	
	@Override
	protected void validateProjection() {
		projection.setPerspective(fov, aspect, near, far, true);
	}

	@Override
	protected void validateView() {
		view = new Matrix4f()//.rotate(rotation)
							 .translate(0, 0, -2f);
	}
}
