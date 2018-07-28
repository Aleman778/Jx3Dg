package jx3d.graphics;

import jx3d.math.*;
import jx3d.util.Disposable;

/**
 * Represents a shader program containing multiple sub shaders.
 * Shader programs are generally used to specify to the graphics
 * card how a mesh (i.e. a list of vertices) should be render to the screen.
 * Shader programs are exclusively executed on the graphics processing unit (GPU).<br>
 * There are a few types of shader programs:<br>
 * <ul>
 * <li>Vertex Shader</li>
 * <li>Fragment Shader</li>
 * <li>Geometry Shader</li>
 * <li>Tessellation Shader
 * 		<ul>
 * 		<li>Control Shader</li>
 * 		<li>Evaluation Shader</li>
 * 		</ul>
 * </li>
 * </ul>
 * Some of the effects that are achieved by shaders include realistic lighting and
 * shadowing, ambient occlusion etc.
 * @author Aleman778
 * @since 1.0
 */
public abstract class Shader implements Disposable {
    
    /**
     * 
     * @param type the type of shader. The type can be any of the following:<br>
     * <code>VERTEX</code>, <code>FRAGMENT</code>, <code>GEOMETRY</code>,
     * <code>TESS_CONTROL_SHADER</code>, <code>TESS_EVALUATION_SHADER</code>
     * @param source the source code to this shader
     * @throws ShaderException when there is a compile error in the shader
     */
    public abstract void add(int type, String source) throws ShaderException;
    
    /**
     * Setup this shader<br>
     * <i>Note:</i> calling this method is not necessary since
     * the <code>enable()</code> method calls this method.
     * Calling this method more than one time has not effect after
     * the first time.  
     */
    public abstract void setup();
    
    /**
     * Enables this shader.<br>
     * <i>Note:</i> only enabled shader can be used for rendering.
     */
    public abstract void enable();
    
    /**
     * Disables this shader.<br>
     * <i>Note:</i> only enabled shader can be used for rendering.
     */
    public abstract void disable();

    /**
     * Disposes the program.<br>
     * <i>Note:</i> program cannot be used after disposing.
     */
    @Override
    public abstract void dispose();
    
    /**
     * Set shader uniform variable value.
     * @param name the variable name
     * @param value the value of the uniform
     */
    public abstract void set(String name, Integer value);
    
    /**
     * Set shader uniform variable value.
     * @param name the variable name
     * @param value the value of the uniform
     */
    public abstract void set(String name, Float value);

    /**
     * Set shader uniform variable value.
     * @param name the variable name
     * @param value the value of the uniform
     */
    public abstract void set(String name, Vector2D value);
    
    /**
     * Set shader uniform variable value.
     * @param name the variable name
     * @param value the value of the uniform
     */
    public abstract void set(String name, Vector3D value);
    
    /**
     * Set shader uniform variable value.
     * @param name the variable name
     * @param value the value of the uniform
     */
    public abstract void set(String name, Vector4D value);

    /**
     * Set shader uniform variable value.
     * @param name the variable name
     * @param vector the value of the uniform
     */
    public abstract void set(String name, Quaternion value);
    
    /**
     * Set shader uniform variable value.
     * @param name the variable name
     * @param value the value of the uniform
     */
    public abstract void set(String name, Color value);

    /**
     * Set shader uniform variable value.
     * @param name the variable name
     * @param value the value of the uniform
     */
    public abstract void set(String name, Matrix22 value);
    
    /**
     * Set shader uniform variable value.
     * @param name the variable name
     * @param value the value of the uniform
     */
    public abstract void set(String name, Matrix33 value);
    
    /**
     * Set shader uniform variable value.
     * @param name the variable name
     * @param value the value of the uniform
     */
    public abstract void set(String name, Matrix44 value);
}
