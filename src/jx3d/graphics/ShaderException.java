package jx3d.graphics;

/**
 * Represents a shader exception that is caught when compiling the shader.
 * @since 1.0
 * @author Aleman778
 */
public class ShaderException extends RuntimeException {

	private static final long serialVersionUID = -8793503870684932702L;

	/**
     * Constructor.
     */
    public ShaderException() {
    	super();
    }

    /**
     * Constructor.
     * @param message exception message
     */
    public ShaderException(String message) {
        super(message);
    }

    /**
     * Constructor.
     * @param thrwbl throwable exception
     */
    public ShaderException(Throwable thrwbl) {
        super(thrwbl);
    }

    /**
     * Constructor.
     * @param message exception message
     * @param thrwbl throwable exception
     */
    public ShaderException(String message, Throwable thrwbl) {
        super(message, thrwbl);
    } 
}
