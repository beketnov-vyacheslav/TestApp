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
	public List<List<Node>> search() {
		boolean canStart = checkWords(wordsPair);
		canStart &= checkDictionary(dictionary);
		if (!canStart) {
			return null;
		}

		Tree tree = new Tree();
		Node<String> currentNode = new Node<>(tree.getRoot(), wordsPair[0]);
		tree.addNode(currentNode);

		while (!currentNode.getItem().equals(wordsPair[1])) {
			// потенциальные слова
			List<String> words = generateCandidates(wordsPair[0]);

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

			// TODO переход на следующий узел
			// проверим, есть ли узлы на том же уровне, что текущий
		}

		return tree.getPath(currentNode);
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
