package com.company;

public class Utilities {

	public static boolean isEmpty(Object o) {
		return !isEmptyNot(o);
	}

	public static boolean isEmptyNot(Object o) {
		return o != null && !"".equals(o);
	}
}
