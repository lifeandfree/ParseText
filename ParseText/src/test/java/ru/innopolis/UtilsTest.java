package ru.innopolis;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

	public static final String ALPHABET_STR = "абвгдежзийклмнопрстуфхцчшщэюя0123456789";

	@Test
	void isBadCharacter() {
		assertTrue(Utils.isBadCharacter("аапаgвв"));
		assertTrue(!Utils.isBadCharacter(getRandomWord(10, ALPHABET_STR)));

	}

	@Test
	void isContainsWordInCollection() {
		String checkWord = "радость";
		TreeSet<String> treeSet = new TreeSet<>();
		treeSet.add(getRandomWord(10, ALPHABET_STR));
		treeSet.add(getRandomWord(10, ALPHABET_STR));
		treeSet.add(getRandomWord(10, ALPHABET_STR));
		treeSet.add(getRandomWord(10, ALPHABET_STR));
		assertTrue(!Utils.isContainsWordInCollection(checkWord, treeSet));
		treeSet.add(checkWord);
		assertTrue(Utils.isContainsWordInCollection(checkWord, treeSet));
	}

	@Test
	void getWordsFromString() {
		List<String> words = Utils.getWordsFromString("word1 word1m.word1 www2s:gggg;tttt45 hyrhrt@");
		assertTrue((words.size() == 7));
	}

	public static String getRandomWord(int length, String alphabet) {
		SecureRandom RND = new SecureRandom();
		StringBuilder sb = new StringBuilder(Math.max(length, 16));
		for (int i = 0; i < length; i++) {
			int len = alphabet.length();
			int random = RND.nextInt(len);
			char c = alphabet.charAt(random);
			sb.append(c);
		}
		return sb.toString();
	}
}