package jx3d.graphics;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Vertex attribute defines how the vertex data should be interpreted.
 *
 * @author Aleman778
 * @since 1.0
 */
public class VertexAttribute {

    /**
     * List of vertex attributes.
     */
    private ArrayList<Attribute> attribs;

    /**
     * The number of elements stored in the vertex attribute.
     */
    private int numElements;

    /**
     * Default Constructor.
     * Create an empty vertex attribute object.
     */
    public VertexAttribute() {
        attribs = new ArrayList<>();
    }

    /**
     * Insert an attribute at a specific location.
     * The location is connected to the layout in shaders.
     *
     * @param location   the attribute location
     * @param size       the number of elements
     * @param normalized the data is normalized, default value is false
     * @param stride     the number of floats between two consecutive attributes, default value is 0
     * @param offset     the number of to the start of this attribute, default value is 0
     */
    public void add(int location, int size, int type, boolean normalized, int stride, int offset) {
        attribs.add(new Attribute(location, size, type, normalized, stride, offset));
        numElements += size;
    }

    /**
     * Remove an attribute located at the given argument.
     *
     * @param location the location of the attribute to remove
     */
    public void remove(int location) {
        attribs.remove(location);
    }

    /**
     * Get an iterator for the list of attributes.
     *
     * @return an iterator object used for iterating through attributes
     */
    public Iterator<Attribute> iterator() {
        return attribs.iterator();
    }

    /**
     * Get an attribute at a specific location.
     * The location is connected to the layout in shaders.
     *
     * @param location the attribute location
     * @return the attribute, null is returned if there
     * is no attribute at the specified location
     */
    public Attribute get(int location) {
        return attribs.get(location);
    }

    /**
     * Check if the vertex attribute is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean empty() {
        return attribs.isEmpty();
    }

    /**
     * Get the number of attributes in one segment.
     *
     * @return the number of attributes
     */
    public int count() {
        return attribs.size();
    }

    /**
     * THe number of elements stored in the vertex attribute.
     *
     * @return the count the number of elements in one segment
     */
    public int size() {
        return numElements;
    }

    /**
     * Attributes specifies the structure of the data in a vertex buffer.
     *
     * @author Aleman778
     * @since 1.0
     */
    public class Attribute {

        /**
         * The location of the attribute.
         * This is connected to the layouts of the shader.
         */
        public final int location;

        /**
         * The number of components per segment
         */
        public final int size;

        /**
         * The type used by this attribute.
         */
        public final int type;

        /**
         * The data is normalized in the buffer.
         */
        public final boolean normalized;

        /**
         * The element offset between two consecutive segments.
         */
        public final int stride;

        /**
         * The element offset to where the data starts.
         */
        public final int pointer;

        /**
         * Creates an attribute with the specific layout.
         *
         * @param location   the location of the attribute
         * @param size       the size of the attribute
         * @param normalized the data is normalized
         * @param stride     the number of elements to next vertex
         * @param pointer    the offset to this attribute in a vertex
         */
        public Attribute(int location, int size, int type, boolean normalized, int stride, int pointer) {
            this.location = location;
            this.size = size;
            this.type = type;
            this.normalized = normalized;
            this.stride = stride;
            this.pointer = pointer;

        }
    }
}
