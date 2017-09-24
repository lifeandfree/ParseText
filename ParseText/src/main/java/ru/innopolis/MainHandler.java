package ru.innopolis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;


public class MainHandler {

	private List<String> resources;
	private static final Logger log = LogManager.getLogger(MainHandler.class.getName());
	private Set<ThreadHandler> threadHandlers = new HashSet<ThreadHandler>();

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
				ThreadHandler threadHandler = new ThreadHandler(res, i);
				threadHandler.start();
				threadHandlers.add(threadHandler);
			}
			else {
				log.error("File " + res + " not found. Continue");
			}
			i++;
		}

	}
}

