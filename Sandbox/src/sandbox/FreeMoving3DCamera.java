package sandbox;

import jx3d.graphics.PerspectiveCamera;
import org.joml.Vector3f;

/**
 * @author Aleman778
 * @since 1.0
 */
public class FreeMoving3DCamera extends PerspectiveCamera {

    private float sensitivityX = 256.0f;
    private float sensitivityY = 256.0f;
    private float sensitivityZoom = 0.32f;
    private float zoom;

    @Override
    public void setup() {
        super.setup();
        install(MOUSE_EVENTS);
        transform.translate(new Vector3f(0.0f, 0.0f, 4f));
        transform.scale(new Vector3f());
        validView = false;
    }

    @Override
    public void mouseDragged(float dx, float dy) {
        if (mouse(MOUSE_BUTTON_RIGHT)) {
            transform.rotateX(dy / sensitivityY);
            transform.rotateY(dx / sensitivityX);
        }
        validView = false;
    }

    @Override
    public void mouseScrolled(float dx, float dy) {
        zoom += sensitivityZoom * dy;
        validView = false;
    }

    @Override
    protected void validateView() {
        Vector3f pos = transform.getPos();
        Vector3f rot = transform.getEulerAngles();
        pos.negate();
        view.translate(pos);
        view.translate(0, 0, zoom);
        view.rotateXYZ(rot);
    }
}
