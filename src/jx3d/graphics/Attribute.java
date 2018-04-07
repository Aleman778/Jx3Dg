package jx3d.graphics;

/**
 * Attributes specifies the structure of the data in a vertex buffer.
 * @since 1.0
 * @author Aleman778
 */
public class Attribute {
	
	/**
	 * The type of data stored in the buffer. 
	 */
	public final int type;
	
	/**
	 * The size of the type in bytes.
	 */
	public final int size;

	/**
	 * The number of items stored in the buffer.
	 */
	public final int count;
	
	/**
	 * The item offset to where the data starts.
	 */
	public final int offset;
	
	/**
	 * The data is normalized in the buffer.
	 */
	public final boolean normalized;
	
	/**
	 * Creates a vertex attribute with the provided specifications.
	 * @param type the type of the data
	 * @param size the size of the type
	 * @param count the number of items
	 * @param offset the item offset
	 * @param normalized the data is normalized
	 */
	public Attribute(int type, int size, int count, int offset, boolean normalized) {
		this.type = type;
		this.size = size;
		this.count = count;
		this.offset = offset;
		this.normalized = normalized;
	}
}
