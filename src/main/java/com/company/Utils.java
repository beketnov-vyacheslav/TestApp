package com.company;

import java.util.List;

public class Utils {

	public static boolean isEmpty(Object o) {
		return !isEmptyNot(o);
	}

	public static boolean isEmptyNot(Object o) {
		return o != null && !"".equals(o);
	}

	public static boolean isEmptyListOfList(List<List> list) {
		return !isEmptyNotListOfList(list);
	}

	public static boolean isEmptyList(List list) {
		return !isEmptyNotList(list);
	}

	public static boolean isEmptyNotList(List list) {
		return list != null && !list.isEmpty();
	}

	public static boolean isEmptyNotListOfList(List<List> list) {
		if (list == null) {
			return false;
		}
		for (List l : list) {
			if (isEmptyNotList(l)) {
				return true;
			}
		}
		return false;
	}
}
