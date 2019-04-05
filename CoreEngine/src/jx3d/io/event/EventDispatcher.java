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
        if (event instanceof GenericEvent) {
            for (ListenerEntry le : getListeners(event.getName())) {
                le.doDispatch(event);
            }
            return;
        }
        for (ListenerEntry le : getListeners(event.getType())) {
            le.doDispatch(event);
        }
        for (ListenerEntry le : getListeners(event.getCategoryFlag())) {
            le.doDispatch(event);
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
        if (type == EventType.None) {
            throw new IllegalArgumentException("Cannot add listener that does not listen for any events.");
        }

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
        if (categories == 0) {
            throw new IllegalArgumentException("Cannot add listener that does not listen for any events.");
        }

        ListenerEntry entry = new ListenerEntry(listener, EventType.None, categories, 0);
        listeners.add(entry);
    }

    /**
     * Get all the listeners of a certain class.
     * @param listener the listener class
     * @return a new {@link ArrayList} containing all matched listeners
     */
    public ArrayList<ListenerEntry> getListeners(Class<? extends Listener> listener) {
        if (!sorted)
            sortListeners();

        ArrayList<ListenerEntry> result = new ArrayList<>();
        for (ListenerEntry le : prioritizedListeners) {
            if (le.listener.getClass() == listener) {
                result.add(le);
            }
        }
        for (ListenerEntry le : listeners) {
            if (le.listener.getClass() == listener) {
                result.add(le);
            }
        }
        return result;
    }

    /**
     * Get all the listeners of a certain event type.
     * @param type the event type
     * @return a new {@link ArrayList} containing all matched listeners
     */
    public ArrayList<ListenerEntry> getListeners(EventType type) {
        if (!sorted)
            sortListeners();

        ArrayList<ListenerEntry> result = new ArrayList<>();
        for (ListenerEntry le : prioritizedListeners) {
            if (le.type == type) {
                result.add(le);
            }
        }
        for (ListenerEntry le : listeners) {
            if (le.type == type) {
                result.add(le);
            }
        }
        return result;
    }

    /**
     * Get all the listeners of a certain event type.
     * @param categories the event categories
     * @return a new {@link ArrayList} containing all matched listeners
     */
    public ArrayList<ListenerEntry> getListeners(int categories) {
        if (!sorted)
            sortListeners();

        ArrayList<ListenerEntry> result = new ArrayList<>();
        for (ListenerEntry le : prioritizedListeners) {
            if ((le.categories & categories) > 0) {
                result.add(le);
            }
        }
        for (ListenerEntry le : listeners) {
            if ((le.categories & categories) > 0) {
                result.add(le);
            }
        }
        return result;
    }

    /**
     * Get all the generic listeners with a specific name.
     * @param name the name of the listener and event
     * @return a new {@link ArrayList} containing all matched listeners
     */
    public ArrayList<ListenerEntry> getListeners(String name) {
        ArrayList<ListenerEntry> result = new ArrayList<>();
        for (ListenerEntry le : prioritizedListeners) {
            if (le.name.equals(name)) {
                result.add(le);
            }
        }
        for (ListenerEntry le : listeners) {
            if (le.name.equals(name)) {
                result.add(le);
            }
        }
        return result;
    }

    /**
     * Get the priority of a specific listener.
     * @param listener the listener
     * @return the listener priority value
     */
    public int getListenerPriority(Listener listener) {
        for (ListenerEntry le : prioritizedListeners) {
            if (le.listener == listener) {
                return le.priority;
            }
        }
        throw new IllegalArgumentException("The given listener has no prioritization.");
    }

    private void sortListeners() {
        //TODO: implement this later
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

            System.out.println(event.getType() + ", " + event.belongsTo(categories));
            if (event.getType() == type || event.belongsTo(categories)) {
                switch (event.getType()) {
                    case MousePressed:
                        ((MouseListener) listener).mousePressed((MouseEvent) event);
                        break;
                    case MouseReleased:
                        ((MouseListener) listener).mouseReleased((MouseEvent) event);
                        break;
                    case MouseMoved:
                        ((MouseListener) listener).mouseMoved((MouseEvent) event);
                        break;
                    case MouseDragged:
                        ((MouseListener) listener).mouseDragged((MouseEvent) event);
                        break;
                    case MouseEntered:
                        ((MouseListener) listener).mouseEntered((MouseEvent) event);
                        break;
                    case MouseExited:
                        ((MouseListener) listener).mouseExited((MouseEvent) event);
                        break;
                }
            }
        }

        @Override
        public int compareTo(ListenerEntry o) {
            return priority - o.priority;
        }
    }
}
