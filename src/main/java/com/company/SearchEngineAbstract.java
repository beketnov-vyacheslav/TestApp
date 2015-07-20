package com.company;

public abstract class SearchEngineAbstract<E> implements SearchEngine<E> {
	protected Dictionary<E> dictionary;
	protected E[] wordsPair;


	public void setDictionary(Dictionary<E> dictionary) {
		this.dictionary = dictionary;
	}

	public void setWordsPair(E[] wordsPair) {
		this.wordsPair = wordsPair;
	}
}
