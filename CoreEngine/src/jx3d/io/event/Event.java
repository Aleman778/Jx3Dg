package jx3d.io.event;

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


    /**
     * Check if the event has been handled by an event listener.
     * @return true if an event listener has handled the event already, false otherwise
     */
    public final boolean isHandled() {
        return handled;
    }

    /**
     * Marks this event as handled. Events marked as handled should not be propagated any further
     * in the layer stack.
     */
    public final void markAsHandled() {
        handled = true;
    }

    @Override
    public String toString() {
        return "Event(" + getType() + "): " + getName() + " (handled=" + handled + ")";
    }
}
