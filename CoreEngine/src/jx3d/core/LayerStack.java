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
     * Layer stack implemented using {@link ArrayList}.
     */
    private ArrayList<Layer> layers;

    /**
     * Index in array where the next layer stack starts.
     */
    private int layerIndex;

    public LayerStack() {
        layers = new ArrayList<>();
    }

    public void pushLayer(Layer layer) {
        if (layer == null)
            throw new NullPointerException();

        layers.add(layerIndex, layer);
    }

    public void pushOverlay(Layer layer) {
        if (layer == null)
            throw new NullPointerException();

        layers.add(0, layer);
        layerIndex++;
    }

    public void popLayer(Layer layer) {
        layers.remove(layer);
    }

    public void popLayer() {
        layers.remove(layerIndex);
    }

    public void popOverlay() {
        if (layerIndex == 0)
            throw new IndexOutOfBoundsException();

        layers.remove(0);
        layerIndex--;
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
