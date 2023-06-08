package co.edu.uniquindio.p2.diplomado.exceptions;

public class NotPresentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Es el constructor de la clase {@link NotPresentException}
	 * 
	 * @param msg
	 */
	public NotPresentException(String msg) {
		super(msg);
	}
}
