package com.company;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary<E> {
	private Set<E> words;


	public void setWords(List<E> strings) {
		if (Utils.isEmpty(strings)) {
			throw new IllegalArgumentException("Dictionary can't be empty");
		}
		words = new HashSet<>();
		for (E s : strings) {
			words.add(s);
		}
	}

	public boolean contains(E word) {
		return words.contains(word);
	}

	public boolean isEmpty() {
		return words.isEmpty();
	}

	public void remove(E word) {
		words.remove(word);
	}
}
