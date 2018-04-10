package jx3d.opengl;

import static jx3d.core.Constants.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.ShortBuffer;

import jx3d.graphics.IndexBuffer;

/**
 * Represents an OpenGL implementation of an index buffer.<br>
 * The OpenGL buffer target for an index buffer is <i>GL_ELEMENT_ARRAY_BUFFER</i>. 
 * @since 1.0
 * @author Aleman778
 * @see IndexBuffer
 */
public class GLIndexBuffer extends IndexBuffer {

	private int object;
	private int usage;
	private ShortBuffer mapBuffer;
	
	/**
	 * Creates an empty vertex buffer with a desired maximum capacity.
	 * @param graphics the graphics processor being used in this thread
	 * @param capacity the maximum number of elements the buffer can hold
	 * @param dynamic elements in the buffer can be modified if the dynamic flag is true
	 */
	public GLIndexBuffer(int capacity, int usage) {
		super(capacity);

		this.usage = glGetUsage(usage);
		this.object = glGenBuffers();
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, object);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, capacity * Float.BYTES, this.usage);
	}

	/**
	 * Creates a buffer containing the provided data.
	 * @param graphics the graphics processor being used in this thread
	 * @param data the array of data to store in the buffer
	 * @param dynamic elements in the buffer can be modified if the dynamic flag is true
	 */
	public GLIndexBuffer(short[] data, int usage) {
		super(data.length);

		this.usage = glGetUsage(usage);
		this.object = glGenBuffers();
		this.position = data.length;
		this.count = data.length;

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, object);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, data, this.usage);
	}

	/**
	 * Creates a buffer containing copies of the buffer data in the provided short buffer.
	 * @param graphics the graphics processor being used in this thread
	 * @param buffer the buffer data to copy from
	 * @param dynamic elements in the buffer can be modified if the dynamic flag is true
	 */
	public GLIndexBuffer(ShortBuffer buffer, int usage) {
		super(buffer.remaining());

		this.usage = glGetUsage(usage);
		this.object = glGenBuffers();
		this.position = buffer.remaining();
		this.count = buffer.remaining();
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, object);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, this.usage);
	}
	
	@Override
	public void bind() {
		check();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, object);
	}

	@Override
	public void unbind() {
		check();
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	@Override
	public void put(short[] data) {
		bind();
		
		glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, position * Float.BYTES, data);

		position += data.length;
		if (position > count)
			count = position;
	}

	@Override
	public void put(ShortBuffer buffer) {
		bind();
		
		int length = buffer.remaining();
		
		glBufferSubData(GL_ELEMENT_ARRAY_BUFFER, position * Float.BYTES, buffer);

		position += length;
		if (position > count)
			count = position;
	}
		
	@Override
	public ShortBuffer map() {
		check();
		
		if (mapBuffer != null)
			return mapBuffer;
		
		bind();
		return (mapBuffer = glMapBuffer(GL_ELEMENT_ARRAY_BUFFER, GL_READ_WRITE).asShortBuffer());
	}

	@Override
	public void unmap() {
		check();
		bind();

		position = mapBuffer.position();
		if (position > count)
			count = position;
		mapBuffer = null;
		glUnmapBuffer(GL_ELEMENT_ARRAY_BUFFER);
	}

	@Override
	public void resize(int size) {
		capacity = size;
		
		bind();
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, size, usage);
	}
	
	@Override
	public void dispose() {
		check();
		
		glDeleteBuffers(object);
		object = -1;
	}
	
    private void check() {
    	if (object == -1)
    		throw new NullPointerException();
    }
    
    private static final int glGetUsage(int usage) {
    	switch (usage) {
    	case STATIC_DRAW:  return GL_STATIC_DRAW;
    	case DYNAMIC_DRAW: return GL_DYNAMIC_DRAW;
    	case STREAM_DRAW:  return GL_STREAM_DRAW;
    	case STATIC_READ:  return GL_STATIC_READ;
    	case DYNAMIC_READ: return GL_DYNAMIC_READ;
    	case STREAM_READ:  return GL_STREAM_READ;
    	case STATIC_COPY:  return GL_STATIC_COPY;
    	case DYNAMIC_COPY: return GL_DYNAMIC_COPY;
    	case STREAM_COPY:  return GL_STREAM_COPY;
	    }
	    return 0;
    }
}
