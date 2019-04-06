package jx3d.io.event;

import jx3d.core.Module;

/**
 * Key event is a container class that contains information about the key that triggered this event.
 * The types of events that uses this class are {@link EventType#KeyDown}, {@link EventType#KeyUp}.
 */
public class KeyEvent extends InputEvent {

    /**
     * The name of the event.
     */
    private String name;

    /**
     * The type of event.
     */
    private EventType type;

    /**
     * The key that triggered the event.
     */
    private int key;

    /**
     * For keys without any token i.e. {@link #getKey()} returns {@link Module#KEY_UNKNOWN} then the
     * scancode can be used as it always returns the same unique number or a given key.
     * However the scancode keys are platform dependent, thus it will not be the same on different devices.
     */
    private int scancode;

    /**
     * The character of the printable key. If the key is not a printable key e.g.
     * {@link Module#KEY_LEFT_SHIFT} then this character is set to 0.
     */
    private char keyChar;

    /**
     * Repeat flag is set to true if the specific {@link #key} has been held down for some time.
     */
    private boolean repeat;

    /**
     * Constructs a {@link KeyEvent} object with specific parameters.
     * @param name the name of the event
     * @param type the type of event
     * @param key the key that triggered this event
     * @param scancode the key scancode, unique for every key, not
     * @param keyChar the character of the printable key
     * @param repeat true if this is a repeated event
     */
    public KeyEvent(String name, EventType type, int key, int scancode, int mods, char keyChar, boolean repeat) {
        super(System.currentTimeMillis(), mods);
        this.name = name;
        this.type = type;
        this.key = key;
        this.scancode = scancode;
        this.keyChar = keyChar;
        this.repeat = repeat;
    }

    /**
     * Get the key that triggered the event.
     * @return the key token e.g. {@link Module#KEY_A}
     */
    public int getKey() {
        return key;
    }

    /**
     * Get the character of the printable key of {@link #getKey()}.
     * However if the key is not printable then character of value 0 is returned.
     * @return the printable key character or 0 if not a printable key
     */
    public char getKeyChar() {
        return keyChar;
    }

    /**
     * Ge the scancode of the key, this is a unique number of each key on the keyboard.
     * <i>Note:</i> the scancode is not platform independent so the scancode value of the same key
     * is not the same on different devices.
     * @return the scancode of this key
     */
    public int getScancode() {
        return scancode;
    }

    /**
     * Check if the mouse event has been repeated this happens
     * when the user holds down the same key for some time.
     * @return true if this is a repeated event, false otherwise
     */
    public boolean isRepeated() {
        return repeat;
    }

    @Override
    public EventType getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCategoryFlag() {
        return Module.KEY_EVENTS;
    }
}
