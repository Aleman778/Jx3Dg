package jx3d.graphics;

import jx3d.util.Disposable;

/**
 * Two dimensional texture is an image that can be mapped onto a
 * two or three dimensional surface. The texture is also sampled
 * using an algorithm that determines how the pixels in the
 * texture is viewed when being resized.
 *
 * @author Aleman778
 * @since 1.0
 */
public abstract class Texture2D implements Disposable {

    /**
     * Mipmapping flag.
     */
    protected boolean mipmapping;

    /**
     * Anisotropic flag.
     */
    protected boolean anisotropic;

    /**
     * Create a new texture with provided settings
     *
     * @param mipmaps enable mipmapping
     * @param anisotropic enable anisotropic filtering
     */
    public Texture2D(boolean mipmaps, boolean anisotropic) {
        this.mipmapping = mipmaps;
        this.anisotropic = anisotropic;
    }

    /**
     * Bind this texture.
     */
    public abstract void bind();

    /**
     * Unbind this texture.
     */
    public abstract void unbind();

    /**
     * Set the source image of the texture.
     *
     * @param image the source image to use
     * @see Texture2D#subImage(Image, int, int, int, int)
     */
    public abstract void image(Image image);

    /**
     * Set the part of a source image of the texture.
     *
     * @param image the sub image to use
     * @param x     the x offset
     * @param y     the y offset
     * @param w     the width of the sub image
     * @param h     the height of the sub image
     */
    public abstract void subImage(Image image, int x, int y, int w, int h);

    /**
     * Add an image as a mipmap at the provided LOD level.
     * <i>Note: </i> in order to use mipmaps please ensure that
     * mipmaps are enabled before.
     *
     * @param image the image of the mipmap
     * @param level the level of detail (LOD)
     */
    public abstract void mipmap(Image image, int level);

    /**
     * Add a part of the image as a mipmap at the provided LOD level.
     *
     * @param image the sub image to use
     * @param level the level of detail (LOD
     * @param x     the x offset
     * @param y     the y offset
     * @param w     the width of the sub image
     * @param h     the height of the sub image
     */
    public abstract void subMipmap(Image image, int level, int x, int y, int w, int h);

    /**
     * Automatically generate mipmaps of the source image.
     */
    public abstract void generateMipmaps();

    /**
     * Set the level-of-detail (LOD) bias
     *
     * @param bias the LOD bias
     */
    public abstract void setLODBias(float bias);

    /**
     * Set the level-of-detail (LOD) range (min / max).
     *
     * @param min the minimum LOD range
     * @param max the maximum LOD range
     */
    public abstract void setLODRange(int min, int max);

    /**
     * Set the number or level-of-detail (LOD) levels.
     *
     * @param levels the maximum number of levels
     */
    public abstract void setNumLODLevels(int levels);

    /**
     * Set the texture minification filter option.
     *
     * @param filter the min filter option
     */
    public abstract void setMinFilter(int filter);

    /**
     * Set the texture magnification filter option.
     *
     * @param filter the mag filter option
     */
    public abstract void setMagFilter(int filter);

    /**
     * Set the maximum amount of anisotropic texture filtering.
     *
     * @param amount the maximum anisotropy
     */
    public abstract void setMaxAnisotropy(float amount);

    /**
     * Set the texture wrapping mode. The different wrapping
     * modes are the following: <br> <code>REPEAT</code>,
     * <code>MIRRORED_REPEAT</code>, <code>CLAMP_TO_EDGE</code>,
     * <code>CLAMP_TO_BORDER</code>, <code>MIRROR_CLAMP_TO_EDGE</code><br>
     * The axis to wrap can be <code>S</code>, <code>T</code> or both
     *
     * @param wrap the wrap mode
     * @param axis the axis to wrap in
     * @see jx3d.core.Module#REPEAT
     * @see jx3d.core.Module#MIRRORED_REPEAT
     * @see jx3d.core.Module#CLAMP_TO_EDGE
     * @see jx3d.core.Module#CLAMP_TO_BORDER
     * @see jx3d.core.Module#MIRROR_CLAMP_TO_EDGE
     */
    public abstract void setWrapMode(int wrap, int axis);

    /**
     * Set the texture sampling option.
     *
     * @param sample one of the sampling algorithm described above
     */
    public abstract void setSample(int sample);

    /**
     * Get the width of the source image.
     *
     * @return the width of the source image
     */
    public abstract int getWidth();

    /**
     * Get the height of the source image.
     *
     * @return the height of the source image
     */
    public abstract int getHeight();
}
