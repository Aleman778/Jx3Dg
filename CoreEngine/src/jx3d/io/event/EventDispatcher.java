package jx3d.io.event;

import java.util.ArrayList;

/**
 * The event dispatcher is used to dispatch events to event listeners.
 * If there are multiple listeners listening for the same event then the user can set priorities
 * e.g. nodes closer to the camera have higher priority.
 */
public class EventDispatcher {

    /**
     * List of all listeners attached to this event dispatcher that are not specifically
     * prioritized by the user. The priority is based on when the event is added.
     * If there are any prioritized listeners then they will be put in a separate
     * list called {@link #prioritizedListeners}.
     */
    private ArrayList<ListenerEntry> listeners;

    /**
     * The prioritizedListeners list of all listeners are based on the priority given to the listeners.
     * If no listeners are prioritized then this array will remain empty. Non prioritized
     * listeners are stored in a separate list called {@link #listeners}.
     */
    private ArrayList<ListenerEntry> prioritizedListeners;

    /**
     * Flag used to check if the {@link #prioritizedListeners} list is sorted or not.
     */
    private boolean sorted = true;


    /**
     * Constructor. Creates an empty event dispatcher.
     */
    public EventDispatcher() {
        listeners = new ArrayList<>();
        prioritizedListeners = new ArrayList<>();
    }

    /**
     * Dispatch an event to everyone who wants listens to this event.
     * Some listeners may be prioritized so those are handled first.
     * If there are no listeners for this event then nothing will happen.
     * @param event the event to dispatch
     */
    public void dispatch(Event event) {
        if (event.getType() != EventType.None) {

        }
    }

    /**
     * Add a basic listener to the event dispatcher.
     * @param name the name is used to identify this event
     * @param listener the basic listener callback function to call
     */
    public void addListener(String name, GenericListener listener) {
        ListenerEntry entry = new ListenerEntry(name, listener, 0);
        listeners.add(entry);
    }

    /**
     * Add a listener to the event dispatcher with a specific event type to listen for.
     * @param type the event type to listen for
     * @param listener the listener to add
     */
    public void addListener(EventType type, Listener listener) {
        ListenerEntry entry = new ListenerEntry(listener, type, 0, 0);
        listeners.add(entry);
    }

    /**
     * Add a listener to the event dispatcher with some specific event categories to listen for.
     * Event categories are bit flags thus you can listen for e.g. MOUSE_EVENTS | KEYBOARD_EVENTS
     * @param categories the event type to listen for
     * @param listener the listener to add
     */
    public void addListener(int categories, Listener listener) {
        ListenerEntry entry = new ListenerEntry(listener, EventType.None, categories, 0);
        listeners.add(entry);
    }

    public void addListener(ListenerEntry entry) {
        listeners.add(entry);
    }

    public ArrayList<Listener> getListeners(Class<? extends Listener> listener) {
        if (!sorted)
            sortListeners();

        ArrayList<Listener> result = new ArrayList<>();
        for (ListenerEntry le : prioritizedListeners) {
            if (le.listener.getClass() == listener) {
                result.add(le.listener);
            }
        }
        for (ListenerEntry le : listeners) {
            if (le.listener.getClass() == listener) {
                result.add(le.listener);
            }
        }
        return result;
    }

    public ArrayList<GenericListener> getBasicListeners(String name) {
        ArrayList<GenericListener> result = new ArrayList<>();
        for (ListenerEntry le : prioritizedListeners) {
            if (le.name == name) {
                result.add(le.genericListener);
            }
        }
        for (ListenerEntry le : listeners) {
            if (le.name == name) {
                result.add(le.genericListener);
            }
        }
        return result;
    }

    public int getListenerPriority(Listener listener) {
        for (ListenerEntry le : prioritizedListeners) {
            if (le.listener == listener) {
                return le.priority;
            }
        }
        throw new IllegalArgumentException("The given listener has no prioritization.");
    }

    private void sortListeners() {

    }

    /**
     * Listener entry is used to give listeners more information and help the dispatcher system to dispatch them
     * to the correct method.
     */
    class ListenerEntry implements Comparable<ListenerEntry> {

        /**
         * The name of the listener.
         * Only used by for basic listeners.
         */
        public String name;

        /**
         * The actual event listener.
         */
        public Listener listener;

        /**
         * The basic event listener, if such is used.
         */
        public GenericListener genericListener;

        /**
         * The type of event to listen for.
         */
        public EventType type;

        /**
         * The categories of events to listen for.
         */
        public int categories;

        /**
         * The event priority, higher priority events are dispatched before lower priority events are.
         */
        public int priority;

        /**
         * Constructs a listener entry with specified parameters.
         * @param listener the actual listener
         * @param type the type of event to listen for
         * @param categories the category of events to listen for
         * @param priority the event priority
         */
        public ListenerEntry(Listener listener, EventType type, int categories, int priority) {
            this.listener = listener;
            this.type = type;
            this.categories = categories;
            this.priority = priority;
        }

        public ListenerEntry(String name, GenericListener listener, int priority) {
            this.name = name;
            this.genericListener = listener;
            this.priority = priority;
        }

        /**
         * Dispatch an event to the correct listener callback function.
         * @param event the event to dispatch
         */
        public void doDispatch(Event event) {
            if (genericListener != null) {
                genericListener.callback((GenericEvent) event);
            }

            if (event.getType() != EventType.None) {
                if (event.getType() == type || event.belongsTo(categories)) {
                    switch (type) {
                        case MousePressed:
                            ((MouseListener) listener).mousePressed((MouseEvent) event);
                        case MouseReleased:
                            ((MouseListener) listener).mouseReleased((MouseEvent) event);
                        case MouseMoved:
                            ((MouseListener) listener).mouseMoved((MouseEvent) event);
                        case MouseDragged:
                            ((MouseListener) listener).mouseDragged((MouseEvent) event);
                        case MouseEntered:
                            ((MouseListener) listener).mouseEntered((MouseEvent) event);
                        case MouseExited:
                            ((MouseListener) listener).mouseExited((MouseEvent) event);
                    }
                }
            }
        }

        @Override
        public int compareTo(ListenerEntry o) {
            return priority - o.priority;
        }
    }
}
