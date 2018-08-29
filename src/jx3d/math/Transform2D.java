package jx3d.math;

/**
 * The transform 2D class is used to map points from one space
 * to another space in 2D. The mapping is controlled
 * by setting the position, rotation and scaling values.
 * This mapping is represented in 3 by 3 matrix and uses
 * a vector-matrix multiplication to map a point (or vector).
 * @author Aleman778
 */
public class Transform2D {

	/**
	 * The fixed rotation point.
	 */
	private Vector2D fix;
	
	/**
	 * The position vector.
	 */
	private Vector2D pos;
	
	/**
	 * The rotation value.
	 */
	private float rotation;
	
	/**
	 * The scaling vector.
	 */
	private Vector2D scale;
	
	/**
	 * The matrix that maps a point to another point
	 * described by the position, rotation and scale values.
	 */
	private Matrix33 mapping;
	
	/**
	 * Valid flag is used to determine if the mapping is up to date
	 * with the position, rotation and scale values.
	 */
	private boolean valid;
	
	/**
	 * Constructor.
	 * Creates an identity transformation that does nothing when mapping.
	 */
	public Transform2D() {
		fix = new Vector2D();
		pos = new Vector2D();
		rotation = 0.0f;
		scale = new Vector2D(1.0f, 1.0f);
		mapping = new Matrix33();
		valid = true;
	}
	
	/**
	 * Get the fixed rotation point.
	 * @return a fixed point when rotating
	 */
	public Vector2D getFix() {
		return fix;
	}
	
	/**
	 * Set a new fixed rotation point.
	 * <i>Note: </i> setting fix to <code>(0, 0)</code>
	 * will not affect the rotation.
	 * @param fix the fixed rotation point
	 */
	public void setFix(Vector2D fix) {
		this.fix = fix;
	}

	/**
	 * Get the current position of the transformation.
	 * @return the position vector
	 */
	public Vector2D getPos() {
		return pos;
	}

	/**
	 * Set the position of the transformation.
	 * @param pos the new position vector
	 */
	public void setPos(Vector2D v) {
		pos = v;
		valid = false;
	}
	
	/**
	 * Move the current position with the provided translation vector.
	 * @param v the translation vector
	 */
	public void translate(Vector2D v) {
		pos = pos.add(v);
		valid = false;
	}

	/**
	 * Get the current rotation of the transformation.
	 * @return the rotation in radians
	 */
	public float getRotation() {
		return rotation;
	}

	/**
	 * Set the rotation of the transformation.
	 * @param rotation the new rotation in radians
	 */
	public void setRotation(float v) {
		rotation = v;
		valid = false;
	}

	/**
	 * Rotate the transformation with the provided value.
	 * @param v the rotation in radians
	 */
	public void rotate(float v) {
		rotation += v;
		valid = false;
	}

	/**
	 * Get the current scaling of the transformation.
	 * @return the scaling vector
	 */
	public Vector2D getScale() {
		return scale;
	}

	/**
	 * Set the scaling of the transformation.
	 * @param scale the new scaling vector
	 */
	public void setScale(Vector2D v) {
		scale = v;
		valid = false;
	}

	/**
	 * Scale the transformation according the provided scaling vector.
	 * @param v the scaling vector
	 */
	public void scale(Vector2D v) {
		scale = scale.mul(v);
		valid = false;
	}

	/**
	 * Get the matrix that maps a point according to
	 * the position, rotation and scaling.
	 * @return the matrix that performs the mapping
	 */
	public Matrix33 getMapping() {
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
		
		valid = true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Transform2D) {
			Matrix33 m1 = getMapping();
			Matrix33 m2 = ((Transform2D) obj).getMapping();
			return m1.equals(m2);
		}
		return false;
	}
}
