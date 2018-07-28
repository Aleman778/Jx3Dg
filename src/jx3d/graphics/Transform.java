package jx3d.graphics;

import jx3d.math.Matrix44;
import jx3d.math.Quaternion;
import jx3d.math.Vector3D;

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
	private Quaternion orientation;
	
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
		orientation = new Quaternion();
		scale = new Vector3D(1, 1, 1);
		mapping = new Matrix44();
		valid = true;
	}

	public Vector3D getPos() {
		return new Vector3D(position);
	}

	public void setPos(Vector3D position) {
		this.position = position;
		this.valid = false;
	}
	
	public void translate(Vector3D v) {
		position = position.add(v);
		if (valid)
			mapping = mapping.translate(v);
	}

	public Quaternion getOrientation() {
		return new Quaternion(orientation);
	}

	public void setOrientation(Quaternion rotation) {
		this.orientation = rotation;
		this.valid = false;
	}
	
	public void rotate(Quaternion q) {
		orientation = orientation.mul(q);
		if (valid)
			mapping = mapping.rotate(q);
	}

	public Vector3D getScale() {
		return new Vector3D(scale);
	}

	public void setScale(Vector3D scale) {
		this.scale = scale;
		this.valid = false;
	}
	
	public void scale(Vector3D v) {
		scale = scale.mul(v);
		if (valid)
			mapping = mapping.scale(v);
	}

	public Matrix44 getMapping() {
		validate();
		return mapping;
	}
	
	public void validate() {
		if (valid)
			return;
		
		mapping = new Matrix44().scale(scale)
								.rotate(orientation)
								.translate(position);
		valid = true;
	}
}
