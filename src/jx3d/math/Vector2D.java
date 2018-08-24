package jx3d.math;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.nio.FloatBuffer;

import jx3d.util.BufferUtils;

/**
 * Represents a two dimensional vector with single-precision.
 * @since 1.0
 * @author Aleman778
 */
public class Vector2D {

	public static final Vector2D ZERO = new Vector2D(0, 0);
	
	/**
	 * The x component of the two dimensional vector.
	 */
	public float x;
	
	/**
	 * The y component of the two dimensional vector.
	 */
	public float y;
	
	/**
	 * Default Constructor.
	 * Creates an empty vector with both values set to zero.
	 */
	public Vector2D() {
		this(0, 0);
	}
	
	/**
	 * Constructor.
	 * @param x the value of the x component of the vector
	 * @param y the value of the y component of the vector
	 */
	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor used to create a new copy of the provided vector.
	 * @param copy the vector to copy from
	 */
	public Vector2D(Vector2D copy) {
		this.x = copy.x;
		this.y = copy.y;
	}
	
	/**
	 * Set both components in the vector to the provided value.
	 * @param d the value of both entries
	 * @return this vector
	 */
	public Vector2D set(float d) {
		return set(d, d);
	}
	
	/**
	 * Set the x and y components in the vector to the provided values.
	 * @param x the value of the x component to set
	 * @param y the value of the y component to set
	 * @return this vector
	 */
	public Vector2D set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	/**
	 * Set this vector to the values of the given vector <code>v</code>.
	 * @param v the vector to copy from
	 * @return this vector
	 */
	public Vector2D set(Vector2D v) {
		return set(v.x, v.y);
	}

	/**
	 * Add the components of this vector by the given values.
	 * @param x the x component to add
	 * @param y the y component to add
	 * @return a new vector holding the result
	 */
	public Vector2D add(float x, float y) {
		return new Vector2D(this.x + x, this.y + y);
	}
	
	/**
	 * Add the components of this vector by the given vector <code>v</code>.
	 * @param v the vector to add
	 * @return a new vector holding the result
	 */
	public Vector2D add(Vector2D v) {
		return new Vector2D(x + v.x, y + v.y);
	}
	

	/**
	 * Subtract the components of this vector by the given values.
	 * @param x the x component to subtract
	 * @param y the y component to subtract
	 * @return a new vector holding the result
	 */
	public Vector2D sub(float x, float y) {
		return new Vector2D(this.x - x, this.y - y);
	}
	
	/**
	 * Subtract the components of this vector by the given vector <code>v</code>.
	 * @param v the vector to subtract
	 * @return a new vector holding the result
	 */
	public Vector2D sub(Vector2D v) {
		return new Vector2D(x - v.x, y - v.y);
	}
	
	/**
	 * Multiply the components of this vector by the given scalar <code>s</code>.
	 * @param s the scalar to multiply
	 * @return a new vector holding the result
	 */
	public Vector2D mul(float s) {
		return new Vector2D(x * s, y * s);
	}
	
	/**
	 * Multiply the components of this vector by the given values.
	 * @param x the x component to multiply
	 * @param y the y component to multiply
	 * @return a new vector holding the result
	 */
	public Vector2D mul(float x, float y) {
		return new Vector2D(this.x * x, this.y * y);
	}
	
	/**
	 * Multiply the components of this vector by the components of the given vector.
	 * @param v the vector to multiply
	 * @return a new vector holding the result
	 */
	public Vector2D mul(Vector2D v) {
		return new Vector2D(x * v.x, y * v.y);
	}
	
	/**
	 * Inverts (or negates) each component in this vector.
	 * @return a new vector holding the result
	 */
	public Vector2D inverse() {
		return new Vector2D(-x, -y);
	}
	
	/**
	 * Multiply the components of this vector by the given values.
	 * @param x the x component to multiply
	 * @param y the y component to multiply
	 * @return the dot product of the the to vectors
	 */
	public float dot(float x, float y) {
		return this.x * x + this.y * y;	
	}
	
	/**
	 * Multiply the components of this vector by the components of the given vector.
	 * @param v the vector to multiply
	 * @return the dot product of the the to vectors
	 */
	public float dot(Vector2D v) {
		return x * v.x + y * v.y;	
	}
	
	/**
	 * Get the length (or magnitude) of the vector.
	 * @return the length of the vector
	 * @see Vector2D#lengthSquared()
	 */
	public float length() {
		return (float) Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Computes the length (or magnitude) of the vector squared.
	 * This function is faster when comparing two lengths.
	 * @return the length of the vector squared
	 * @see Vector2D#length()
	 */
	public float lengthSquared() {
		return x * x + y * y;
	}
	
	/**
	 * Computes the distance between the point of this vector and the provided point. 
	 * @param x the x component of point
	 * @param y the y component of point
	 * @return the distance between the two points
	 */
	public float distance(float x, float y) {
		float dx = this.x - x;
		float dy = this.y - y;
		return (float) Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * Computes the distance squared between the point of this vector and the provided point. 
	 * @param x the x component of point
	 * @param y the y component of point
	 * @return the distance between the two points
	 */
	public float distanceSquared(float x, float y) {
		float dx = this.x - x;
		float dy = this.y - y;
		return dx * dx + dy * dy;
	}

	/**
	 * Computes the distance between the points of this vector and the point of the provided vector <code>v</code>.
	 * @param v the point of the vector
	 * @return the distance between the two vectors points
	 */
	public float distance(Vector2D v) {
		return distance(v.x, v.y);
	}

	/**
	 * Computes the distance <b>squared</b> between the points of this vector and the point of the provided vector <code>v</code>.
	 * This function is faster when comparing two distances.
	 * @param v the point of the vector
	 * @return the distance between the two vectors points
	 */
	public float distanceSquared(Vector2D v) {
		return distanceSquared(v.x, v.y);
	}
	
	/**
	 * Normalize this vector.
	 * @return this vector
	 * @throws IllegalStateException if this vector is a zero vector i.e. length is zero
	 */
	public Vector2D normalize() throws IllegalStateException {
		float invLength = (float) Math.sqrt(x * x + y * y);
		if (invLength > 0.0f) {
			invLength = 1.0f / invLength;
			x *= invLength;
			y *= invLength;
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
	public Vector2D lerp(Vector2D v, float t, Vector2D dest) {
		dest.x = x + (v.x - x) * t;
		dest.y = y + (v.y - y) * t;
		return dest;
	}
	
	/**
	 * Get the linearly interpolated vector from this to the provided target vector <code>v</code> at time <code>t</code>.
	 * <i>Note:</i> the result is held in this vector.
	 * @param v the target vector
	 * @param t the time from [0..1]
	 * @return this vector
	 */
	public Vector2D lerp(Vector2D v, float t) {
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
	}
	
	/**
	 * Read vector data from external source.
	 * @param in input source
	 * @throws IOException if an I/O error occurs
	 */
	public void readExternal(ObjectInput in) throws IOException {
		x = in.readFloat();
		y = in.readFloat();
	}
	
	/**
	 * Returns the components of this vector in a float buffer.
	 * @return a new float buffer
	 */
	public FloatBuffer toFloatBuffer() {
		return BufferUtils.createFloatBuffer(x, y);
	}
	
	@Override
	public String toString() {
		return "Vector2: (" + x + ", " + y + ")";
	}
}
