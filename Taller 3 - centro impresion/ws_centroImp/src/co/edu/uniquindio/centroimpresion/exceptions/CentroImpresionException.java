package co.edu.uniquindio.centroimpresion.exceptions;

public class CentroImpresionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TipoCentroException tipoException;
	private Object source;

	public CentroImpresionException(TipoCentroException tipoException, Object source) {
		super(tipoException.getMsg() + " (" + source.getClass().getSimpleName() + ")");
		this.source = source;
	}

	public TipoCentroException getTipoException() {
		return tipoException;
	}

	public Object getSource() {
		return source;
	}

}
