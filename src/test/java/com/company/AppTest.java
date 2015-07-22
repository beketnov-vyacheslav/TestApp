package com.company;

import com.company.Tree.Node;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppTest {
	public static final String WRONG_FILE_PATH = "wrong file path";
	public static final String WORDS_FILE_PATH = "/words.txt";
	public static final String DICT_FILE_PATH = "/dict.txt";


	@Test
	public void wrongPathToWords() throws URISyntaxException {
		checkResourceDictExist();

		Path words = Paths.get(WRONG_FILE_PATH);
		Path dict = Paths.get(getClass().getResource(DICT_FILE_PATH).toURI());

		runApp(words, dict);
	}

	@Test
	public void wrongPathToDictionary() throws URISyntaxException {
		checkResourceWordsExist();

		Path words = Paths.get(getClass().getResource(WORDS_FILE_PATH).toURI());
		Path dict = Paths.get(WRONG_FILE_PATH);

		runApp(words, dict);
	}

	@Test
	public void goodPaths() throws URISyntaxException {
		checkResourceWordsExist();
		checkResourceDictExist();

		Path words = Paths.get(getClass().getResource(WORDS_FILE_PATH).toURI());
		Path dict = Paths.get(getClass().getResource(DICT_FILE_PATH).toURI());

		runApp(words, dict);
	}

	private static void runApp(Path words, Path dict) {
		TestApp app = new TestApp(words, dict);
		SearchEngine<String> searchEngine = createMockSearchEngine();
		app.setSearchEngine(searchEngine);
		app.start();
	}

	private static SearchEngine<String> createMockSearchEngine() {
		List<List<Node<String>>> resultList = new ArrayList<>();
		SearchEngine<String> searchEngine = mock(SearchEngine.class);
		when(searchEngine.search()).thenReturn(resultList);
		return searchEngine;
	}

	private void checkResourceWordsExist() {
		Assert.assertNotNull("Test file missing", getClass().getResource("/words.txt"));
	}

	private void checkResourceDictExist() {
		Assert.assertNotNull("Test file missing", getClass().getResource("/dict.txt"));
	}
}
