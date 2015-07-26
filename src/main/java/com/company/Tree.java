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

	public Node<E> getNextElement(Node<E> node, boolean levelOnly) {
		Node<E> parent = node.getParent();
		if (parent != null) {
			Node<E> rightElement = getRightElementFor(node);
			if (rightElement != null) {
				return rightElement;
			}

			if (!levelOnly) {
				Node<E> nodeBelowFirst = getFirstElement(getFirstElement(parent));
				if (nodeBelowFirst != null) {
					return nodeBelowFirst;
				}

				// поднимаемся наверх
				return getNewPath(node);
			}
		}
		return getFirstElement(node);
	}

	private Node<E> getNewPath(Node<E> node) {
		Node<E> previous = null;
		Node<E> current = node.getParent();
		while (current != null) {
			List<Node<E>> children = current.getChildren();
			if (Utils.isEmptyNotList(children) && children.size() > 1) {
				break;
			}
			previous = current;
			current = current.getParent();
		}
		if (previous != null) {
			current = getRightElementFor(previous);
			while (current != null && Utils.isEmptyList(current.getChildren())) {
				current = getRightElementFor(current);
			}
			return getFirstElement(current);
		}
		return current;
	}

	private Node<E> getFirstElement(Node<E> node) {
		if (node != null) {
			List<Node<E>> children = node.getChildren();
			if (children != null && !children.isEmpty()) {
				return children.get(0);
			}
		}
		return null;
	}

	public Node<E> getRightElementFor(Node<E> node) {
		Node<E> parent = node.getParent();
		if (parent != null) {
			List<Node<E>> children = parent.getChildren();
			if (Utils.isEmptyNotList(children)) {
				int indexOfNextElement = children.indexOf(node) + 1;
				if (indexOfNextElement < children.size()) {
					return children.get(indexOfNextElement);
				}
			}
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
			child.parent = this;
		}

		public E getItem() {
			return this.data;
		}

		public void setData(E data) {
			this.data = data;
		}
	}
}

