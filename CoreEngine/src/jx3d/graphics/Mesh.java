package jx3d.graphics;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

/**
 * <p>
 * The mesh is a container for all the data necessary to represent
 * an object in 3d-space. The data stored here does not specify
 * how it should be rendered i.e. it has no materials.
 * This class has support for the following properties
 * <code>vertices</code>, <code>normals</code>, <code>uv</code>
 * [1 through 8], <code>colors</code>, and <code>indices</code>.<br>
 * </p>
 * <p>
 * <i>Note:</i> that by default these properties are set to null, which means that
 * they are not used by the mesh.
 * </p>
 *
 * @author Aleman778
 * @since 1.0
 */
public class Mesh {

    /**
     * The vertices used by this mesh.
     */
    public Vector3f[] vertices;

    /**
     * The normals used by this mesh.
     */
    public Vector3f[] normals;

    /**
     * The base texture coordinates used by this mesh.
     */
    public Vector2f[] uv;

    /**
     * The second texture coordinates used by this mesh.
     */
    public Vector2f[] uv2;

    /**
     * The third texture coordinates used by this mesh.
     */
    public Vector2f[] uv3;

    /**
     * The forth texture coordinates used by this mesh.
     */
    public Vector2f[] uv4;

    /**
     * The fifth texture coordinates used by this mesh.
     */
    public Vector2f[] uv5;

    /**
     * The sixth texture coordinates used by this mesh.
     */
    public Vector2f[] uv6;

    /**
     * The seventh texture coordinates used by this mesh.
     */
    public Vector2f[] uv7;

    /**
     * The eights texture coordinates used by this mesh.
     */
    public Vector2f[] uv8;

    /**
     * The colors used by this mesh.
     */
    public Color[] colors;

    /**
     * The tangents used by this mesh.
     */
    public Vector4f[] tangens;

    /**
     * Array of indices, this has to be divisible by
     * the number of indices that represents a face.
     */
    public short[] indices;

    /**
     * Constructor creates an empty mesh with a specific capacity.
     */
    public Mesh() {
    }

    /**
     * Get the number of vertices that are created.
     *
     * @return the number of vertices
     */
    public int vertexCount() {
        if (vertices == null)
            return 0;

        return vertices.length;
    }
}
