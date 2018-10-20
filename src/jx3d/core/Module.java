package jx3d.core;

import jx3d.graphics.Graphics;
import jx3d.graphics.Shader;
import jx3d.graphics.Texture2D;

/**
 * Shared constants in the jx3DGraphics API.
 * @since 1.0
 * @author Aleman778
 */
public class Module {
	/*
	 * Constants
	 */
	
	//Mathematical constants
	/**
	 * Mathematical constant PI has value 3.1415927.
	 * It is the ratio of the circumference of a circle to its diameter.
	 * @see Module#HALF_PI
	 * @see Module#THIRD_PI
	 * @see Module#QUARTER_PI
	 * @see Module#TWO_PI
	 * @see Module#TAU
	 */
	public static final float PI = (float) Math.PI;
	
	/**
	 * Mathematical constant HALF_PI has value 1.5707964.
	 * It is half of the ratio of the circumference of a circle to its diameter.
	 * @see Module#PI
	 * @see Module#THIRD_PI
	 * @see Module#QUARTER_PI
	 * @see Module#TWO_PI
	 * @see Module#TAU
	 */
	public static final float HALF_PI = (float) (Math.PI / 2.0);

	/**
	 * Mathematical constant THIRD_PI has value 1.0471976.
	 * It is one third of the ratio of the circumference of a circle to its diameter.
	 * @see Module#PI
	 * @see Module#HALF_PI
	 * @see Module#QUARTER_PI
	 * @see Module#TWO_PI
	 * @see Module#TAU
	 */
	public static final float THIRD_PI = (float) (Math.PI / 3.0);

	/**
	 * Mathematical constant QUARTER_PI has value 0.7853982.
	 * It is one quarter of the ratio of the circumference of a circle to its diameter.
	 * @see Module#PI
	 * @see Module#HALF_PI
	 * @see Module#THIRD_PI
	 * @see Module#TWO_PI
	 * @see Module#TAU
	 */
	public static final float QUARTER_PI = (float) (Math.PI / 4.0);
	
	/**
	 * Mathematical constant TWO_PI has value 6.2831855.
	 * It is twice the ratio of the circumference of a circle to its diameter.
	 * @see Module#PI
	 * @see Module#HALF_PI
	 * @see Module#THIRD_PI
	 * @see Module#QUARTER_PI
	 * @see Module#TAU
	 */
	public static final float TWO_PI = (float) (2.0 * Math.PI);
	
	/**
	 * Mathematical constant TAU is an alias of TWO_PI and has value 6.2831855.
	 * It is twice the ratio of the circumference of a circle to its diameter.
	 * @see Module#PI
	 * @see Module#HALF_PI
	 * @see Module#THIRD_PI
	 * @see Module#QUARTER_PI
	 * @see Module#TWO_PI
	 */
	public static final float TAU = (float) (2.0 * Math.PI);
	
	//Axis (bit flags)
	/**
	 * The x axis, can be combined as follows:<br>
	 * <code>axis = X | Y;</code> (x and y plane in &#8477<sup>2</sup>)
	 * @see Module#Y
	 * @see Module#Z
	 */
	public static final int X = 1;

	/**
	 * The y axis, can be combined as follows:<br>
	 * <code>axis = X | Y;</code> (x and y plane in &#8477<sup>2</sup>)
	 * @see Module#X
	 * @see Module#Z
	 */
	public static final int Y = 2;

	/**
	 * The z axis, can be combined as follows:<br>
	 * <code>axis = X | Y;</code> (x and y plane in &#8477<sup>2</sup>)
	 * @see Module#X
	 * @see Module#Y
	 */
	public static final int Z = 4;

	/**
	 * The s axis is an alias for {@link Module#X}.
	 * This is used for texture wrapping.
	 * @see Module#T
	 * @see Module#R
	 */
	public static final int S = 1;
	
	/**
	 * The t axis is an alias for {@link Module#Y}.
	 * This is used for texture wrapping.
	 * @see Module#S
	 * @see Module#R
	 */
	public static final int T = 2;

	/**
	 * The r axis is an alias for {@link Module#Z}.
	 * This is used for texture wrapping.
	 * @see Module#S
	 * @see Module#T
	 */
	public static final int R = 4;
	
	/**
	 * Epsilon has value 0.0001.
	 */
	public static final float EPSILON = 0.0001f;

	//I/O constants
	/**
	 * Read flag is used to specify a read-only access.
	 */
	public static final int READ = 1;
	
	/**
	 * Write flag is used to specify a write-only access.
	 */
	public static final int WRITE = 2;

	/**
	 * Read and write flag is used to specify both read and write access.
	 */
	public static final int READ_WRITE = 3;
	
	//Primitive rendering mode
	/**
	 * Point primitives are defined as individual vertices.
	 */
	public static final int POINTS = 0;
	
	/**
	 * Lines primitives renders a line between groups of two unconnected vertices.
	 * @see Module#LINE_STRIP
	 * @see Module#LINE_LOOP
	 */
	public static final int LINES = 1;
	
	/**
	 * Line strip primitives renders a multiple lines from the first to last vertices passing through
	 * every vertex in the order of the list.
	 * in the order they are 
	 * @see Module#LINES
	 * @see Module#LINE_LOOP
	 */
	public static final int LINE_STRIP = 2;
	
	/**
	 * @see Module#LINES
	 * @see Module#LINE_STRIP
	 */
	public static final int LINE_LOOP = 3;
	
	/**
	 * @see Module#TRIANGLES
	 * @see Module#TRIANGLE_STRIP
	 * @see Module#TRIANGLE_FAN
	 */
	public static final int TRIANGLES = 4;
	
	/**
	 * @see Module#TRIANGLES
	 * @see Module#TRIANGLE_STRIP
	 * @see Module#TRIANGLE_FAN
	 */
	public static final int TRIANGLE_STRIP = 5;
	
	/**
	 * @see Module#TRIANGLES
	 * @see Module#TRIANGLE_STRIP
	 * @see Module#TRIANGLE_FAN
	 */
	public static final int TRIANGLE_FAN = 6;
	
	/**
	 * @see Module#QUADS
	 */
	public static final int QUAD_STRIP = 7;
	
	/**
	 * @see Module#QUAD_STRIP
	 */
	public static final int QUADS = 8;
	
	public static final int POLYGON = 9;
	
	public static final int PATCHES = 10;
	
	//Shader Type Constants
	/**
	 * <i>Vertex shader</i> is a type of shader that is performed for each vertex in a mesh.
	 * This shader is used for transforming and projecting a mesh to screen-space coordinates.
	 * The output from this shader is the new transformed vertex.
	 * @see Module#FRAGMENT_SHADER
	 * @see Module#GEOMETRY_SHADER
	 * @see Module#TESS_CONTROL_SHADER
	 * @see Module#TESS_EVALUATION_SHADER
	 */
	public static final int VERTEX_SHADER = 0;
	
	/**
	 * <i>Fragment shader</i> is a type of shader that is performed for each fragment (i.e. pixel) that is being rendered.
	 * This shader is used to apply graphical effects such as lighting, shadowing etc.
	 * The output from this shader is the color of the fragment that is being rendered.
	 * @see Module#VERTEX_SHADER
	 * @see Module#GEOMETRY_SHADER
	 * @see Module#TESS_CONTROL_SHADER
	 * @see Module#TESS_EVALUATION_SHADER
	 */
	public static final int FRAGMENT_SHADER = 1;
	
	/**
	 * <i>Geometry shader</i> is a type of shader that is performed on a primitive that is being rendered.
	 * This shader is used to modify the primitive before rendering.
	 * The output from this shader is zero or primitives
	 * @see Module#VERTEX_SHADER
	 * @see Module#FRAGMENT_SHADER
	 * @see Module#TESS_CONTROL_SHADER
	 * @see Module#TESS_EVALUATION_SHADER
	 */
	public static final int GEOMETRY_SHADER = 2;
	
	/**
	 * <i>Tessellation control shader</i> is a type of shader that is used to control how much
	 * tessellation a specific patch gets.
	 * @see Module#VERTEX_SHADER
	 * @see Module#FRAGMENT_SHADER
	 * @see Module#GEOMETRY_SHADER
	 * @see Module#TESS_EVALUATION_SHADER
	 */
	public static final int TESS_CONTROL_SHADER = 3;
	
	/**
	 * <i>Tessellation evaluation shader</i> is a type of shader that is used to evaluate the 
	 * already subdivided (tessellated) geometry and form new vertices and further modify them.
	 * @see Module#VERTEX_SHADER
	 * @see Module#FRAGMENT_SHADER
	 * @see Module#GEOMETRY_SHADER
	 * @see Module#TESS_CONTROL_SHADER
	 */
	public static final int TESS_EVALUATION_SHADER = 4;
	
	//Buffer Usage Constants
	/**
	 * The user will write data to the buffer once, but the user will not read it.
	 * @see Module#DYNAMIC_DRAW
	 * @see Module#STREAM_DRAW
	 */
	public static final int STATIC_DRAW = 0;
	
	/**
	 * The user will not be writing data to the buffer, but the user will read it back.
	 * @see Module#DYNAMIC_READ
	 * @see Module#STREAM_READ
	 */
	public static final int STATIC_READ = 1;
	
	/**
	 * The user will not be writing data to the buffer nor reading the data.
	 * @see Module#DYNAMIC_COPY
	 * @see Module#STREAM_COPY
	 */
	public static final int STATIC_COPY = 2;
	
	/**
	 * The user will write data to the buffer occasionally, but the user will not read it.
	 * @see Module#STATIC_DRAW
	 * @see Module#STREAM_DRAW
	 */
	public static final int DYNAMIC_DRAW = 3;

	/**
	 * The user will not be writing data to the buffer, but the user will read it back.
	 * @see Module#STATIC_READ
	 * @see Module#STREAM_READ
	 */
	public static final int DYNAMIC_READ = 4;

	/**
	 * The user will not be writing data to the buffer nor reading the data.
	 * @see Module#STATIC_COPY
	 * @see Module#STREAM_COPY
	 */
	public static final int DYNAMIC_COPY = 5;

	/**
	 * The user will write data to the buffer after every use (or almost every use), but the user will not read it.
	 * @see Module#STATIC_DRAW
	 * @see Module#DYNAMIC_DRAW
	 */
	public static final int STREAM_DRAW = 6;

	/**
	 * The user will not be writing data to the buffer, but the user will read it back.
	 * @see Module#STATIC_DRAW
	 * @see Module#DYNAMIC_DRAW
	 */
	public static final int STREAM_READ = 7;

	/**
	 * The user will not be writing data to the buffer nor reading the data.
	 * @see Module#STATIC_COPY
	 * @see Module#STREAM_COPY
	 */
	public static final int STREAM_COPY = 8;
	
	//Texture sampling
	/**
	 * Point texture sampling is a
	 * nearest-neighbor interpolation method works taking the
	 * pixel closest to the current sample point.<br>
	 * <i>Note:</i> magnifying the texture causes the final pixel to appear large and blocky.<br>
	 * <i>Note:</i> minifying the texture causes aliasing and noise.<br>
	 * This method is useful when displaying pixel art and you want to preserve hard edges.
	 */
	public static final int POINT = 0;
	
	/**
	 * Linear texture sampling is an interpolation method that is
	 * calculated by interpolating four neighboring points around the current sample.<br>
	 * <i>Note:</i> use mipmapping in combination with this in order to avoid aliasing and noise effects.
	 */
	public static final int LINEAR = 1;

	/**
	 * Bilinear texture sampling is an interpolation method that is
	 * calculated by combining the weighted average of four nearest points around the current sample.<br>
	 * <i>Note:</i> the weighted is determined by the distance from the camera to the sample.
	 */
	public static final int BILINEAR = 2;

	/**
	 * Trilinear texture sampling is an interpolation method that 
	 * uses the different mipmaps in combination with
	 * <i>bilinear</i> interpolation.<br>
	 * <i>Note:</i> without mipmapping enabled the algorithm reverts back to <i>bilinear</i> interpolation.
	 * @see Module#BILINEAR
	 */
	public static final int TRILINEAR = 3;
	
	//TODO: add javadoc here soon!
	/**
	 * Uses the pixel that is nearest of 
	 * @see Texture2D#setMinFilter(int)
	 * @see Texture2D#setMagFilter(int)
	 */
	public static final int NEAREST = 4;
	public static final int NEAREST_MIPMAP_NEAREST = 5;
	public static final int NEAREST_MIPMAP_LINEAR = 6;
	public static final int LINEAR_MIPMAP_NEAREST = 7;
	public static final int LINEAR_MIPMAP_LINEAR  = 8;
	
	//Texture wrapping modes
	//TODO: add javadoc here soon!
	/**
	 * Repeat texture wrap mode.
	 * Repeats the texture when drawing outside the texture area.
	 * @see Texture2D#setWrapMode(int, int)
	 */
	public static final int REPEAT = 0;
	
	/**
	 * Mirrored repeat texture wrap mode.
	 * Repeats and mirror the texture when
	 * drawing outside the texture area.
	 * @see Texture2D#setWrapMode(int, int)
	 */
	public static final int MIRRORED_REPEAT = 1;
	
	/**
	 * Clamp to edge texture wrap mode.
	 * Extends the edge pixels to the edge of the shape.
	 * @see Texture2D#setWrapMode(int, int)
	 */
	public static final int CLAMP_TO_EDGE = 2;
	
	/**
	 * Clamp to border texture wrap mode.
	 * The texture is clamped to a border.
	 * @see Texture2D#setWrapMode(int, int)
	 */
	public static final int CLAMP_TO_BORDER = 3;
	
	/**
	 * Mirror clamp to edge texture wrap mode.
	 * The the first pixels of the mirrored repeated texture
	 * is extended to the edge of the shape.
	 * @see Texture2D#setWrapMode(int, int)
	 */
	public static final int MIRROR_CLAMP_TO_EDGE = 4;
	
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
	 * The OpenGL rendering engine with debug messages enabled.<br>
	 * <i>Note:</i> this configuration should not be used
	 * in released applications.
	 */
	public static final int OPENGL_DEBUG = 1;
	
	/**
	 * OpenGL profile for version 2.x.
	 */
	public static final int GL20_PROFILE = 2;
	
	/**
	 * OpenGL profile for version 3.x with the core profile.
	 */
	public static final int GL30_CORE_PROFILE = 3;
	
	/**
	 * OpenGL profile for version 3.x with the compatibility profile.
	 * The OpenGL compatibility profile includes deprecated functionality
	 * of previous OpenGL versions.
	 */
	public static final int GL30_COMPAT_PROFILE = 4;

	/**
	 * OpenGL profile for version 4.x with the core profile.
	 */
	public static final int GL40_CORE_PROFILE = 5;

	/**
	 * OpenGL profile for version 4.x with the compatibility profile.
	 * The OpenGL compatibility profile includes deprecated functionality
	 * of previous OpenGL versions.
	 */
	public static final int GL40_COMPAT_PROFILE = 6;
	
	
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
	
	//Stroke cap / join
	/**
	 * Stroke cap butt.
	 */
	public static final int BUTT = 0;
	
	/**
	 * Stroke cap and join round.
	 */
	public static final int ROUND = 1;
	
	/**
	 * Stroke cap square.
	 */
	public static final int SQUARE = 2;
	
	/**
	 * Stroke join bevel.
	 */
	public static final int BEVEL = 3;
	
	/**
	 * Stroke join miter.
	 */
	public static final int MITER = 4;
	
	//Event handlers
	/**
	 * Keyboard events e.g. keyUp(), keyDown() etc. 
	 */
	public static final int KEY_EVENTS = 1;
	
	/**
	 * Mouse events e.g. mousePressed(), mouseReleased() etc.
	 */
	public static final int MOUSE_EVENTS = 2;
	
	/**
	 * Touch events.
	 */
	public static final int TOUCH_EVENTS = 4;
	
	/**
	 * Gamepad events.
	 */
	public static final int GAMEPAD_EVENTS = 8;
	
	/**
	 * Window events. e.g. windowResized(), windowMoved() etc.
	 */
	public static final int WINDOW_EVENTS = 16;
	
	//Input
	/**
	 * Printable keys.
	 */
    public static final int
        KEY_SPACE         = 32,
        KEY_APOSTROPHE    = 39,
        KEY_COMMA         = 44,
        KEY_MINUS         = 45,
        KEY_PERIOD        = 46,
        KEY_SLASH         = 47,
        KEY_0             = 48,
        KEY_1             = 49,
        KEY_2             = 50,
        KEY_3             = 51,
        KEY_4             = 52,
        KEY_5             = 53,
        KEY_6             = 54,
        KEY_7             = 55,
        KEY_8             = 56,
        KEY_9             = 57,
        KEY_SEMICOLON     = 59,
        KEY_EQUAL         = 61,
        KEY_A             = 65,
        KEY_B             = 66,
        KEY_C             = 67,
        KEY_D             = 68,
        KEY_E             = 69,
        KEY_F             = 70,
        KEY_G             = 71,
        KEY_H             = 72,
        KEY_I             = 73,
        KEY_J             = 74,
        KEY_K             = 75,
        KEY_L             = 76,
        KEY_M             = 77,
        KEY_N             = 78,
        KEY_O             = 79,
        KEY_P             = 80,
        KEY_Q             = 81,
        KEY_R             = 82,
        KEY_S             = 83,
        KEY_T             = 84,
        KEY_U             = 85,
        KEY_V             = 86,
        KEY_W             = 87,
        KEY_X             = 88,
        KEY_Y             = 89,
        KEY_Z             = 90,
        KEY_LEFT_BRACKET  = 91,
        KEY_BACKSLASH     = 92,
        KEY_RIGHT_BRACKET = 93,
        KEY_GRAVE_ACCENT  = 96,
        KEY_WORLD_1       = 161,
        KEY_WORLD_2       = 162;

    /**
     * Function keys.
     */
    public static final int
        KEY_ESCAPE        = 256,
        KEY_ENTER         = 257,
        KEY_TAB           = 258,
        KEY_BACKSPACE     = 259,
        KEY_INSERT        = 260,
        KEY_DELETE        = 261,
        KEY_RIGHT         = 262,
        KEY_LEFT          = 263,
        KEY_DOWN          = 264,
        KEY_UP            = 265,
        KEY_PAGE_UP       = 266,
        KEY_PAGE_DOWN     = 267,
        KEY_HOME          = 268,
        KEY_END           = 269,
        KEY_CAPS_LOCK     = 280,
        KEY_SCROLL_LOCK   = 281,
        KEY_NUM_LOCK      = 282,
        KEY_PRINT_SCREEN  = 283,
        KEY_PAUSE         = 284,
        KEY_F1            = 290,
        KEY_F2            = 291,
        KEY_F3            = 292,
        KEY_F4            = 293,
        KEY_F5            = 294,
        KEY_F6            = 295,
        KEY_F7            = 296,
        KEY_F8            = 297,
        KEY_F9            = 298,
        KEY_F10           = 299,
        KEY_F11           = 300,
        KEY_F12           = 301,
        KEY_F13           = 302,
        KEY_F14           = 303,
        KEY_F15           = 304,
        KEY_F16           = 305,
        KEY_F17           = 306,
        KEY_F18           = 307,
        KEY_F19           = 308,
        KEY_F20           = 309,
        KEY_F21           = 310,
        KEY_F22           = 311,
        KEY_F23           = 312,
        KEY_F24           = 313,
        KEY_F25           = 314,
        KEY_KP_0          = 320,
        KEY_KP_1          = 321,
        KEY_KP_2          = 322,
        KEY_KP_3          = 323,
        KEY_KP_4          = 324,
        KEY_KP_5          = 325,
        KEY_KP_6          = 326,
        KEY_KP_7          = 327,
        KEY_KP_8          = 328,
        KEY_KP_9          = 329,
        KEY_KP_DECIMAL    = 330,
        KEY_KP_DIVIDE     = 331,
        KEY_KP_MULTIPLY   = 332,
        KEY_KP_SUBTRACT   = 333,
        KEY_KP_ADD        = 334,
        KEY_KP_ENTER      = 335,
        KEY_KP_EQUAL      = 336,
        KEY_LEFT_SHIFT    = 340,
        KEY_LEFT_CONTROL  = 341,
        KEY_LEFT_ALT      = 342,
        KEY_LEFT_SUPER    = 343,
        KEY_RIGHT_SHIFT   = 344,
        KEY_RIGHT_CONTROL = 345,
        KEY_RIGHT_ALT     = 346,
        KEY_RIGHT_SUPER   = 347,
        KEY_MENU          = 348;
        
    /**
     * The first key.
     */
    public static final int KEY_FIRST = 0;

    /**
     * The last key.
     */
    public static final int KEY_LAST = KEY_MENU;
    
    /**
     * MOD key shift bit flag.
     */
    public static final int MOD_SHIFT = 1;

    /**
     * MOD key control bit flag.
     */
    public static final int MOD_CONTROL = 2;

    /**
     * MOD key alt bit flag.
     */
    public static final int MOD_ALT = 4;

    /**
     * MOD key super (e.g. windows key) bit flag.
     */
    public static final int MOD_SUPER = 8;

    /**
     * MOD key caps lock bit flag.
     */
    public static final int MOD_CAPS_LOCK = 16;

    /**
     * MOD key num lock bit flag.
     */
    public static final int MOD_NUM_LOCK = 32;

    /**
     * MOD key scroll lock bit flag.
     */
    public static final int MOD_SCROLL_LOCK = 64;
    
	/**
	 * Mouse buttons.
	 */
	public static final int
		MOUSE_BUTTON_1 = 0,
		MOUSE_BUTTON_2 = 1,
		MOUSE_BUTTON_3 = 2,
		MOUSE_BUTTON_4 = 3,
		MOUSE_BUTTON_5 = 4,
		MOUSE_BUTTON_6 = 5,
		MOUSE_BUTTON_7 = 6,
		MOUSE_BUTTON_8 = 7,
		MOUSE_BUTTON_LEFT = 0,
		MOUSE_BUTTON_RIGHT = 1,
		MOUSE_BUTTON_MIDDLE = 2,
		MOUSE_BUTTON_FIRST = MOUSE_BUTTON_1,
		MOUSE_BUTTON_LAST = MOUSE_BUTTON_8;
	
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

	/*
	 * Basic variables 
	 */
	
	/**
	 * The handle to the display that this module lives in.
	 */
	public Window window;
	
	/**
	 * The handle to the rendering engine that is being used by this window.
	 * @see #window
	 */
	public Graphics graphics;
	
	/**
	 * The handle to the file I/O handler that is being used by this window.
	 * @see #window
	 */
	public Files files;
	
	/**
	 * The handle to the input handler that is being used by this window.
	 * @see #window
	 */
	public Input input;
	
	/*
	 * Basic events
	 */
	/**
	 * The setup event is triggered once this object is ready to be used.
	 * Use this method for initializing variables and other settings.
	 */
	public void setup() {}
	
	/**
	 * The update event is triggered for each time the window requests an update from the running program,
	 * this is normally performed via a loop that continuously triggers this event 30 times every second.
	 * The rate in which this is executed can be customized.
	 */
	public void update() {}
	
	/**
	 * The draw event is triggered for each time the window requests a new frame from the running program,
	 * this is normally performed via a loop that continuously requests new frames 30 times every second.
	 * The rate at which new frames are requested (frame rate) can be customized.
	 */
	public void draw() {}
	
	/**
	 * Key down event is triggered when the user presses
	 * a key on the keyboard.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(KEY_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param key the pressed key
	 */
	public void keyDown(int key) {}

	/**
	 * Key up event is triggered when the user releases
	 * a key on the keyboard.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(KEY_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param key the released key
	 */
	public void keyUp(int key) {}

	/**
	 * Key repeat event is triggered repeatedly when the user holds
	 * down a key on the keyboard for some time.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(KEY_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param key the repeating key
	 */
	public void keyRepeat(int key) { }

	/**
	 * Mouse pressed event is triggered when the user presses
	 * a button on the mouse.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param button the pressed mouse button
	 */
	public void mousePressed(int button) { }

	/**
	 * Mouse released event is triggered when the user releases
	 * a button on the mouse.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param button the released mouse button
	 */
	public void mouseReleased(int button) { }

	/**
	 * Mouse clicked event is triggered when the user clicks
	 * a button on the mouse.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param button the clicked mouse button
	 */
	public void mouseClicked(int button) { }

	/**
	 * Mouse entered event is triggered when the user moves the
	 * cursor on this node.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 */
	public void mouseEntered() { }

	/**
	 * Mouse exited event is triggered when the user moves the
	 * cursor away from this node.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 */
	public void mouseExited() { }
	
	/**
	 * Mouse moved event is triggered when the user moves the cursor.
	 * If the user presses and holds a button then the 
	 * {@link #mouseDragged(int, double, double)} event is used instead.
	 * The change in position is provided as argument.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param dx the movement in the x direction 
	 * @param dy the movement in the y direction
	 */
	public void mouseMoved(double dx, double dy) { }

	/**
	 * Mouse dragged event is triggered when the user moves the cursor
	 * after pressing and holding a button. If the user releases the button
	 * then the {@link #mouseMoved(double, double)} event is used instead. 
	 * The mouse button and the change in position is provided as argument.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param button the mouse button that is used to trigger the dragging
	 * @param dx the movement in the x direction 
	 * @param dy the movement in the y direction
	 */
	public void mouseDragged(double dx, double dy) { }
	
	/**
	 * <p>
	 * Mouse scrolled event is triggered when the user scrolls on the
	 * mouse wheel or using touch gesture on a trackpad.
	 * The change in the scroll position is provided as argument.
	 * </p>
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(MOUSE_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param dx the scrolling in the x direction 
	 * @param dy the scrolling in the y direction
	 */
	public void mouseScrolled(double dx, double dy) { }
	
	/**
	 * Window resized event is triggered when the user resizes the window.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param width the new width of the window
	 * @param height the new height of the window
	 */
	public void windowResized(int width, int height) { }
	
	/**
	 * Window moved event is triggered when the user moves the window.
	 * @param x the new x location of the window
	 * @param y the new y location of the window
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 */
	public void windowMoved(int x, int y) { }
	
	/**
	 * Window focus event is triggered when the window has lost focus
	 * or when then user interacts with the window and it regains the focus.
	 * The focused state is provided as argument.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param focused true if the window is focused, false otherwise
	 */
	public void windowFocus(boolean focused) { }
	
	/**
	 * Window iconify event is triggered when the user iconify (minimize) the window
	 * or when the user restores the window.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param iconified true if the window is iconified, false otherwise
	 */
	public void windowIconify(boolean iconified) { }
	
	/**
	 * Window maximize event is triggered when the user maximizes the window
	 * or when the user restores the window.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 * @param maximized true if the window is maximized, false otherwise
	 */
	public void windowMaximize(boolean maximized) { }
	
	/**
	 * Window closed event is triggered when the user closes the window.
	 * <p>
	 * <i>Note:</i> in order for this event to be used you need to call
	 * <code>install(WINDOW_EVENT);</code> in the {@link #setup()} method.
	 * </p>
	 */
	public void windowClosed() { }
	
	/*
	 * Functions
	 */
	
	//Rendering functions
	/**
	 * Set the rendering viewport.
	 * @param x the x location of the viewport
	 * @param y the y location of the viewport
	 * @param w the width of the viewport
	 * @param h the height of the viewport
	 */
	public final void viewport(int x, int y, int w, int h) {
		graphics.viewport(x, y, w, h);
	}
           
	/**
	 * Load a shader program with a basic vertex shader
	 * and a specific fragment shader.
	 * @param fragment the fragment shader location
	 * @return the loaded shader program
	 */
	public Shader loadShader(String fragment) {
		return graphics.loadShader(fragment);
	}
	       
	/**
	 * Load a shader program with a specific fragment
	 * and vertex shader.
	 * @param fragment the fragment shader location
	 * @param vertex the vertex shader location
	 * @return the loaded shader program
	 */
	public Shader loadShader(String fragment, String vertex) {
		return graphics.loadShader(fragment, vertex);
	}
	       
	/**
	 * Load a predefined shader program.
	 * @param shader the shader program to load
	 * @return the loaded shader program
	 */
	public Shader loadShader(int shader) {
		return graphics.loadShader(shader);
	}
	
	//File I/O
	/**
	 * Open a file with read and write access.
	 * @param file the path to the file can be
	 * 		  relative to your project, a local file
	 * 		  on your machine or an URL.
	 * @return
	 */
	public boolean open(String file) {
		return files.open(READ_WRITE, file);
	}
	
	/**
	 * Open a file with a specific access
	 */
	public boolean open(int access, String file) {
		return files.open(access, file);
	}
	
	public void close() {
		files.close();
	}
	
	public String read() {
		return files.read();
	}
	
	public String readln() {
		return files.readln();
	}
	
	public boolean write(String data) {
		return files.write(data);
	}
	
	//Events
	public void install(int events) {
		if (this instanceof Node) {
			install(events, (Node) this);
		}
	}
	
	public void install(int events, Node node) {
		if ((events & KEY_EVENTS) == KEY_EVENTS)
			input.addKeyListener(node);
		if ((events & MOUSE_EVENTS) == MOUSE_EVENTS)
			input.addMouseListener(node);
		if ((events & WINDOW_EVENTS) == WINDOW_EVENTS)
			input.addWindowListener(node);
	}
}
