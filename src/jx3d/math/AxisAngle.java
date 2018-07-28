package jx3d.math;

/**
 * Represents a three dimensional rotation of a given <code>angle</code> about an axis.
 * The angle is represented in radians and the axis is represented as a three dimensional
 * unit vector of components <code>(x, y, z)</code>.  
 * @since 1.0
 * @author Aleman778
 */
public class AxisAngle {

	/**
	 * The angle of the rotation in radians.
	 */
	public float angle;
	
	/**
	 * The x component of the rotation axis.
	 */
	public float x;
	
	/**
	 * The y component of the rotation axis.
	 */
	public float y;
	
	/**
	 * The z component of the rotation axis.
	 */
	public float z;
	
	/**
	 * Constructor.
	 * Creates a copy of the provided axis angle <code>a</code>.
	 * @param a the axis angle to copy from
	 */
	public AxisAngle(AxisAngle a) {
		this.angle = a.angle;
		this.x = a.x;
		this.y = a.y;
		this.z = a.z;
	}
	
	public AxisAngle(Quaternion q) {
		
	}
	
	public AxisAngle(float angle, float x, float y, float z) {
		
	}
	
	public AxisAngle set(AxisAngle a) {
		return this;
	}
	
	public AxisAngle set(float angle, float x, float y, float z) {
		return this;
	}
	
	public AxisAngle set(Matrix44 m) {
		return this;
	}
	
	public Quaternion get(Quaternion q) {

		return null;
	}
	
	public Matrix44 get(Matrix44 m) {

		return null;
	}
	
	/**
	 * Normalize the axis vector.
	 * @return this axis angle
	 * @throws IllegalStateException if the axis vector is a zero vector i.e. length is zero
	 */
	public AxisAngle normalize() throws IllegalStateException {
		float invLength = (float) Math.sqrt(x * x + y * y + z * z);
		if (invLength > 0.0f) {
			invLength = 1.0f / invLength;
			x *= invLength;
			y *= invLength;
			z *= invLength;
		} else {
			throw new IllegalStateException("Cannot normalize a zero vector.");
		}
		return this;
	}
	
	public AxisAngle rotate(float angle) {
		return this;
	}
	
	public Vector3D transform(Vector3D v) {
		return null;
	}
	
	public Vector4D transform(Vector4D v) {
		return null;
	}
}
