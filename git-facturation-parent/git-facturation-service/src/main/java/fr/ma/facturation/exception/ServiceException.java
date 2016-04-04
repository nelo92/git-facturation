/*
 * Cree le 5 avr. 2016.
 * (c) Ag2r - La Mondiale, 2016. Tous droits reserves.
 */
package fr.ma.facturation.exception;

public class ServiceException extends Exception {

	private static final long	serialVersionUID	= -3849616913119541157L;

	public ServiceException() {
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

