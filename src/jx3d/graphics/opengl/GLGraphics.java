package jx3d.graphics.opengl;

import static org.lwjgl.opengl.GL11.*;

import static jx3d.core.Constants.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import jx3d.core.Display;
import jx3d.desktop.LwjglGL20;
import jx3d.desktop.LwjglGL30;
import jx3d.graphics.Graphics;
import jx3d.graphics.IndexBuffer;
import jx3d.graphics.Shader;
import jx3d.graphics.VertexArray;
import jx3d.graphics.VertexBuffer;

/**
 * OpenGL graphics context.
 * Supports all OpenGL versions including OpenGLES, WebGL.
 * @since 1.0
 * @author Aleman778
 */
public class GLGraphics extends Graphics {

	/**
	 * OpenGL state variables.
	 */
	public GLState state;
	
	/**
	 * OpenGL 20 API interface.
	 */
	private GL20 gl20;
	
	/**
	 * OpenGL 30 API interface.<br>
	 * <i>Note:</i> if this variable is null then
	 * this version might be unsupported your hardware.
	 */
	private GL30 gl30;
	
	/**
	 * OpenGL capabilities object.
	 */
	private GLCapabilities capabilities;
	
	/**
	 * Flag determines if the context have been initialized before.
	 */
	private boolean initialized;
	
	/**
	 * Create a new OpenGL graphics context.
	 * @param display
	 */
	public GLGraphics(Display display) {
		super(display);
	}

	@Override
	public void init() {
		if (initialized)
			return;
		
		initialized = false;
		capabilities = GL.createCapabilities();
		gl20 = new LwjglGL20();
		if (capabilities.OpenGL30) {
			gl30 = new LwjglGL30();
		}
		state = new GLState(gl20);
	}

	@Override
	public void prepare() {
		//TODO: implement renderer preparation code here. Maybe call clear(...) function?
	}

	@Override
	public void present() {
		//TODO: implement renderer presentation code here.
	}

	@Override
	public void clear(int flag) {
		gl20.clear(flag);
		gl20.clearColor(clearColor.red, clearColor.green, 
						clearColor.blue, clearColor.alpha);
	}

	@Override
	public void viewport(int x, int y, int w, int h) {
		gl20.viewport(x, y, w, h);
	}

	@Override
	public void render(int mode, VertexArray vao) {
		vao.bind();
		gl20.drawArrays(glGetShapeMode(mode), 0, vao.count());
	}

	@Override
	public void render(int mode, VertexArray vao, IndexBuffer ibo) {
		vao.bind();
		ibo.bind();
		gl20.drawElements(glGetShapeMode(mode), vao.count(), type, indices);
	}

	@Override
	public Shader loadShader(String fragment) {
		Shader shader = new GLSLShader();
		shader.add(FRAGMENT_SHADER, source);
	}

	@Override
	public Shader loadShader(String fragment, String vertex) {
	}

	@Override
	public Shader loadShader(int shader) {
	}

	@Override
	public VertexBuffer createVBO(int capacity, boolean dynamic) {

	}

	@Override
	public VertexBuffer createVBO(float[] data, boolean dynamic) {
	}

	@Override
	public VertexBuffer createVBO(FloatBuffer data, boolean dynamic) {
	}

	@Override
	public IndexBuffer createIBO(int capacity, boolean dynamic) {
	}

	@Override
	public IndexBuffer createIBO(short[] data, boolean dynamic) {
	}

	@Override
	public IndexBuffer createIBO(ShortBuffer data, boolean dynamic) {
	}

	@Override
	public VertexArray createVAO() {
	}

	private static final int glGetShapeMode(int mode) {
		switch (mode) {
		case POINTS: 		 return GL_POINT;
		case LINES: 	 	 return GL_LINES;
		case LINE_STRIP: 	 return GL_LINE_STRIP;
		case LINE_LOOP: 	 return GL_LINE_LOOP;
		case TRIANGLES: 	 return GL_TRIANGLES;
		case TRIANGLE_STRIP: return GL_TRIANGLE_STRIP;
		case TRIANGLE_FAN:   return GL_TRIANGLE_FAN;
		case QUAD_STRIP: 	 return GL_QUAD_STRIP;
		case QUADS: 		 return GL_QUADS;
		case POLYGON: 	     return GL_POLYGON;
		}
		
		return 0;
	}
	
	private static final int glGetDepthFunc(int func) {
		switch (func) {
		case ALWAYS:   return GL_ALWAYS;
		case NEVER:    return GL_NEVER;
		case LESS: 	   return GL_LESS;
		case EQUAL:    return GL_EQUAL;
		case LEQUAL:   return GL_LEQUAL;
		case GREATER:  return GL_GREATER;
		case NOTEQUAL: return GL_NOTEQUAL;
		case GEQUAL:   return GL_GEQUAL;
		}
		
		return 0;
	}
	
	private static final int glGetStencilFunc(int func) {
		switch (func) {
		case KEEP: 		return GL_KEEP;
		case ZERO: 		return GL_ZERO;
		case REPLACE: 	return GL_REPLACE;
		case INCR: 		return GL_INCR;
		//case INCR_WRAP: return GL_INCR_WRAP;
		case DECR: 		return GL_DECR;
		//case DECR_WRAP: return GL_DECR_WRAP;
		case INVERT: 	return GL_INVERT;
		}
		
		return 0;
	}
	
	public static final int glGetType(int type) {
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
	
	public static final int glGetSizeOf(int type) {
		switch (type) {
		case INT: 		   return Integer.BYTES;
		case UNSIGNED_INT:   return Integer.BYTES;
		case FLOAT: 		   return Float.BYTES;
		case DOUBLE: 	 	   return Double.BYTES;
		case SHORT: 		   return Short.BYTES;
		case UNSIGNED_SHORT: return Short.BYTES;
		case LONG: 		   return Long.BYTES;
		}
		
		return 0;
	}
}
