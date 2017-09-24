package ru.innopolis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	private static final Logger log = LogManager.getLogger(Utils.class.getName());

	public static void main(String[] args) {
		System.out.println(isFileNameCorrect("gfgg5!@#$%^"));
	}

		public static boolean isFileNameCorrect(String name){

		Pattern pattern = Pattern.compile("(.+)?[><\\|\\?*/:\\\\\"](.+)?");
		Matcher matcher = pattern.matcher(name);
		return !matcher.find();

	}
}
