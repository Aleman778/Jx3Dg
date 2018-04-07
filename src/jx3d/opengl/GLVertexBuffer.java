package jx3d.opengl;

import java.nio.FloatBuffer;

import jx3d.graphics.VertexBuffer;

import static org.lwjgl.opengl.GL15.*;

/**
 * @since 1.0
 * @author Aleman778
 */
public class GLVertexBuffer extends VertexBuffer {

	private int object;
	private int usage;
	
	/**
	 * @param capacity
	 */
	public GLVertexBuffer(int capacity, int usage) {
		super(capacity);
	
	}

	/* (non-Javadoc)
	 * @see jx3d.util.Disposable#dispose()
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see jx3d.graphics.VertexBuffer#put(short[])
	 */
	@Override
	public void put(short[] data) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see jx3d.graphics.VertexBuffer#put(java.nio.FloatBuffer)
	 */
	@Override
	public void put(FloatBuffer buffer) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see jx3d.graphics.VertexBuffer#map()
	 */
	@Override
	public FloatBuffer map() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see jx3d.graphics.VertexBuffer#unmap()
	 */
	@Override
	public void unmap() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see jx3d.graphics.Buffer#bind()
	 */
	@Override
	public void bind() {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see jx3d.graphics.Buffer#unbind()
	 */
	@Override
	public void unbind() {
		// TODO Auto-generated method stub
		
	}

}
