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
	 * The forward vector.
	 */
	private Vector3f forward;

	/**
	 * The right vector.
	 */
	private Vector3f right;
	
	/**
	 * The up vector.
	 */
	private Vector3f up;
	
	/**
	 * The origin, fixed point or center of rotation.
	 */
	private Vector3f origin;
	
	/**
	 * The position vector.
	 */
	private Vector3f position;
	
	/**
	 * The euler angles (in radians).
	 */
	private Vector3f eulerAngles;
	
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
	 * Valid flag for the quaternion rotation is used to determine if this
	 * object holds the latest rotation update.
	 */
	private boolean validQuaternion;

	/**
	 * Valid flag for euler rotation is used to determine if this
	 * object holds the latest rotation update.
	 */
	private boolean validEuler;
	
	/**
	 * Valid flag for the mapping matrix is used to determine if the matrix is up to date
	 * with changes to the the position, rotation, scale and origin.
	 */
	private boolean validMapping;
	
	/**
	 * Origin not set flag is used to determine if the origin is changed or not.
	 */
	private boolean originChanged;
	
	/**
	 * Constructor.
	 * Creates an identity transformation that does nothing when mapping. 
	 */
	public Transform() {
		origin = new Vector3f();
		position = new Vector3f();
		eulerAngles = new Vector3f();
		rotation = new Quaternionf();
		scale = new Vector3f(1.0f, 1.0f, 1.0f);
		mapping = new Matrix4f();
		validEuler = true;
		validQuaternion = true;
		validMapping = true;
		originChanged = false;
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
		this.originChanged = true;
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
	 * @param vertices the new position {@link Vector3f}
	 */
	public void setPos(Vector3f v) {
		position = v;
		validMapping = false;
	}
	
	/**
	 * Move the current position with the provided translation vector.
	 * @param v the translation {@link Vector3f}
	 */
	public void translate(Vector3f v) {
		position = position.add(v);
		validMapping = false;
	}

	/**
	 * Get the current orientation of the transformation.
	 * @return a {@link Quaternionf} holding the orientation 
	 */
	public Quaternionf getRotation() {
		validateQuaternion();
		return new Quaternionf(rotation);
	}

	/**
	 * Set the orientation of the transformation.
	 * @param rotation the new orientation {@link Quaternionf}
	 */
	public void setRotation(Quaternionf q) {
		rotation = q;
		validQuaternion = true;
		validEuler = false;
		validMapping = false;
	}
	
	/**
	 * Rotate the transformation with the provided orientation quaternion.
	 * @param q the orientation {@link Quaternionf}
	 */
	public void rotate(Quaternionf q) {
		validateQuaternion();
		rotation = rotation.mul(q);
		validEuler = false;
		validMapping = false;
	}
	
	/**
	 * Get the rotation of the transformation represented in euler angles.
	 * @return a matrix holding the euler angles
	 */
	public Vector3f getEulerAngles() {
		validateEuler();
		return new Vector3f(eulerAngles);
	}
	
	/**
	 * Set the rotation of the transformation using euler angles (in radians).
	 * @param angles euler angles (in radians)
	 */
	public void setEulerAngles(Vector3f angles) {
		eulerAngles.set(angles);
		validEuler = true;
		validQuaternion = false;
		validMapping = false;
	}
	
	/**
	 * Apply an euler rotation to <code>this</code> transform rotating the given radians about
	 * the cartesian base unit axes,called the euler angles using rotation sequence <code>XYZ</code>. 
	 * @param angles a vector holding all the euler angles in radians
	 */
	public void rotateXYZ(Vector3f angles) {
		validateEuler();
		eulerAngles.add(angles);
		validQuaternion = false;
		validMapping = false;
	}

	/**
	 * Apply an euler rotation to <code>this</code> transform rotating the given radians about the x axis
	 * @param angle the angle in radians to rotate
	 */
	public void rotateX(float angle) {
		validateEuler();
		eulerAngles.x += angle;
		validQuaternion = false;
		validMapping = false;
	}

	/**
	 * Apply an euler rotation to <code>this</code> transform rotating the given radians about the y axis
	 * @param angle the angle in radians to rotate
	 */
	public void rotateY(float angle) {
		validateEuler();
		eulerAngles.y += angle;
		validQuaternion = false;
		validMapping = false;
	}

	/**
	 * Apply an euler rotation to <code>this</code> transform rotating the given radians about the z axis
	 * @param angle the angle in radians to rotate
	 */
	public void rotateZ(float angle) {
		validateEuler();
		eulerAngles.z += angle;
		validQuaternion = false;
		validMapping = false;
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
		validMapping = false;
	}
	
	/**
	 * Scale the transformation according the provided scaling vector.
	 * @param v the scaling {@link Vector3f}
	 */
	public void scale(Vector3f v) {
		scale = scale.mul(v);
		validMapping = false;
	}

	/**
	 * Get the matrix that maps a point according to
	 * the position, orientation and scaling.
	 * @return a {@link Matrix4f} that performs the mapping
	 */
	public Matrix4f getMapping() {
		validateMapping();
		return new Matrix4f(mapping);
	}
	
	/**
	 * Validate the quaternion that holds the rotation.
	 */
	private void validateQuaternion() {
		if (validQuaternion)
			return;
		if (validEuler)
			rotation.rotationXYZ(eulerAngles.x, eulerAngles.y, eulerAngles.z);
		validQuaternion = true;
	}
	
	/**
	 * Validate the euler angles.
	 */
	private void validateEuler() {
		if (validEuler)
			return;
		if (validQuaternion)
			rotation.getEulerAnglesXYZ(eulerAngles);
		validEuler = true;
	}
	
	
	/**
	 * Validate the transformation (mapping) matrix if any changes has
	 * been made to the transformation.
	 */
	private void validateMapping() {
		if (validMapping)
			return;
		validateQuaternion();
		if (originChanged) {
			Vector3f invOrigin = new Vector3f(origin);
			invOrigin.mul(scale);
			invOrigin.negate();
			mapping = new Matrix4f().translate(position)
									.rotate(rotation)
									.translate(invOrigin)
									.scale(scale);
		} else {
			mapping = new Matrix4f().translationRotateScale(position, rotation, scale);	
		}
		validMapping = true;
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
