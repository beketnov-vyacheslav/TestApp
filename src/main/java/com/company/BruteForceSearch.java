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

		Tree.Node<String> root = new Tree.Node<>();
		root.setData(wordsPair[0]);

		Tree<String> tree = new Tree<>();
		tree.setRootElement(root);

		String word = root.getItem();
		dictionary.remove(word);

		Node<String> currentNode = root;
		List<Node<String>> nodes = new ArrayList<>();

		while (!word.equals(wordsPair[1]) && currentNode != null) {
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
				if (!c.equals(wordsPair[1])) {
					dictionary.remove(c);
				}
			}

			// формируем очередной уровень дерева
			for (String c : words) {
				Node<String> n = new Node<>(c);
				currentNode.addChild(n);

				if (c.equals(wordsPair[1])) {
					nodes.add(n);
				}
			}

			currentNode = tree.getNextElementByLevel(currentNode);

			if (currentNode != null) {
				word = currentNode.getItem();
			} else if (nodes.isEmpty()) {
				return null;
			}
		}

		return tree.getPaths(nodes);
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
				if (Utils.isEmptyNot(s)) {
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
		if (Utils.isEmpty(words) || words.length != 2) {
			return false;
		}
		if (Utils.isEmpty(words[0]) || Utils.isEmpty(words[1])) {
			return false;
		}
		return true;
	}
}
