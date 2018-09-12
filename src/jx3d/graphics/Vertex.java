package jx3d.graphics;

import org.joml.Vector3f;

/**
 * Standard vertex holds the following data:<br>
 * <ul>
 * 		<li><code>position</code> - the vertex location in 3D space.</li>
 * 		<li><code>normal</code> - the normal vector corresponding to this vertex.</li>
 * 		<li><code>texcoord</code> - the texture coordinates used by this vertex.</li>
 * </ul>
 * There is also a method {@link Vertex#data()} that is used to convert this vertex
 * from its variable vector format to a float array which is used to create vertex buffers.
 * 
 * This class can be inherited in order to change the vertex data.
 * @since 1.0
 * @author Aleman778
 */
public class Vertex {

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
	 * @return array holding the vertex data
	 */
	public float[] data() {
		return new float[] {
			position.x, position.x, position.x,
			normal.x, normal.y, normal.z,
			texcoord.x, texcoord.y
		};
	}
}
