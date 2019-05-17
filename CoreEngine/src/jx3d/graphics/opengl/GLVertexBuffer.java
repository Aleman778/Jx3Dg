package jx3d.graphics.opengl;

import jx3d.core.JX3D;
import jx3d.graphics.VertexBuffer;
import jx3d.util.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Represents an OpenGL implementation of a vertex buffer.<br>
 * The OpenGL buffer target for a vertex buffer is <i>GL_ARRAY_BUFFER</i>.
 *
 * @author Aleman778
 * @see VertexBuffer
 * @since 1.0
 */
public class GLVertexBuffer extends VertexBuffer {

    private final GL20 gl;

    private int object;
    private int usage;
    private boolean allocated;
    private ByteBuffer mapBuffer;

    /**
     * Create an empty vertex buffer with a provided usage.
     *
     * @param usage    how the buffer is used
     */
    public GLVertexBuffer(int usage) {
        this(0, usage);
    }

    /**
     * Creates an empty vertex buffer with a desired maximum capacity.
     *
     * @param capacity the maximum number of elements the buffer can hold
     * @param usage    describes how the buffer is used
     */
    public GLVertexBuffer(int capacity, int usage) {
        super(capacity);

        this.gl = JX3D.gl20;
        this.usage = usage;
        this.allocated = false;
        this.object = gl.genBuffer();
    }

    @Override
    public void bind() {
        gl.bindBuffer(GL20.ARRAY_BUFFER, object);
    }

    @Override
    public void unbind() {
        gl.bindBuffer(GL20.ARRAY_BUFFER, 0);
    }

    @Override
    public void set(float[] data) {
        if (data == null)
            throw new IllegalArgumentException("Cannot set null as vertex buffer data.");

        FloatBuffer buffer = BufferUtils.createFloatBuffer(data);
        set(buffer);
    }

    @Override
    public void set(FloatBuffer buffer) {
        if (buffer == null)
            throw new IllegalArgumentException("Cannot set null as vertex buffer.");

        int size = buffer.remaining() * Float.BYTES;
        gl.bufferData(GL20.ARRAY_BUFFER, size, buffer, usage);
        capacity = buffer.remaining();
        allocated = true;
    }

    @Override
    public void insert(float[] data, int index) {
        if (data == null)
            throw new IllegalArgumentException("Cannot insert null into vertex buffer.");

        FloatBuffer buffer = BufferUtils.createFloatBuffer(data);
        insert(buffer, index);
    }

    @Override
    public void insert(FloatBuffer buffer, int index) {
        if (buffer == null)
            throw new IllegalArgumentException("Cannot insert null buffer into vertex buffer.");

        if (capacity == 0 && !allocated)
            capacity = buffer.remaining() + index;

        if (index + buffer.remaining() > capacity)
            throw new IllegalArgumentException("The insert exceeds the capacity of this buffer.");

        if (!allocated)
            gl.bufferData(GL20.ARRAY_BUFFER, capacity * Float.BYTES, null, usage);

        gl.bufferSubData(GL20.ARRAY_BUFFER, index * Float.BYTES, buffer.remaining(), buffer);
        allocated = true;
    }

    @Override
    public ByteBuffer map() {
        if (mapBuffer != null)
            throw new IllegalStateException("Call the unmap method before calling map.");
        mapBuffer = gl.mapBuffer(GL20.ARRAY_BUFFER, GL20.READ_WRITE);
        return mapBuffer;
    }

    @Override
    public void unmap() {
        if (mapBuffer == null)
            throw new IllegalStateException("Call the map method before calling unmap.");

        mapBuffer = null;
        gl.unmapBuffer(GL20.ARRAY_BUFFER);
    }

    @Override
    public void resize(int newCapacity) {
        capacity = newCapacity;
        gl.bufferData(GL20.ARRAY_BUFFER, capacity, null, usage);
    }


    @Override
    public void dispose() {
        gl.deleteBuffer(object);
        object = -1;
    }
}
