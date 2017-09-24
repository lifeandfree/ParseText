package ru.innopolis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 */
public class App {
	private static final Logger log = LogManager.getLogger(App.class.getName());

	public static void main(String[] args) {

		int argsQ = args.length;
		if (argsQ > 0) {
			log.info("It's " + argsQ + " resources. I start treatment.");
			List<String> resList = new ArrayList<String>(Arrays.asList(args));
			MainHandler mainHandler = new MainHandler(resList);
			mainHandler.startHandler();
		} else {
			log.warn("Not one of the resources is specified. The processing is skipped.");
		}

	}


}
