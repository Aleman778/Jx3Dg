package jx3d.math;

/**
 * Four dimensional complex number.
 * @author Alexander Mennborg
 */
public class Quaternion {
    
    /**
     * The first component of the imaginary part.
     */
    public float x;
    
    /**
     * The second component of the imaginary part.
     */
    public float y;
    
    /**
     * The third component of the imaginary part.
     */
    public float z;
    
    /**
     * The real part of the quaternion.
     */
    public float w;

    /**
     * Constructor.
     * Create a new quaternion and initialize its components to <code>(x=0, y=0, z=0, w=1)</code> where
     * <code>(x, y, z)</code> is the imaginary part and <code>w</code> is the real part.
     */
    public Quaternion() {
        this(0, 0, 0, 1);
    }

    /**
     * Constructor.
     * Create a new quaternion and initialize its components to the given values <code>(x, y, z, w)</code>.
     * @param x the first component of the imaginary part
     * @param y the second component of the imaginary part
     * @param z the third component of the imaginary part
     * @param w the real part of the quaternion
     */
    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
    
    /**
	 * Constructor.
	 * Create a copy of the provided quaternion.
	 * @param copy the quaternion to copy from
	 */
    public Quaternion(Quaternion copy) {
        this.x = copy.x;
        this.y = copy.y;
        this.z = copy.z;
        this.w = copy.w;
    }

    /**
     * Set the components of this quaternion.
     * @param x the first component of the imaginary part
     * @param y the second component of the imaginary part
     * @param z the third component of the imaginary part
     * @param w the real part of the quaternion
     */
    public Quaternion set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }
    
    public Quaternion set(AxisAngle a) {
    	float s = (float) Math.sin(a.angle * 0.5);
    	x = a.x * s;
    	y = a.y * s;
    	z = a.z * s;
    	w = (float) Math.cos(a.angle * 0.5);
    	return this;
    }
    
    
    /**
     * Set the quaternion based on the euler angles pitch, yaw and roll.
     * @param x the rotation about the pitch axis in <i>radians</i>
     * @param y the rotation about the yaw axis in <i>radians</i>
     * @param z the rotation about the roll axis in <i>radians</i>
     * @return a new quaternion holding the result
     */
    public static Quaternion euler(float x, float y, float z) {
    	Quaternion result = new Quaternion();
        float sinx = (float) Math.sin(x * 0.5f);
        float cosx = (float) Math.cos(x * 0.5f);
        float siny = (float) Math.sin(y * 0.5f);
        float cosy = (float) Math.cos(y * 0.5f);
        float sinz = (float) Math.sin(z * 0.5f);
        float cosz = (float) Math.cos(z * 0.5f);
        result.w = cosy * cosz * cosx - siny * sinz * sinx;
        result.x = siny * sinz * cosx + cosy * cosz * sinx;
        result.y = siny * cosz * cosx + cosy * sinz * sinx;
        result.z = cosy * sinz * cosx - siny * cosz * sinx;
        return result;
    }
    
    public static Quaternion rotation(Vector3D axis, float angle) {
    	return null;
    }
	
    /**
     * 
     * @param q
     * @return
     */
	public Quaternion mul(Quaternion q) {
    	Quaternion result = new Quaternion();
    	result.x =  x * q.w + y * q.z - z * q.y + w * q.x;
    	result.y = -x * q.z + y * q.w + z * q.x + w * q.y;
    	result.z =  x * q.y - y * q.x + z * q.w + w * q.z;
    	result.w = -x * q.x - y * q.y - z * q.z + w * q.w;
    	return result;
	}
    
    /**
     * Get the conjugate of the provided conjugate
     * @param quat the quaternion
     * @return
     */
    public static Quaternion conjugate(Quaternion quat) {
    	return new Quaternion(-quat.x, -quat.y, -quat.z, quat.w);
    }
	
	/**
	 * Get the normailized quaternion of <code>this</code>.
	 * @return a new quaternion holding the result
	 */
	public Quaternion normalize() {
		float len = (float) (Math.sqrt(x * x + y * y + z * z + w * w));
		if (len > 0)
			return new Quaternion(x / len, y / len, z / len, w / len);
		
		return new Quaternion();
	}

	/**
	 * Convert this quaternion into a 4 by 4 matrix.
	 * @return a new matrix holding the result.
	 */
    public Matrix44 toMatrix4() {
    	Matrix44 result = new Matrix44();
    	float invLen = 1.0f / (float) Math.sqrt(x * x + y * y + z * z + w * w);
    	float qx = x * invLen;
    	float qy = y * invLen;
    	float qz = z * invLen;
    	float qw = w * invLen;
    	result.m00 = 1.0f - 2.0f * qy * qy - 2.0f * qz * qz;
    	result.m01 = 2.0f * qx * qy - 2.0f * qz * qw;
        result.m02 = 2.0f * qx * qz + 2.0f * qy * qw;
        result.m10 = 2.0f * qx * qy + 2.0f * qz * qw;
        result.m11 = 1.0f - 2.0f * qx * qx - 2.0f * qz * qz;
        result.m12 = 2.0f * qy * qz - 2.0f * qx * qw;
        result.m20 = 2.0f * qx * qz - 2.0f * qy * qw;
        result.m21 = 2.0f * qy * qz + 2.0f * qx * qw;
    	result.m22 = 1.0f - 2.0f * qx * qx - 2.0f * qy * qy;
    	return result.transpose();
    }

    @Override
    public String toString() {
        return String.format("Quaternion: [x = %f, y = %f, z = %f, w = %f]", x, y, z, w);
    }
}
