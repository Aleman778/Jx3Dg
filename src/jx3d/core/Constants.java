package jx3d.core;

/**
 * Shared constants in the jx3DGraphics API.
 * @since 1.0
 * @author Aleman778
 */
public class Constants extends Module {

	//Mathematical constants
	/**
	 * Mathematical constant PI has value 3.1415927.
	 * It is the ratio of the circumference of a circle to its diameter.
	 * @see Constants#HALF_PI
	 * @see Constants#THIRD_PI
	 * @see Constants#QUARTER_PI
	 * @see Constants#TWO_PI
	 * @see Constants#TAU
	 */
	public static final float PI = (float) Math.PI;
	
	/**
	 * Mathematical constant HALF_PI has value 1.5707964.
	 * It is half of the ratio of the circumference of a circle to its diameter.
	 * @see Constants#PI
	 * @see Constants#THIRD_PI
	 * @see Constants#QUARTER_PI
	 * @see Constants#TWO_PI
	 * @see Constants#TAU
	 */
	public static final float HALF_PI = (float) (Math.PI / 2.0);

	/**
	 * Mathematical constant THIRD_PI has value 1.0471976.
	 * It is one third of the ratio of the circumference of a circle to its diameter.
	 * @see Constants#PI
	 * @see Constants#HALF_PI
	 * @see Constants#QUARTER_PI
	 * @see Constants#TWO_PI
	 * @see Constants#TAU
	 */
	public static final float THIRD_PI = (float) (Math.PI / 3.0);

	/**
	 * Mathematical constant QUARTER_PI has value 0.7853982.
	 * It is one quarter of the ratio of the circumference of a circle to its diameter.
	 * @see Constants#PI
	 * @see Constants#HALF_PI
	 * @see Constants#THIRD_PI
	 * @see Constants#TWO_PI
	 * @see Constants#TAU
	 */
	public static final float QUARTER_PI = (float) (Math.PI / 4.0);
	
	/**
	 * Mathematical constant TWO_PI has value 6.2831855.
	 * It is twice the ratio of the circumference of a circle to its diameter.
	 * @see Constants#PI
	 * @see Constants#HALF_PI
	 * @see Constants#THIRD_PI
	 * @see Constants#QUARTER_PI
	 * @see Constants#TAU
	 */
	public static final float TWO_PI = (float) (2.0 * Math.PI);
	
	/**
	 * Mathematical constant TAU is an alias of TWO_PI and has value 6.2831855.
	 * It is twice the ratio of the circumference of a circle to its diameter.
	 * @see Constants#PI
	 * @see Constants#HALF_PI
	 * @see Constants#THIRD_PI
	 * @see Constants#QUARTER_PI
	 * @see Constants#TWO_PI
	 */
	public static final float TAU = (float) (2.0 * Math.PI);
	
	/**
	 * Epsilon has value 0.0001.
	 */
	public static final float EPSILON = 0.0001f;
	
	//Primitives
	/**
	 * 
	 */
	public static final int POINTS 		   = 0;
	public static final int LINES 		   = 1;
	public static final int LINE_STRIP 	   = 2;
	public static final int LINE_LOOP 	   = 3;
	public static final int TRIANGLES 	   = 4;
	public static final int TRIANGLE_STRIP = 5;
	public static final int TRIANGLE_FAN   = 6;
	public static final int QUAD_STRIP 	   = 7;
	public static final int QUADS 		   = 8;
	public static final int POLYGON 	   = 9;
	
	//Shader Type Constants
	/**
	 * <i>Vertex shader</i> is a type of shader that is performed for each vertex in a mesh.
	 * This shader is used for transforming and projecting a mesh to screen-space coordinates.
	 * The output from this shader is the new transformed vertex.
	 * @see Constants#FRAGMENT_SHADER
	 * @see Constants#GEOMETRY_SHADER
	 * @see Constants#TESS_CONTROL_SHADER
	 * @see Constants#TESS_EVALUATION_SHADER
	 */
	public static final int VERTEX_SHADER = 0;
	
	/**
	 * <i>Fragment shader</i> is a type of shader that is performed for each fragment (i.e. pixel) that is being rendered.
	 * This shader is used to apply graphical effects such as lighting, shadowing etc.
	 * The output from this shader is the color of the fragment that is being rendered.
	 * @see Constants#VERTEX_SHADER
	 * @see Constants#GEOMETRY_SHADER
	 * @see Constants#TESS_CONTROL_SHADER
	 * @see Constants#TESS_EVALUATION_SHADER
	 */
	public static final int FRAGMENT_SHADER = 1;
	
	/**
	 * <i>Geometry shader</i> is a type of shader that is performed on a primitive that is being rendered.
	 * This shader is used to modify the primitive before rendering.
	 * The output from this shader is zero or primitives
	 * @see Constants#VERTEX_SHADER
	 * @see Constants#FRAGMENT_SHADER
	 * @see Constants#TESS_CONTROL_SHADER
	 * @see Constants#TESS_EVALUATION_SHADER
	 */
	public static final int GEOMETRY_SHADER = 2;
	
	/**
	 * <i>Tessellation control shader</i> is a type of shader that is used to control how much
	 * tessellation a specific patch gets.
	 * @see Constants#VERTEX_SHADER
	 * @see Constants#FRAGMENT_SHADER
	 * @see Constants#GEOMETRY_SHADER
	 * @see Constants#TESS_EVALUATION_SHADER
	 */
	public static final int TESS_CONTROL_SHADER = 3;
	
	/**
	 * <i>Tessellation evaluation shader</i> is a type of shader that is used to evaluate the 
	 * already subdivided (tessellated) geometry and form new vertices and further modify them.
	 * @see Constants#VERTEX_SHADER
	 * @see Constants#FRAGMENT_SHADER
	 * @see Constants#GEOMETRY_SHADER
	 * @see Constants#TESS_CONTROL_SHADER
	 */
	public static final int TESS_EVALUATION_SHADER = 4;
	
	//Buffer Usage Constants
	/**
	 * The user will write data to the buffer once, but the user will not read it.
	 * @see Constants#DYNAMIC_DRAW
	 * @see Constants#STREAM_DRAW
	 */
	public static final int STATIC_DRAW = 0;
	
	/**
	 * The user will not be writing data to the buffer, but the user will read it back.
	 * @see Constants#DYNAMIC_READ
	 * @see Constants#STREAM_READ
	 */
	public static final int STATIC_READ = 1;
	
	/**
	 * The user will not be writing data to the buffer nor reading the data.
	 * @see Constants#DYNAMIC_COPY
	 * @see Constants#STREAM_COPY
	 */
	public static final int STATIC_COPY = 2;
	
	/**
	 * The user will write data to the buffer occasionally, but the user will not read it.
	 * @see Constants#STATIC_DRAW
	 * @see Constants#STREAM_DRAW
	 */
	public static final int DYNAMIC_DRAW = 3;

	/**
	 * The user will not be writing data to the buffer, but the user will read it back.
	 * @see Constants#STATIC_READ
	 * @see Constants#STREAM_READ
	 */
	public static final int DYNAMIC_READ = 4;

	/**
	 * The user will not be writing data to the buffer nor reading the data.
	 * @see Constants#STATIC_COPY
	 * @see Constants#STREAM_COPY
	 */
	public static final int DYNAMIC_COPY = 5;

	/**
	 * The user will write data to the buffer after every use (or almost every use), but the user will not read it.
	 * @see Constants#STATIC_DRAW
	 * @see Constants#DYNAMIC_DRAW
	 */
	public static final int STREAM_DRAW = 6;

	/**
	 * The user will not be writing data to the buffer, but the user will read it back.
	 * @see Constants#STATIC_DRAW
	 * @see Constants#DYNAMIC_DRAW
	 */
	public static final int STREAM_READ = 7;

	/**
	 * The user will not be writing data to the buffer nor reading the data.
	 * @see Constants#STATIC_COPY
	 * @see Constants#STREAM_COPY
	 */
	public static final int STREAM_COPY = 8;
	
	//Depth testing functions
	/** 
	 * The depth test <i>always</i> passes.
	 */
	public static final int ALWAYS = 0;
	
	/**
	 * The depth test <i>never</i> passes.
	 */
	public static final int NEVER = 1;

	/**
	 * The depth test passes when the fragment's depth value is <i>less</i> than the stored depth value.
	 */
	public static final int LESS = 2;
	
	/**
	 * The depth test passes when the fragment's depth value is <i>equal</i> to the stored depth value.
	 */
	public static final int EQUAL = 3;
	
	/**
	 * The depth test passes when the fragment's depth value is <i>less than or equal</i> to the stored depth value.
	 */
	public static final int LEQUAL = 4;
	
	/**
	 * The depth test passes when the fragment's depth value is <b>grater</b> than the stored depth value.
	 */
	public static final int GREATER = 5;
	
	/**
	 * The depth test passes when the fragment's depth value is <b>not equal</b> than the stored depth value.
	 */
	public static final int NOTEQUAL = 6;
	
	/**
	 * The depth test passes when the fragment's depth value is <b>grater than or equal</b> to the stored depth value.
	 */
	public static final int GEQUAL = 7;
	
	//Rendering engines
	
	// -> OpenGL
	/**
	 * The OpenGL rendering engine.
	 */
	public static final int OPENGL = 0;
	
	/**
	 * OpenGL profile for version 2.x.
	 */
	public static final int GL20_PROFILE = 1;
	
	/**
	 * OpenGL profile for version 3.x with the core profile.
	 */
	public static final int GL30_PROFILE = 2;
	
	/**
	 * OpenGL profile for version 3.x with the compatibility profile.
	 * The OpenGL compatibility profile includes deprecated functionality
	 * of previous OpenGL versions.
	 */
	public static final int GL30_COMPAT_PROFILE = 3;

	/**
	 * OpenGL profile for version 3.x with the core profile.
	 */
	public static final int GL40_PROFILE = 4;

	/**
	 * OpenGL profile for version 4.x with the compatibility profile.
	 * The OpenGL compatibility profile includes deprecated functionality
	 * of previous OpenGL versions.
	 */
	public static final int GL40_COMPAT_PROFILE = 4;
	
	
	//Stencil testing functions
	/** 
	 * Keeps the currently stored value.
	 */
	public static final int KEEP = 0;
	
	/** 
	 * The stencil value is set to zero.
	 */
	public static final int ZERO = 1;
	
	/** 
	 * The stencil value is replaced by a provided reference value passed in the function {@link Graphics
	 * #setStencilFunc(StencilFunc, int, int)}.
	 */
	public static final int REPLACE = 2;
	public static final int INCR = 3;
	public static final int INCR_WRAP = 4;
	public static final int DECR = 5;
	public static final int DECR_WRAP = 6;
	public static final int INVERT  = 7;
	
	//Data types
	/**
	 * 4 byte <i>integer</i> type.
	 */
	public static final int INT = 0;
	
	/**
	 * 4 byte <i>unsigned integer<i> type.
	 */
	public static final int UNSIGNED_INT = 1;
	
	/**
	 * 4 byte <i>floating point</i> type.
	 */
	public static final int FLOAT = 2;
	
	/**
	 * 8 byte <i>double precision floating point</i> type.
	 */
	public static final int DOUBLE = 3;
	
	/**
	 * 8 byte <i>long</i> type.
	 */
	public static final int LONG = 4;
	
	/**
	 * 2 byte <i>short</i> type.
	 */
	public static final int SHORT = 5;
	
	/**
	 * 2 byte unsigned <i>short</i> type.
	 */
	public static final int UNSIGNED_SHORT = 6;
	
	/**
	 * 2 byte <i>character</i> type.
	 */
	public static final int CHAR = 7;
	
	/**
	 * Boolean type.
	 */
	public static final int BOOLEAN = 8;
	
	/**
	 * Byte <i>type</i>.
	 */
	public static final int BYTE = 9;
	
	/**
	 * Vector type containing 2 <i>floating point numbers</i>.
	 */
	public static final int VEC2 = 10;
	
	/**
	 * Vector type containing 3 <i>floating point numbers</i>.
	 */
	public static final int VEC3 = 11;
	
	/**
	 * Vector type containing 4 <i>floating point numbers</i>.
	 */
	public static final int VEC4 = 12;
	
	/**
	 * Matrix type containing 2x2 <i>floating point numbers</i>.
	 */
	public static final int MAT2 = 13;
	
	/**
	 * Matrix type containing 3x3 <i>floating point numbers</i>.
	 */
	public static final int MAT3 = 14;
	
	/**
	 * Matrix type containing 4x4 <i>floating point numbers</i>.
	 */
	public static final int MAT4 = 15;
	
	/**
	 * Quaternion type containing 4 <i>floating point numbers</i>.
	 */
	public static final int QUAT = 16;
}
