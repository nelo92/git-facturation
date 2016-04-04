/*
 * Cree le 11 févr. 2016.
 * (c) Ag2r - La Mondiale, 2016. Tous droits reserves.
 */
package fr.ma.facturation.exception;

public class CalculFactureException extends Exception {

	private static final long	serialVersionUID	= -8179242789292824233L;

	public CalculFactureException() {
	}

	public CalculFactureException(String message) {
		super(message);
	}

	public CalculFactureException(Throwable cause) {
		super(cause);
	}

	public CalculFactureException(String message, Throwable cause) {
		super(message, cause);
	}

	public CalculFactureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
