package jx3d.io.event;

import static jx3d.core.Module.*;

/**
 * Input event class is an abstract class contains common information that is shared between all types of input events.
 * This class contains a timestamp and a state of the available input modifiers e.g. shift key, control key etc.
 */
public abstract class InputEvent extends Event {

    /**
     * When the time (in milliseconds) when the input event was created.
     */
    private long when;

    /**
     * The state of the modifiers at the time when this input event was created.
     */
    private int mods;

    /**
     * Constructs an {@link InputEvent} object with specific parameters.
     * @param when
     * @param mods
     */
    public InputEvent(long when, int mods) {
        this.when = when;
        this.mods = mods;
    }

    /**
     * Check if the shift modifier is down on this event.
     * @return true if shift modifier is down
     */
    public boolean isShiftDown() {
        return (mods & MOD_SHIFT) != 0;
    }

    /**
     * Check if the control modifier is down on this event.
     * @return true if control modifier is down
     */
    public boolean isControlDown() {
        return (mods & MOD_CONTROL) != 0;
    }

    /**
     * Check if the alt modifier is down on this event.
     * @return true if alt modifier is down
     */
    public boolean isAltDown() {
        return (mods & MOD_ALT) != 0;
    }

    /**
     * Check if the super modifier is down on this event.
     * @return true if super modifier is down
     */
    public boolean isSuperDown() {
        return (mods & MOD_SUPER) != 0;
    }

    /**
     * Check if the shift modifier is down on this event.
     * @return true if shift modifier is down
     */
    public boolean isCapsLockDown() {
        return (mods & MOD_CAPS_LOCK) != 0;
    }

    /**
     * Check if the shift modifier is down on this event.
     * @return true if shift modifier is down
     */
    public boolean isNumLockDown() {
        return (mods & MOD_NUM_LOCK) != 0;
    }

    /**
     * Check if the shift modifier is down on this event.
     * @return true if shift modifier is down
     */
    public boolean isScrollLockDown() {
        return (mods & MOD_SCROLL_LOCK) != 0;
    }

    /**
     * Get the time (in milliseconds) when the input event was created.
     * @return the time when the input event was created
     */
    public long getWhen() {
        return when;
    }

    /**
     * Get the state of the input modifiers.
     * @return the modifiers
     */
    public int getMods() {
        return mods;
    }
}
