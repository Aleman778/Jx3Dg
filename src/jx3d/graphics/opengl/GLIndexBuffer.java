package jx3d.graphics.opengl;

import static jx3d.core.Constants.*;

import jx3d.graphics.IndexBuffer;
import jx3d.util.BufferUtils;

import java.nio.ShortBuffer;

/**
 * Represents an OpenGL implementation of an index buffer.<br>
 * The OpenGL buffer target for an index buffer is <i>GL_ELEMENT_ARRAY_BUFFER</i>. 
 * @since 1.0
 * @author Aleman778
 * @see IndexBuffer
 */
public class GLIndexBuffer extends IndexBuffer {

	private final GL20 gl;
	
	private int object;
	private int usage;
	private ShortBuffer mapBuffer;
	
	/**
	 * Creates an empty vertex buffer with a desired maximum capacity.
	 * @param graphics the graphics processor being used in this thread
	 * @param capacity the maximum number of elements the buffer can hold
	 * @param dynamic elements in the buffer can be modified if the dynamic flag is true
	 */
	public GLIndexBuffer(GL20 graphics, int capacity, int usage) {
		super(capacity);
		
		this.gl = graphics;
		this.usage = usage;
		this.object = gl.genBuffer();
		this.position = 0;
		this.count = 0;
		
		gl.bindBuffer(GL20.ELEMENT_ARRAY_BUFFER, object);
		gl.bufferData(GL20.ELEMENT_ARRAY_BUFFER, capacity, null, usage);
	}

	/**
	 * Creates a buffer containing copies of the buffer data in the provided short buffer.
	 * @param graphics the graphics processor being used in this thread
	 * @param buffer the buffer data to copy from
	 * @param dynamic elements in the buffer can be modified if the dynamic flag is true
	 */
	public GLIndexBuffer(GL20 graphics, ShortBuffer buffer, int usage) {
		super(buffer.remaining());
		
		this.gl = graphics;
		this.usage = usage;
		this.object = gl.genBuffer();
		this.position = buffer.remaining();
		this.count = buffer.remaining();
		
		gl.bindBuffer(GL20.ELEMENT_ARRAY_BUFFER, object);
		gl.bufferData(GL20.ELEMENT_ARRAY_BUFFER, buffer.remaining(), buffer, usage);
	}

	/**
	 * Creates a buffer containing the provided data.
	 * @param graphics the graphics processor being used in this thread
	 * @param data the array of data to store in the buffer
	 * @param dynamic elements in the buffer can be modified if the dynamic flag is true
	 */
	public GLIndexBuffer(GL20 graphics, short[] data, int usage) {
		this(graphics, BufferUtils.createShortBuffer(data), usage);
	}
	
	@Override
	public void bind() {
		check();
		gl.bindBuffer(GL20.ELEMENT_ARRAY_BUFFER, object);
	}

	@Override
	public void unbind() {
		check();
		gl.bindBuffer(GL20.ELEMENT_ARRAY_BUFFER, 0);
	}
	
	@Override
	public void put(short[] data) {
		bind();
		
		ShortBuffer buffer = BufferUtils.createShortBuffer(data);
		gl.bufferSubData(GL20.ELEMENT_ARRAY_BUFFER, position * Float.BYTES, data.length, buffer);
		position += data.length;
		if (position > count)
			count = position;
	}

	@Override
	public void put(ShortBuffer buffer) {
		bind();
		
		int length = buffer.remaining();
		gl.bufferSubData(GL20.ELEMENT_ARRAY_BUFFER, position * Float.BYTES, length, buffer);
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
		return (mapBuffer = gl.mapBuffer(GL20.ELEMENT_ARRAY_BUFFER, GL30.READ_WRITE).asShortBuffer());
	}

	@Override
	public void unmap() {
		check();
		bind();

		position = mapBuffer.position();
		if (position > count)
			count = position;
		mapBuffer = null;
		gl.unmapBuffer(GL20.ELEMENT_ARRAY_BUFFER);
	}

	@Override
	public void resize(int size) {
		capacity = size;
		
		bind();
		gl.bufferData(GL20.ELEMENT_ARRAY_BUFFER, size, null, usage);
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
}
