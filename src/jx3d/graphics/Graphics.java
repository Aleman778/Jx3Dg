package jx3d.graphics;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import jx3d.core.Display;

import static jx3d.core.Constants.*;

/**
 * Abstract graphics context class provides methods for
 * drawing on a display. An implemented graphics class
 * class is generally built on top of a graphics api,
 * e.g. OpenGL. 
 * @since 1.0
 * @author Aleman778
 */
public abstract class Graphics {

	//Parent display
	protected final Display display;

	//Color buffer
	protected boolean redMask 	= true;
	protected boolean greenMask = true;
	protected boolean blueMask 	= true;
	protected boolean alphaMask = true;
	
	//Depth buffer
	protected boolean depthTest = false;
	protected boolean depthMask = true;
	protected int depthFunc = LESS;
	
	//Stencil buffer
	protected boolean stencilTest = false;
	protected int stencilMask = 0xFFFFFFFF;
	protected int stencilFunc = KEEP;
	
	/**
	 * Constructor.
	 * Creates a new graphics object.
	 * @param display the parent display
	 */
	public Graphics(Display display) {
		this.display = display;
	}
	
	//Rendering functions
	public abstract void init();
	
	public abstract void prepare();
	
	public abstract void present();

	public abstract void clear(int flag);
	
	public abstract void viewport(int x, int y, int w, int h);

	public abstract void render(int mode, VertexArray vao);
	
	public abstract void render(int mode, VertexArray vao, IndexBuffer ibo);
	
	public abstract void background(float red, float green, float blue, float alpha);
	
	//Shader functions
	public abstract Shader loadShader(String fragment);
	
	public abstract Shader loadShader(String fragment, String vertex);
	
	public abstract Shader loadShader(int shader);

	//Native buffer functions
	public abstract VertexBuffer createVBO(int capacity, int usage);
	
	public abstract VertexBuffer createVBO(float[] data, int usage);
	
	public abstract VertexBuffer createVBO(FloatBuffer data, int usage);

	public abstract IndexBuffer createIBO(int capacity, int usage);
	
	public abstract IndexBuffer createIBO(short[] data, int usage);
	
	public abstract IndexBuffer createIBO(ShortBuffer data, int usage);
	
	public abstract VertexArray createVAO();
	
	/**
	 * Enable or disable the writing to each color component of the color buffer.
	 * @param red enable the red buffer (or channel)
	 * @param green enable the green buffer (or channel)
	 * @param blue enable the blue buffer (or channel)
	 * @param alpha enable the alpha buffer (or channel)
	 */
	public void setColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
		this.redMask   = red;
		this.greenMask = green;
		this.blueMask  = blue;
		this.alphaMask = alpha;
	}
	
	/**
	 * Set the depth testing flag.
	 * @param enable enable depth testings
	 */
	public void setDepthTest(boolean enable) {
		this.depthTest = enable;
	}
	
	/**
	 * Set the depth buffer function.
	 * @param func the depth function
	 */
	public void setDepthFunc(int func) {
		this.depthFunc = func;
	}
	
	/**
	 * Enable or disable the writing to the depth buffer.
	 * @param mask enable depth buffer writing
	 */
	public void setDepthMask(boolean mask) {
		this.depthMask = mask;
	}
	
	/**
	 * Set the stencil testing flag,
	 * @param enable enable stencil testing
	 */
	public void setStencilTest(boolean enable) {
		this.stencilTest = enable;
	}
	
	/**
	 * Set the stencil buffer function with the provided reference and mask.
	 * @param func the stencil function
	 * @param ref the reference value used by
	 * @param mask the stencil bits to be affected
	 */
	public void setStencilFunc(int func, int ref, int mask) {
		this.stencilFunc = func;
}
}
