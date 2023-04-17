package co.edu.uniquindio.centroimpresion.model;

public enum OpcionObjeto {

	DOCUMENTO("Documento"), DOCUMENTO_ESPEFICO("Documento Especifico"), IMPRESORA_LASER("Impresora Laser"),
	IMPRESORA_CARTUCHO("Impresora Cartucho"), IMPRESORA("Impresora");

	private String text;

	/**
	 * Es el constructor de las opciones del documento
	 * 
	 * @param text
	 */
	private OpcionObjeto(String text) {
		this.text = text;
	}

	/**
	 * Obtiene el texto del tipo
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * Obtiene el valor del tipo a partir de su texto, retorna un null si no se
	 * encuentra
	 * 
	 * @param text
	 * @return
	 */
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
