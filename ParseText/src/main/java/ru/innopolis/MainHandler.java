package ru.innopolis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;


public class MainHandler {

	private List<String> resources;
	private static final Logger log = LogManager.getLogger(MainHandler.class.getName());
	private List<ThreadHandler> threadHandlers = new ArrayList<>();
	private static TreeSet<String> treeSetWords = new TreeSet<>();

	public MainHandler(List<String> resList) {

		this.resources = resList;

	}

	public void startHandler() {

		Indicator indicator = new Indicator(false, false);
		byte i = 0;
		for (String res: resources
			 ) {

			if ((new FileHandler()).exists(res)){
				log.info("Start Thread for " + res + " resource");
				ThreadHandler threadHandler = new ThreadHandler(res, i, indicator, treeSetWords);
				threadHandler.start();
				threadHandlers.add(threadHandler);
				i++;
			}
			else {
				log.warn("File " + res + " not found. Continue");
			}

		}

	}
}

