package co.edu.uniquindio.centroimpresion.exceptions;

public class CentroImpresionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object source;

	public CentroImpresionException(String msg, Object source) {
		super(msg);
		this.source = source;
	}

	public Object getSource() {
		return source;
	}

}
