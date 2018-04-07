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
	 * Constructor.
	 * @param capacity the maximum number of elements in the index buffer
	 */
	public IndexBuffer(int capacity) {
		super(capacity);
	}

    /**
     * Put data in the buffer.
     * @param data the data to put
     */
	public abstract void put(short[] data);

    /**
     * Put data in the buffer.
     * @param buffer  the data to put
     */
	public abstract void put(ShortBuffer buffer);

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
