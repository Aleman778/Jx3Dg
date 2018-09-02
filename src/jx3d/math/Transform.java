package jx3d.math;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

/**
 * The transform class is used to map points from one space
 * to another space in 3D. The mapping is controlled
 * by setting the position, rotation and scaling values.
 * This mapping is represented in 4 by 4 matrix and uses
 * a vector-matrix multiplication to map a point (or vector).
 * @author Aleman778
 */
public class Transform {

	/**
	 * The origin, fixed point or center of rotation.
	 */
	private Vector3f origin;
	
	/**
	 * The position vector.
	 */
	private Vector3f position;
	
	/**
	 * The rotation quaternion.
	 */
	private Quaternionf rotation;
	
	/**
	 * The scaling vector.
	 */
	private Vector3f scale;
	
	/**
	 * The matrix that maps a point to another point
	 * described by the position, rotation and scaling values.
	 */
	private Matrix4f mapping;
	
	/**
	 * Valid flag is used to determine if the mapping is up to date
	 * with the position, rotation and scale values.
	 */
	private boolean valid;
	
	/**
	 * Constructor.
	 * Creates an identity transformation that does nothing when mapping. 
	 */
	public Transform() {
		origin = new Vector3f();
		position = new Vector3f();
		rotation = new Quaternionf();
		scale = new Vector3f(1.0f, 1.0f, 1.0f);
		mapping = new Matrix4f();
		valid = true;
	}

	/**
	 * Get the origin of the transformation, this is a fixed point,
	 * or the center of rotation.
	 * @return a {@link Vector3f} holding the origin
	 */
	public Vector3f getOrigin() {
		return new Vector3f(origin);
	}

	/**
	 * Set the origin of the transformation, this is a fixed point,
	 * or the center of rotation.
	 * @param origin the origin to set
	 */
	public void setOrigin(Vector3f origin) {
		this.origin = origin;
	}
	
	/**
	 * Get the current position of the transformation.
	 * @return a {@link Vector3f} holding the position
	 */
	public Vector3f getPos() {
		return new Vector3f(position);
	}

	/**
	 * Set the position of the transformation.
	 * @param position the new position {@link Vector3f}
	 */
	public void setPos(Vector3f v) {
		position = v;
		valid = false;
	}
	
	/**
	 * Move the current position with the provided translation vector.
	 * @param v the translation {@link Vector3f}
	 */
	public void translate(Vector3f v) {
		position = position.add(v);
		valid = false;
	}

	/**
	 * Get the current orientation of the transformation.
	 * @return a {@link Quaternionf} holding the orientation 
	 */
	public Quaternionf getOrientation() {
		return new Quaternionf(rotation);
	}

	/**
	 * Set the orientation of the transformation.
	 * @param rotation the new orientation {@link Quaternionf}
	 */
	public void setOrientation(Quaternionf q) {
		rotation = q;
		valid = false;
	}
	
	/**
	 * Rotate the transformation with the provided orientation quaternion.
	 * @param q the orientation {@link Quaternionf}
	 */
	public void rotate(Quaternionf q) {
		rotation = rotation.mul(q);
		valid = false;
	}

	/**
	 * Get the current scaling of the transformation.
	 * @return the scaling {@link Vector3f}
	 */
	public Vector3f getScale() {
		return new Vector3f(scale);
	}

	/**
	 * Set the scaling of the transformation.
	 * @param scale the new scaling {@link Vector3f}
	 */
	public void setScale(Vector3f v) {
		scale = v;
		valid = false;
	}
	
	/**
	 * Scale the transformation according the provided scaling vector.
	 * @param v the scaling {@link Vector3f}
	 */
	public void scale(Vector3f v) {
		scale = scale.mul(v);
		valid = false;
	}

	/**
	 * Get the matrix that maps a point according to
	 * the position, orientation and scaling.
	 * @return a {@link Matrix4f} that performs the mapping
	 */
	public Matrix4f getMapping() {
		validate();
		return mapping;
	}
	
	/**
	 * Validate the transformation matrix if any changes has
	 * been made to the transformation.
	 * <i>Note: </i> the method <code>getMapping()</code> is the 
	 * validated matrix.
	 */
	public void validate() {
		if (valid)
			return;
		
		Vector3f invOrigin = new Vector3f();
		origin.negate(invOrigin);
		mapping = new Matrix4f().scale(scale)
								.translate(origin)
								.rotate(rotation)
								.translate(invOrigin)
								.translate(position);
		valid = true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Transform) {
			Matrix4f m1 = getMapping();
			Matrix4f m2 = ((Transform) obj).getMapping();
			return m1.equals(m2);
		}
		return false;
	}
}
