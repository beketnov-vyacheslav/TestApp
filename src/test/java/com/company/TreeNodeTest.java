package com.company;

import com.company.Tree.Node;
import org.junit.Assert;
import org.junit.Test;

public class TreeNodeTest {
	private static final String ITEM_ROOT = "root";

	private static final String ITEM_1_LEVEL_1 = "1st item in 1st level";
	private static final String ITEM_2_LEVEL_1 = "2nd item in 1st level";
	private static final String ITEM_3_LEVEL_1 = "3rd item in 1st level";

	private static final String ITEM_1_LEVEL_2 = "1st item in 2nd level";
	private static final String ITEM_2_LEVEL_2 = "2nd item in 2nd level";


	@Test
	public void rootNotNull() {
		Tree<String> tree = new Tree<>();
		Node<String> root = tree.getRootElement();

		Assert.assertNull(root);
	}

	@Test
	public void treeCreation() {
		Tree<String> tree = createTree();
		Node<String> root = tree.getRootElement();

		Assert.assertNotNull(root);
	}

	@Test
	public void nextElementByLevel() {
		Tree<String> tree = createTree();
		createAndAddFewNodes(tree);

		Node<String> root = tree.getRootElement();
		Assert.assertEquals(ITEM_ROOT, root.getItem());

		Node<String> actual = tree.getNextElement(root, false);
		Assert.assertEquals(ITEM_1_LEVEL_1, actual.getItem());

		actual = tree.getNextElement(actual, false);
		Assert.assertEquals(ITEM_2_LEVEL_1, actual.getItem());

		actual = tree.getNextElement(actual, false);
		Assert.assertEquals(ITEM_3_LEVEL_1, actual.getItem());

		actual = tree.getNextElement(actual, false);
		Assert.assertEquals(ITEM_1_LEVEL_2, actual.getItem());

		actual = tree.getNextElement(actual, false);
		Assert.assertEquals(ITEM_2_LEVEL_2, actual.getItem());

		actual = tree.getNextElement(actual, false);
		Assert.assertNull(actual);
	}

	private static void createAndAddFewNodes(Tree<String> tree) {
		Node<String> node11 = new Node<>(ITEM_1_LEVEL_1);
		Node<String> node21 = new Node<>(ITEM_2_LEVEL_1);
		Node<String> node31 = new Node<>(ITEM_3_LEVEL_1);

		Node<String> node12 = new Node<>(ITEM_1_LEVEL_2);
		Node<String> node22 = new Node<>(ITEM_2_LEVEL_2);

		Node<String> root = tree.getRootElement();
		root.addChild(node11);
		root.addChild(node21);
		root.addChild(node31);

		node11.addChild(node12);
		node31.addChild(node22);
	}


	private static Tree<String> createTree() {
		Tree<String> tree = new Tree<>();
		tree.setRootElement(new Node<>(ITEM_ROOT));
		return tree;
	}
}
