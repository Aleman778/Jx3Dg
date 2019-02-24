package jx3d.graphics;

import jx3d.util.Disposable;

/**
 * Abstract representation of a buffer used store data of a specific primitive type.
 * The capacity of the buffer is generally static but can be changed during runtime
 * if implementation supports it.
 * This class is similar to {@link java.nio.Buffer} but this class has a different purpose:
 * <ul>
 * <li>
 * This buffer should be used to store information necessary to render an object of multiple objects
 * on a graphics card using a standard graphics API.
 * </li>
 * <li>
 * Any implementation of this buffer class should only be used for sending data to the
 * graphical processing unit (GPU) and has no actual client side storage.
 * </li>
 * <li>
 * The user may request data to be accessed on the client but this should only be handled
 * by the targeted graphics API.
 * </li>
 * </ul>
 *
 * @author Aleman778
 * @since 1.0
 */
public abstract class Buffer implements Disposable {

    /**
     * The maximum number of elements that this buffer can hold.
     */
    protected int capacity;

    /**
     * Default Constructor.
     * Create an empty buffer
     */
    public Buffer() {
        this(0);
    }

    /**
     * Constructor.
     * Create an empty buffer with a specific capacity.
     *
     * @param capacity the capacity to set
     */
    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Bind this buffer.
     */
    public abstract void bind();

    /**
     * Unbind this buffer.
     */
    public abstract void unbind();

    /**
     * Resizing the buffer changes the buffers capacity to the given <code>newCapacity</code>.<br>
     * <i>Note:</i> the buffer has a fixed capacity and will not automatically resize when full
     * unless you explicitly call this function.<br>
     * <i>Note:</i> this method generally creates a new buffer and copies its content so
     * it is recommended to set a the capacity once to be large enough to hold all the dynamic data.
     *
     * @param newCapacity the new capacity of the buffer
     */
    public abstract void resize(int newCapacity);

    /**
     * Get the capacity of this buffer.
     *
     * @return the capacity
     */
    public final int capacity() {
        return capacity;
    }
}
