package com.company;

import java.util.ArrayList;
import java.util.List;

public class Tree<E> {
	private Node<E> root;


	public Tree() {
		root = new Node<>(null);
	}

	public void addNode(Node<E> node) {
		Node<E> parent = getParent(node);
		parent.addChild(node);
	}

	public void addItemToNode(E item, Node<E> node) {
		if (node != null)
			node.setItem(item);
	}

	public Node<E> getRoot() {
		return root;
	}

	private Node<E> getParent(Node<E> node) {
		if (node != null) {
			node.getParent();
		}
		return null;
	}

	public List<Node<E>> getPath(Node<E> node) {
		List<Node<E>> path = new ArrayList<>();
		Node<E> current = node;
		while (current.getParent() != null) {
			path.add(current.getParent());
			current = current.getParent();
		}
		return path;
	}


	public static class Node<E> {
		private Node<E> parent;
		private List<Node<E>> children;
		private E item;


		public Node(Node<E> parent, E item) {
			this(parent);
			this.item = item;
		}

		public Node(Node<E> parent) {
			this.parent = parent;
		}

		public Node<E> getParent() {
			return parent;
		}

		public List<Node<E>> getChildren() {
			return children;
		}

		public void setItem(E item) {
			this.item = item;
		}

		public E getItem() {
			return item;
		}

		public void addChild(Node<E> child) {
			if (children == null) {
				children = new ArrayList<>();
			}
			children.add(child);
		}

		public Node<E> getNext() {
			Node<E> parent = getParent();
			if (parent != null) {
				List<Node<E>> l = parent.getChildren();
				int indexOfNextItem = l.indexOf(this) + 1;
				if (l.size() > indexOfNextItem) {
					return l.get(indexOfNextItem);
				}
				return l.get(0);
			}
			return null;
		}
	}
}
