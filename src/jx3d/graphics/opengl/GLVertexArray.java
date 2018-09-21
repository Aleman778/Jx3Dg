package jx3d.graphics.opengl;

import jx3d.graphics.VertexArray;
import jx3d.graphics.VertexAttribute;
import jx3d.graphics.VertexAttribute.Attribute;
import jx3d.graphics.VertexBuffer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents an OpenGL implementation of a vertex array.
 * @since 1.0
 * @author Aleman778
 * @see VertexArray
 */
public class GLVertexArray extends VertexArray {

	private final GL30 gl;
	
	private ArrayList<VertexBuffer> buffers;
	private int object;
	
	/**
	 * Constructor. Create an empty vertex array.
	 */
	public GLVertexArray(GL30 graphics) {
		gl = graphics;
		object = gl.genVertexArray();
		buffers = new ArrayList<>();
	}
	
	@Override
	public void bind() {
		gl.bindVertexArray(object);
	}
	
	@Override
	public void unbind() {
		gl.bindVertexArray(0);
	}
	
	@Override
	public void put(VertexBuffer buf, VertexAttribute attrib) {
		if (attrib.empty())
			throw new IllegalArgumentException("Provided buffer does not have any attributes attached.");
		
		//Bind Vertex Array
		bind();
		
		//Bind Vertex Buffer
		buf.bind();
		
		//Apply layouts
		Iterator<Attribute> it = attrib.iterator();
		while (it.hasNext()) {
			Attribute a = it.next();
			gl.enableVertexAttribArray(a.location);
			gl.vertexAttribPointer(a.location, a.size, GL20.FLOAT,
					a.normalized, a.stride * 4, a.pointer * 4);
		}		
		
		buffers.add(buf);
	}
	
	@Override
	public void dispose() {
		gl.deleteVertexArray(object);
		object = -1;
	}
	
	@Override
	public int count() {
		int count = 0;
		for (VertexBuffer buf : buffers)
			count += buf.capacity();
		return count;
	}
}
