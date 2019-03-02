package jx3d.graphics.opengl;

import jx3d.core.JX3D;
import jx3d.graphics.IndexBuffer;
import jx3d.util.BufferUtils;

import java.nio.ShortBuffer;

/**
 * Represents an OpenGL implementation of an index buffer.<br>
 * The OpenGL buffer target for an index buffer is <i>GL_ELEMENT_ARRAY_BUFFER</i>.
 *
 * @author Aleman778
 * @see IndexBuffer
 * @since 1.0
 */
public class GLIndexBuffer extends IndexBuffer {

    private final GL20 gl;

    private int object;
    private int usage;
    private boolean allocated;
    private ShortBuffer mapBuffer;

    /**
     * Create an empty index buffer with a provided usage.
     *
     * @param usage    describes how the buffer is used
     */
    public GLIndexBuffer(int usage) {
        this(0, usage);
    }

    /**
     * Creates an empty index buffer with a desired maximum capacity.
     *
     * @param capacity the maximum number of elements the buffer can hold
     * @param usage    describes how the buffer is used
     */
    public GLIndexBuffer(int capacity, int usage) {
        super(capacity);

        this.gl = JX3D.gl20;
        this.usage = usage;
        this.allocated = false;
        this.object = gl.genBuffer();
    }

    @Override
    public void bind() {
        gl.bindBuffer(GL20.ELEMENT_ARRAY_BUFFER, object);
    }

    @Override
    public void unbind() {
        gl.bindBuffer(GL20.ELEMENT_ARRAY_BUFFER, 0);
    }

    @Override
    public void set(short[] data) {
        if (data == null)
            throw new IllegalArgumentException("Cannot set null as index buffer data.");

        ShortBuffer buffer = BufferUtils.createShortBuffer(data);
        set(buffer);
    }

    @Override
    public void set(ShortBuffer buffer) {
        if (buffer == null)
            throw new IllegalArgumentException("Cannot set null as vertex buffer.");

        int size = buffer.remaining();
        gl.bufferData(GL20.ELEMENT_ARRAY_BUFFER, size, buffer, usage);
        capacity = buffer.remaining();
        allocated = true;
    }

    @Override
    public void insert(short[] data, int index) {
        if (data == null)
            throw new IllegalArgumentException("Cannot put null data into vertex buffer.");

        ShortBuffer buffer = BufferUtils.createShortBuffer(data);
        insert(buffer, index);
    }

    @Override
    public void insert(ShortBuffer buffer, int index) {
        if (buffer == null)
            throw new IllegalArgumentException("Cannot put null buffer into vertex buffer.");

        if (capacity == 0 && !allocated)
            capacity = buffer.remaining() + index;

        if (index + buffer.remaining() > capacity)
            throw new IllegalArgumentException("The insert exceeds the capacity of this buffer.");

        if (!allocated)
            gl.bufferData(GL20.ELEMENT_ARRAY_BUFFER, capacity * Float.BYTES, null, usage);

        gl.bufferSubData(GL20.ELEMENT_ARRAY_BUFFER, index * Short.BYTES, buffer.remaining(), buffer);
        allocated = true;
    }

    @Override
    public ShortBuffer map() {
        if (mapBuffer != null)
            throw new IllegalStateException("Call the unmap method before calling map.");

        return (mapBuffer = gl.mapBuffer(GL20.ELEMENT_ARRAY_BUFFER, GL20.READ_WRITE).asShortBuffer());
    }

    @Override
    public void unmap() {
        if (mapBuffer == null)
            throw new IllegalStateException("Call the map method before calling unmap.");

        mapBuffer = null;
        gl.unmapBuffer(GL20.ELEMENT_ARRAY_BUFFER);
    }

    @Override
    public void resize(int newCapacity) {
        capacity = newCapacity;
        gl.bufferData(GL20.ELEMENT_ARRAY_BUFFER, capacity, null, usage);
    }


    @Override
    public void dispose() {
        gl.deleteBuffer(object);
        object = -1;
    }
}
