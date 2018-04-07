package jx3d.core;

/**
 * Shared constants in the jx3DGraphics API.
 *  
 * @since 1.0
 * @author Aleman778
 */
public class Constants {

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
	
	//Data types
	/**<b>Type:</b> 4 byte integer.*/
	public static final int INT 		   = 0;
	
	/**<b>Type:</b> 4 byte unsigned integer.*/
	public static final int UNSIGNED_INT   = 1;
	
	/**<b>Type:</b> 4 byte floating point.*/
	public static final int FLOAT 		   = 2;
	
	/**<b>Type:</b> 8 byte double precision floating point.*/
	public static final int DOUBLE 		   = 3;
	
	/**<b>Type:</b> 8 byte long.*/
	public static final int LONG 		   = 4;
	
	/**<b>Type:</b> 2 byte short.*/
	public static final int SHORT 		   = 5;
	
	/**<b>Type:</b> 2 byte unsigned short.*/
	public static final int UNSIGNED_SHORT = 6;
	
	/**<b>Type:</b> 2 byte character.*/
	public static final int CHAR		   = 7;
	
	/**<b>Type:</b> boolean.*/
	public static final int BOOLEAN 	   = 8;
	
	/**<b>Type:</b> 1 byte.*/
	public static final int BYTE           = 9;
	
	/**<b>Type:</b> vector of 2 floating point numbers.*/
	public static final int VEC2		   = 10;
	
	/**<b>Type:</b> vector of 3 floating point numbers.*/
	public static final int VEC3           = 11;
	
	/**<b>Type:</b> vector of 4 floating point numbers.*/
	public static final int VEC4		   = 12;
	
	/**<b>Type:</b> matrix of 2x2 floating point numbers.*/
	public static final int MAT2		   = 13;
	
	/**<b>Type:</b> matrix of 3x3 floating point numbers.*/
	public static final int MAT3  		   = 14;
	
	/**<b>Type:</b> matrix of 4x4 floating point numbers.*/
	public static final int MAT4		   = 15;
	
	/**<b>Type:</b> quaternion of 4 floating point numbers.*/
	public static final int QUAT		   = 16;
}
