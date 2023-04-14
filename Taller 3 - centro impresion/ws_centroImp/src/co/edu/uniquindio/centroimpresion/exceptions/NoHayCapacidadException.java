package co.edu.uniquindio.centroimpresion.exceptions;

public class NoHayCapacidadException extends Exception {
	public NoHayCapacidadException() {
		super("No hay suficiente capacidad en la impresora");
	}
}
