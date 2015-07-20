package com.company;

import com.company.Tree.Node;

import java.util.ArrayList;
import java.util.List;

public class BruteForceSearch extends SearchEngineAbstract<String> {
	private Alphabet alphabet;


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
		tree.addNode(currentNode);
		String word = currentNode.getItem();

		while (!word.equals(wordsPair[1])) {
			// потенциальные слова
			List<String> words = generateCandidates(word);

			// отсекаем слова, которых нет в словаре
			for (String c : words) {
				if (!dictionary.contains(c)) {
					words.remove(c);
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
		}

		List<List<Node<String>>> res = new ArrayList<>();
		res.add(tree.getPath(currentNode));
		return res;
	}

	private List<String> generateCandidates(String word) {
		List<String> candidates = new ArrayList<>();
		char[] letters = alphabet.getCharacters();

		for (int i = 0; i < word.length(); ++i) {
			for (char c : letters) {
				String s = changeLetterWithIndex(i, c, word);
				candidates.add(s);
			}
		}

		return candidates;
	}

	private String changeLetterWithIndex(int i, Character c, String word) {
		char[] wordByLetters = new char[word.length()];
		word.getChars(0, word.length() - 1, wordByLetters, 0);
		wordByLetters[i] = c;
		return new String(wordByLetters);
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
