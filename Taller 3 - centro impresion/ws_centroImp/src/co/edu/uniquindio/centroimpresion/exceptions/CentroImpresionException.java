package co.edu.uniquindio.centroimpresion.exceptions;

public class CentroImpresionException extends Exception {

	private TipoCentroException tipoException;

	public CentroImpresionException(TipoCentroException tipoException) {
		super(tipoException.getMsg());
		this.tipoException = tipoException;

	}

	public TipoCentroException getTipoException() {
		return tipoException;
	}

}
