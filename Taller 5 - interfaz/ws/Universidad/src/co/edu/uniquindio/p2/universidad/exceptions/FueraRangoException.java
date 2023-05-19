package co.edu.uniquindio.p2.universidad.exceptions;

public class FueraRangoException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public FueraRangoException(String msg) {
		super(msg);
	}

	public FueraRangoException() {
		super("El dato esta fuera de rango");
	}

}
