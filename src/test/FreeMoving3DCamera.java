package test;

import org.joml.Quaternionf;
import org.joml.Vector3f;

import jx3d.graphics.PerspectiveCamera;

/**
 * @since 1.0
 * @author Aleman778
 */
public class FreeMoving3DCamera extends PerspectiveCamera {

	private float sensitivityX = 256.0f;
	
	private float sensitivityY = 256.0f; 
	
	@Override
	public void setup() {
		super.setup();
		install(MOUSE_EVENTS);
		
		transform.translate(new Vector3f(0.0f, 0.0f, 4f));
		validView = false;
	}
	
	@Override
	public void mouseDragged(double dx, double dy) {
		if (input.mouseState[MOUSE_BUTTON_RIGHT]) {
			transform.rotateX((float) -dy / sensitivityY);
			transform.rotateY((float) -dx / sensitivityX);
		}
		validView = false;
	}
}
