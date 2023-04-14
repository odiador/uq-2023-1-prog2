package co.edu.uniquindio.centroimpresion.exceptions;

public class NoSePuedeLeerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSePuedeLeerException() {
		super("El archivo no se puede leer");
	}
}
