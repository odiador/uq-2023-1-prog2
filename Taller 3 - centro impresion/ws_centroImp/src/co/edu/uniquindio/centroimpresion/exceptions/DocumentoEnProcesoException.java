package co.edu.uniquindio.centroimpresion.exceptions;

public class DocumentoEnProcesoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DocumentoEnProcesoException() {
		super("El documento ya esta en proceso");
	}
}
