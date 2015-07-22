package com.company;

import java.util.ArrayList;
import java.util.List;


public class Tree<E> {
	private Node<E> rootElement;


	public Node<E> getRootElement() {
		return this.rootElement;
	}

	public void setRootElement(Node<E> rootElement) {
		this.rootElement = rootElement;
	}

	public List<List<Node<E>>> getPaths(List<Node<E>> nodes) {
		List<List<Node<E>>> paths = new ArrayList<>();

		for (Node<E> n : nodes) {
			Node<E> current = n;
			List<Node<E>> path = new ArrayList<>();
			path.add(current);
			while (current.getParent() != null) {
				path.add(current.getParent());
				current = current.getParent();
			}
			paths.add(path);
		}
		return paths;
	}

	public Node<E> getNextElement(Node<E> node) {
		Node<E> n = getNextElementByLevel(node);
		if (n != null) {
			return n;
		}

		List<Node<E>> children = node.getChildren();

		if (Utils.isEmptyNotList(children)) {
			return children.get(0);
		}
		return null;
	}

	public Node<E> getNextElementByLevel(Node<E> node) {
		if (node == null) {
			return null;
		}

		Node<E> parent = node.getParent();
		if (parent != null) {
			List<Node<E>> nodes = parent.getChildren();
			int indexOfNextItem = nodes.indexOf(node) + 1;
			if (nodes.size() > indexOfNextItem) {
				return nodes.get(indexOfNextItem);
			}
			// иначе обошли всех потомков уровня
		}

		return null;
	}


	public static class Node<E> {
		private Node<E> parent;
		private E data;
		private List<Node<E>> children;


		public Node() {
			super();
		}

		public Node(E data) {
			this();
			setData(data);
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public Node<E> getParent() {
			return parent;
		}

		public List<Node<E>> getChildren() {
			if (this.children == null) {
				return new ArrayList<>();
			}
			return this.children;
		}

		public void setChildren(List<Node<E>> children) {
			this.children = children;
		}

		public void addChild(Node<E> child) {
			if (children == null) {
				children = new ArrayList<>();
			}
			children.add(child);
		}

		public E getItem() {
			return this.data;
		}

		public void setData(E data) {
			this.data = data;
		}
	}
}

