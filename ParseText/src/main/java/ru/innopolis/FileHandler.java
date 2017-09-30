package ru.innopolis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Отвечает за работу с файлами.
 *
 */
public class FileHandler {

	private static final Logger log = LogManager.getLogger(FileHandler.class.getName());


	/**
	 * Проверить существует ли файл.
	 *
	 * @param fileName
	 *            - имя файла.
	 */
	boolean exists(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			log.error("File not found");
			try {
				throw new FileNotFoundException(file.getName());
			}
			catch (FileNotFoundException e) {
				log.error("File not found" + e.toString());
				return false;
			}
		}
		else {
			if (!isFile(file)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Проверить данный объект {@link File} является ли файлом.
	 *
	 * @param file
	 *            {@link File} файл для проверки
	 * @return true/false
	 */
	boolean isFile(File file) {
		if (!file.isFile()) {
			log.error("File is not a file");
			try {
				throw new FileNotFoundException(file.getName());
			}
			catch (FileNotFoundException e) {
				log.error(  "File is not a File" + e.toString());
				return false;
			}
		}
		return true;
	}

}
