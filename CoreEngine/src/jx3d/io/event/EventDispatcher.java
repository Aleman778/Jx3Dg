package jx3d.io.event;

import java.util.ArrayList;
import java.util.List;

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
     * list called {@link #sorted}.
     */
    private ArrayList<ListenerEntry> listeners;

    /**
     * The priority of each listener according to the original listener array list.
     */
    private ArrayList<Integer> priority;

    /**
     * The sorted list of all listeners are based on the priority given to the listeners.
     * If no listeners are prioritized then this array will remain empty. Non prioritized
     * listeners are stored in a separate list called {@link #listeners}.
     */
    private ArrayList<ListenerEntry> sorted;

    /**
     * Constructor. Creates an empty event dispatcher.
     */
    public EventDispatcher() {
        listeners = new ArrayList<>();
        priority = new ArrayList<>();
        sorted = new ArrayList<>();
    }

    public void dispatch(Event event) {

    }

    public void addListener(String name, BasicListener listener) {
        ListenerEntry entry = new ListenerEntry(name, listener, 0);
        listeners.add(entry);
    }

    public void addListener(EventType type, Listener listener) {

    }

    public void addListener(int categories, Listener listener) {

    }

    public void addListener(ListenerEntry entry) {


    }

    public ArrayList<Listener> getListeners(Class<? extends Listener> listener) {
        ArrayList<Listener> result = new ArrayList<>();
        for (ListenerEntry le : listeners) {
            if (le.listener.getClass() == listener) {
                result.add(le.listener);
            }
        }
        return result;
    }

    public ArrayList<Listener> getListeners(String name) {
        ArrayList<Listener> result = new ArrayList<>();
        return result;
    }

    public int getListenerPriority(Event event) {
        return 0;
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
        public BasicListener basicListener;

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

        public ListenerEntry(String name, BasicListener listener, int priority) {
            this.name = name;
            this.basicListener = listener;
            this.priority = priority;
        }

        /**
         * Dispatch an event to the correct listener callback function.
         * @param event the event to dispatch
         */
        public void doDispatch(Event event) {
            if (basicListener != null) {
                basicListener.callback(event);
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
