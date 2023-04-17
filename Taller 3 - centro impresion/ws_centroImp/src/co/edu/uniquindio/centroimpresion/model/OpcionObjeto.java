package co.edu.uniquindio.centroimpresion.model;

public enum OpcionObjeto {

	DOCUMENTO("Documento"), DOCUMENTO_ESPEFICO("Documento Espec�fico"), IMPRESORA_LASER("Impresora Laser"),
	IMPRESORA_CARTUCHO("Impresora Cartucho"), IMPRESORA("Impresora");

	private String text;

	private OpcionObjeto(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public static OpcionObjeto obtenerOpcion(String text) {
		OpcionObjeto[] values = OpcionObjeto.values();
		for (OpcionObjeto opcionObjeto : values) {
			if (opcionObjeto.getText().equals(text)) {
				return opcionObjeto;
			}
		}
		return null;
	}
}
