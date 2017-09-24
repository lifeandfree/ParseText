package ru.innopolis.exception;

public class HandlerException extends RuntimeException {

	private String errorCode;
	private String errorMessage;

	public HandlerException() {
		super();
	}

	public HandlerException(String errorMessage) {

		super(errorMessage);
	}

	public HandlerException(String errorCode, String errorMessage) {
		super(errorCode + ": " + errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public HandlerException(String errorMessage, Throwable rootCause) {

		super(errorMessage, rootCause);
	}

	/**
	 * @param errorMessage
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public HandlerException(String errorMessage, Throwable cause, boolean enableSuppression,
							boolean writableStackTrace) {
		super(errorMessage, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param cause
	 */
	public HandlerException(Throwable cause) {
		super(cause);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
