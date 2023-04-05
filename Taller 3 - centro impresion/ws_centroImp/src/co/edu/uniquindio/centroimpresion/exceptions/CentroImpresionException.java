package co.edu.uniquindio.centroimpresion.exceptions;

public class CentroImpresionException extends Exception {

	private TipoCentroException tipoException;
	private Class<?> clase;

	public CentroImpresionException(TipoCentroException tipoException, Class<?> clase) {
		super(tipoException.getMsg() + " (" + clase.getSimpleName() + ")");
		this.tipoException = tipoException;
	}

	public Class<?> getClase() {
		return clase;
	}

	public TipoCentroException getTipoException() {
		return tipoException;
	}

}
