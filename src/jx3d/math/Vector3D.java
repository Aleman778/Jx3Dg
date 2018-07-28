package jx3d.math;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.FloatBuffer;

import jx3d.util.BufferUtils;

/**
 * Represents a three dimensional vector with single-precision.
 * @since 1.0
 * @author Aleman778
 */
public class Vector3D {

	/**
	 * The x component of the three dimensional vector.
	 */
	public float x;
	
	/**
	 * The y component of the three dimensional vector.
	 */
	public float y;
	
	/**
	 * The z component of the three dimensional vector.
	 */
	public float z;
	
	/**
	 * Default Constructor.
	 * Creates an empty vector with all three values set to zero.
	 */
	public Vector3D() {
		this(0, 0, 0);
	}
	
	/**
	 * Constructor.
	 * @param x the value of the x component of the vector
	 * @param y the value of the y component of the vector
	 * @param z the value of the z component of the vector
	 */
	public Vector3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Constructor used to create a new copy of the provided vector.
	 * @param copy the vector to copy from
	 */
	public Vector3D(Vector3D copy) {
		this.x = copy.x;
		this.y = copy.y;
		this.z = copy.z;
	}
	
	/**
	 * Set all three components in the vector to the provided value.
	 * @param d the value of all three entries
	 * @return this vector
	 */
	public Vector3D set(float d) {
		return set(d, d, d);
	}
	
	/**
	 * Set the x, y and z components in the vector to the provided values.
	 * @param x the value of the x component to set
	 * @param y the value of the y component to set
	 * @param z the value of the z component to set
	 * @return this vector
	 */
	public Vector3D set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	/**
	 * Set this vector to the values of the given vector <code>v</code>.
	 * @param v the vector to copy from
	 * @return this vector
	 */
	public Vector3D set(Vector3D v) {
		return set(v.x, v.y, v.z);
	}

	/**
	 * Add the components of this vector by the given values.
	 * @param x the x component to add
	 * @param y the y component to add
	 * @param z the z component to add
	 * @return a new vector holding the result
	 */
	public Vector3D add(float x, float y, float z) {
		return new Vector3D(this.x + x, this.y + y, this.z + z);
	}
	
	/**
	 * Add the components of this vector by the given vector <code>v</code>.
	 * @param v the vector to add
	 * @return a new vector holding the result
	 */
	public Vector3D add(Vector3D v) {
		return new Vector3D(x + v.x, y + v.y, z + v.z);
	}
	

	/**
	 * Subtract the components of this vector by the given values.
	 * @param x the x component to subtract
	 * @param y the y component to subtract
	 * @param z the z component to subtract
	 * @return a new vector holding the result
	 */
	public Vector3D sub(float x, float y, float z) {
		return new Vector3D(this.x - x, this.y - y, this.z - z);
	}
	
	/**
	 * Subtract the components of this vector by the given vector <code>v</code>.
	 * @param v the vector to subtract
	 * @return a new vector holding the result
	 */
	public Vector3D sub(Vector3D v) {
		return new Vector3D(x - v.x, y - v.y, z - v.z);
	}
	
	/**
	 * Multiply the components of this vector by the given scalar <code>s</code>.
	 * @param s the scalar to multiply
	 * @return a new vector holding the result
	 */
	public Vector3D mul(float s) {
		return new Vector3D(x * s, y * s, z * s);
	}
	
	/**
	 * Multiply the components of this vector by the given values.
	 * @param x the x component to multiply
	 * @param y the y component to multiply
	 * @param z the z component to multiply
	 * @return a new vector holding the result
	 */
	public Vector3D mul(float x, float y, float z) {
		return new Vector3D(this.x * x, this.y * y, this.z * z);
	}
	
	/**
	 * Multiply the components of this vector by the components of the given vector.
	 * @param v the vector to multiply
	 * @return a new vector holding the result
	 */
	public Vector3D mul(Vector3D v) {
		return new Vector3D(x * v.x, y * v.y, z * v.z);
	}
	
	/**
	 * Inverts (or negates) each component in this vector.
	 * @return a new vector holding the result
	 */
	public Vector3D inverse() {
		return new Vector3D(-x, -y, -z);
	}
	
	/**
	 * Get the cross product of this vector by the given values <code>(x, y, z)</code>.
	 * @param x the x component of the other vector
	 * @param y the y component of the other vector
	 * @param z the z component of the other vector
	 * @return a new vector holding the result
	 */
	public Vector3D cross(float x, float y, float z) {
		return new Vector3D(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
	}
	
	/**
	 * Get the cross product of this vector and vector <code>v</code>
	 * @param v the other vector
	 * @return a new vector holding the result
	 */
	public Vector3D cross(Vector3D v) {
		return new Vector3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}
	
	/**
	 * Multiply the components of this vector by the given values.
	 * @param x the x component to multiply
	 * @param y the y component to multiply
	 * @param z the z component to multiply
	 * @return the dot product of the the to vectors
	 */
	public float dot(float x, float y, float z) {
		return this.x * x + this.y * y + this.z * z;	
	}
	
	/**
	 * Multiply the components if thus vector by the components of the given vector.
	 * @param v the vector to multiply
	 * @return the dot product of the the to vectors
	 */
	public float dot(Vector3D v) {
		return x * v.x + y * v.y + z * v.z;	
	}
	
	/**
	 * Get the length (or magnitude) of the vector.
	 * @return the length of the vector
	 * @see Vector3D#lengthSquared()
	 */
	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Computes the length (or magnitude) of the vector squared.
	 * This function is faster when comparing two lengths.
	 * @return the length of the vector squared
	 * @see Vector3D#length()
	 */
	public float lengthSquared() {
		return x * x + y * y + z * z;
	}
	
	/**
	 * Computes the distance between the point of this vector and the provided point. 
	 * @param x the x component of point
	 * @param y the y component of point
	 * @param z the z component of point
	 * @return the distance between the two points
	 */
	public float distance(float x, float y, float z) {
		float dx = this.x - x;
		float dy = this.y - y;
		float dz = this.z - z;
		return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	/**
	 * Computes the distance squared between the point of this vector and the provided point. 
	 * @param x the x component of point
	 * @param y the y component of point
	 * @param z the z component of point
	 * @return the distance between the two points
	 */
	public float distanceSquared(float x, float y, float z) {
		float dx = this.x - x;
		float dy = this.y - y;
		float dz = this.z - z;
		return dx * dx + dy * dy + dz * dz;
	}
	
	/**
	 * Computes the distance between the points of this vector and the point of the provided vector <code>v</code>.
	 * @param v the point of the vector
	 * @return the distance between the two vectors points
	 */
	public float distance(Vector3D v) {
		return distance(v.x, v.y, v.z);
	}

	/**
	 * Computes the distance <b>squared</b> between the points of this vector and the point of the provided vector <code>v</code>.
	 * This function is faster when comparing two distances.
	 * @param v the point of the vector
	 * @return the distance between the two vectors points
	 */
	public float distanceSquared(Vector3D v) {
		return distanceSquared(v.x, v.y, v.z);
	}
	
	/**
	 * Normalize this vector.
	 * @return this vector
	 * @throws IllegalStateException if this vector is a zero vector i.e. length is zero
	 */
	public Vector3D normalize() throws IllegalStateException {
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
	
	/**
	 * Get the linearly interpolated vector from this to the provided target vector <code>v</code> at time <code>t</code>.
	 * The result is stored in <code>dest</code>.
	 * @param v the target vector
	 * @param t the time from [0..1]
	 * @param dest the vector to hold the result in
	 * @return the dest vector
	 */
	public Vector3D lerp(Vector3D v, float t, Vector3D dest) {
		dest.x = x + (v.x - x) * t;
		dest.y = y + (v.y - y) * t;
		dest.z = z + (v.z - z) * t;
		return dest;
	}
	
	/**
	 * Get the linearly interpolated vector from this to the provided target vector <code>v</code> at time <code>t</code>.
	 * <i>Note:</i> the result is held in this vector.
	 * @param v the target vector
	 * @param t the time from [0..1]
	 * @return this vector
	 */
	public Vector3D lerp(Vector3D v, float t) {
		return lerp(v, t, this);
	}
	
	/**
	 * Write vector data to external source.
	 * @param out output source
	 * @throws IOException if an I/O error occurs
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeFloat(x);
		out.writeFloat(y);
		out.writeFloat(z);
	}
	
	/**
	 * Read vector data from external source.
	 * @param in input source
	 * @throws IOException if an I/O error occurs
	 */
	public void readExternal(ObjectInput in) throws IOException {
		x = in.readFloat();
		y = in.readFloat();
		z = in.readFloat();
	}
	
	/**
	 * Returns the components of this vector in a float buffer.
	 * @return a new float buffer
	 */
	public FloatBuffer toFloatBuffer() {
		return BufferUtils.createFloatBuffer(x, y, z);
	}
	
	@Override
	public String toString() {
		return "Vector3: (" + x + ", " + y + ", " + z + ")";
	}
}
