package jx3d.graphics;

import jx3d.util.Disposable;

/**
 * Abstract representation of a buffer used store data of a specific primitive type. 
 * The capacity of the buffer is generally static but can be changed during runtime
 * if implementation supports it.
 * This class is similar to {@link java.nio.Buffer} but this class has a different purpose:
 * <ul>
 * 		<li>
 *			This buffer should be used to store information necessary to render an object of multiple objects
 *			on a graphics card using a standard graphics API. 
 * 		</li>
 * 		<li>
 * 			Any implementation of this buffer class should only be used for sending data to the
 *			graphical processing unit (GPU) and has no actual client side storage.
 * 		</li>
 *  	<li>
 *  		The user may request data to be accessed on the client but this should only be handled
 *  		by the targeted graphics API.
 *  	</li>
 * </ul>
 * @since 1.0
 * @author Aleman778
 */
public abstract class Buffer implements Disposable {
	
	protected int capacity;
	protected int position;
	protected int count;
	
	/**
	 * Constructor.
	 * Create an empty buffer with a specific capacity.
	 * @param capacity the capacity to set
	 */
	public Buffer(int capacity) {
		this.capacity = capacity;
		this.position = 0;
		this.count = 0;
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
	 * Clear the buffer.<br>
	 * <i>Note</i>: this method may not remove the actual contents of this buffer on the GPU,
	 * it depends on the implementation and graphics API. In order to force the graphics API to 
	 * remove the data then use {@link Buffer#dispose()} instead.
	 */
	public void clear() {
		position = 0;
		count = 0;
	}
	
	/**
	 * Get the capacity of this buffer.
	 * @return the capacity
	 */
	public final int capacity() {
		return capacity;
	}
	
	/**
	 * Get the current position in this buffer.
	 * @return the position
	 */
	public final int position() {
		return position;
	}
	
	/**
	 * Get the number of elements currently in the buffer.
	 * @return
	 */
	public final int count() {
		return count;
	}
	
	/**
	 * Check if this buffer is empty.
	 * @return true if the buffer is empty, otherwise false
	 */
	public final boolean empty() {
		return count == 0;
	}
}
