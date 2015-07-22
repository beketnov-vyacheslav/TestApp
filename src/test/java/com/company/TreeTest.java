package com.company;

import com.company.Tree.Node;
import org.junit.Assert;
import org.junit.Test;

public class TreeTest {

	@Test
	public void rootNotNull() {
		Tree<String> tree = new Tree<>();
		Node<String> root = tree.getRootElement();

		Assert.assertNotNull(root);
	}

	@Test
	public void treeCreation() {

	}
}
