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
     * @param config the application configurations
     * @param deviceContext the device context
     */
    public static void Create(RenderAPI renderAPI, Object config, Object deviceContext) {
        Context.renderAPI = renderAPI;
        switch (renderAPI) {
            case OPENGL: Context.instance = new GLContext(config, deviceContext);
        }
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
