package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestApp {
	public static final Charset DEFAULT_CHARSET = Charset.defaultCharset();

	private Path taskPath;
	private Path dictPath;
	private String result = "";


	public TestApp(Path taskPath, Path dictPath) {
		this.taskPath = taskPath;
		this.dictPath = dictPath;
	}

	public void start() {
		String[] words = getWords();
		if (!words[0].isEmpty() && !words[1].isEmpty()) {
			findPaths(words[0], words[1]);
			showResult();
		}
	}

	private String[] getWords() {
		String[] pair = new String[2];
		try {
			try (BufferedReader reader = Files.newBufferedReader(taskPath, DEFAULT_CHARSET)) {
				String line;
				int counter = 0;
				while ((line = reader.readLine()) != null) {
					pair[counter] = line;
					++counter;
				}
			}
		} catch (IOException e) {
			System.err.println("Can't read out the file: " + taskPath);
			// TODO LOGGER
			// e.printStackTrace();
		}
		return pair;
	}

	private void findPaths(String from, String to) {

	}

	private void showResult() {
		if (result.isEmpty()) {
			System.out.println("Can't find a path for words using this dictionary");
		} else {
			System.out.println("Chain is" + result);
		}
	}
}
