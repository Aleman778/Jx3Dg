package jx3d.core;

import jx3d.util.Disposable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * The layer stack is a stack with two types of layers, regular layers and overlays.
 * The overlays are pushed and popped on the top of the stack as a normal stack (LIFO queue).
 * However normal layers are pushed and popped below the overlays functioning as a separate
 * stack on top of another stack. This is implemented using an linked list.
 */
public class LayerStack implements Disposable, Iterable<Layer> {

    /**
     * Layer stack implemented using {@link LinkedList}.
     */
    private LinkedList<Layer> layers;

    /**
     * Index in array where the next layer stack starts.
     */
    private int layerIndex;

    /**
     * Creates an empty layer stack.
     */
    public LayerStack() {
        layers = new LinkedList<>();
    }

    /**
     * Push a normal layer onto the top of the stack below the overlay stack.
     * @param layer the layer to push
     */
    public void pushLayer(Layer layer) {
        if (layer == null)
            throw new NullPointerException();

        layers.add(layerIndex, layer);
    }

    /**
     * Push an overlay onto the top of the stack.
     * @param layer
     */
    public void pushOverlay(Layer layer) {
        if (layer == null)
            throw new NullPointerException();

        layers.add(0, layer);
        layerIndex++;
    }

    /**
     * Pop a specific layer from the stack.
     * @param layer the layer to pop
     * @return true if the layer was successfully popped from the stack
     */
    public boolean popLayer(Layer layer) {
        return layers.remove(layer);
    }

    /**
     * Remove the top layer on the normal layer stack.
     * Note that overlays are not removed using this method.
     * @see #popOverlay()
     */
    public void popLayer() {
        layers.remove(layerIndex);
    }

    /**
     * Remove the top layer on the overlay layer stack.
     * Note that if there are no overlays and only normal layers
     * then {@link IndexOutOfBoundsException} is thrown.
     * @see #popLayer()
     */
    public void popOverlay() {
        if (layerIndex == 0)
            throw new IndexOutOfBoundsException();

        layers.remove(0);
        layerIndex--;
    }

    /**
     * Default iterator is used by for each loops and is using the {@link #begin()} iterator.
     * The iteration order is first overlays then at last normal layers.
     * @return the begin iterator
     */
    @Override
    public Iterator<Layer> iterator() {
        return begin();
    }

    /**
     * Begin iterator is used to traverse the stack from top to bottom i.e. overlays are
     * iterated through first then at last normal layers.
     * @return the begin iterator
     * @see #end()
     */
    public ListIterator<Layer> begin() {
        return layers.listIterator();
    }

    /**
     * End iterator is used to traverse the stack from bottom to top i.e. normal layers are
     * iterated through first then at last overlays.
     * @return the end iterator
     * @see #begin()
     */
    public ListIterator<Layer> end() {
        return layers.listIterator(layers.size() - 1);
    }

    @Override
    public void dispose() {
        for (Layer layer : layers) {
            layer.dispose();
        }
        layers.clear();
        layerIndex = 0;
    }
}
