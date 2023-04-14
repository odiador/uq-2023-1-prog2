package co.edu.uniquindio.centroimpresion.exceptions;

public class NoHayCapacidadException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoHayCapacidadException() {
		super("No hay suficiente capacidad en la impresora");
	}
}
