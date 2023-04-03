package co.edu.uniquindio.centroimpresion.view.principal;

public enum OpcionPrincipal {
	AGREGAR("Agregar"), IMPRIMIR("Imprimir"), ELIMINAR("Eliminar"), VER("Ver"), ACERCA_DE("Acerca De");

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
