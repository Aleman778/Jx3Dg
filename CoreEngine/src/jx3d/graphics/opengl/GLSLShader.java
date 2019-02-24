package jx3d.graphics.opengl;

import jx3d.graphics.Color;
import jx3d.graphics.Shader;
import jx3d.graphics.ShaderException;
import jx3d.util.BufferUtils;
import org.joml.*;

import java.util.HashMap;

import static jx3d.core.Module.*;


/**
 * Represents a shader program written in the OpenGL Shading Language (or GLSL for short).
 *
 * @author Aleman778
 * @since 1.0
 */
public class GLSLShader extends Shader {

    private final GL20 gl;
    private final HashMap<String, Uniform> uniforms;

    private int program;
    private boolean ready;

    /**
     * Create a new empty shader program.
     */
    public GLSLShader(GL20 graphics) {
        gl = graphics;
        uniforms = new HashMap<>();
        program = gl.createProgram();
        ready = false;
    }

    @Override
    public void add(int type, String source) throws ShaderException {
        if (ready) {
            throw new IllegalStateException("Cannot modify shader after being used once.");
        }

        int shader = gl.createShader(GLGraphics.glGetShaderType(type));
        gl.shaderSource(shader, source);
        gl.compileShader(shader);
        if (gl.getShaderi(shader, GL20.COMPILE_STATUS) == GL20.FALSE) {
            String log = gl.getShaderInfoLog(shader);
            gl.deleteShader(shader);
            throw new ShaderException("Compilation error:\n" + log);
        }

        gl.attachShader(program, shader);
        gl.deleteShader(shader);
    }

    @Override
    public void setup() {
        if (ready)
            return;

        ready = true;
        gl.linkProgram(program);
        gl.validateProgram(program);
    }

    @Override
    public void enable() {
        check();
        setup();
        gl.useProgram(program);
    }

    @Override
    public void disable() {
        check();

        if (!ready)
            return;

        gl.useProgram(0);
    }

    @Override
    public void dispose() {
        gl.deleteProgram(program);
        program = -1;
    }

    @Override
    public void set(String name, Integer value) {
        Uniform uniform = setImpl(name, value, INT);
        if (uniform != null) {
            gl.uniform1i(uniform.location, value);
        }
    }

    @Override
    public void set(String name, Float value) {
        Uniform uniform = setImpl(name, value, FLOAT);
        if (uniform != null) {
            gl.uniform1f(uniform.location, value);
        }
    }

    @Override
    public void set(String name, Vector2f value) {
        Uniform uniform = setImpl(name, value, VEC2);
        if (uniform != null) {
            gl.uniform2f(uniform.location, value.x, value.y);
        }
    }

    @Override
    public void set(String name, Vector3f value) {
        Uniform uniform = setImpl(name, value, VEC3);
        if (uniform != null) {
            gl.uniform3f(uniform.location, value.x, value.y, value.z);
        }
    }

    @Override
    public void set(String name, Vector4f value) {
        Uniform uniform = setImpl(name, value, VEC4);
        if (uniform != null) {
            gl.uniform4f(uniform.location, value.x, value.y, value.z, value.w);
        }
    }

    @Override
    public void set(String name, Quaternionf value) {
        Uniform uniform = setImpl(name, value, QUAT);
        if (uniform != null) {
            gl.uniform4f(uniform.location, value.x, value.y, value.z, value.w);
        }
    }

    @Override
    public void set(String name, Color value) {
        Uniform uniform = setImpl(name, value, VEC4);
        if (uniform != null) {
            gl.uniform4f(uniform.location, value.red, value.green, value.blue, value.alpha);
        }
    }

    @Override
    public void set(String name, Matrix3f value) {
        Uniform uniform = setImpl(name, value, MAT3);
        if (uniform != null) {
            gl.uniformMatrix3fv(uniform.location, 12, false, BufferUtils.toFloatBuffer(value));
        }
    }

    @Override
    public void set(String name, Matrix4f value) {
        Uniform uniform = setImpl(name, value, MAT4);
        if (uniform != null) {
            gl.uniformMatrix4fv(uniform.location, 16, false, BufferUtils.toFloatBuffer(value));
        }
    }

    private Uniform setImpl(String name, Object value, int type) {
        Uniform uniform = uniforms.get(name);
        if (uniform == null) {
            int location = gl.getUniformLocation(program, name);
            if (location != -1) {
                uniform = new Uniform(type, location, value);
                uniforms.put(name, uniform);
            } else
                return null;
        } else {
            if (!uniform.set(value))
                return uniform;
        }
        return uniform;
    }

    private void check() {
        if (program == -1)
            throw new NullPointerException();
    }

    /**
     * Uniform class holds the following information
     * the type of uniform, the location in OpenGL and
     * its value.
     *
     * @author Aleman778
     */
    protected class Uniform {
        int type;
        int location;
        Object value;

        /**
         * Create a new uniform with the given values.
         *
         * @param type     the type of the uniform can be any of these
         *                 <code>INT</code>, <code>UNSIGNED_INT</code>, <code>FLOAT</code>
         *                 <code>DOUBLE</code>, <code>LONG</code>, <code>SHORT</code>
         *                 <code>UNSIGNED_SHORT</code>, <code>CHAR</code>, <code>BOOLEAN</code>
         *                 <code>BYTE</code>, <code>VEC2</code>, <code>VEC3</code>
         *                 <code>VEC4</code>, <code>QUAT</code>, <code>MAT2</code>
         *                 <code>MAT3</code> or <code>MAT4</code>
         * @param location the uniform location
         * @param value the value to set uniform to
         */
        public Uniform(int type, int location, Object value) {
            this.type = type;
            this.location = location;
            this.value = value;
        }

        /**
         * Set a new value to this uniform.
         * <i>Note:</i> this function does not perform any OpenGL operations.
         *
         * @param newValue the value to set
         * @return true if the new value does not equal the old value, false otherwise
         */
        public boolean set(Object newValue) {
            if (!value.equals(newValue)) {
                value = newValue;
                return true;
            }
            return false;
        }
    }
}
