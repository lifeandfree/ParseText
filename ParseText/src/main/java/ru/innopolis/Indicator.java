package ru.innopolis;

/**
 * Класс отвечает за отслеживание статуса потоков.
 *
 */
public class Indicator {

	private volatile boolean isDuplicate;
	private volatile boolean isBadCharacter;

	public Indicator(boolean isDuplicate, boolean isBadCharacter) {
		this.isDuplicate = isDuplicate;
		this.isBadCharacter = isBadCharacter;
	}

	public Indicator() {

		this.isDuplicate = false;
		this.isBadCharacter = false;

	}

	public boolean isDuplicate() {
		return isDuplicate;
	}

	public void setDuplicate(boolean duplicate) {
		isDuplicate = duplicate;
	}

	public boolean isBadCharacter() {
		return isBadCharacter;
	}

	public void setBadCharacter(boolean badCharacter) {
		isBadCharacter = badCharacter;
	}
}
