package jx3d.io.events;

/**
 * Abstract event class is a basis for every event in the event system.
 */
public abstract class Event {

    /**
     * Flag used to check if event already has been handled by other handler.
     */
    private boolean handled = false;

    /**
     * Get the type of the event.
     * @return the event type
     */
    public abstract EventType getType();

    /**
     * Get the name of the event.
     * @return the  event name
     */
    public abstract String getName();

    /**
     * Get the event category flag e.g. {@link jx3d.core.Module#MOUSE_EVENTS}.
     * Can be a combination of event categories but an event should only belong
     * to a single category.
     * @return the event category that this event belongs to
     */
    public abstract int getCategoryFlag();

    /**
     * Check if this event belongs to any of the specified categories.
     * @param category the category to check for
     * @return true if the category belongs to any of the specified categories.
     */
    public final boolean belongsTo(int category) {
        return (getCategoryFlag() & category) != 0;
    }

    @Override
    public String toString() {
        return "Event: " + getName() + " (handled=" + handled + ")";
    }

}
