package jx3d.graphics;

/**
 * Image class consists of a two dimensional
 * map of pixels represented in integers.
 *
 * @author Aleman778
 * @since 1.0
 */
public class Image {

    private final int width, height;
    private int[] pixels;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
    }

    public Image(int width, int height, int[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;

        if (width * height != pixels.length)
            throw new IllegalArgumentException("Provided image size does not match the size of the pixel array.");
    }

    public void set(int x, int y, Color color) {
        set(x, y, color.toInteger());
    }

    public void set(int x, int y, int pixel) {
        checkBounds(x, y);
        pixels[x + y * width] = pixel;
    }

    public int get(int x, int y) {
        checkBounds(x, y);
        return pixels[x + y * width];
    }

    public Color getColor(int x, int y) {
        checkBounds(x, y);
        return new Color(pixels[x + y * width]);
    }

    public void fill(int pixel) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = pixel;
        }
    }

    public Image resize(int newWidth, int newHeight) {
        if (width == newWidth && height == newHeight) {
            return this;
        }

        Image newImage = new Image(newWidth, newHeight);
        int[] newPixels = newImage.pixels;
        for (int i = 0; i < newPixels.length; i++) {
            if (i < pixels.length) {
                newPixels[i] = pixels[i];
            }
        }
        return newImage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getSize() {
        return pixels.length;
    }

    private void checkBounds(int x, int y) {
        if (x < 0 || x > width)
            throw new IllegalArgumentException("The value x = " + x + " is out of bounds expects a value from 0 to " + width);
        if (y < 0 || y > height)
            throw new IllegalArgumentException("The value y = " + y + " is out of bounds expects a value from 0 to " + height);
    }
}
