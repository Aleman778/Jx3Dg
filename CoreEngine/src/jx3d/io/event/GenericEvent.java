package jx3d.io.event;

import java.util.HashMap;

/**
 * Generic event is a simple event that can be used to send information to another
 * {@link jx3d.core.Layer} or {@link jx3d.core.Node}. This event has a name, subject and multiple arguments.
 * Arguments are stored as key value pairs, values are stored as type {@link Object} and this class provides
 * some methods of casting to other types e.g. {@link #getInt(String)} returns argument as an integer.
 */
public class GenericEvent extends Event {

    /**
     * The name of the event identifies the type of event this is. The event dispatcher uses
     * the event name to find a listener that has the same name.
     */
    protected String name;

    /**
     *
     */
    protected String subject;

    protected HashMap<String, Object> arguments;

    public GenericEvent(String name, String subject) {
        this(name, subject, null);
    }

    public GenericEvent(String name, String subject, HashMap<String, Object> arguments) {
        this.name = name;
        this.subject = subject;
        this.arguments = arguments;
    }

    public final String getSubject() {
        return subject;
    }

    public final Object getObject(String argument) {
        return arguments.get(argument);
    }

    public final String getString(String argument) {
        return (String) arguments.get(argument);
    }

    public final int getInt(String argument) {
        return (Integer) arguments.get(argument);
    }

    public final float getFloat(String argument) {
        return (Float) arguments.get(argument);
    }

    public final double getDouble(String argument) {
        return (Double) arguments.get(argument);
    }

    @Override
    public final EventType getType() {
        return EventType.None;
    }

    @Override
    public final int getCategoryFlag() {
        return 0;
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder argString = new StringBuilder();
        if (arguments != null) {
            for (String key : arguments.keySet()) {
                argString.append("," + key + "=" + arguments.get(key));
            }
        }
        return "Generic" + super.toString() + " {subject=" + subject + argString.toString() + "}";
    }
}
