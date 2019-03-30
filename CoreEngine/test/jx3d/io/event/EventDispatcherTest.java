package jx3d.io.event;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventDispatcherTest {

    @Test
    public void testBasicListener() {
        EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.addListener("PrintHello", (Event e) -> {
            System.out.println("PrintHello");
            e.markAsHandled();
        });
        dispatcher.dispatch(null);
    }

    public void testEventDispatcher() {
        fail("Not yet implemented!");
    }

}
