package jx3d.graphics;

import jx3d.graphics.opengl.GLContext;

/**
 * Abstract Context class.
 */
public abstract class Context {

    /**
     * All the supported rendering APIS
     */
    public enum RenderAPI {
        NONE, OPENGL
    }

    /**
     * Context singleton instance.
     */
    private static Context instance = null;

    /**
     * Render API singleton instance.
     */
    private static RenderAPI renderAPI = RenderAPI.NONE;

    /**
     * Create a new context.
     * @param renderAPI the render api to use
     */
    public Context(RenderAPI renderAPI) {
        assert Context.instance != null : "There already exists a graphics context!";
        Context.instance = this;
        Context.renderAPI = renderAPI;
    }

    /**
     * Get the render API.
     * @return the render API
     */
    public static RenderAPI getRenderAPI() {
        return renderAPI;
    }

    /**
     * Get the context instance.
     * @return the context
     */
    public static Context getInstance() {
        return instance;
    }
}
