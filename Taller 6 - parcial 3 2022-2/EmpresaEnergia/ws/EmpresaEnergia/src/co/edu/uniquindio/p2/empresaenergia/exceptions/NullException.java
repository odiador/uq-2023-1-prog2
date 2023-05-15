package co.edu.uniquindio.p2.empresaenergia.exceptions;

public class NullException extends Exception {

	/**
	 * Es el constructor de la clase NullException
	 * 
	 * @param msg
	 */
	public NullException(String msg) {
		super(msg);
	}

	/**
	 * Es el constructor de la clase NullException sin parámetros, tiene de
	 * mensaje: "El objeto enviado es null"
	 */
	public NullException() {
		super("El objeto enviado es null");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
