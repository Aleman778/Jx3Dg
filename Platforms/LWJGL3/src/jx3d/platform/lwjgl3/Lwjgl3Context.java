package jx3d.platform.lwjgl3;

import jx3d.graphics.Context;
import jx3d.graphics.opengl.GLContext;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

public class Lwjgl3Context extends GLContext {

    private GLCapabilities glc;

    public Lwjgl3Context(Context.RenderAPI renderAPI, boolean forwardCompatible, long window) {
        super(renderAPI);

        GLFW.glfwMakeContextCurrent(window);
        glc = GL.createCapabilities(forwardCompatible);
        forwardCompat = glc.forwardCompatible;
        if (glc.OpenGL11) { minorVersion = 1; majorVersion = 1; }
        if (glc.OpenGL12) { minorVersion = 2; }
        if (glc.OpenGL13) { minorVersion = 3; }
        if (glc.OpenGL14) { minorVersion = 4; }
        if (glc.OpenGL15) { minorVersion = 5; }
        if (glc.OpenGL20) { minorVersion = 0; majorVersion = 2;}
        if (glc.OpenGL21) { minorVersion = 1; }
        if (glc.OpenGL30) { minorVersion = 0; majorVersion = 3; }
        if (glc.OpenGL31) { minorVersion = 1; }
        if (glc.OpenGL32) { minorVersion = 2; }
        if (glc.OpenGL33) { minorVersion = 3; }
        if (glc.OpenGL40) { minorVersion = 0; majorVersion = 4; }
        if (glc.OpenGL41) { minorVersion = 1; }
        if (glc.OpenGL42) { minorVersion = 2; }
        if (glc.OpenGL43) { minorVersion = 3; }
        if (glc.OpenGL44) { minorVersion = 4; }
        if (glc.OpenGL45) { minorVersion = 5; }
        if (glc.OpenGL46) { minorVersion = 6; }
    }
}
