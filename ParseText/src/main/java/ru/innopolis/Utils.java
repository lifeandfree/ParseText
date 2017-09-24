package ru.innopolis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	private static final Logger log = LogManager.getLogger(Utils.class.getName());
	public static final String REGEXP_BAD_CHARACTER = "^[А-Яа-я0-9]+$";

	public static void main(String[] args) {
		System.out.println(isFileNameCorrect("gfgg5!@#$%^"));
	}

	public static boolean isFileNameCorrect(String name) {
		Pattern pattern = Pattern.compile("(.+)?[><\\|\\?*/:\\\\\"](.+)?");
		Matcher matcher = pattern.matcher(name);
		return !matcher.find();
	}

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
	 * Проверяет на соотвествие текста валидным символам
	 * @param w слово
	 * @param c перечень элементов
	 * @return	true/false
	 */
	public static boolean containsWordInCollection(String w, Collection c) {
		return c.contains(w);
	}



}
