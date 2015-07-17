package com.company;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Tree {
	private Node root;


	public Tree() {
		root = new Node(null);
	}

	// TODO вообще не пойми что делает
	public void addNode(Node node) {
		Node parent = getParent(node);
		parent.addChild(node);
	}

	public Node getRoot() {
		return root;
	}

	// TODO
	private Node getParent(Node node) {
		return null;
	}

	// TODO
	public List<List<Node>> getPath(Node<String> node) {
		return null;
	}


	public static class Node<E> {
		private Node parent;
		private Set<Node> children;
		private E item;


		public Node(Node parent, E item) {
			this(parent);
			this.item = item;
		}

		public Node(Node parent) {
			this.parent = parent;
		}

		public Node getParent() {
			return parent;
		}

		public Set<Node> getChildren() {
			return children;
		}

		public void setItem(E item) {
			this.item = item;
		}

		public E getItem() {
			return item;
		}

		public void addChild(Node child) {
			if (children == null) {
				children = new LinkedHashSet<>();
			}
			children.add(child);
		}
	}
}
