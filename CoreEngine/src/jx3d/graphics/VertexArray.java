package jx3d.graphics;

import jx3d.graphics.opengl.GLVertexArray;
import jx3d.util.Disposable;

/**
 * Represents an array of vertex buffers and each buffer is described by an attribute map.
 *
 * @author Aleman778
 * @since 1.0
 */
public abstract class VertexArray implements Disposable {

    /**
     * Create a new vertex array based on the rendering API.
     * @return a new vertex array object
     */
    public static VertexArray create() {
        switch (Context.getRenderAPI()) {
            case OPENGL: return new GLVertexArray();
        }
        throw new IllegalStateException("There is no vertex array support for the current render API!");
    }

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
     *
     * @param buf     the vertex buffer to add
     * @param attribs the attribute map that describes the vertex buffer
     */
    public abstract void put(VertexBuffer buf, VertexAttribute attribs);

    /**
     * Get the number of elements in the vertex array.
     *
     * @return the count
     */
    public abstract int count();
}
