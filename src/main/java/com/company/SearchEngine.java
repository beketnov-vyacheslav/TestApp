package com.company;

import java.util.List;

public interface SearchEngine<E> {
	public List<List<Tree.Node<E>>> search() throws RuntimeException;
}
