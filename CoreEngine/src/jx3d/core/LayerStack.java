package jx3d.core;

import jx3d.util.Disposable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * The layer stack is a stack with two types of layers, regular layers and overlays.
 * The overlays are pushed and popped on the top of the stack as a normal stack (LIFO queue).
 * However normal layers are pushed and popped below the overlays functioning as a separate
 * stack on top of another stack.
 */
public class LayerStack implements Disposable {

    /**
     *
     */
    private ArrayList<Layer> layers;

    /**
     * The index of
     */
    private int seperatorIndex;

    public LayerStack() {
        layers = new ArrayList<>();
    }

    public void pushLayer(Layer layer) {
        layers.add(layer);
    }

    public void popLayer(Layer layer) {
        layers.remove(layer);
    }

    public void pushOverlay(Layer layer) {
        layers.add(0, layer);
    }

    public void popOverlay(Layer layer) {
        layers.remove(0);
    }

    public ListIterator<Layer> begin() {
        return layers.listIterator();
    }

    public ListIterator<Layer> end() {
        return layers.listIterator(layers.size() - 1);
    }

    @Override
    public void dispose() {
        Iterator<Layer> it = begin();
        while (it.hasNext()) {
            Layer layer = it.next();
            //layer.dispose()
        }
    }
}
