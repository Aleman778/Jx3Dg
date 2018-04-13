package jx3d.graphics.opengl;

import static jx3d.core.Constants.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

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

	private ArrayList<VertexBuffer> buffers;
	
	private int object;
	private int index;
	
	/**
	 * Constructor. Create an empty vertex array.
	 */
	public GLVertexArray() {
		this.object = glGenVertexArrays();
		this.buffers = new ArrayList<>();
		this.index = 0;
	}
	
	@Override
	public void bind() {
		check();
		
		glBindVertexArray(object);
	}
	
	@Override
	public void unbind() {
		check();
		
		glBindVertexArray(0);
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
			glEnableVertexAttribArray(index);
			glVertexAttribPointer(index, e.count, glGetType(e.type), e.normalized, attrib.stride(), e.offset * e.size);
			index += 1;
		}
		
		buffers.add(buf);
	}
	
	@Override
	public void clear() {
		check();
		
		bind();
		
		for (int i = 0; i < index; i++) {
			glDisableVertexAttribArray(i);
		}
		index = 0;
	}
	
	@Override
	public void dispose() {
		check();
		
		glDeleteVertexArrays(object);
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
    
	private static final int glGetType(int type) {
		switch (type) {
		case INT: 		     return GL_INT;
		case UNSIGNED_INT:   return GL_UNSIGNED_INT;
		case FLOAT: 		 return GL_FLOAT;
		case DOUBLE: 		 return GL_DOUBLE;
		case SHORT: 		 return GL_SHORT;
		case UNSIGNED_SHORT: return GL_UNSIGNED_SHORT;
		}
		
		return 0;
	}
}
