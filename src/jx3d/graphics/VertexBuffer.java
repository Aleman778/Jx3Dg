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
	 * Default constructor.
	 * Create an empty vertex buffer.
	 */
	public VertexBuffer() {
		super();
	}
	
	/**
	 * Constructor.
	 * Create an empty vertex buffer with a specific capacity.
	 * @param capacity the maximum number of elements in the vertex buffer
	 */
	public VertexBuffer(int capacity) {
		super(capacity);
	}
	
	/**
	 * Set the contents of the provided float array into this buffer.<br>
	 * <i>Note:</i> if there already contains data in this buffer,
	 * this method will overwrite the previous data.<br>
	 * <i>Note:</i> the buffers capacity is set to the same size
	 * as the length of the input array.
	 * @throws IllegalArgumentException if the input array is null
	 * @param data the data to set
	 */
	public abstract void set(float[] data);
	
	/**
	 * Set the contents of the provided float buffer into this buffer.<br>
	 * <i>Note:</i> if there already contains data in this buffer,
	 * this method will overwrite the previous data.<br>
	 * <i>Note:</i> the buffers capacity is set to the remaining
	 * size of the float buffer.
	 * @throws IllegalArgumentException if the input buffer is null
	 * @param buffer the buffer data to set
	 */
	public abstract void set(FloatBuffer buffer);

    /**
     * Put the provided data in this buffer at the specified index.
     * @throws IllegalArgumentException if the insert exceeds the buffers capacity
     * 									or if the input data is null
     * @param data the data to put in the buffer
     */
	public abstract void insert(float[] data, int index);

    /**
     * Insert the contents of the provided float buffer into this buffer 
     * at a specific index
     * @throws IllegalArgumentException if the insert exceeds the buffers capacity
     * 									or if the input buffer is null
     * @param buffer the buffer data to insert 
     */
	public abstract void insert(FloatBuffer buffer, int index);

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
