package jx3d.io.event;

import java.util.HashMap;

public class GenericEvent extends Event {

    protected String name;

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

    public String getSubject() {
        return subject;
    }

    public Object getObject(String argument) {
        return arguments.get(argument);
    }

    public String getString(String argument) {
        return (String) arguments.get(argument);
    }

    public int getInt(String argument) {
        return (Integer) arguments.get(argument);
    }

    public float getFloat(String argument) {
        return (Float) arguments.get(argument);
    }

    public double getDouble(String argument) {
        return (Double) arguments.get(argument);
    }

    @Override
    public EventType getType() {
        return EventType.None;
    }

    @Override
    public int getCategoryFlag() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }
}
