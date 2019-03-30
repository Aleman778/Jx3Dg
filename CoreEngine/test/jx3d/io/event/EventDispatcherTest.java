package jx3d.io.event;

import jx3d.core.Module;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

import static jx3d.io.event.EventType.*;

public class EventDispatcherTest {

    @Test
    public void testGenericListeners() {
        EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.addListener("Print", (GenericEvent e) -> {
            System.out.println(e);
            assertEquals("GenericEvent name", e.getName(), "Print");
            assertEquals("GenericEvent subject", e.getSubject(), "Hello World");
            assertEquals("GenericEvent argument meaningOfLife", e.getInt("meaningOfLife"), 42);
            assertEquals("GenericEvent argument lorem", e.getString("lorem"), "ipsum");
            assertEquals("GenericEvent argument floatPI", e.getFloat("floatPI"), Module.PI, Module.EPSILON);
            assertEquals("GenericEvent argument doublePI", e.getDouble("doublePI"), Math.PI, Module.EPSILON);
            e.markAsHandled();
        });

        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("meaningOfLife", 42);
        arguments.put("lorem", "ipsum");
        arguments.put("floatPI", Module.PI);
        arguments.put("doublePI", Math.PI);
        GenericEvent event = new GenericEvent("Print", "Hello World", arguments);

        dispatcher.dispatch(event);
        assertTrue(event.isHandled());
    }

    @Test
    public void testTypeListeners() {
        EventDispatcher dispatcher = new EventDispatcher();
        TestListener listener = new TestListener();
        dispatcher.addListener(MousePressed, listener);
        dispatcher.addListener(MouseReleased, listener);
        dispatcher.addListener(MouseEntered, listener);
        dispatcher.addListener(MouseExited, listener);
        dispatcher.addListener(MouseMoved, listener);
        dispatcher.addListener(MouseDragged, listener);

        MouseEvent event = generateMouseEvent(MousePressed);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());

        event = generateMouseEvent(MouseReleased);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());

        event = generateMouseEvent(MouseEntered);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());

        event = generateMouseEvent(MouseExited);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());

        event = generateMouseEvent(MouseMoved);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());

        event = generateMouseEvent(MouseDragged);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());

    }

    @Test
    public void testCategoricalListeners() {
        EventDispatcher dispatcher = new EventDispatcher();
        TestListener listener = new TestListener();
        dispatcher.addListener(Module.MOUSE_EVENTS, listener);

        MouseEvent event = generateMouseEvent(MousePressed);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());

        event = generateMouseEvent(MouseReleased);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());

        event = generateMouseEvent(MouseEntered);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());

        event = generateMouseEvent(MouseExited);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());

        event = generateMouseEvent(MouseMoved);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());

        event = generateMouseEvent(MouseDragged);
        dispatcher.dispatch(event);
        assertTrue(event.isHandled());
    }

    @Test
    public void testPrioritizedListeners() {
        fail("Not implemented yet!");
    }

    @Test
    public void testEventPropogation() {
        fail("Not implemented yet!");
    }

    public MouseEvent generateMouseEvent(EventType type) {
        return new MouseEvent(type, "gen", 0, 0, 0, 0, 0, 0);
    }

    class TestListener extends EventAdapter {

        @Override
        public void mousePressed(MouseEvent event) {
            System.out.println(event);
            assertEquals(event.getType(), MousePressed);
            assertFalse(event.isHandled());
            event.markAsHandled();
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            System.out.println(event);
            assertEquals(event.getType(), MouseReleased);
            assertFalse(event.isHandled());
            event.markAsHandled();
        }

        @Override
        public void mouseEntered(MouseEvent event) {
            System.out.println(event);
            assertEquals(event.getType(), MouseEntered);
            assertFalse(event.isHandled());
            event.markAsHandled();
        }

        @Override
        public void mouseExited(MouseEvent event) {
            System.out.println(event);
            assertEquals(event.getType(), MouseExited);
            assertFalse(event.isHandled());
            event.markAsHandled();
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            System.out.println(event);
            assertEquals(event.getType(), MouseMoved);
            assertFalse(event.isHandled());
            event.markAsHandled();
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            System.out.println(event);
            assertEquals(event.getType(), MouseDragged);
            assertFalse(event.isHandled());
            event.markAsHandled();
        }
    }
}
