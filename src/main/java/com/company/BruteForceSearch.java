package com.company;

import com.company.Tree.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BruteForceSearch implements SearchEngine<String> {
	private Alphabet alphabet;
	private Dictionary<String> dictionary;
	private String[] wordsPair;


	public BruteForceSearch() {
		alphabet = new Alphabet();
	}

	@Override
	public List<List<Node<String>>> search() {
		boolean canStart = checkWords(wordsPair);
		canStart &= checkDictionary(dictionary);
		if (!canStart) {
			return null;
		}

		Tree<String> tree = new Tree<>();
		Node<String> currentNode = tree.getRoot();
		currentNode.setItem(wordsPair[0]);
		String word = currentNode.getItem();

		dictionary.remove(wordsPair[0]);

		while (!word.equals(wordsPair[1])) {
			// потенциальные слова
			List<String> words = generateCandidates(word);

			// отсекаем слова, которых нет в словаре
			for (Iterator<String> iterator = words.iterator(); iterator.hasNext(); ) {
				String s = iterator.next();
				if (!dictionary.contains(s)) {
					iterator.remove();
				}
			}

			// удаляем полученные слова из словаря (для исключения цикличности поиска)
			for (String c : words) {
				dictionary.remove(c);
			}

			// формируем очередной уровень дерева
			for (String c : words) {
				Node<String> n = new Node<>(currentNode, c);
				currentNode.addChild(n);
			}

			currentNode = currentNode.getNext();
			if (currentNode != null) {
				word = currentNode.getItem();
			} else {
				return null;
			}

			// добавляем последний элемент до выхода из цикла
			if (word.equals(wordsPair[1])) {
				Node<String> n = new Node<>(currentNode, wordsPair[1]);
				currentNode.addChild(n);
			}
		}

		List<List<Node<String>>> res = new ArrayList<>();
		res.add(tree.getPath(currentNode));
		return res;
	}

	@Override
	public void setDictionary(Dictionary<String> dictionary) {
		this.dictionary = dictionary;
	}

	@Override
	public void setWordsPair(String[] wordsPair) {
		this.wordsPair = wordsPair;
	}

	private List<String> generateCandidates(String word) {
		List<String> candidates = new ArrayList<>();
		char[] letters = alphabet.getCharacters();

		for (int i = 0; i < word.length(); ++i) {
			for (char c : letters) {
				String s = changeLetterWithIndex(i, c, word);
				if (Utilities.isEmptyNot(s)) {
					candidates.add(s);
				}
			}
		}

		return candidates;
	}

	private String changeLetterWithIndex(int i, Character c, String word) {
		char[] wordByLetters = new char[word.length()];
		word.getChars(0, word.length(), wordByLetters, 0);
		if (wordByLetters[i] != c) {
			wordByLetters[i] = c;
			return new String(wordByLetters);
		}
		return null;
	}

	private boolean checkDictionary(Dictionary<String> dictionary) {
		return !(dictionary == null || dictionary.isEmpty());
	}

	private boolean checkWords(String[] words) {
		if (Utilities.isEmpty(words) || words.length != 2) {
			return false;
		}
		if (Utilities.isEmpty(words[0]) || Utilities.isEmpty(words[1])) {
			return false;
		}
		return true;
	}
}
