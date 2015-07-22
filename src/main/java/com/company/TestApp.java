package com.company;

import com.company.Tree.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestApp {
	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	private Path taskPath;
	private Path dictPath;
	private SearchEngine<String> searchEngine;
	private List<List<Node<String>>> result;


	public TestApp(Path taskPath, Path dictPath) {
		this.taskPath = taskPath;
		this.dictPath = dictPath;
		searchEngine = new BruteForceSearch();
	}

	public void setSearchEngine(SearchEngine<String> searchEngine) {
		this.searchEngine = searchEngine;
	}

	public void start() {
		String[] words;
		Dictionary<String> dictionary;
		boolean canStart;
		try {
			words = getWords();
			canStart = checkWords(words);

			dictionary = getDict(dictPath);
			canStart &= checkDictionary(dictionary, words);
		} catch (IOException e) {
			System.err.println("Can't read out files with input data");
			return;
		}

		if (canStart) {
			findPaths(words[0], words[1], dictionary);
			showResult();
		}
	}

	private boolean checkWords(String[] words) {
		if (words.length != 2) {
			System.out.println("Необходимо указать 2 слова");
			return false;
		}
		if (Utils.isEmpty(words[0])) {
			System.out.println("Не указано исходное слово");
			return false;
		}
		if (Utils.isEmpty(words[1])) {
			System.out.println("Не указано конечное слово");
			return false;
		}
		return true;
	}

	private boolean checkDictionary(Dictionary<String> dict, String[] words) {
		if (dict == null) {
			return false;
		}
		if (!dict.contains(words[0])) {
			System.out.println("Начальное слово не содержится в словаре");
			return false;
		}
		if (!dict.contains(words[1])) {
			System.out.println("Конечное слово не содержится в словаре");
			return false;
		}
		return true;
	}

	private String[] getWords() throws IOException {
		List<String> words = readLinesFromFile(taskPath);
		return words.toArray(new String[2]);
	}

	private List<String> readLinesFromFile(Path path) throws IOException {
		List<String> lines = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(path, DEFAULT_CHARSET)) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line.trim());
			}
		}
		return lines;
	}

	private void findPaths(String from, String to, Dictionary<String> dictionary) {
		searchEngine.setDictionary(dictionary);
		searchEngine.setWordsPair(new String[]{from, to});
		result = searchEngine.search();
	}

	private Dictionary<String> getDict(Path dictPath) throws IOException {
		Dictionary<String> dictionary = new Dictionary<>();
		dictionary.setWords(readLinesFromFile(dictPath));
		return dictionary;
	}

	private void showResult() {
		if (result == null || result.size() < 1) {
			System.out.println("Не удалось обнаружить путь преобразования с заданным словарем");
		} else {
			for (List list : result) {
				Collections.reverse(list);
			}

			System.out.println("Решение:");
			int length = result.get(0).size();
			for (int i = 0; i < length; ++i) {
				for (List<Node<String>> list : result) {
					System.out.print(list.get(i).getItem() + '\t');
				}
				System.out.print("\r\n");
			}
		}
	}
}
