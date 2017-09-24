package ru.innopolis;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.innopolis.exception.BadCharacterException;
import ru.innopolis.exception.DubplicationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

/**
 * Обработчик конкретного потока
 */
public class ThreadHandler extends Thread {

	private static final Logger log = LogManager.getLogger(ThreadHandler.class.getName());

	private String resource;
	private byte number;
	Indicator indicator;
	private TreeSet<String> words;
	private boolean stoped;

	public ThreadHandler(String resource, byte i, Indicator indicator, TreeSet<String> treeSetWords) {

		this.number = i;
		this.resource = resource;
		this.indicator = indicator;
		this.words = treeSetWords;
		this.stoped = false;
	}

	@Override
	public void run() {
		log.info("Start thread - " + this.number);

		log.info("Synchronized indicator - " + this.number);
		try {
			Thread.sleep(100);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		File file = new File(this.resource);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {

				String strFormFile;
				while(!indicator.isDuplicate() && !indicator.isBadCharacter() && !this.stoped) {
					strFormFile = in.readLine();
					if (strFormFile == null) {
						this.stoped = true;
					} else {
						//while ((strFormFile = in.readLine()) != null) {
						List<String> wordsFromStr = Utils.getWordsFromString(strFormFile);
						for (String word : wordsFromStr) {
							if (Utils.isBadCharacter(word)) {
								synchronized (indicator) {
									indicator.setBadCharacter(true);
								}
								log.warn("Thread: " + this.number + " The thread will be stopped on this collection: " + words.toString());
								throw new BadCharacterException("Thread: " + this.number + " Error: Stop: This word <" + word + "> does not pass the validity check.");
							} else {

								if (Utils.isContainsWordInCollection(word, words)) {
									synchronized (indicator) {
										indicator.setDuplicate(true);
									}
									throw new DubplicationException("Thread: " + this.number + " This word <" + word + "> already exists in the repository. The thread will be stopped on this collection: " + words.toString());
								} else {
									synchronized (indicator) {
										words.add(word);
									}
									log.info("Thread: " + this.number + ". Added word <" + word + ">. The collection contains the following elements:" + words.toString());
								}
							}
						}
					}
				}
				log.info("Thread: " + this.number + " File was read. The thread on this collection: " + words.toString());
			}
			finally {
				in.close();
			}
		}
		catch (IOException e) {
			log.error("Thread: " + this.number + " Problems with file output" + e.toString());
			throw new RuntimeException(e);
		}
	}
}
