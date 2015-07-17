package com.company;

import java.util.List;

public interface SearchEngine<E> {
	public List<E> search() throws RuntimeException;
}
