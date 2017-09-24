package ru.innopolis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	private static final Logger log = LogManager.getLogger(Utils.class.getName());
	public static final String REGEXP_BAD_CHARACTER = "^[А-Яа-я0-9]+$";

	/**
	 * Проверяет на соотвествие текста валидным символам
	 * @param word слово
	 * @return	true/false
	 */
	public static boolean isBadCharacter(String word) {
		Pattern pattern = Pattern.compile(REGEXP_BAD_CHARACTER);
		Matcher matcher = pattern.matcher(word);
		return !matcher.find();
	}

	/**
	 * Проверяет на совпадение элемента с элементами коллекции
	 * @param w слово
	 * @param c перечень элементов
	 * @return	true/false
	 */
	public static boolean isContainsWordInCollection(String w, Collection c) {
		return c.contains(w);
	}

	/**
	 * Разбивает строку на слова
	 * @param str строка для разбияния
	 * @return	Массив слов
	 */
	public static List<String> getWordsFromString(String str) {
		List<String> res = new ArrayList<>();
		StringBuilder word = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isAlphabetic(c) || Character.isDigit(c)) {
				word.append(c);
			}
			else{
				if (word.length() > 0){
					res.add(word.toString());
					word = new StringBuilder();
				}
			}
		}
		return res;
		//return str.split([ .,:;!?()<>\\n\\r\\t]+);
	}
}
