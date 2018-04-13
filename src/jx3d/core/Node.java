package jx3d.core;

import java.util.HashSet;

import jx3d.util.Disposable;

/**
 * Node is an abstract class that is used to represent an object in the application.
 * Nodes can be combined to form a tree (to be specific a DAG or directed acyclic graph).
 * The root in the tree is generally the corresponding display. A node is a part of the application and can perform any
 * number of behaviors such as rendering, ai, physics or scripted.
 * @since 1.0
 * @author Aleman778
 */
public abstract class Node extends Functions implements Disposable {

	private HashSet<Node> children;
	private String name = getClass().getSimpleName();
	private Node parent;
	
	/**
	 * Constructor. Creates an empty node that has no connections.
	 * @see Node#add(Node) 
	 */
	public Node() {
		children = new HashSet<>();
	}
	
	/**
	 * Add the provided node to this node.
	 * @param node
	 */
	public final void add(Node node) {
		children.add(node);
		node.parent = this;
	}

	/**
	 * Get an array of all the adjacent nodes (or children) to this node. 
	 * @return an array of adjacent nodes
	 */
	public final Node[] children() {
		return children.toArray(new Node[children.size()]);
	}
	
	/**
	 * Get the parent node to this node.
	 * @return the parent node
	 */
	public final Node parent() {
		return parent;
	}

	/**
	 * Check if the provided node is a sibling to this node.
	 * @param node the node to check with
	 * @return true if the two nodes are siblings, otherwise false
	 */
	public final boolean sibling(Node node) {
		return children.contains(node);
	}
	
	/**
	 * Check if this node is a leaf (or has no children).
	 * @return true if this node is a leaf, otherwise false
	 */
	public final boolean isLeaf() {
		return children.isEmpty();
	}
	
	/**
	 * Check if this node is the root of a tree.
	 * @return true if this node is the root, otherwise false
	 */
	public final boolean isRoot() {
		return (parent == null);
	}
	
	/**
	 * Set the name of this node.
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the name of this node.
	 * @return the current name of this node
	 */
	public final String getName() {
		return name;
	}
}
