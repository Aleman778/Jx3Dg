package jx3d.graphics;

import java.nio.FloatBuffer;

/**
 * Representing an abstract buffer used to store information about vertices.
 * The layout of this buffer is specified in the buffer layout class
 * This buffer uses float as a primitive type for each element.  
 * @since 1.0
 * @author Aleman778
 */
public abstract class VertexBuffer extends Buffer {
	
	/**
	 * Constructor.
	 * @param capacity the maximum number of elements in the index buffer
	 */
	public VertexBuffer(int capacity) {
		super(capacity);
	}

    /**
     * Put data in the buffer.
     * @param data the data to put
     */
	public abstract void put(float[] data);

    /**
     * Put data in the buffer.
     * @param buffer  the data to put
     */
	public abstract void put(FloatBuffer buffer);

	/**
	 * Map this buffer's data into the client's address space.
	 */
	public abstract FloatBuffer map();

	/**
	 * Unmaps the buffer.<br>
	 * <b>Note:</b> this call is required for any buffer changes to be rendered. 
	 */
	public abstract void unmap();
}
