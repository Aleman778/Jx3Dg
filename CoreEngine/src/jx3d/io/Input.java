package jx3d.io;

import jx3d.core.Node;
import jx3d.core.Window;

import java.util.HashSet;
import java.util.Set;

import static jx3d.core.Module.*;

/**
 * Input interface should be implemented for objects such as a window that has inputs that can be checked whenever
 * requested. This is called input polling and should always implemented by the window classes.
 * @author Aleman778
 * @since 1.0
 */
public interface Input {

    /**
     * Check if a specified key on the keyboard is being pressed at the moment.
     * @param key the specific key to check
     * @return true if the specified key is down, false otherwise
     */
    boolean isKeyDown(int key);

    /**
     * Check if a specific mouse button is being pressed at the moment.
     * @param button the specific mouse button to check
     * @return true if the specific mouse button is down, false otherwise
     */
    boolean isMouseButtonDown(int button);

    float[] getMousePos();

    /**
     * Get the mouse x position inside the related component.
     * @return the x position
     */
    float getMouseX();

    /**
     * Get the mouse y position inside the related component.
     * @return the y position
     */
    float getMouseY();

}
