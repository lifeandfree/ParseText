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
	private Indicator indicator;
	private static TreeSet<String> words;
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
		File file = new File(this.resource);
		try {
			BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			try {
				while(!indicator.isDuplicate() && !indicator.isBadCharacter() && !this.stoped) {
					checkOnDuplicateAndBadCh(in);
				}
				printReport();
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

	public void checkOnDuplicateAndBadCh(BufferedReader in) throws IOException {
		String strFormFile;
		strFormFile = in.readLine();
		checkAndHandlerStr(strFormFile);
	}

	public void checkOnDuplicateAndBadCh2(BufferedReader in) throws IOException {

	}

	void checkAndHandlerStr(String strFormFile) {
		if (strFormFile == null) {
			this.stoped = true;
		} else {
			List<String> wordsFromStr = Utils.getWordsFromString(strFormFile);
			for (String word : wordsFromStr) {
				if (Utils.isBadCharacter(word)) {
					setBadCharacterError(word);
				} else {
					if (Utils.isContainsWordInCollection(word, words)) {
						setDuplicateError(word);
					} else {
						if (indicator.isDuplicate() || indicator.isBadCharacter()) {
							break;
						} else {
							addWordInStore(word);
						}
					}
				}
			}
		}
	}

	void printReport() {
		String wordsStr;
		int wordsStrLen;
		synchronized (words) {
			wordsStr = words.toString();
			wordsStrLen = words.size();
		}
		if (this.stoped) {
			log.info("Thread: " + this.number + ". Number of items in the collection - " + wordsStrLen + " . File was read. The thread on this collection: " + wordsStr);
		} else {
			if (indicator.isDuplicate()) {
				log.info("Thread: " + this.number + ". Number of items in the collection - " + wordsStrLen + " . File was not read. Thread finished from dublicated. The thread on this collection: " + wordsStr);
			}
			if (indicator.isBadCharacter()) {
				log.info("Thread: " + this.number + ". Number of items in the collection - " + wordsStrLen + " . File was not read. Thread finished from bad character. The thread on this collection: " + wordsStr);
			}
		}
	}

	void addWordInStore(String word) {
		String wordsStr;
		int wordsStrLen;
		synchronized (words) {
			words.add(word);
			wordsStr = words.toString();
			wordsStrLen = words.size();
			log.info("Thread: " + this.number + ". Number of items in the collection - " + wordsStrLen + " . Added word <" + word + ">. The collection contains the following elements:" + wordsStr);
		}
	}

	void setBadCharacterError(String word) {
		String wordsStr;
		int wordsStrLen;
		indicator.setBadCharacter(true);
		synchronized (words) {
			wordsStr = words.toString();
			wordsStrLen = words.size();
			log.warn("Thread: " + this.number + ". Number of items in the collection - " + wordsStrLen + " . The thread will be stopped on this collection: " + wordsStr);
			throw new BadCharacterException("Thread: " + this.number + ". Number of items in the collection - " + wordsStrLen + " . Error: Stop: This word <" + word + "> does not pass the validity check.");
		}

	}

	void setDuplicateError(String word) {
		String wordsStr;
		int wordsStrLen;
		indicator.setDuplicate(true);
		synchronized (words) {
			wordsStr = words.toString();
			wordsStrLen = words.size();
			throw new DubplicationException("Thread: " + this.number + ". Number of items in the collection - " + wordsStrLen + " . This word <" + word + "> already exists in the repository.  The thread will be stopped on this collection: " + wordsStr);
		}
	}
}
