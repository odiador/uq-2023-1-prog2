package co.edu.uniquindio.p2.empresaenergia.model;

public abstract class Cliente extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cliente(String id, String nombre) {
		super(id, nombre);
	}

	public abstract TipoCliente getTipoCliente();

	@Override
	public String toString() {
		return String.format("Cliente [id=%s, nombre=%s]", id, nombre);
	}

}
