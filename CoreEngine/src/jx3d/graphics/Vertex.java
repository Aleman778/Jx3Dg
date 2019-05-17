package jx3d.graphics;

import jx3d.core.Module;
import org.joml.Vector3f;


/**
 * <p>
 * Standard vertex holds the following ordered data:<br>
 * <ol>
 * <li><code>position</code> - the vertex location in 3D space.</li>
 * <li><code>normal</code> - the normal vector corresponding to this vertex.</li>
 * <li><code>texcoord</code> - the texture coordinates used by this vertex.</li>
 * </ol>
 * </p>
 * <p>
 * The method {@link Vertex#data()} is used to convert this vertex from its variable
 * vector format to a float array which is then used to create vertex buffers.
 * </p>
 * <p>
 * The method {@link Vertex#attributes()} returns the vertex attributes that are used
 * i.e. the format of the vertex data.
 * </p>
 * This class can be inherited in order to change the vertex data.
 *
 * @author Aleman778
 * @since 1.0
 */
public class Vertex {

    private static final VertexAttribute ATTRIBUTE;

    static {
        ATTRIBUTE = new VertexAttribute();
        ATTRIBUTE.add(0, 3, Module.FLOAT, false, 8, 0);
        ATTRIBUTE.add(1, 3, Module.FLOAT, false, 8, 3);
        ATTRIBUTE.add(2, 2, Module.FLOAT, false, 8, 5);
    }

    /**
     * The position vector of this vertex.
     */
    public Vector3f position;

    /**
     * The normal vector of this vertex.
     */
    public Vector3f normal;

    /**
     * The texture coordinate vector of this vertex.
     */
    public Vector3f texcoord;

    /**
     * Get an array containing the data in this vertex.
     *
     * @return array holding the vertex data
     */
    public float[] data() {
        return new float[]{
                position.x, position.x, position.x,
                normal.x, normal.y, normal.z,
                texcoord.x, texcoord.y
        };
    }

    public VertexAttribute attributes() {
        return ATTRIBUTE;
    }
}
