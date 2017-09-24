package ru.innopolis.exception;

public class DubplicationException extends HandlerException {

	/**
	 *
	 */
	public DubplicationException() {
	}

	/**
	 * @param message
	 */
	public DubplicationException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DubplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DubplicationException(String message, Throwable cause, boolean enableSuppression,
								   boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public DubplicationException(Throwable cause) {
		super(cause);
	}
}
