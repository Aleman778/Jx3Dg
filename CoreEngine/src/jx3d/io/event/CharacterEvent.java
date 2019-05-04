package jx3d.io.event;

import jx3d.core.Module;

/**
 * Character event is a container class that contains information about the character that was typed on the keyboard.
 * This event is only used by one event type {@link EventType#Character}.
 */
public class CharacterEvent extends Event {

    /**
     * The name of the event.
     */
    private String name;

    /**
     * The character represented in unicode.
     */
    private int unicode;

    /**
     * Create a new character event with specific unicode character.
     * @param unicode the character
     */
    public CharacterEvent(String name, int unicode) {
        super();
        this.unicode = unicode;
    }

    /**
     * Get the unicode character.
     * @return the character in unicode
     */
    public int getUnicode() {
        return unicode;
    }

    @Override
    public EventType getType() {
        return EventType.Character;
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
