package jx3d.graphics;

import jx3d.core.Window;

import static jx3d.core.Module.*;

/**
 * Abstract graphics context class provides methods for
 * drawing on a display. An implemented graphics class
 * class is generally built on top of a graphics api,
 * e.g. OpenGL. 
 * @since 1.0
 * @author Aleman778
 */
public abstract class Graphics {

	/**
	 * The window owner.
	 */
	protected final Window window;

	/**
	 * The currently used material.
	 */
	protected Material material;
	
	/**
	 * Enable the red color channel.
	 */
	protected boolean redMask 	= true;
	
	/**
	 * Enable the green color channel.
	 */
	protected boolean greenMask = true;
	
	/**
	 * Enable the blue color channel.
	 */
	protected boolean blueMask 	= true;
	
	/**
	 * Enable the alpha color channel.
	 */
	protected boolean alphaMask = true;

	/**
	 * Enable depth testing.
	 */
	protected boolean depthTest = false;
	
	/**
	 * Enable writing to the depth buffer. 
	 */
	protected boolean depthMask = true;
	
	/**
	 * Depth buffer comparison method.
	 */
	protected int depthFunc = LESS;
	
	/**
	 * Enable stencil testing.
	 */
	protected boolean stencilTest = false;
	
	/**
	 * Stencil mask
	 */
	protected int stencilMask = 0xFFFFFFFF;
	
	/**
	 * Stencil function
	 */
	protected int stencilFunc = KEEP;
	
	/**
	 * Constructor.
	 * Creates a new graphics object.
	 * @param window the window owner
	 */
	public Graphics(Window window) {
		this.window = window;
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
	public abstract VertexBuffer createVBO(int usage);
	
	public abstract VertexBuffer createVBO(int usage, int capacity);
	
	public abstract IndexBuffer createIBO(int usage);
	
	public abstract IndexBuffer createIBO(int usage, int capacity);
	
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
