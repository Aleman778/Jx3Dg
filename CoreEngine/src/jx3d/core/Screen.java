package jx3d.core;

/**
 * Screen helper class holds general information about a specific screen.
 *
 * @author Aleman778
 * @since 1.0
 */
public interface Screen {

    /**
     * Returns the width, in screen coordinates of the screen.
     *
     * @return the width
     */
    int getWidth();

    /**
     * Returns the height, in screen coordinates of the screen.
     *
     * @return the height
     */
    int getHeight();

    /**
     * The bit depth of the red channel.
     *
     * @return the bit depth
     */
    int getRedBits();

    /**
     * The bit depth of the green channel.
     *
     * @return the bit depth
     */
    int getGreenBits();

    /**
     * The bit depth of the blue channel.
     *
     * @return the bit depth
     */
    int getBlueBits();

    /**
     * The screens refresh rate.
     *
     * @return refresh rate
     */
    double getRefreshRate();
}

