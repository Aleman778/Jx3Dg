package jx3d.core;

/**
 * Shared constants in the jx3DGraphics API. 
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
	
}
