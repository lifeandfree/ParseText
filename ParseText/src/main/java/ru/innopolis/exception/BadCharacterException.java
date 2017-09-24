package ru.innopolis.exception;

public class BadCharacterException extends HandlerException {

	/**
	 *
	 */
	public BadCharacterException() {
	}

	/**
	 * @param message
	 */
	public BadCharacterException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BadCharacterException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BadCharacterException(String message, Throwable cause, boolean enableSuppression,
								 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public BadCharacterException(Throwable cause) {
		super(cause);
	}
}
