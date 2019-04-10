package jx3d.io.event;

/**
 * Event listener contains callback functions to all evens that the core engine uses.
 */
public interface EventListener extends Listener, MouseListener, KeyListener, WindowListener {

    /**
     * The setup event is triggered once this object is ready to be used.
     * Use this method for initializing variables and other settings.
     */
    void setup();

    /**
     * The update event is triggered for each time the window requests an update from the running program,
     * this is normally performed via a loop that continuously triggers this event 30 times every second.
     * The rate in which this is executed can be customized.
     */
    void update();

    /**
     * The draw event is triggered for each time the window requests a new frame from the running program,
     * this is normally performed via a loop that continuously requests new frames 30 times every second.
     * The rate at which new frames are requested (frame rate) can be customized.
     */
    void draw();
}
