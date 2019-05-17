package jx3d.graphics;

import jx3d.graphics.opengl.GLGraphics;
import jx3d.graphics.opengl.GLVertexBuffer;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;


/**
 * Representing an abstract buffer used to store information about vertices.
 * The layout of this buffer is specified in the buffer layout class
 * This buffer uses float as a primitive type for each element.
 *
 * @author Aleman778
 * @since 1.0
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
     *
     * @param capacity the maximum number of elements in the vertex buffer
     */
    public VertexBuffer(int capacity) {
        super(capacity);
    }

    /**
     * Create a new vertex buffer based on the rendering API.
     * @param capacity the buffer capacity
     * @param usage the buffer usage
     * @return a new vertex buffer object
     */
    public static VertexBuffer create(int capacity, int usage) {
        switch (Context.getRenderAPI()) {
            case OPENGL: return new GLVertexBuffer(capacity, GLGraphics.glGetBufferUsage(usage));
        }
        throw new IllegalStateException("There is no vertex buffer support for the current render API!");
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
    public abstract void set(float[] data);

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
    public abstract void set(FloatBuffer buffer);

    /**
     * Put the provided data in this buffer at the specified index.
     *
     * @param data the data to put in the buffer
     * @throws IllegalArgumentException if the insert exceeds the buffers capacity
     *                                  or if the input data is null
     */
    public abstract void insert(float[] data, int index);

    /**
     * Insert the contents of the provided float buffer into this buffer
     * at a specific index
     *
     * @param buffer the buffer data to insert
     * @throws IllegalArgumentException if the insert exceeds the buffers capacity
     *                                  or if the input buffer is null
     */
    public abstract void insert(FloatBuffer buffer, int index);

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
