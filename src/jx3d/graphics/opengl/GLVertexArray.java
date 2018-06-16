package jx3d.graphics.opengl;

import jx3d.graphics.Attribute;
import jx3d.graphics.AttributeMap;
import jx3d.graphics.VertexArray;
import jx3d.graphics.VertexBuffer;

import java.util.ArrayList;

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
	private int index;
	
	/**
	 * Constructor. Create an empty vertex array.
	 */
	public GLVertexArray(GL30 graphics) {
		gl = graphics;
		object = gl.genVertexArray();
		buffers = new ArrayList<>();
		index = 0;
	}
	
	@Override
	public void bind() {
		check();
		
		gl.bindVertexArray(object);
	}
	
	@Override
	public void unbind() {
		check();
		
		gl.bindVertexArray(0);
	}
	
	@Override
	public void put(VertexBuffer buf, AttributeMap attrib) {
		check();
		
		if (attrib.empty())
			throw new IllegalArgumentException("Provided buffer does not have any attributes attached.");
		
		//Bind Vertex Array
		bind();
		
		//Bind Vertex Buffer
		buf.bind();
		
		//Apply layouts
		for (int i = 0; i < attrib.size(); i++) {
			Attribute e = attrib.getAt(i);
			gl.enableVertexAttribArray(index);
			gl.vertexAttribPointer(index, e.count, GLGraphics.glGetType(e.type),
					e.normalized, attrib.stride(), e.offset * e.size);
			index += 1;
		}
		
		buffers.add(buf);
	}
	
	@Override
	public void clear() {
		check();
		
		bind();
		
		for (int i = 0; i < index; i++) {
			gl.disableVertexAttribArray(i);
		}
		index = 0;
	}
	
	@Override
	public void dispose() {
		check();
		
		gl.deleteVertexArray(object);
		object = -1;
	}
	
	@Override
	public int count() {
		int count = 0;
		for (VertexBuffer buf : buffers)
			count += buf.count();
		return count;
	}

    private void check() {
    	if (object == -1)
    		throw new NullPointerException();
    }
}
