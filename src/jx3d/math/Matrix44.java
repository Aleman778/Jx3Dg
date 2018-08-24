package jx3d.math;

import jx3d.util.BufferUtils;

import java.nio.FloatBuffer;

import static java.lang.Math.*;

/**
 * Represents a 4 by 4 matrix with single precision. The class also
 * contains functions to transform the matrix. The matrix is column-major:
 * <p>
 * <code>
 * 		m00  m10  m20  m30<br>
 * 		m01  m11  m21  m31<br>
 * 		m02  m12  m22  m32<br>
 * 		m03  m13  m23  m33<br>
 * </code>
 * </p>
 * @since 1.0
 * @author Aleman778
 */
public final class Matrix44 {
	
	public float m00, m01, m02, m03;
	public float m10, m11, m12, m13;
	public float m20, m21, m22, m23;
	public float m30, m31, m32, m33;

	/**
	 * Default Constructor. The matrix is an identity matrix as default.
	 */
	public Matrix44() {
		this.m00 = 1;
		this.m11 = 1;
		this.m22 = 1;
		this.m33 = 1;
	}

	/**
	 * Constructor.
	 * 
	 * @param m00 matrix entry row 1, column 1
	 * @param m01 matrix entry row 1, column 2
	 * @param m02 matrix entry row 1, column 3
	 * @param m03 matrix entry row 1, column 4
	 * @param m10 matrix entry row 2, column 1
	 * @param m11 matrix entry row 2, column 2
	 * @param m12 matrix entry row 2, column 3
	 * @param m13 matrix entry row 2, column 4
	 * @param m20 matrix entry row 3, column 1
	 * @param m21 matrix entry row 3, column 2
	 * @param m22 matrix entry row 3, column 3
	 * @param m23 matrix entry row 3, column 4
	 * @param m30 matrix entry row 4, column 1
	 * @param m31 matrix entry row 4, column 2
	 * @param m32 matrix entry row 4, column 3
	 * @param m33 matrix entry row 4, column 4
	 */
	public Matrix44(float m00, float m01, float m02, float m03, 
				   float m10, float m11, float m12, float m13, 
				   float m20, float m21, float m22, float m23,
				   float m30, float m31, float m32, float m33) {
		this.m00 = m00;
		this.m01 = m01;
		this.m02 = m02;
		this.m03 = m03;
		this.m10 = m10;
		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m20 = m20;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m30 = m30;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;
	}
	
	/**
	 * Constructor used to create a new copy of the provided matrix.
	 * @param copy the matrix to copy from
	 */
	public Matrix44(Matrix44 copy) {
		this.m00 = copy.m00;
		this.m01 = copy.m01;
		this.m02 = copy.m02;
		this.m03 = copy.m03;
		this.m10 = copy.m10;
		this.m11 = copy.m11;
		this.m12 = copy.m12;
		this.m13 = copy.m13;
		this.m20 = copy.m20;
		this.m21 = copy.m21;
		this.m22 = copy.m22;
		this.m23 = copy.m23;
		this.m30 = copy.m30;
		this.m31 = copy.m31;
		this.m32 = copy.m32;
		this.m33 = copy.m33;
	}

	/**
	 * Constructor.
	 * @param entries float array containing at least 16 elements
	 */
	public Matrix44(float[] entries) {
		if (entries.length < 16) {
			throw new IllegalArgumentException(
					"A 4x4 matrix requires at least 16 elements as an argument (Found: " + entries.length + ").");
		}

		this.m00 = entries[0];
		this.m01 = entries[1];
		this.m21 = entries[9];
		this.m02 = entries[2];
		this.m03 = entries[3];
		this.m10 = entries[4];
		this.m11 = entries[5];
		this.m12 = entries[6];
		this.m13 = entries[7];
		this.m20 = entries[8];
		this.m22 = entries[10];
		this.m23 = entries[11];
		this.m30 = entries[12];
		this.m31 = entries[13];
		this.m32 = entries[14];
		this.m33 = entries[15];
	}

	/**
	 * Create a transformation matrix that performs a translation by the given vector <code>v</code>.
	 * @param v the vector to translate to
	 * @return a new matrix holding the result
	 */
	public final Matrix44 translate(Vector3D v) {
		return translate(v.x, v.y, v.z);
	}
	
	/**
	 * Create a transformation matrix that performs a translation by the given values <code>(x, y, z)</code>.
	 * @param x the x component of the translation
	 * @param y the y component of the translation
	 * @param z the z component of the translation
	 * @return a new matrix holding the result
	 */
	public final Matrix44 translate(float x, float y, float z) {
		Matrix44 result = new Matrix44();
		result.m03 = x;
		result.m13 = y;
		result.m23 = z;
		return mulAffine(result);
	}
	
	/**
	 * Create a transformation matrix that performs a three dimensional rotation by the given euler angles <code>(x, y, z)</code>.
	 * @param x the angle to rotate around the pitch axis (in radians)
	 * @param y the angle to rotate around the yaw axis (in radians)
	 * @param z the angle to rotate around the roll axis (in radians)
	 * @return a new matrix holding the result
	 */
	public final Matrix44 rotate(float x, float y, float z) {
		Matrix44 result = new Matrix44();
		result.m00 = (float) ( cos(y) * cos(z));
		result.m01 = (float) ( cos(x) * sin(z) + sin(x) * sin(y) * cos(z));
		result.m02 = (float) ( sin(x) * sin(z) - cos(x) * sin(y) * cos(z));
		result.m10 = (float) (-cos(y) * sin(z));
		result.m11 = (float) ( cos(x) * cos(z) - sin(x) * sin(y) * sin(z));
		result.m12 = (float) ( sin(x) * cos(z) + cos(x) * sin(y) * sin(z));
		result.m20 = (float) ( sin(y));
		result.m21 = (float) (-sin(x) * cos(y));
		result.m22 = (float) ( cos(x) * cos(y));
		return mulAffine(result);
	}

	/**
	 * Create a transformation matrix that performs a rotation by the given angle <code>a</code> and rotation axis vector <code>v</code>.
	 * @param a the angle to rotate (in radians)
	 * @param v the axis to rotate about
	 * @return a new matrix holding the result
	 */
	public final Matrix44 rotate(float a, Vector3D v) {
		return rotate(a, v.x, v.y, v.z);
	}
	
	/**
	 * Create a transformation matrix that performs a rotation by the given angle <code>a</code> and rotation axis <code>(x, y, z)</code>.
	 * <i>Note:</i> the rotation axis has to be a unit vector.
	 * @param a the angle to rotate (in radians)
	 * @param x the x component of the axis to rotate about
	 * @param y the y component of the axis to rotate about
	 * @param z the z component of the axis to rotate about
	 * @return a new matrix holding the result
	 */
	public final Matrix44 rotate(float a, float x, float y, float z) {
		Matrix44 result = new Matrix44();
		float sin = (float) Math.sin(a);
		float cos = (float) Math.cos(a);
		float cos2 = 1.0f - cos;
		float xy = x * y, xz = x * z, yz = y * z;
		result.m00 = cos + x * x * cos2;
		result.m10 = xy * cos2 - z * sin;
		result.m20 = xz * cos2 + y * sin;
		result.m01 = xy * cos2 + z * sin;
		result.m11 = cos + y * y * cos2;
		result.m21 = yz * cos2 - x * sin;
		result.m02 = xz * cos2 - y * sin;
	    result.m12 = yz * cos2 + x * sin;
		result.m22 = cos + z * z * cos2;
		return mulAffine(result);
	}

	/**
	 * Create a transformation matrix that performs a rotation by the given quaternion <code>q</code>.
	 * @param q the quaternion to 
	 * @return a new matrix holding the result
	 */
	public final Matrix44 rotate(Quaternion q) {
		return mulAffine(q.toMatrix4());
	}

	/**
	 * Create a transformation matrix that performs a scaling by the given vector <code>v</code>.
	 * @param v the vector to scale to
	 * @return a new matrix holding the result
	 */
	public final Matrix44 scale(Vector3D v) {
		return scale(v.x, v.y, v.z);
	}

	/**
	 * Create a transformation matrix that performs a scaling by the given values <code>(x, y, z)</code>.
	 * @param x the scaling in the x axis
	 * @param y the scaling in the y axis
	 * @param z the scaling in the z axis
	 * @return a new matrix holding the result
	 */
	public final Matrix44 scale(float x, float y, float z) {
		Matrix44 result = new Matrix44();
		result.m00 = x;
		result.m11 = y;
		result.m22 = z;

		return mulAffine(result);
	}
	
    /**
     * Create a orthographic projection matrix that projects a three dimensional object onto a plane. 
     * Orthographic projection does not make distant objects appear smaller. The view frustum is
     * defined as a box and everything inside that box is displayed on the screen.
     * @param left the position of the left frustum edge
     * @param right the position of the right frustum edge
     * @param bottom the position of the bottom frustum edge
     * @param top the position of the top frustum edge
     * @param near the near clipping plane distance
     * @param far the far clipping plane distance
     * @return a new matrix holding the result
     */
    public final Matrix44 orthographic(float left, float right, float bottom, float top, float near, float far) {
        Matrix44 result = new Matrix44();
        result.m00 = 2.0f / (right - left);
        result.m11 = 2.0f / (top - bottom);
        result.m22 = 2.0f / (near - far);
        result.m03 = (left + right) / (left - right);
        result.m13 = (bottom + top) / (bottom - top);
        result.m23 = (far + near) / (far - near);
        return mul(result);
    }
	
	/**
	 * Create a perspective projection matrix that projects a three dimensional object onto a plane.
	 * Perspective projection makes distant objects appear smaller to create depth in a scene.
	 * The view frustum is defined as a pyramid looking shape and everything inside it is displayed on the screen. 
	 * @param fov the field of view angle
	 * @param aspectRatio the aspect ration of the display
	 * @param near the closest viewing limit in the z-direction
	 * @param far the farthest viewing limit in the z-direction
     * @return a new matrix holding the result
	 * @see jx3d.core.Display#getAspectRatio()
	 */
	public final Matrix44 perspective(float fov, float aspectRatio, float near, float far) {
		Matrix44 result = new Matrix44();
		float angle = (float) Math.tan(Math.toRadians(fov / 2.0f));
		float range = near - far;
		result.m00 = 1.0f / (angle * aspectRatio);
		result.m11 = 1.0f / angle;
		result.m22 = (-near - far) / range;
		result.m23 = 2.0f * far * near / range;		
		return mul(result);
	}
	
	/**
	 * Create a "look at" transformation matrix.  
	 * @param eye the camera (or eye) location
	 * @param target the vector of the target point
	 * @param up the vector pointing in the up direction
	 * @return a new matrix holding the result
	 */
	public final Matrix44 lookAt(Vector3D eye, Vector3D target, Vector3D up) {
		Matrix44 result = new Matrix44();
		
		Vector3D dest = target.sub(eye);
		Vector3D zaxis = dest.normalize();
		Vector3D xaxis = up.cross(zaxis).normalize();
		Vector3D yaxis = zaxis.cross(xaxis);
		
		result.m00 = xaxis.x;
		result.m01 = xaxis.y;
		result.m02 = xaxis.z;
		result.m10 = yaxis.x;
		result.m11 = yaxis.y;
		result.m12 = yaxis.z;
		result.m20 = zaxis.x;
		result.m21 = zaxis.y;
		result.m22 = zaxis.z;
		result.m30 = -eye.x;
		result.m31 = -eye.y;
		result.m32 = -eye.z;
		
		return mul(result);
	}
	
	/**
	 * Add this matrix by the given matrix <code>m</code> component-wise.
	 * @param m the matrix to add
	 * @return a new matrix holding the result
	 */
	public Matrix44 add(Matrix44 mat) {
		Matrix44 result = new Matrix44();
		result.m00 = m00 + mat.m00;
		result.m01 = m01 + mat.m01;
		result.m02 = m02 + mat.m02;
		result.m03 = m03 + mat.m03;
		result.m10 = m10 + mat.m10;
		result.m11 = m11 + mat.m11;
		result.m12 = m12 + mat.m12;
		result.m13 = m13 + mat.m13;
		result.m20 = m20 + mat.m20;
		result.m21 = m21 + mat.m21;
		result.m22 = m22 + mat.m22;
		result.m23 = m23 + mat.m23;
		result.m30 = m30 + mat.m30;
		result.m31 = m31 + mat.m31;
		result.m32 = m32 + mat.m32;
		result.m33 = m33 + mat.m33;
		return result;
	}
	
	/**
	 * Subtract this matrix by the given matrix <code>m</code> component-wise.
	 * @param m the matrix to subtract
	 * @return a new matrix holding the result
	 */
	public Matrix44 sub(Matrix44 mat) {
		Matrix44 result = new Matrix44();
		result.m00 = m00 - mat.m00;
		result.m01 = m01 - mat.m01;
		result.m02 = m02 - mat.m02;
		result.m03 = m03 - mat.m03;
		result.m10 = m10 - mat.m10;
		result.m11 = m11 - mat.m11;
		result.m12 = m12 - mat.m12;
		result.m13 = m13 - mat.m13;
		result.m20 = m20 - mat.m20;
		result.m21 = m21 - mat.m21;
		result.m22 = m22 - mat.m22;
		result.m23 = m23 - mat.m23;
		result.m30 = m30 - mat.m30;
		result.m31 = m31 - mat.m31;
		result.m32 = m32 - mat.m32;
		result.m33 = m33 - mat.m33;
		return result;
	}
	
	/**
	 * Multiply this matrix by the given matrix <code>m</code>.
	 * This matrix will be on the left hand. This is a regular
	 * matrix multiplication (unoptimized).
	 * <p>
	 * For an affine transformation matrix use the
	 * optimized version {@link #mulAffine(Matrix44)} instead.
	 * </p>
	 * <p>
	 * For a translation matrix use the optimized
	 * version {@link #mulTranslation(Matrix44)} instead.
	 * </p>
	 * <p>
	 * For a perspective projection matrix use the optimized
	 * version {@link #mulPerspective(Matrix44)} instead.
	 * </p>
	 * <p>
	 * For a orthographic projection matrix use the optimized
	 * version {@link #mulOrthographic(Matrix44)} instead.
	 * </p>
	 * @param m the matrix to multiply on the right hand
	 * @return a new matrix holding the result
	 * @see Matrix44#mulAffine(Matrix44)
	 * @see Matrix44#mulTranslation(Matrix44)
	 * @see Matrix44#mulPerspective(Matrix44)
	 * @see Matrix44#mulOrthographic(Matrix44)
	 */
	public Matrix44 mul(Matrix44 m) {
		Matrix44 result = new Matrix44();
		result.m00 = m00 * m.m00 + m10 * m.m01 + m20 * m.m02 + m30 * m.m03;
		result.m01 = m01 * m.m00 + m11 * m.m01 + m21 * m.m02 + m31 * m.m03;                                                                            
		result.m02 = m02 * m.m00 + m12 * m.m01 + m22 * m.m02 + m32 * m.m03;                                                                          
		result.m03 = m03 * m.m00 + m13 * m.m01 + m23 * m.m02 + m33 * m.m03;
		result.m10 = m00 * m.m10 + m10 * m.m11 + m20 * m.m12 + m30 * m.m13;
		result.m11 = m01 * m.m10 + m11 * m.m11 + m21 * m.m12 + m31 * m.m13;                                                             
		result.m12 = m02 * m.m10 + m12 * m.m11 + m22 * m.m12 + m32 * m.m13;                                                            
		result.m13 = m03 * m.m10 + m13 * m.m11 + m23 * m.m12 + m33 * m.m13;
		result.m20 = m00 * m.m20 + m10 * m.m21 + m20 * m.m22 + m30 * m.m23;
		result.m21 = m01 * m.m20 + m11 * m.m21 + m21 * m.m22 + m31 * m.m23;
		result.m22 = m02 * m.m20 + m12 * m.m21 + m22 * m.m22 + m32 * m.m23;
		result.m23 = m03 * m.m20 + m13 * m.m21 + m23 * m.m22 + m33 * m.m23;
		result.m30 = m00 * m.m30 + m10 * m.m31 + m20 * m.m32 + m30 * m.m33;
		result.m31 = m01 * m.m30 + m11 * m.m31 + m21 * m.m32 + m31 * m.m33;
		result.m32 = m02 * m.m30 + m12 * m.m31 + m22 * m.m32 + m32 * m.m33;
		result.m33 = m03 * m.m30 + m13 * m.m31 + m23 * m.m32 + m33 * m.m33;
		return result;
	}
	
	/**
	 * Multiply this matrix by the given matrix <code>m</code>.
	 * This matrix will be on the left hand.
	 * <p>
	 * This method is an optimized version of {@link #mul(Matrix44)} and assumes that
	 * <code>this</code> and the given <code>m</code> matrix are both an affine
	 * transformation matrix (i.e. their last rows are equal to <code>(0, 0, 0, 1)</code>). 
	 * </p>
	 * <p>
	 * The resulting matrix will also be an affine transformation matrix.
	 * </p>
	 * @param m the affine transformation matrix to multiply on the right hand
	 * @return a new matrix holding the result
	 * @see Matrix44#mul(Matrix44)
	 */
	public Matrix44 mulAffine(Matrix44 m) {
		Matrix44 result = new Matrix44();
		result.m00 = m00 * m.m00 + m10 * m.m01 + m20 * m.m02;
		result.m01 = m01 * m.m00 + m11 * m.m01 + m21 * m.m02;                                                                            
		result.m02 = m02 * m.m00 + m12 * m.m01 + m22 * m.m02;
		result.m03 = m03;
		result.m10 = m00 * m.m10 + m10 * m.m11 + m20 * m.m12;
		result.m11 = m01 * m.m10 + m11 * m.m11 + m21 * m.m12;                                                             
		result.m12 = m02 * m.m10 + m12 * m.m11 + m22 * m.m12; 
		result.m13 = m13; 
		result.m20 = m00 * m.m20 + m10 * m.m21 + m20 * m.m22;
		result.m21 = m01 * m.m20 + m11 * m.m21 + m21 * m.m22;
		result.m22 = m02 * m.m20 + m12 * m.m21 + m22 * m.m22;
		result.m23 = m23;
		result.m30 = m00 * m.m30 + m10 * m.m31 + m20 * m.m32;
		result.m31 = m01 * m.m30 + m11 * m.m31 + m21 * m.m32;
		result.m32 = m02 * m.m30 + m12 * m.m31 + m22 * m.m32;
		return result;
	}
	
	/**
	 * Multiply this matrix by the given matrix <code>m</code>.
	 * This matrix will be on the left hand.
	 * <p>
	 * This method is an optimized version of {@link #mul(Matrix44)} and assumes that
	 * <code>this</code> matrix is an affine transformation matrix (i.e. their last
	 * rows are equal to <code>(0, 0, 0, 1)</code>) and that the given matrix
	 * <code>m</code> is a translation matrix.
	 * </p>
	 * <p>
	 * The resulting matrix will also be an affine transformation matrix.
	 * </p>
	 * @param m the translation matrix to multiply on the right hand
	 * @return a new matrix holding the result
	 * @see Matrix44#mul(Matrix44)
	 */
	public Matrix44 mulTranslation(Matrix44 m) {
		Matrix44 result = new Matrix44();
		result.m00 = m00;
		result.m01 = m01;
		result.m02 = m02;
		result.m03 = m03;
		result.m10 = m10;
		result.m11 = m11;
		result.m12 = m12;
		result.m13 = m13; 
		result.m20 = m20;
		result.m21 = m21;
		result.m22 = m22;
		result.m23 = m23;
		result.m30 = m.m30 + m30;
		result.m31 = m.m31 + m31;
		result.m32 = m.m32 + m32;
		result.m33 = m33;
		return result;
	}
	
	/**
	 * Multiply this matrix by the given matrix <code>m</code>.
	 * This matrix will be on the left hand.
	 * <p>
	 * This method is an optimized version of {@link #mul(Matrix44)} and assumes that
	 * <code>this</code> matrix is a perspective projection and that the given matrix
	 * <code>m</code> is a view matrix.
	 * </p>
	 * @param m the translation matrix to multiply on the right hand
	 * @return a new matrix holding the result
	 * @see Matrix44#mul(Matrix44)
	 */
	public Matrix44 mulPerspective(Matrix44 m) {
		Matrix44 result = new Matrix44();
		result.m00 = m00 * m.m00;
		result.m01 = m11 * m.m01;
		result.m02 = m22 * m.m02;
		result.m03 = m23 * m.m02;
		result.m10 = m00 * m.m10;
		result.m11 = m11 * m.m11;
		result.m12 = m22 * m.m12;
		result.m13 = m23 * m.m12;
		result.m20 = m00 * m.m20;
		result.m21 = m11 * m.m21;
		result.m22 = m22 * m.m22;
		result.m23 = m23 * m.m22;
		result.m30 = m00 * m.m30;
		result.m31 = m11 * m.m31;
		result.m32 = m22 * m.m32 + m32;
		result.m33 = m23 * m.m32;
		return result;
	}
	
	/**
	 * Multiply this matrix by the given matrix <code>m</code>.
	 * This matrix will be on the left hand.
	 * <p>
	 * This method is an optimized version of {@link #mul(Matrix44)} and assumes that
	 * <code>this</code> matrix is an orthographic projection matrix and that the
	 * given matrix <code>m</code> is a view matrix.
	 * </p>
	 * @param m the translation matrix to multiply on the right hand
	 * @return a new matrix holding the result
	 * @see Matrix44#mul(Matrix44)
	 */
	public Matrix44 mulOrthographic(Matrix44 m) {
		Matrix44 result = new Matrix44();
		result.m00 = m00 * m.m00;
		result.m01 = m01 * m.m01;
		result.m02 = m02 * m.m02;
		result.m03 = 0.0f;
		result.m10 = m10 * m.m10;
		result.m11 = m11 * m.m11;
		result.m12 = m12 * m.m12;
		result.m13 = 0.0f;
		result.m20 = m20 * m.m20;
		result.m21 = m21 * m.m21;
		result.m22 = m22 * m.m22;
		result.m23 = 0.0f;
		result.m30 = m00 * m.m30 + m30;
		result.m31 = m11 * m.m31 + m31;
		result.m32 = m11 * m.m32 + m32;
		result.m33 = 1.0f;
		return result;
	}
	
	/**
	 * Multiply this matrix by the given vector <code>v</code>.
	 * @param v the vector to multiply
	 * @return a new vector holding the result 
	 */
	public Vector4D mul(Vector4D vec) {
		Vector4D result = new Vector4D();
		result.x = this.m00 * vec.x + this.m01 * vec.y + this.m02 * vec.z + this.m03 * vec.w;
		result.y = this.m10 * vec.x + this.m11 * vec.y + this.m12 * vec.z + this.m13 * vec.w;
		result.z = this.m20 * vec.x + this.m21 * vec.y + this.m22 * vec.z + this.m23 * vec.w;
		result.w = this.m30 * vec.x + this.m31 * vec.y + this.m32 * vec.z + this.m33 * vec.w;
		return result;
	}

	/**
	 * Multiply this matrix by the given scalar <code>s</code> component-wise.
	 * @param s the scalar to multiply with
	 * @return a new matrix holding the result
	 */
	public Matrix44 mul(float s) {
		Matrix44 result = new Matrix44();
		result.m00 = m00 * s;
		result.m01 = m01 * s;
		result.m02 = m02 * s;
		result.m03 = m03 * s;
		result.m10 = m10 * s;
		result.m11 = m11 * s;
		result.m12 = m12 * s;
		result.m13 = m13 * s;
		result.m20 = m20 * s;
		result.m21 = m21 * s;
		result.m22 = m22 * s;
		result.m23 = m23 * s;
		result.m30 = m30 * s;
		result.m31 = m31 * s;
		result.m32 = m32 * s;
		result.m33 = m33 * s;
		return result;
	}
	
	/**
	 * Get the transpose of this matrix.
	 * @return a new matrix holding the result
	 */
	public Matrix44 transpose() {
		Matrix44 result = new Matrix44();
		result.m00 = m00;
		result.m01 = m10;
		result.m02 = m20;
		result.m03 = m30;
		result.m10 = m01;
		result.m11 = m11;
		result.m12 = m21;
		result.m13 = m31;
		result.m20 = m02;
		result.m21 = m12;
		result.m22 = m22;
		result.m23 = m32;
		result.m30 = m03;
		result.m31 = m13;
		result.m32 = m23;
		result.m33 = m33;
		return result;
	}

	/**
	 * Get the determinant of this matrix.
	 * <p>
	 * For an affine transformation matrix use {@link Matrix44#determinantAffine()} instead.
	 * </p>
	 * @return a float holding the result
	 */
	public float determinant() {
		return (m00 * m11 - m01 * m10) * (m22 * m33 - m23 * m32)
             + (m02 * m10 - m00 * m12) * (m21 * m33 - m23 * m31)
             + (m00 * m13 - m03 * m10) * (m21 * m32 - m22 * m31)
             + (m01 * m12 - m02 * m11) * (m20 * m33 - m23 * m30)
             + (m03 * m11 - m01 * m13) * (m20 * m32 - m22 * m30)
             + (m02 * m13 - m03 * m12) * (m20 * m31 - m21 * m30);
	}

	/**
	 * Get the determinant of this matrix.
	 * <p>
	 * This is an optimized version of {@link Matrix44#determinant()} and assumes that
	 * <code>this</code> matrix is an affine transformation matrix.
	 * </p>
	 * @return a float holding the result
	 * @see Matrix44#determinant()
	 */
	public float determinantAffine() {
		return m00 * (m11 * m22 - m12 * m21)
			 - m01 * (m10 * m22 - m12 * m20)
			 + m02 * (m10 * m21 - m11 * m20);
	}

	/**
	 * Get the inverse of this matrix.
	 * @return a new matrix holding the result
	 * @throws IllegalStateException if the matrix is singular i.e. the determinant is zero
	 */
	public Matrix44 inverse() throws IllegalStateException {
		Matrix44 result = new Matrix44();
		float x0  = m00 * m11 - m01 * m10;
        float x1  = m00 * m12 - m02 * m10;
        float x2  = m00 * m13 - m03 * m10;
        float x3  = m01 * m12 - m02 * m11;
        float x4  = m01 * m13 - m03 * m11;
        float x5  = m02 * m13 - m03 * m12;
        float x6  = m20 * m31 - m21 * m30;
        float x7  = m20 * m32 - m22 * m30;
        float x8  = m20 * m33 - m23 * m30;
        float x9  = m21 * m32 - m22 * m31;
        float x10 = m21 * m33 - m23 * m31;
        float x11 = m22 * m33 - m23 * m32;
        float det = x0 * x11 - x1 * x10 + x2 * x9 + x3 * x8 - x4 * x7 + x5 * x6;
		if (det == 0f) {
			throw new IllegalStateException("Cannot compute the inverse of a singular matrix.");
		}

		float reciprocal = 1.0f / det;
		result.m00 = ( m11 * x11 - m12 * x10 + m13 * x8) * reciprocal;
        result.m01 = (-m01 * x11 + m02 * x10 - m03 * x8) * reciprocal;
        result.m02 = ( m31 * x5  - m32 * x4  + m33 * x3) * reciprocal;
        result.m03 = (-m21 * x5  + m22 * x4  - m23 * x3) * reciprocal;
        result.m10 = (-m10 * x11 + m12 * x8  - m13 * x7) * reciprocal;
        result.m11 = ( m00 * x11 - m02 * x8  + m03 * x7) * reciprocal;
        result.m12 = (-m30 * x5  + m32 * x2  - m33 * x1) * reciprocal;
        result.m13 = ( m20 * x5  - m22 * x2  + m23 * x1) * reciprocal;
        result.m20 = ( m10 * x10 - m11 * x8  + m13 * x6) * reciprocal;
        result.m21 = (-m00 * x10 + m01 * x8  - m03 * x6) * reciprocal;
        result.m22 = ( m30 * x4  - m31 * x2  + m33 * x0) * reciprocal;
        result.m23 = (-m20 * x4  + m21 * x2  - m23 * x0) * reciprocal;
        result.m30 = (-m10 * x9  + m11 * x7  - m12 * x6) * reciprocal;
        result.m31 = ( m00 * x9  - m01 * x7  + m02 * x6) * reciprocal;
        result.m32 = (-m30 * x3  + m31 * x1  - m32 * x0) * reciprocal;
        result.m33 = ( m20 * x3  - m21 * x1  + m22 * x0) * reciprocal;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Matrix44) {
			Matrix44 mat = (Matrix44) obj;
			if (m00 != mat.m00)
				return false;
			if (m01 != mat.m01)
				return false;
			if (m02 != mat.m02)
				return false;
			if (m03 != mat.m03)
				return false;
			if (m10 != mat.m10)
				return false;
			if (m11 != mat.m11)
				return false;
			if (m12 != mat.m12)
				return false;
			if (m13 != mat.m13)
				return false;
			if (m20 != mat.m20)
				return false;
			if (m21 != mat.m21)
				return false;
			if (m22 != mat.m22)
				return false;
			if (m23 != mat.m23)
				return false;
			if (m30 != mat.m30)
				return false;
			if (m31 != mat.m31)
				return false;
			if (m32 != mat.m32)
				return false;
			if (m33 != mat.m33)
				return false;
			
			return true;
		}
		return false;
	}
	
	/**
	 * Get an array of each element in column-major ordering.
	 * @return a new array holding the result
	 */
	public float[] toArray() {
		float[] result = new float[16];
		result[0]  = m00;
		result[1]  = m10;
		result[2]  = m20;
		result[3]  = m30;
		result[4]  = m01;
		result[5]  = m11;
		result[6]  = m21;
		result[7]  = m31;
		result[8]  = m02;
		result[9]  = m12;
		result[10] = m22;
		result[11] = m32;
		result[12] = m03;
		result[13] = m13;
		result[14] = m23;
		result[15] = m33;
		return result;
	}
	
	/**
	 * Get a float buffer of each element in this matrix ordered in column-major.
	 * @return
	 */
	public FloatBuffer toFloatBuffer() {
		FloatBuffer result = BufferUtils.createFloatBuffer(m00, m10, m20, m30,
														   m01, m11, m21, m31,
														   m02, m12, m22, m32,
														   m03, m13, m23, m33);
		return result;
	}

	@Override
	public String toString() {
		return String.format(
				"Matrix4: [%.3f %.3f %.3f %.3f]\n%9s[%.3f %.3f %.3f %.3f]\n%9s[%.3f %.3f %.3f %.3f]\n%9s[%.3f %.3f %.3f %.3f]",
				m00, m01, m02, m03, "", m10, m11, m12, m13, "", m20, m21, m22, m23, "", m30, m31, m32, m33);
	}
}
