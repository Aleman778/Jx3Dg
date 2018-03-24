package jx3d.math;

import java.nio.FloatBuffer;

import jx3d.util.BufferUtils;

/**
 * Represents a 3 by 3 matrix with single precision. The class also
 * contains functions to transform the matrix. The matrix is column-major:
 * <p>
 * <code>
 * 		m00  m10  m20<br>
 * 		m01  m11  m21<br>
 * 		m02  m12  m22<br>
 * </code>
 * </p>
 * @since 1.0
 * @author Aleman778
 */
public final class Matrix3 {

	public float m00, m01, m02;
	public float m10, m11, m12;
	public float m20, m21, m22;
	
	/**
	 * Default Constructor. The matrix is an identity matrix as default.
	 */
	public Matrix3() {
		m00 = 1;
		m11 = 1;
		m22 = 1;
	}
	
	/**
	 * Constructor.
	 * @param m00 matrix entry row 1, column 1
	 * @param m01 matrix entry row 1, column 2
	 * @param m02 matrix entry row 1, column 3
	 * @param m10 matrix entry row 2, column 1
	 * @param m11 matrix entry row 2, column 2
	 * @param m12 matrix entry row 2, column 3
	 * @param m20 matrix entry row 3, column 1
	 * @param m21 matrix entry row 3, column 2
	 * @param m22 matrix entry row 3, column 3
	 */
	public Matrix3(float m00, float m01, float m02,
				   float m10, float m11, float m12,
				   float m20, float m21, float m22) {
		this.m00 = m00;
		this.m01 = m01;
		this.m02 = m02;
		this.m10 = m10;
		this.m11 = m11;
		this.m12 = m12;
		this.m20 = m20;
		this.m21 = m21;
		this.m22 = m22;
	}
	
	/**
	 * Constructor used to create a new copy of the provided matrix.
	 * @param copy the matrix to copy from
	 */
	public Matrix3(Matrix3 copy) {
		this.m00 = copy.m00;
		this.m01 = copy.m01;
		this.m02 = copy.m02;
		this.m10 = copy.m10;
		this.m11 = copy.m11;
		this.m12 = copy.m12;
		this.m20 = copy.m20;
		this.m21 = copy.m21;
		this.m22 = copy.m22;
	}
	
	/**
	 * Constructor.
	 * @param entries float array containing at least 9 elements
	 */
	public Matrix3(float[] entries) {
		if (entries.length < 9) {
			throw new IllegalArgumentException("A 3x3 matrix requires at least 9 elements as an argument (Found: " + entries.length + ").");
		}
		
		this.m00 = entries[0];
		this.m01 = entries[1];
		this.m02 = entries[2];
		this.m10 = entries[3];
		this.m11 = entries[4];
		this.m12 = entries[5];
		this.m20 = entries[6];
		this.m21 = entries[7];
		this.m22 = entries[8];
	}

	/**
	 * Create a transformation matrix that performs a translation by the given vector <code>v</code> in two dimensions.
	 * @param v the vector to translate to
	 * @return a new matrix holding the result
	 */
	public static final Matrix3 createTranslation(Vector2 v) {
		return createTranslation(v.x, v.y);
	}
	
	/**
	 * Create a transformation matrix that performs a translation by the given values <code>(x, y)</code> in two dimensions.
	 * @param x the x component of the translation
	 * @param y the y component of the translation
	 * @return a new matrix holding the result
	 */
	public static final Matrix3 createTranslation(float x, float y) {
		Matrix3 result = new Matrix3();
		result.m02 = x;
		result.m12 = y;
		return result;
	}
	
	/**
	 * Create a transformation matrix that performs a rotation in the XY-plane.
	 * @return a new matrix holding the transformation
	 */
	public static final Matrix3 createRotationXY(float angle) {
		Matrix3 result = new Matrix3();
		result.m00 = (float)  Math.cos(angle);
		result.m01 = (float) -Math.sin(angle);
		result.m10 = (float)  Math.sin(angle);
		result.m11 = (float)  Math.cos(angle);
		return result;
	}
	
	/**
	 * Create a transformation matrix that performs a rotation in the XZ-plane.
	 * @return a new matrix holding the transformation
	 */
	public static final Matrix3 createRotationXZ(float angle) {
		Matrix3 result = new Matrix3();
		result.m00 = (float)  Math.cos(angle);
		result.m02 = (float)  Math.sin(angle);
		result.m20 = (float) -Math.sin(angle);
		result.m22 = (float)  Math.cos(angle);
		return result;
	}
	
	/**
	 * Create a transformation matrix that performs a rotation in the YZ-plane.
	 * @return a new matrix holding the transformation
	 */
	public static final Matrix3 createRotationYZ(float angle) {
		Matrix3 result = new Matrix3();
		result.m11 = (float)  Math.cos(angle);
		result.m12 = (float) -Math.sin(angle);
		result.m21 = (float)  Math.sin(angle);
		result.m22 = (float)  Math.cos(angle);
		return result;
	}

	/**
	 * Create a transformation matrix that performs a scaling by the given values <code>(x, y, z)</code>. 
	 * @param x the x component of the scaling
	 * @param y the y component of the scaling
	 * @param z the z component of the scaling
	 * @return a new matrix holding the transformation
	 */
	public static final Matrix3 createScale(float x, float y, float z) {
		Matrix3 result = new Matrix3();
		result.m00 = x;
		result.m11 = y;
		result.m22 = z;
		return result;
	}
	
	/**
	 * Add this matrix by the given matrix <code>m</code> component-wise.
	 * @param m the matrix to add
	 * @return a new matrix holding the result
	 */
	public Matrix3 add(Matrix3 mat) {
		Matrix3 result = new Matrix3();
		result.m00 = m00 + mat.m00;
		result.m01 = m01 + mat.m01;
		result.m02 = m02 + mat.m02;
		result.m10 = m10 + mat.m10;
		result.m11 = m11 + mat.m11;
		result.m12 = m12 + mat.m12;
		result.m20 = m20 + mat.m20;
		result.m21 = m21 + mat.m21;
		result.m22 = m22 + mat.m22;
		return result;
	}
	
	/**
	 * Subtract this matrix by the given matrix <code>m</code> component-wise.
	 * @param m the matrix to add
	 * @return a new matrix holding the result
	 */
	public Matrix3 sub(Matrix3 mat) {
		Matrix3 result = new Matrix3();
		result.m00 = m00 - mat.m00;
		result.m01 = m01 - mat.m01;
		result.m02 = m02 - mat.m02;
		result.m10 = m10 - mat.m10;
		result.m11 = m11 - mat.m11;
		result.m12 = m12 - mat.m12;
		result.m20 = m20 - mat.m20;
		result.m21 = m21 - mat.m21;
		result.m22 = m22 - mat.m22;
		return result;
	}
	
	/**
	 * Multiply this matrix by the given matrix <code>m</code>.
	 * This matrix will be on the left hand.
	 * @param m the matrix to multiply on the right hand
	 * @return a new matrix holding the result 
	 */
	public Matrix3 mul(Matrix3 m) {
		Matrix3 result = new Matrix3();
		result.m00 = this.m00 * m.m00 + this.m10 * m.m01 + this.m20 * m.m02;
		result.m01 = this.m01 * m.m00 + this.m11 * m.m01 + this.m21 * m.m02;                                                                            
		result.m02 = this.m02 * m.m00 + this.m12 * m.m01 + this.m22 * m.m02;
		result.m10 = this.m00 * m.m10 + this.m10 * m.m11 + this.m20 * m.m12;
		result.m11 = this.m01 * m.m10 + this.m11 * m.m11 + this.m21 * m.m12;                                                             
		result.m12 = this.m02 * m.m10 + this.m12 * m.m11 + this.m22 * m.m12;
		result.m20 = this.m00 * m.m20 + this.m10 * m.m21 + this.m20 * m.m22;
		result.m21 = this.m01 * m.m20 + this.m11 * m.m21 + this.m21 * m.m22;
		result.m22 = this.m02 * m.m20 + this.m11 * m.m21 + this.m22 * m.m22;
		
		return result;
	}
	
	/**
	 * Multiply this matrix by the given vector <code>v</code>.
	 * @param v the vector to multiply
	 * @return a new vector holding the result 
	 */
	public Vector3 mul(Vector3 v) {
		Vector3 result = new Vector3();
		result.x = this.m00 * v.x + this.m01 * v.y + this.m02 * v.z;
		result.y = this.m10 * v.x + this.m11 * v.y + this.m12 * v.z;
		result.z = this.m20 * v.x + this.m21 * v.y + this.m22 * v.z;
		
		return result;
	}

	/**
	 * Multiply this matrix by the given scalar <code>s</code> component-wise.
	 * @param s the scalar to multiply with
	 * @return a new matrix holding the result
	 */
	public Matrix3 mul(float s) {
		Matrix3 result = new Matrix3();
		result.m00 = m00 * s;
		result.m01 = m01 * s;
		result.m02 = m02 * s;
		result.m10 = m10 * s;
		result.m11 = m11 * s;
		result.m12 = m12 * s;
		result.m20 = m20 * s;
		result.m21 = m21 * s;
		result.m22 = m22 * s;
		
		return result;
	}
	
	/**
	 * Get the transpose of this matrix.
	 * @return a new matrix holding the result
	 */
	public Matrix3 transpose() {
		Matrix3 result = new Matrix3();
		result.m00 = m00;
		result.m01 = m10;
		result.m02 = m20;
		result.m10 = m01;
		result.m11 = m11;
		result.m12 = m21;
		result.m20 = m02;
		result.m21 = m12;
		result.m22 = m22;
		
		return result;
	}
	
	/**
	 * Get the determinant of the matrix.
	 * @return a float holding the result
	 */
	public float determinant() {
		return m00 * (m11 * m22 - m12 * m21)
			 - m01 * (m10 * m22 - m12 * m20)
			 + m02 * (m10 * m21 - m11 * m20);
	}
	
	/**
	 * Get the inverse of this matrix.
	 * @return a new matrix holding the result
	 * @throws IllegalStateException if the matrix is singular i.e. the determinant is zero.
	 */
	public Matrix3 inverse() throws IllegalStateException {
		Matrix3 result = new Matrix3();
		float det = determinant();
		if (det == 0f) {
			throw new IllegalStateException("Cannot compute the inverse of a singular matrix.");
		}
		
		float reciprocal = 1.0f / det;
		
		result.m00 = (m11 * m22 - m12 * m21) * reciprocal;
		result.m01 = (m02 * m21 - m01 * m22) * reciprocal;
		result.m02 = (m01 * m12 - m02 * m11) * reciprocal;
		result.m10 = (m12 * m20 - m10 * m22) * reciprocal;
		result.m11 = (m00 * m22 - m02 * m20) * reciprocal;
		result.m12 = (m02 * m10 - m00 * m12) * reciprocal;
		result.m20 = (m10 * m21 - m11 * m20) * reciprocal;
		result.m21 = (m01 * m20 - m00 * m21) * reciprocal;
		result.m22 = (m00 * m11 - m01 * m10) * reciprocal;
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Matrix3) {
			Matrix3 mat = (Matrix3) obj;
			if (m00 != mat.m00)
				return false;
			if (m01 != mat.m01)
				return false;
			if (m02 != mat.m02)
				return false;
			if (m10 != mat.m10)
				return false;
			if (m11 != mat.m11)
				return false;
			if (m12 != mat.m12)
				return false;
			if (m20 != mat.m20)
				return false;
			if (m21 != mat.m21)
				return false;
			if (m22 != mat.m22)
				return false;
			
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the components of this matrix in a float buffer.
	 * @return a new float buffer holding the data
	 */
	public FloatBuffer toFloatBuffer() {
		FloatBuffer result = BufferUtils.createFloatBuffer(m00, m01, m02,
														   m10, m11, m12,
														   m20, m21, m22);
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("Matrix3: [%.3f %.3f %.3f]\n%9s[%.3f %.3f %.3f]\n%9s[%.3f %.3f %.3f]", m00, m01, m02, "", m10, m11, m12, "", m20, m21, m22);
	}
}
