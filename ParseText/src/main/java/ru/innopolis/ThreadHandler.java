package ru.innopolis;


/**
 * Обработчик конкретного потока
 */
public class ThreadHandler extends Thread {

	private String resource;
	private byte number;

	public ThreadHandler(String resource, byte i) {
		this.number = i;
		this.resource = resource;

	}

	@Override
	public void run() {
		super.run();
	}


}
