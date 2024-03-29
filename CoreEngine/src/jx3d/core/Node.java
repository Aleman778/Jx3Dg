package jx3d.core;

import jx3d.math.Transform;

import java.util.ArrayList;

/**
 * Node is an abstract class that is used to represent an object in the application.
 * Nodes can be combined to form a tree data structure (specifically a directed acyclic graph).
 * The root in the tree is generally the corresponding display. A node is a part of the application and can perform any
 * number of behaviors such as rendering, ai, physics or scripted.
 *
 * @author Aleman778
 * @since 1.0
 */
public abstract class Node extends Module {

    /**
     * The name of this node.
     */
    private String name;

    /**
     * The parent node, if null then this is the root of the tree.
     */
    private Node parent;

    /**
     * List of the children of this node.
     */
    private ArrayList<Node> children;

    /**
     * The transform object used by this node.
     */
    private Transform transform;

    /**
     * Constructor. Creates an empty node that has no connections.
     *
     * @see Node#add(Node)
     */
    public Node() {
        name = getClass().getSimpleName() + "#" + Integer.toHexString(hashCode());
        parent = null;
        children = new ArrayList<>();
        transform = new Transform();
    }

    /**
     * Add the provided node to this node.
     *
     * @param node the node to add
     */
    public final void add(Node node) {
        if (node.parent != null) {
            throw new IllegalStateException("This node already has a parent");
        }

        children.add(node);
        node.parent = this;
        node.window = window;
    }

    /**
     * Get an array of all the adjacent nodes (or children) to this node.
     *
     * @return an array of adjacent nodes
     */
    public final Node[] children() {
        return children.toArray(new Node[0]);
    }

    /**
     * Get the parent node to this node.
     *
     * @return the parent node
     */
    public final Node parent() {
        return parent;
    }

    /**
     * Check if the provided node is a sibling to this node.
     *
     * @param node the node to check with
     * @return true if the two nodes are siblings, otherwise false
     */
    public final boolean sibling(Node node) {
        return children.contains(node);
    }

    /**
     * Check if this node is a leaf (or has no children).
     *
     * @return true if this node is a leaf, otherwise false
     */
    public final boolean isLeaf() {
        return children.isEmpty();
    }

    /**
     * Check if this node is the root of a tree.
     *
     * @return true if this node is the root, otherwise false
     */
    public final boolean isRoot() {
        return (parent == null);
    }

    /**
     * Set the name of this node.
     *
     * @param name the name to set
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of this node.
     *
     * @return the current name of this node
     */
    public final String getName() {
        return name;
    }

}
