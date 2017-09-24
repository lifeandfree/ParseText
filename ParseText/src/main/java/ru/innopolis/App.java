package ru.innopolis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {
	private static final Logger log = LogManager.getLogger(App.class.getName());

	public static void main(String[] args) {

		String []argss = {"1.txt", "2.txt","3.txt","4.txt","5.txt","6.txt"};
		//int argsQ = args.length;
		int argsQ = argss.length;
		if (argsQ > 0) {
			log.info("It's " + argsQ + " resources. I start treatment.");
			List<String> resList = new ArrayList<String>(Arrays.asList(argss));
			MainHandler mainHandler = new MainHandler(resList);
			mainHandler.startHandler();
		} else {
			log.warn("Not one of the resources is specified. The processing is skipped.");
		}

	}


}