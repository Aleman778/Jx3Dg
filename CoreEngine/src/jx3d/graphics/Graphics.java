package jx3d.graphics;

import jx3d.core.Window;

import static jx3d.core.Module.KEEP;
import static jx3d.core.Module.LESS;

/**
 * Abstract graphics context class provides methods for
 * drawing on a display. An implemented graphics class
 * class is generally built on top of a graphics api,
 * e.g. OpenGL.
 *
 * @author Aleman778
 * @since 1.0
 */
public abstract class Graphics {

    /**
     * The window owner.
     */
    protected final Window window;

    /**
     * The currently used material.
     */
    protected Material material;

    /**
     * Enable the red color channel.
     */
    protected boolean redMask = true;

    /**
     * Enable the green color channel.
     */
    protected boolean greenMask = true;

    /**
     * Enable the blue color channel.
     */
    protected boolean blueMask = true;

    /**
     * Enable the alpha color channel.
     */
    protected boolean alphaMask = true;

    /**
     * Enable depth testing.
     */
    protected boolean depthTest = false;

    /**
     * Enable writing to the depth buffer.
     */
    protected boolean depthMask = true;

    /**
     * Depth buffer comparison method.
     */
    protected int depthFunc = LESS;

    /**
     * Enable stencil testing.
     */
    protected boolean stencilTest = false;

    /**
     * Stencil mask
     */
    protected int stencilMask = 0xFFFFFFFF;

    /**
     * Stencil function
     */
    protected int stencilFunc = KEEP;

    /**
     * Constructor.
     * Creates a new graphics object.
     *
     * @param window the window owner
     */
    public Graphics(Window window) {
        this.window = window;
    }

    //Rendering functions
    public abstract void init();

    public abstract void prepare();

    public abstract void present();

    public abstract void clear(int flag);

    public abstract void viewport(int x, int y, int w, int h);

    public abstract void render(int mode, VertexArray vao);

    public abstract void render(int mode, VertexArray vao, IndexBuffer ibo);

    public abstract void background(float red, float green, float blue, float alpha);

    //Primitive functions
    public void rect(float x, float y, float w, float h) {

    }

    public void ellipse(float x, float y, float rx, float ry) {

    }

    public void arc(float x, float y, float rx, float ry, float a1, float a2, boolean ccw) {

    }

    public void quad(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {

    }

    public void triangle(float x1, float y1, float x2, float y2, float x3, float y3) {

    }

    public void line(float x1, float y1, float x2, float y2) {

    }

    public void point(float x, float y) {

    }

    //Shader functions
    public abstract Shader loadShader(String fragment);

    public abstract Shader loadShader(String fragment, String vertex);

    public abstract Shader loadShader(int shader);

    //TODO: This implementation is subject to change later, some platforms do not use the window construct e.g. HTML5.
    public float getWidth() {
        return window.getWidth();
    }

    public float getHeight() {
        return window.getHeight();
    }

    public float getAspectRatio() {
        return window.getAspectRatio();
    }

    /**
     * Enable or disable the writing to each color component of the color buffer.
     *
     * @param red   enable the red buffer (or channel)
     * @param green enable the green buffer (or channel)
     * @param blue  enable the blue buffer (or channel)
     * @param alpha enable the alpha buffer (or channel)
     */
    public void setColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
        this.redMask = red;
        this.greenMask = green;
        this.blueMask = blue;
        this.alphaMask = alpha;
    }

    /**
     * Set the depth testing flag.
     *
     * @param enable enable depth testings
     */
    public void setDepthTest(boolean enable) {
        this.depthTest = enable;
    }

    /**
     * Set the depth buffer function.
     *
     * @param func the depth function
     */
    public void setDepthFunc(int func) {
        this.depthFunc = func;
    }

    /**
     * Enable or disable the writing to the depth buffer.
     *
     * @param mask enable depth buffer writing
     */
    public void setDepthMask(boolean mask) {
        this.depthMask = mask;
    }

    /**
     * Set the stencil testing flag,
     *
     * @param enable enable stencil testing
     */
    public void setStencilTest(boolean enable) {
        this.stencilTest = enable;
    }

    /**
     * Set the stencil buffer function with the provided reference and mask.
     *
     * @param func the stencil function
     * @param ref  the reference value used by
     * @param mask the stencil bits to be affected
     */
    public void setStencilFunc(int func, int ref, int mask) {
        this.stencilFunc = func;
    }
}
