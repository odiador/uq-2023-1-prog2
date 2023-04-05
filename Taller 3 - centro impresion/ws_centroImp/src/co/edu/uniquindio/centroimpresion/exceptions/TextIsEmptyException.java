package co.edu.uniquindio.centroimpresion.exceptions;

public class TextIsEmptyException extends Exception {
	private String tipoTexto;

	public TextIsEmptyException(String tipoTexto) {
		this.tipoTexto = tipoTexto;
	}

	public String getTipoTexto() {
		return tipoTexto;
	}
}
