package jx3d.graphics;

import static jx3d.core.Module.*;

/**
 * Shapes is the combination of a mesh with a material.
 * There is also the 
 * @since 1.0
 * @author Aleman778
 */
public class Shape {

	/**
	 * Graphics API reference.
	 */
	private Graphics g;
	
	/**
	 * Map of vertex attributes that defines the 
	 */
	private VertexAttribute attributes;

	/**
	 * Material defines how the mesh should be rendered on the screen.
	 */
	private Material material; 

	/**
	 * Array of vertices that represents this mesh.
	 * The float array contains 
	 */
	private float[] vertices;
	
	/**
	 * Array of indices that builds this mesh.
	 */
	private short[] indices;

	/**
	 * Pointer that points to the next element vertices array.
	 */
	private int vertexPtr;
	
	/**
	 * Pointer that points to the next element in the indices array.
	 */
	public int indexPtr;

	/**
	 * Primitive kind.
	 */
	private int kind;
	
	/**
	 * Flag, true if the shape is open for editing.
	 */
	private boolean open;
	
	public Shape(Graphics graphics) {
		this.g = graphics;
		this.material = graphics.material;
		this.attributes = new VertexAttribute();
	}
	
	/**
	 * Begin the editing session.
	 * Using {@link Shape#vertex(float...)} methods
	 */
	public void begin() {
		
	}
	
	public void begin(int kind) {
		this.kind = kind;
	}
	
	/**
	 * End the editing session.
	 * This will prepare the shape for rendering.
	 */
	public void end() {
		
	}
	
	/**
	 * 
	 * @param data
	 */
	public void vertex(float... data) {
		vertexImpl(data);
	}
	
	public void vertex(float px, float py,
					   float nx, float ny,
					   float tx, float ty) {
		vertexImpl(new float[] {px, py, nx, ny, tx, ty});
	}
	
	public void vertex(float px, float py, float pz,
					   float nx, float ny, float nz,
					   float tx, float ty) {
		vertexImpl(new float[] {px, py, pz, nx, ny, nz, tx, ty});
	}
	

	/**
	 * Put a vertex into the array of vertices
	 * @param v the vertex to put
	 */
	public void vertex(Vertex v) {
		 vertexImpl(v.data());
	}
	
	public void vertexImpl(float[] data) {
		//vertices[vertexPtr++] = data;
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
