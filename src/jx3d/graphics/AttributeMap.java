package jx3d.graphics;

import java.util.ArrayList;
import java.util.HashMap;

import jx3d.core.Constants;

/**
 * Represents a hash map containing attributes indexed by a name (of type String).
 * An attribute map can be attached to a vertex array by calling {@link VertexArray#put(VertexBuffer, AttributeMap)}.
 * @since 1.0
 * @author Aleman778
 * @see VertexArray#put(VertexBuffer, AttributeMap)
 */
public class AttributeMap extends HashMap<String, Attribute> {

	private static final long serialVersionUID = 1L;

	private ArrayList<String> indices = new ArrayList<>();
	
	/**
	 * Position attribute defines the location of each vertex in a mesh.
	 */
	public Attribute position;
	/**
	 * Texture coordinate attribute defines the location in a texture for each vertex. 
	 */
	public Attribute texcoord;
	
	/**
	 * Color attribute defines a color of each vertex generally represented by a four dimensional vector containing the components <code>(red, green, blue, alpha)</code>.
	 */
	public Attribute color;
	
	/**
	 * Normal attribute defines the normal vector of each vertex.
	 */
	public Attribute normal;
	
	private int count = 0;
	private int stride = 0;
	
	/**
	 * Create an attribute of the provided properties.
	 * @param name the name of the attribute (this is used as the index)
	 * @param type the type of the data e.g. INT, FLOAT, DOUBLE, etc.
	 * @param size
	 * @param count
	 * @param offset
	 * @param normalized
	 * @return
	 */
	public Attribute put(String name, int type, int size, int count, int offset, boolean normalized) {
		return put(name, new Attribute(type, size, count, offset, normalized));
	}
	
	@Override
	public Attribute put(String name, Attribute value) {
		this.stride += value.count * value.size;
		this.count  += value.count;

		indices.add(name);
		return super.put(name, value);
	}
	
	public void putPosition(int count) {
		position = new Attribute(Constants.FLOAT, 4, count, this.count, false);
		put("position", position);
	}
	
	public void putTexcoord(int count) {
		texcoord = new Attribute(Constants.FLOAT, 4, count, this.count, false);
		put("texcoord", texcoord);
	}
	
	public void putColor(int count) {
		color = new Attribute(Constants.FLOAT, 4, count, this.count, false);
		put("color", color);
	}
	
	public void putNormal(int count) {
		normal = new Attribute(Constants.FLOAT, 4, count, this.count, false);
		put("normal", normal);
	}
	
	public Attribute getAt(int index) {
		return get(indices.get(index));
	}
	
	public int stride() {
		return stride;
	}
	
	public int count() {
		return count;
	}
	
	public boolean empty() {
		return count == 0;
	}
}
