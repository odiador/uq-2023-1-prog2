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
}
