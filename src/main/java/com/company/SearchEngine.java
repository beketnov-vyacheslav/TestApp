package com.company;

import java.util.List;

public interface SearchEngine {
	public List<List<Tree.Node>> search() throws RuntimeException;
}
