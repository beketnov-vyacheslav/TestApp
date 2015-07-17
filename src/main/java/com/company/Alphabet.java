package com.company;

public class Alphabet {
	private char[] chars;

	public Alphabet() {
		chars = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toUpperCase().toCharArray();
	}

	public char[] getCharacters() {
		return chars;
	}
}
