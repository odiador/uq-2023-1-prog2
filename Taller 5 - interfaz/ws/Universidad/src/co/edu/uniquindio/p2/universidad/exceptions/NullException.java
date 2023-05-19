package co.edu.uniquindio.p2.universidad.exceptions;

public class NullException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public NullException(String msg) {
		super(msg);
	}

	public NullException() {
		super("Un elemento es null");
	}
}
