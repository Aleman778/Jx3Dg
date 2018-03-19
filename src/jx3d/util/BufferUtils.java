package jx3d.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

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
}
