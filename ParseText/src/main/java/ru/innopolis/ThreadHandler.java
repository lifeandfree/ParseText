package ru.innopolis;


/**
 * Обработчик конкретного потока
 */
public class ThreadHandler extends Thread {

	private String resource;
	private byte number;
	Indicator indicator;


	public ThreadHandler(String resource, byte i) {
		this.number = i;
		this.resource = resource;

	}

	@Override
	public void run() {
		super.run();



	}


}
