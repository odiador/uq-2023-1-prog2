package co.edu.uniquindio.p2.universidad.exceptions;

public class EstudianteException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public EstudianteException(String msg) {
		super(msg);
	}

	public EstudianteException() {
		super("Hay un error relacionado con el estudiante");
	}
}
