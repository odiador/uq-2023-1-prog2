package co.edu.uniquindio.centroimpresion.exceptions;

public enum TipoCentroException {
	ADD("No se pudo agregar");
	private String msg;

	private TipoCentroException(String msg) {
		this.msg = msg;

	}

	public String getMsg() {
		return msg;
	}
}
