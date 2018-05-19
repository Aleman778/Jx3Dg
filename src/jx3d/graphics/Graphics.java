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
	protected Color clearColor = Color.BLACK;
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
	
	public abstract void viewport(float x, float y, float w, float h);

	public abstract void render(int mode, VertexArray vao);
	
	public abstract void render(int mode, VertexArray vao, IndexBuffer ibo);
	
	//Shader functions
	public abstract Shader loadShader(String fragment);
	
	public abstract Shader loadShader(String fragment, String vertex);
	
	public abstract Shader loadShader(int shader);

	//Native buffer functions
	public abstract VertexBuffer createVBO(int capacity, boolean dynamic);
	
	public abstract VertexBuffer createVBO(float[] data, boolean dynamic);
	
	public abstract VertexBuffer createVBO(FloatBuffer data, boolean dynamic);

	public abstract IndexBuffer createIBO(int capacity, boolean dynamic);
	
	public abstract IndexBuffer createIBO(short[] data, boolean dynamic);
	
	public abstract IndexBuffer createIBO(ShortBuffer data, boolean dynamic);
	
	public abstract VertexArray createVAO();
}
