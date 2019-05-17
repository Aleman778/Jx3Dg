package jx3d.graphics;

import jx3d.graphics.opengl.GLGraphics;
import jx3d.graphics.opengl.GLIndexBuffer;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

/**
 * Representing an abstract buffer used to store indices that in combination with
 * a vertex buffer/ array constructs a shape that can be rendered.
 * This buffer uses short as a primitive type for each element.
 *
 * @author Aleman778
 * @since 1.0
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
     *
     * @param capacity the maximum number of elements in the index buffer
     */
    public IndexBuffer(int capacity) {
        super(capacity);
    }

    /**
     * Create a new index buffer based on the rendering API.
     * @param capacity the buffer capacity
     * @param usage the buffer usage
     * @return a new index buffer object
     */
    public static IndexBuffer create(int capacity, int usage) {
        switch (Context.getRenderAPI()) {
            case OPENGL: return new GLIndexBuffer(capacity, GLGraphics.glGetBufferUsage(usage));
        }
        throw new IllegalStateException("There is no index buffer support for the current render API!");
    }

    /**
     * Set the contents of the provided float array into this buffer.<br>
     * <i>Note:</i> if there already contains data in this buffer,
     * this method will overwrite the previous data.<br>
     * <i>Note:</i> the buffers capacity is set to the same size
     * as the length of the input array.
     *
     * @param data the data to set
     * @throws IllegalArgumentException if the input array is null
     */
    public abstract void set(short[] data);

    /**
     * Set the contents of the provided float buffer into this buffer.<br>
     * <i>Note:</i> if there already contains data in this buffer,
     * this method will overwrite the previous data.<br>
     * <i>Note:</i> the buffers capacity is set to the remaining
     * size of the float buffer.
     *
     * @param buffer the buffer data to set
     * @throws IllegalArgumentException if the input buffer is null
     */
    public abstract void set(ShortBuffer buffer);

    /**
     * Put the provided data in this buffer at the specified index.
     *
     * @param data the data to put in the buffer
     * @throws IllegalArgumentException if the insert exceeds the buffers capacity
     *                                  or if the input data is null
     */
    public abstract void insert(short[] data, int index);

    /**
     * Insert the contents of the provided float buffer into this buffer
     * at a specific index
     *
     * @param buffer the buffer data to insert
     * @throws IllegalArgumentException if the insert exceeds the buffers capacity
     *                                  or if the input buffer is null
     */
    public abstract void insert(ShortBuffer buffer, int index);

    /**
     * Map this buffer's data into the client's address space.
     */
    public abstract ByteBuffer map();

    /**
     * Unmaps the buffer.<br>
     * <b>Note:</b> this call is required for any buffer changes to be rendered.
     */
    public abstract void unmap();
}
