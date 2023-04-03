package co.edu.uniquindio.centroimpresion.view.principalmenu;

public enum OpcionTipoObjeto {

	DOCUMENTO("Documento"), IMPRESORA("Impresora");

	private String text;

	private OpcionTipoObjeto(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
}
