package jx3d.graphics;

/**
 * Color class is represented by a four dimensional vector of components
 * <code>(red, green, blue, alpha)</code>each with single-precision.
 * @since 1.0
 * @author Aleman778
 */
public class Color {

	/**
	 * The red component of the color vector.
	 */
	public float r;
	
	/**
	 * The green component of the color vector.
	 */
	public float g;
	
	/**
	 * The blue component of the color vector.
	 */
	public float b;
	
	/**
	 * The alpha component of the color vector.
	 */
	public float a;
	
	/**
	 * Default Constructor.
	 * Creates the color black i.e. <code>(r=0, g=0, b=0, a=0)</code>.
	 */
	public Color() {
		this(0,0,0,0);
	}
	
	/**
	 * Constructor.
	 * Creates a color based on the given <code>rgba</code> value.
	 * The <code>rgba</code> integer is constructed from 32-bits,
	 * where bit 1 through 8 is the red component,
	 *       bit 9 through 16 is the green component,
	 *       bit 17 through 24 is the blue component and
	 *       bit 25 through 32 is the alpha component.
	 * @param rgba the color to set
	 */
	public Color(int rgba) {
		this.r = (rgba & 0xFF);
		
	}
	
	/**
	 * Constructor.
	 * Creates a color based on the given components <code>(r, g, b, a)</code>.
	 * @param r the red component to set
	 * @param g the green component to set
	 * @param b the blue component to set
	 * @param a the alpha component to set
	 */
	public Color(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	/**
	 * Constructor.
	 * Creates a copy of the provided color <code>copy</code>.
	 * @param copy the color to copy from
	 */
	public Color(Color copy) {
		this.r = copy.r;
		this.g = copy.g;
		this.b = copy.b;
		this.a = copy.a;
	}

	/**
	 * Set all four components in the color to the provided value.
	 * @param d the value of all four entries
	 * @return this color
	 */
	public Color set(float d) {
		return set(d, d, d, d);
	}
	
	/**
	 * Set the x, y, z and w components in the color to the provided values.
	 * @param x the value of the x component to set
	 * @param y the value of the y component to set
	 * @param z the value of the z component to set
	 * @param w the value of the w component to set
	 * @return this color
	 */
	public Color set(float x, float y, float z, float w) {
		this.r = x;
		this.g = y;
		this.b = z;
		this.a = w;
		return this;
	}
	
	/**
	 * Set this color to the values of the given color <code>v</code>.
	 * @param v the color to copy from
	 * @return this color
	 */
	public Color set(Color v) {
		return set(v.r, v.g, v.b, v.a);
	}

	/**
	 * Add the components of this color by the given values.
	 * @param x the x component to add
	 * @param y the y component to add
	 * @param z the z component to add
	 * @param w the w component to add
	 * @return a new color holding the result
	 */
	public Color add(float x, float y, float z, float w) {
		return new Color(this.r + x, this.g + y, this.b + z, this.a + w);
	}
	
	/**
	 * Add the components of this color by the given color <code>v</code>.
	 * @param v the color to add
	 * @return a new color holding the result
	 */
	public Color add(Color v) {
		return new Color(r + v.r, g + v.g, b + v.b, a + v.a);
	}
	

	/**
	 * Subtract the components of this color by the given values.
	 * @param x the x component to subtract
	 * @param y the y component to subtract
	 * @param z the z component to subtract
	 * @param w the w component to subtract
	 * @return a new color holding the result
	 */
	public Color sub(float x, float y, float z, float w) {
		return new Color(this.r - x, this.g - y, this.b - z, this.a - w);
	}
	
	/**
	 * Subtract the components of this color by the given color <code>v</code>.
	 * @param v the color to subtract
	 * @return a new color holding the result
	 */
	public Color sub(Color v) {
		return new Color(r - v.r, g - v.g, b - v.b, a - v.a);
	}
	
	/**
	 * Multiply the components of this color by the given scalar <code>s</code>.
	 * @param s the scalar to multiply
	 * @return a new color holding the result
	 */
	public Color mul(float s) {
		return new Color(r * s, g * s, b * s, a * s);
	}
	
	/**
	 * Multiply the components of this color by the given values.
	 * @param x the x component to multiply
	 * @param y the y component to multiply
	 * @param z the z component to multiply
	 * @param w the w component to multiply
	 * @return a new color holding the result
	 */
	public Color mul(float x, float y, float z, float w) {
		return new Color(this.r * x, this.g * y, this.b * z, this.a * w);
	}
	
	/**
	 * Multiply the components of this color by the components of the given color.
	 * @param v the color to multiply
	 * @return a new color holding the result
	 */
	public Color mul(Color v) {
		return new Color(r * v.r, g * v.g, b * v.b, a * v.a);
	}
	
	/**
	 * Inverts (or negates) each component in this color.
	 * @return a new color holding the result
	 */
	public Color inverse() {
		return new Color(1.0f-r, 1.0f-g, 1.0f-b, 1.0f-a);
	}
	
	/**
	 * Get the linearly interpolated color from this to the provided target color <code>v</code> at time <code>t</code>.
	 * The result is stored in <code>dest</code>.
	 * @param v the target color
	 * @param t the time from [0..1]
	 * @param dest the color to hold the result in
	 * @return the dest color
	 */
	public Color lerp(Color v, float t, Color dest) {
		dest.r = r + (v.r - r) * t;
		dest.g = g + (v.g - g) * t;
		dest.b = b + (v.b - b) * t;
		dest.a = a + (v.a - a) * t;
		return dest;
	}
	
	/**
	 * Get the linearly interpolated color from this to the provided target color <code>v</code> at time <code>t</code>.
	 * <i>Note:</i> the result is held in this color.
	 * @param v the target color
	 * @param t the time from [0..1]
	 * @return this color
	 */
	public Color lerp(Color v, float t) {
		return lerp(v, t, this);
	}

	@Override
	public String toString() {
		return "Color: (" + r + ", " + g + ", " + b + ", " + a + ")";
	}
}
