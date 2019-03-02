package jx3d.graphics.opengl;

import jx3d.core.JX3D;
import jx3d.core.Log;
import jx3d.core.Window;
import jx3d.graphics.*;

import static jx3d.core.Module.*;

/**
 * OpenGL graphics context.
 * Supports all OpenGL versions including OpenGLES, WebGL.
 *
 * @author Aleman778
 * @since 1.0
 */
public class GLGraphics extends Graphics {

    /**
     * OpenGL state variables.
     */
    public GLState state;

    /**
     * Flag determines if the context have been initialized before.
     */
    private boolean initialized;

    /**
     * Create a new OpenGL graphics context.
     *
     * @param display the window to target
     */
    public GLGraphics(Window display) {
        super(display);
    }

    @Override
    public void init() {
        if (initialized)
            return;

        Log.CORE.info("Initializing graphics for OpenGL " + GLContext.getMajorVersion() + "." + GLContext.getMinorVersion());


        //Setup backface culling, JUST TEST MOVE THIS LATER LATER
        JX3D.gl20.cullFace(GL20.BACK);
        JX3D.gl20.depthFunc(GL20.LESS);
        JX3D.gl20.enable(GL20.DEPTH_TEST);
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
        JX3D.gl20.clear(flag);
    }

    @Override
    public void viewport(int x, int y, int w, int h) {
        JX3D.gl20.viewport(x, y, w, h);
    }

    @Override
    public void render(int mode, VertexArray vao) {
        vao.bind();
        JX3D.gl20.drawArrays(glGetShapeMode(mode), 0, vao.count());
    }

    @Override
    public void render(int mode, VertexArray vao, IndexBuffer ibo) {
        vao.bind();
        ibo.bind();
        JX3D.gl20.drawElements(glGetShapeMode(mode), vao.count(), GL20.UNSIGNED_SHORT, 0);
    }

    @Override
    public void background(float red, float green, float blue, float alpha) {
        JX3D.gl20.clear(GL20.COLOR_BUFFER_BIT);
        JX3D.gl20.clearColor(red, green, blue, alpha);
    }

    @Override
    public Shader loadShader(String fragment) {
        String source = JX3D.files.loadText(fragment);
        Shader shader = new GLSLShader(JX3D.gl20);
        shader.add(FRAGMENT_SHADER, source);
        shader.setup();
        return shader;
    }

    @Override
    public Shader loadShader(String fragment, String vertex) {
        String fsource = JX3D.files.loadText(fragment);
        String vsource = JX3D.files.loadText(vertex);
        Shader shader = new GLSLShader(JX3D.gl20);
        shader.add(FRAGMENT_SHADER, fsource);
        shader.add(VERTEX_SHADER, vsource);
        shader.setup();
        return shader;
    }


    @Override
    public Shader loadShader(int shader) {
        return null;
    }

    @Override
    public void setDepthTest(boolean enable) {
        super.setDepthTest(enable);

        if (enable) {
            JX3D.gl20.enable(GL20.DEPTH_TEST);
        } else {
            JX3D.gl20.disable(GL20.DEPTH_TEST);
        }
    }

    @Override
    public void setDepthFunc(int func) {
        super.setDepthFunc(func);

        JX3D.gl20.depthFunc(glGetDepthFunc(func));
    }

    @Override
    public void setDepthMask(boolean mask) {
        super.setDepthMask(mask);
        JX3D.gl20.depthMask(mask);
    }

    @Override
    public void setStencilTest(boolean enable) {
        super.setStencilTest(enable);

        if (enable) {
            JX3D.gl20.enable(GL20.STENCIL_TEST);
        } else {
            JX3D.gl20.disable(GL20.STENCIL_TEST);
        }
    }

    @Override
    public void setStencilFunc(int func, int ref, int mask) {
        this.stencilFunc = func;

        JX3D.gl20.stencilFunc(glGetStencilFunc(func), ref, mask);
    }

    public static int glGetBufferUsage(int usage) {
        switch (usage) {
            case STATIC_DRAW:
                return GL20.STATIC_DRAW;
            case STATIC_READ:
                return GL20.STATIC_READ;
            case STATIC_COPY:
                return GL20.STATIC_COPY;
            case DYNAMIC_DRAW:
                return GL20.DYNAMIC_DRAW;
            case DYNAMIC_READ:
                return GL20.DYNAMIC_READ;
            case DYNAMIC_COPY:
                return GL20.DYNAMIC_COPY;
            case STREAM_DRAW:
                return GL20.STREAM_DRAW;
            case STREAM_READ:
                return GL20.STREAM_READ;
            case STREAM_COPY:
                return GL20.STREAM_COPY;
        }

        return 0;
    }

    public static int glGetShaderType(int type) {
        switch (type) {
            case VERTEX_SHADER:
                return GL20.VERTEX_SHADER;
            case FRAGMENT_SHADER:
                return GL20.FRAGMENT_SHADER;
            case GEOMETRY_SHADER:
                return GL30.EXT.GEOMETRY_SHADER;
            case TESS_CONTROL_SHADER:
                return GL30.EXT.TESS_CONTROL_SHADER;
            case TESS_EVALUATION_SHADER:
                return GL30.EXT.TESS_EVALUATION_SHADER;
        }

        return -1;
    }

    public static int glGetShapeMode(int mode) {
        switch (mode) {
            case POINTS:
                return GL20.POINTS;
            case LINES:
                return GL20.LINES;
            case LINE_LOOP:
                return GL20.LINE_LOOP;
            case LINE_STRIP:
                return GL20.LINE_STRIP;
            case TRIANGLES:
                return GL20.TRIANGLES;
            case TRIANGLE_STRIP:
                return GL20.TRIANGLE_STRIP;
            case TRIANGLE_FAN:
                return GL20.TRIANGLE_FAN;
            case QUAD_STRIP:
                return GL20.QUAD_STRIP;
            case QUADS:
                return GL20.QUADS;
            case POLYGON:
                return GL20.POLYGON;
        }

        return 0;
    }

    public static int glGetDepthFunc(int func) {
        switch (func) {
            case ALWAYS:
                return GL20.ALWAYS;
            case NEVER:
                return GL20.NEVER;
            case LESS:
                return GL20.LESS;
            case EQUAL:
                return GL20.EQUAL;
            case LEQUAL:
                return GL20.LEQUAL;
            case GREATER:
                return GL20.GREATER;
            case NOTEQUAL:
                return GL20.NOTEQUAL;
            case GEQUAL:
                return GL20.GEQUAL;
        }

        return 0;
    }

    public static int glGetStencilFunc(int func) {
        switch (func) {
            case KEEP:
                return GL20.KEEP;
            case ZERO:
                return GL20.ZERO;
            case REPLACE:
                return GL20.REPLACE;
            case INCR:
                return GL20.INCR;
            //case INCR_WRAP: return GL_INCR_WRAP;
            case DECR:
                return GL20.DECR;
            //case DECR_WRAP: return GL_DECR_WRAP;
            case INVERT:
                return GL20.INVERT;
        }

        return 0;
    }

    public static int glGetType(int type) {
        switch (type) {
            case INT:
                return GL20.INT;
            case UNSIGNED_INT:
                return GL20.UNSIGNED_INT;
            case FLOAT:
                return GL20.FLOAT;
            case DOUBLE:
                return GL20.DOUBLE;
            case SHORT:
                return GL20.SHORT;
            case UNSIGNED_SHORT:
                return GL20.UNSIGNED_SHORT;
        }

        return 0;
    }

    public static int glGetTextureWrapMode(int type) {
        switch (type) {
            case REPEAT:
                return GL20.REPEAT;
            case MIRRORED_REPEAT:
                return GL20.MIRRORED_REPEAT;
            case CLAMP_TO_EDGE:
                return GL20.CLAMP_TO_EDGE;
            case CLAMP_TO_BORDER:
                return GL20.EXT.CLAMP_TO_BORDER;
            case MIRROR_CLAMP_TO_EDGE:
                return GL20.EXT.MIRROR_CLAMP_TO_EDGE;
        }
        return 0;
    }

    public static int getSizeOf(int type) {
        switch (type) {
            case INT:
                return Integer.BYTES;
            case UNSIGNED_INT:
                return Integer.BYTES;
            case FLOAT:
                return Float.BYTES;
            case DOUBLE:
                return Double.BYTES;
            case SHORT:
                return Short.BYTES;
            case UNSIGNED_SHORT:
                return Short.BYTES;
            case LONG:
                return Long.BYTES;
        }

        return 0;
    }
}
