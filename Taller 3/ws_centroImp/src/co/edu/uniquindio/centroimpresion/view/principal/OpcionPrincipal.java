package co.edu.uniquindio.centroimpresion.view.principal;

public enum OpcionPrincipal {
	AGREGAR_DOCUMENTO("Agregar Documento"), AGREGAR_IMPRESORA("Agregar Impresora"), IMPRIMIR_DOCUMENTO(
			"Imprimir Documento"), IMPRIMIR_DOCUMENTO_ESPECIFICO("Imprimir Documento Específico"), ELIMINAR_DOCUMENTO(
					"Eliminar Documento"), ELIMINAR_IMPRESORA("Eliminar Impresora"), VER_DOCUMENTOS(
							"Ver Documentos"), VER_IMPRESORAS("Ver Impresoras"), ACERCA_DE("Acerca De");

	private String text;

	/**
	 * Es el constructor de la enumeracion {@link OpcionPrincipal}
	 *
	 * @param text
	 */
	OpcionPrincipal(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
