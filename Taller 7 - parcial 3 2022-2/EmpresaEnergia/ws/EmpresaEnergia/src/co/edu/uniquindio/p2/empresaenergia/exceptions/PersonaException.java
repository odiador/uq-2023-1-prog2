package co.edu.uniquindio.p2.empresaenergia.exceptions;

public class PersonaException extends Exception {
	/**
	 * Es el constructor de la clase PersonaException
	 * 
	 * @param msg
	 */
	public PersonaException(String msg) {
		super(msg);
	}

	/**
	 * Es el constructor de la clase PersonaException sin parámetros, tiene de mensaje:
	 * "Hay un error relacionado a la persona"
	 */
	public PersonaException() {
		super("Hay un error relacionado a la persona");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
