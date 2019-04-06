package jx3d.io.event;

/**
 * Event adapter is a convenience class that inherits event listener so you can choose to only override the
 * event listener callback functions you need.
 */
public class EventAdapter implements EventListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setup() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyDown(KeyEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyUp(KeyEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(MouseEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(MouseEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(MouseEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseDragged(MouseEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseScrolled(MouseScrollEvent event) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowResized(int width, int height) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowMoved(int x, int y) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowFocus(boolean focused) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowIconify(boolean iconified) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowMaximize(boolean maximized) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void windowClosed() {
    }
}
