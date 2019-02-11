package jx3d.graphics;

import jx3d.core.Module;
import org.joml.Vector3f;

/**
 * Camera with a perspective projection
 * The projection is two dimensional and
 * its view frustum shape is defined by a
 * truncated square pyramid. The camera
 * can be moved and rotated in 3d space.
 *
 * @author Aleman778
 * @since 1.0
 */
public class PerspectiveCamera extends Camera {

    /**
     * The cameras field of view.
     */
    private float fov;

    /**
     * The cameras aspect ratio.
     */
    private float aspect;

    /**
     * The cameras aspect ration is using the displays aspect ratio.
     */
    private boolean windowAspect;

    /**
     * Default constructor.
     * Create an orthographic camera that takes up the entire display screen.
     */
    public PerspectiveCamera() {
        this(new Viewport());
    }

    /**
     * Constructor.
     * Create an orthographic camera with a provided viewport.
     *
     * @param viewport
     */
    public PerspectiveCamera(Viewport viewport) {
        super(viewport);
        aspect = -1;
        near = 0.1f;
        fov = Module.QUARTER_PI;
        windowAspect = true;
    }

    /**
     * Get the cameras field of view.
     *
     * @return the cameras fov
     */
    public float getFov() {
        return fov;
    }

    /**
     * Set the cameras field of view.
     *
     * @param fov the fov to set
     */
    public void setFov(float fov) {
        this.fov = fov;
        this.validProj = false;
    }

    /**
     * Set the aspect ratio used by this camera
     *
     * @return the aspect ratio
     */
    public float getAspectRatio() {
        if (windowAspect && window != null)
            return window.getAspectRatio();
        else
            return aspect;
    }

    /**
     * @param aspect the aspect to set
     */
    public void setAspectRatio(float aspect) {
        this.aspect = aspect;
        this.windowAspect = false;
        this.validProj = false;
    }

    /**
     * Use the aspect ratio based on the window size instead of providing your own.
     * <i>Note:</i> when the window size changes the aspect ratio will respond to the these changes.
     */
    public void useWindowAspectRatio() {
        this.windowAspect = true;
        this.validProj = false;
    }

    @Override
    protected void validateProjection() {
        projection.setPerspective(fov, getAspectRatio(), near, far, false);
    }

    @Override
    protected void validateView() {
        Vector3f pos = transform.getPos();
        Vector3f rot = transform.getEulerAngles();
        pos.negate();
        rot.negate();
        view.rotateXYZ(rot);
        view.translate(pos);
    }
}
