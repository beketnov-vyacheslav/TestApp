package com.company;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest {
	public static Object nullObject = null;
	public static List emptyList = new ArrayList();
	public static List<List> emptyListOfList = new ArrayList<>();


	@Test
	public void isEmptyNull() {
		boolean actual = Utils.isEmpty(nullObject);

		Assert.assertTrue(actual);
	}

	@Test
	public void isEmptyList() {
		boolean actual = Utils.isEmptyList(emptyList);

		Assert.assertTrue(actual);
	}

	@Test
	public void isEmptyListOfList() {
		emptyListOfList.add(new ArrayList());

		boolean actual = Utils.isEmptyListOfList(emptyListOfList);

		Assert.assertTrue(actual);
	}

	@Test
	public void isEmptyNotNull() {
		boolean actual = Utils.isEmptyNot(nullObject);

		Assert.assertFalse(actual);
	}

	@Test
	public void isEmptyNotList() {
		boolean actual = Utils.isEmptyNotList(emptyList);

		Assert.assertFalse(actual);
	}

	@Test
	public void isEmptyNotListOfList() {
		emptyListOfList.add(new ArrayList());

		boolean actual = Utils.isEmptyNotListOfList(emptyListOfList);

		Assert.assertFalse(actual);
	}
}
