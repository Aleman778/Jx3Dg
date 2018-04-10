package jx3d.graphics;

import jx3d.util.Disposable;

/**
 * Represents an array of vertex buffers and each buffer is described by an attribute map.
 * @since 1.0
 * @author Aleman778
 */
public abstract class VertexArray implements Disposable {

	/**
	 * Bind the vertex array.
	 */
	public abstract void bind();
	
	/**
	 * Unbind the vertex array.
	 */
	public abstract void unbind();

	/**
	 * Put a given vertex buffer into this vertex array. The provided attribute map has
	 * to represent the structure of each attribute inside the given vertex buffer.
	 * @param buf the vertex buffer to add
	 * @param attribs the attribute map that describes the vertex buffer
	 */
	public abstract void put(VertexBuffer buf, AttributeMap attribs);
	
	/**
	 * Clear the vertex array.<br>
	 * <i>Note</i>: this method may not remove the actual contents of this vertex array,
	 * it depends on the implementation and graphics API. This method does not clear any
	 * data that have been added to this array, this can be done manually by calling
	 * {@link Buffer#clear()} for each buffer. In order to force the graphics API to 
	 * remove the data then use {@link VertexArray#dispose()} instead.
	 */
	public abstract void clear();
	
	/**
	 * Get the number of elements in the vertex array.
	 * @return the count
	 */
	public abstract int count();
}
