package co.edu.uniquindio.centroimpresion.exceptions;

public class TextIsEmptyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoTexto;

	public TextIsEmptyException(String tipoTexto) {
		super(tipoTexto);
		this.tipoTexto = tipoTexto;

	}

	public String getTipoTexto() {
		return tipoTexto;
	}
}
