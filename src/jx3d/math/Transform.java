package jx3d.math;

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
	 * The position vector.
	 */
	private Vector3D position;
	
	/**
	 * The rotation quaternion.
	 */
	private Quaternion rotation;
	
	/**
	 * The scaling vector.
	 */
	private Vector3D scale;
	
	/**
	 * The matrix that maps a point to another point
	 * described by the position, rotation and scaling values.
	 */
	private Matrix44 mapping;
	
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
		position = new Vector3D();
		rotation = new Quaternion();
		scale = new Vector3D(1.0f, 1.0f, 1.0f);
		mapping = new Matrix44();
		valid = true;
	}

	/**
	 * Get the current position of the transformation.
	 * @return the position vector
	 */
	public Vector3D getPos() {
		return new Vector3D(position);
	}

	/**
	 * Set the position of the transformation.
	 * @param position the new position vector
	 */
	public void setPos(Vector3D v) {
		position = v;
		valid = false;
	}
	
	/**
	 * Move the current position with the provided translation vector.
	 * @param v the translation vector
	 */
	public void translate(Vector3D v) {
		position = position.add(v);
		valid = false;
	}

	/**
	 * Get the current orientation of the transformation.
	 * @return the orientation quaternion
	 */
	public Quaternion getRotation() {
		return new Quaternion(rotation);
	}

	/**
	 * Set the orientation of the transformation.
	 * @param rotation the new orientation quaternion
	 */
	public void setRotation(Quaternion q) {
		rotation = q;
		valid = false;
	}
	
	/**
	 * Rotate the transformation with the provided orientation quaternion.
	 * @param q the orientation quaternion
	 */
	public void rotate(Quaternion q) {
		rotation = rotation.mul(q);
		valid = false;
	}

	/**
	 * Get the current scaling of the transformation.
	 * @return the scaling vector
	 */
	public Vector3D getScale() {
		return new Vector3D(scale);
	}

	/**
	 * Set the scaling of the transformation.
	 * @param scale the new scaling vector
	 */
	public void setScale(Vector3D v) {
		scale = v;
		valid = false;
	}
	
	/**
	 * Scale the transformation according the provided scaling vector.
	 * @param v the scaling vector
	 */
	public void scale(Vector3D v) {
		scale = scale.mul(v);
		valid = false;
	}

	/**
	 * Get the matrix that maps a point according to
	 * the position, orientation and scaling.
	 * @return the matrix that performs the mapping
	 */
	public Matrix44 getMapping() {
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
		
		mapping = new Matrix44().scale(scale)
								.rotate(rotation)
								.translate(position);
		valid = true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Transform) {
			Matrix44 m1 = getMapping();
			Matrix44 m2 = ((Transform) obj).getMapping();
			return m1.equals(m2);
		}
		return false;
	}
}
