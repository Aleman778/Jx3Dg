package jx3d.io.event;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventDispatcherTest {

    @Test
    public void testBasicListener() {
        EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.addListener("Print", (GenericEvent e) -> {
            System.out.println(e.getSubject());
            assertEquals(e.getName(), "Print");
            assertEquals(e.getSubject(), "Hello World");
            e.markAsHandled();
        });
        dispatcher.dispatch(new GenericEvent("Print", "Hello World"));
    }

    @Test
    public void testEventDispatcher() {
        fail("Not yet implemented!");
    }

}
