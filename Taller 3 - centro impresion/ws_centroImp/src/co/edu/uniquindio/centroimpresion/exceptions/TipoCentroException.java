package co.edu.uniquindio.centroimpresion.exceptions;

public enum TipoCentroException {
	ADD("No se pudo agregar"), REMOVE("No se pudo eliminar"), UPDATE("No se pudo actualizar"), NULL("Es null");
	private String msg;

	private TipoCentroException(String msg) {
		this.msg = msg;

	}

	public String getMsg() {
		return msg;
	}
}
