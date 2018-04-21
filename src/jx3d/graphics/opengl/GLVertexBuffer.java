package jx3d.graphics.opengl;

import static jx3d.core.Constants.*;

import jx3d.graphics.VertexBuffer;
import jx3d.util.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Represents an OpenGL implementation of a vertex buffer.<br>
 * The OpenGL buffer target for a vertex buffer is <i>GL_ARRAY_BUFFER</i>. 
 * @since 1.0
 * @author Aleman778
 * @see VertexBuffer
 */
public class GLVertexBuffer extends VertexBuffer {
	
	private final GL20 gl;

	private int object;
	private int usage;
	private FloatBuffer mapBuffer;
	
	/**
	 * Creates an empty vertex buffer with a desired maximum capacity.
	 * @param graphics the graphics processor being used in this thread
	 * @param capacity the maximum number of elements the buffer can hold
	 * @param dynamic elements in the buffer can be modified if the dynamic flag is true
	 */
	public GLVertexBuffer(int capacity, int usage) {
		super(capacity);
		
		this.gl = null;
		this.usage = glGetUsage(usage);
		this.object = gl.genBuffer();
		this.position = 0;
		this.count = 0;
		
		gl.bindBuffer(GL20.ARRAY_BUFFER, object);
		gl.bufferData(GL20.ARRAY_BUFFER, capacity * Float.BYTES, null, this.usage);
	}
	
	/**
	 * Creates a buffer containing copies of the buffer data in the provided float buffer.
	 * @param graphics the graphics processor being used in this thread
	 * @param buffer the buffer data to copy from
	 * @param dynamic elements in the buffer can be modified if the dynamic flag is true
	 */
	public GLVertexBuffer(FloatBuffer buffer, int usage) {
		super(buffer.remaining());

		this.gl = null;
		this.usage = glGetUsage(usage);
		this.object = gl.genBuffer();
		this.position = buffer.remaining();
		this.count = buffer.remaining();
		
		gl.bindBuffer(GL20.ARRAY_BUFFER, object);
		gl.bufferData(GL20.ARRAY_BUFFER, buffer.remaining(), buffer, this.usage);
	}
	
	/**
	 * Creates a buffer containing the provided data.
	 * @param graphics the graphics processor being used in this thread
	 * @param data the array of data to store in the buffer
	 * @param dynamic elements in the buffer can be modified if the dynamic flag is true
	 */
	public GLVertexBuffer(float[] data, int usage) {
		this(BufferUtils.createFloatBuffer(data), usage);
	}
	
	@Override
	public void bind() {
		check();
		gl.bindBuffer(GL20.ARRAY_BUFFER, object);
	}

	@Override
	public void unbind() {
		check();
		
		gl.bindBuffer(GL20.ARRAY_BUFFER, 0);
	}
	
	@Override
	public void put(float[] data) {
		bind(); 
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data);
		gl.bufferSubData(GL20.ARRAY_BUFFER, position * Float.BYTES, data.length, buffer);

		position += data.length;
		if (position > count)
			count = position;
	}

	@Override
	public void put(FloatBuffer buffer) {
		bind();
		
		int length = buffer.remaining();
		gl.bufferSubData(GL20.ARRAY_BUFFER, position * Float.BYTES, length, buffer);

		position += length;
		if (position > count)
			count = position;
	}
		
	@Override
	public FloatBuffer map() {
		check();
		
		if (mapBuffer != null)
			return mapBuffer;
		
		bind();
		
		return (mapBuffer = gl.mapBuffer(GL20.ARRAY_BUFFER, GL20.READ_WRITE).asFloatBuffer());
	}

	@Override
	public void unmap() {
		check();
		bind();
		
		position = mapBuffer.position();
		if (position > count)
			count = position;
		mapBuffer = null;
		gl.unmapBuffer(GL20.ARRAY_BUFFER);
	}

	@Override
	public void resize(int size) {
		capacity = size;
		
		bind();
		gl.bufferData(GL20.ARRAY_BUFFER, size, null, usage);
	}
	
	@Override
	public void dispose() {
		check();
		
		gl.deleteBuffer(object);
		object = -1;
	}
	
    private void check() {
    	if (object == -1)
    		throw new NullPointerException();
    }
    
    private static final int glGetUsage(int usage) {
    	switch (usage) {
    	case STATIC_DRAW:  return GL20.STATIC_DRAW;
    	case DYNAMIC_DRAW: return GL20.DYNAMIC_DRAW;
    	case STREAM_DRAW:  return GL20.STREAM_DRAW;
    	case STATIC_READ:  return GL20.STATIC_READ;
    	case DYNAMIC_READ: return GL20.DYNAMIC_READ;
    	case STREAM_READ:  return GL20.STREAM_READ;
    	case STATIC_COPY:  return GL20.STATIC_COPY;
    	case DYNAMIC_COPY: return GL20.DYNAMIC_COPY;
    	case STREAM_COPY:  return GL20.STREAM_COPY;
	    }
	    return 0;
    }
}
