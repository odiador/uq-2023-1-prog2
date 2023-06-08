package co.edu.uniquindio.p2.diplomado.model;

public enum Genero {
	MASCULINO("Masculino"), FEMENINO("Femenino");

	private final String text;

	Genero(String text) {
		this.text = text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	public static String[] textValues() {
		Genero[] values = values();
		String[] textValues = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			textValues[i] = values[i].getText();
		}
		return textValues;
	}

	public static Genero of(String text) {
		Genero[] values = values();
		for (Genero genero : values) {
			if (genero.getText().equals(text))
				return genero;
		}
		return null;
	}
}
