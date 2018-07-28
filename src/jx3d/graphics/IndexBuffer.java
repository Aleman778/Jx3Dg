package jx3d.graphics;

import java.nio.ShortBuffer;

/**
 * Representing an abstract buffer used to store indices that in combination with
 * a vertex buffer/ array constructs a shape that can be rendered.
 * This buffer uses short as a primitive type for each element.  
 * @since 1.0
 * @author Aleman778
 */
public abstract class IndexBuffer extends Buffer {

	/**
	 * Default constructor.
	 * Create an empty index buffer.
	 */
	public IndexBuffer() {
		super();
	}
	
	/**
	 * Constructor.
	 * Create an empty index buffer with a specific capacity.
	 * @param capacity the maximum number of elements in the index buffer
	 */
	public IndexBuffer(int capacity) {
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
	public abstract void set(short[] data);
	
	/**
	 * Set the contents of the provided float buffer into this buffer.<br>
	 * <i>Note:</i> if there already contains data in this buffer,
	 * this method will overwrite the previous data.<br>
	 * <i>Note:</i> the buffers capacity is set to the remaining
	 * size of the float buffer.
	 * @throws IllegalArgumentException if the input buffer is null
	 * @param buffer the buffer data to set
	 */
	public abstract void set(ShortBuffer buffer);

    /**
     * Put the provided data in this buffer at the specified index.
     * @throws IllegalArgumentException if the insert exceeds the buffers capacity
     * 									or if the input data is null
     * @param data the data to put in the buffer
     */
	public abstract void insert(short[] data, int index);

    /**
     * Insert the contents of the provided float buffer into this buffer 
     * at a specific index
     * @throws IllegalArgumentException if the insert exceeds the buffers capacity
     * 									or if the input buffer is null
     * @param buffer the buffer data to insert 
     */
	public abstract void insert(ShortBuffer buffer, int index);

	/**
	 * Map this buffer's data into the client's address space.
	 */
	public abstract ShortBuffer map();

	/**
	 * Unmaps the buffer.<br>
	 * <b>Note:</b> this call is required for any buffer changes to be rendered. 
	 */
	public abstract void unmap();
}
