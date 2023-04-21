package co.edu.uniquindio.agentatelefonica.p2.model;

public enum Categoria {
	OFICINA("Oficina"), FIESTA("Fiesta"), AMIGOS("Amigos"), FAMILIA("Familia");

	private String nombre;

	private Categoria(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public static String[] textValues() {
		Categoria[] values = Categoria.values();
		String[] textValues = new String[values.length];
		for (int i = 0; i < values.length; i++)
			textValues[i] = values[i].getNombre();
		return textValues;
	}
}
