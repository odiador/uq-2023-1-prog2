package co.edu.uniquindio.centroimpresion.model;

public enum OpcionObjeto {

	DOCUMENTO("Documento"), DOCUMENTO_ESPEFICO("Documento Específico"), IMPRESORA_LASER(
			"Impresora Laser"), IMPRESORA_CARTUCHO("Impresora Cartucho"), IMPRESORA("Impresora");

	private String text;

	private OpcionObjeto(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
