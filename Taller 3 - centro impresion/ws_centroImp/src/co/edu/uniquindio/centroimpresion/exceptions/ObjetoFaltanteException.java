package co.edu.uniquindio.centroimpresion.exceptions;

public class ObjetoFaltanteException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoTexto;

	public ObjetoFaltanteException(String tipoTexto) {
		super("El dato " + tipoTexto + " esta vacio/a");
		this.tipoTexto = tipoTexto;
	}

	public ObjetoFaltanteException(String msg, String tipoTexto) {
		super(msg);
		this.tipoTexto = tipoTexto;

	}

	public String getTipoTexto() {
		return tipoTexto;
	}
}
