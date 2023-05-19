package co.edu.uniquindio.p2.empresaenergia.exceptions;

public class ClienteException extends Exception {
	/**
	 * Es el constructor de la clase ClienteException
	 * 
	 * @param msg
	 */
	public ClienteException(String msg) {
		super(msg);
	}

	/**
	 * Es el constructor de la clase ClienteException sin parámetros, tiene de mensaje:
	 * "Hay un error relacionado al cliente"
	 */
	public ClienteException() {
		super("Hay un error relacionado al cliente");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
