package jx3d.graphics;

/**
 * A mesh data structure that contains all the 
 * vertices, indices and other data such as 
 * materials and vertex attributes. 
 * @since 1.0
 * @author Aleman778
 */
public class Mesh {

	/**
	 * Array of vertices that represents this mesh.
	 */
	private Vertex[] vertices;
	
	/**
	 * Pointer that points to the next element vertices array.
	 */
	private int vertexPtr;
	
	/**
	 * Array of indices that builds this mesh.
	 */
	private short[] indices;
	
	/**
	 * Pointer that points to the next element in the indices array.
	 */
	public int indexPtr;

	/**
	 * The attributes that are used by this mesh.
	 */
	private AttributeMap attributes;
	
	/**
	 * Create an empty mesh with a specific number of vertices and indices.
	 * @param numVertices number of vertices
	 * @param numIndices number of indices
	 */
	public Mesh(int numVertices, int numIndices) {
		this.vertices = new Vertex[numVertices];
		this.indices = new short[numIndices];
		this.vertexPtr = 0;
		this.indexPtr = 0;
	}
	
	/**
	 * Create a mesh with provided vertices and indices.
	 * @param vertices
	 * @param indices
	 */
	public Mesh(Vertex[] vertices, short[] indices) {
		this(vertices, indices, null);
	}

	/**
	 * Create a mesh with provided vertics, indices 
	 * a texture and an attribute map.
	 * @param vertices array of points in 3D space that defines the mesh
	 * @param indices array of indices defines connections between the vertices
	 * @param attributes attributes defines how the vertex data should be interpreted
	 */
	public Mesh(Vertex[] vertices, short[] indices,
					AttributeMap attributes) {
		this.vertices = vertices;
		this.indices = indices;
		this.vertexPtr = vertices.length;
		this.indexPtr = indices.length;
		this.attributes = attributes;
	}
	
	/**
	 * Put a vertex into the array of vertices
	 * @param v the vertex to put
	 */
	public void putVertex(Vertex v) {
		vertices[vertexPtr++] = v;
	}
	
	/**
	 * Put some vertices into the array of vertices in the mesh data.
	 * @param verts some vertices to put
	 */
	public void putVertices(Vertex... verts) {
		for (Vertex v: verts) {
			vertices[vertexPtr++] = v;
		}
	}
	
	/**
	 * Put an array of indices into the array of indices in the mesh data.
	 * @param idxs some indices to put
	 */
	public void putIndices(short... idxs) {
		for (short s: idxs) {
			indices[indexPtr++] = s;
		}
	}
	
	/**
	 * Get the array of vertices.
	 * @return array holding all the vertices in this mesh
	 */
	public Vertex[] vertices() {
		return vertices;
	}

	/**
	 * Retrieve the vertex data in a float array format.
	 * @return float array holding all the data from the vertices in this mesh
	 */
	public float[] vertexData() {
		int count = attributes.count();
		float[] data = new float[count * vertices.length];
		for (int i = 0; i < vertices.length; i++) {
			float[] arr = vertices[i].data();
			for (int j = 0; j < arr.length; j++) {
				if (j < count)
					data[i * count + j] =  arr[j];
			}
		}
		return data;
	}
	
	/**
	 * Get the array of indices.
	 * @return array holding all the indices in this mesh
	 */
	public short[] indexData() {
		return indices;
	}
	
	/**
	 * Get the number of vertices in this mesh.
	 * @return the number of vertices
	 */
	public int numVertices() {
		return vertices.length;
	}
	
	/**
	 * Get the number of indices in this mesh
	 * @return the number of indices
	 */
	public int numIndices() {
		return indices.length;
	}
}
