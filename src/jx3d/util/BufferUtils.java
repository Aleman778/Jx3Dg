package jx3d.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

import org.joml.*;

/**
 * Buffer utilities is a helper class that create buffers. 
 * @since 1.0
 * @author Aleman778
 */
public class BufferUtils {

	/**
	 * Static class, cannot be instantiated. 
	 */
	private BufferUtils() {}
	
	/**
	 * Creates an empty byte buffer with provided capacity.
	 * @param capacity the number of bytes the buffer can hold
	 * @return a new empty byte buffer 
	 */
    public static ByteBuffer createEmptyByteBuffer(int capacity) {
        ByteBuffer result = ByteBuffer.allocateDirect(capacity).order(ByteOrder.nativeOrder());
        return result;
    }
    
    /**
     * Creates a byte buffer with provided data.
     * @param data the data to put in buffer
     * @return a new byte buffer
     */
    public static ByteBuffer createByteBuffer(byte... data) {
        ByteBuffer result = ByteBuffer.allocateDirect(data.length).order(ByteOrder.nativeOrder());
        result.put(data).flip();
        return result;
    }

	/**
	 * Creates an empty integer buffer with provided capacity.
	 * @param capacity the number of elements the buffer can hold
	 * @return a new empty integer buffer 
	 */
    public static IntBuffer createEmptyIntBuffer(int capacity) {
    	IntBuffer result = createEmptyByteBuffer(capacity * Integer.BYTES).asIntBuffer();
        return result;
    }
    
    /**
     * Creates an integer buffer with provided data.
     * @param data the data to put in buffer
     * @return a new integer buffer
     */
    public static IntBuffer createIntBuffer(int... data) {
        IntBuffer result = ByteBuffer.allocateDirect(data.length * Integer.BYTES).order(ByteOrder.nativeOrder()).asIntBuffer();
        result.put(data).flip();
        return result;
    }

	/**
	 * Creates an empty long buffer with provided capacity.
	 * @param capacity the number of elements the buffer can hold
	 * @return a new empty integer buffer 
	 */
    public static LongBuffer createEmptyLongBuffer(int capacity) {
    	LongBuffer result = createEmptyByteBuffer(capacity * Integer.BYTES).asLongBuffer();
        return result;
    }
    
    /**
     * Creates an long buffer with provided data.
     * @param data the data to put in buffer
     * @return a new integer buffer
     */
    public static LongBuffer createLongBuffer(long... data) {
        LongBuffer result = ByteBuffer.allocateDirect(data.length * Integer.BYTES).order(ByteOrder.nativeOrder()).asLongBuffer();
        result.put(data).flip();
        return result;
    }

	/**
	 * Creates an empty short buffer with provided capacity.
	 * @param capacity the number of elements the buffer can hold
	 * @return a new empty integer buffer 
	 */
    public static ShortBuffer createEmptyShortBuffer(int capacity) {
    	ShortBuffer result = createEmptyByteBuffer(capacity * Integer.BYTES).asShortBuffer();
        return result;
    }
    
    /**
     * Creates an short buffer with provided data.
     * @param data the data to put in buffer
     * @return a new integer buffer
     */
    public static ShortBuffer createShortBuffer(short... data) {
        ShortBuffer result = ByteBuffer.allocateDirect(data.length * Integer.BYTES).order(ByteOrder.nativeOrder()).asShortBuffer();
        result.put(data).flip();
        return result;
    }

	/**
	 * Creates an empty float buffer with provided capacity.
	 * @param capacity the number of elements the buffer can hold
	 * @return a new empty float buffer 
	 */
    public static FloatBuffer createEmptyFloatBuffer(int capacity) {
    	FloatBuffer result = createEmptyByteBuffer(capacity * Float.BYTES).asFloatBuffer();
        return result;
    }
    
    /**
     * Creates a float buffer with provided data.
     * @param data the data to put in buffer
     * @return a new float buffer
     */
    public static FloatBuffer createFloatBuffer(float... data) {
        FloatBuffer result = ByteBuffer.allocateDirect(data.length * Float.BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer();
        result.put(data).flip();
        return result;
    }

	/**
	 * Creates an empty double buffer with provided capacity.
	 * @param capacity the number of elements the buffer can hold
	 * @return a new empty double buffer 
	 */
    public static DoubleBuffer createEmptyDoubleBuffer(int capacity) {
    	DoubleBuffer result = createEmptyByteBuffer(capacity * Double.BYTES).asDoubleBuffer();
        return result;
    }
    
    /**
     * Creates a double buffer with provided data.
     * @param data the data to put in buffer
     * @return a new double buffer
     */
    public static DoubleBuffer createDoubleBuffer(double... data) {
        DoubleBuffer result = ByteBuffer.allocateDirect(data.length * Double.BYTES).order(ByteOrder.nativeOrder()).asDoubleBuffer();
        result.put(data).flip();
        return result;
    }
    
    public static FloatBuffer toFloatBuffer(Matrix3f m) {
    	return BufferUtils.createFloatBuffer(m.m00, m.m01, m.m02,
    										 m.m10, m.m11, m.m12,
									 		 m.m20, m.m21, m.m22);
    }

    public static FloatBuffer toFloatBuffer(Matrix4f m) {
    	return BufferUtils.createFloatBuffer(m.m00(), m.m01(), m.m02(), m.m03(),
    										 m.m10(), m.m11(), m.m12(), m.m13(),
									 		 m.m20(), m.m21(), m.m22(), m.m23(),
									 		 m.m30(), m.m31(), m.m32(), m.m33());
    }
}
