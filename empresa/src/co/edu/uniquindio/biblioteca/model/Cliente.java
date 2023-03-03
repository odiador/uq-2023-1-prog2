package co.edu.uniquindio.biblioteca.model;

public class Cliente extends Persona {

	private String numeroTelefono;

	/**
	 * Es el constructor de la clase Cliente
	 * 
	 * @param nombre
	 * @param edad
	 * @param numeroTelefono
	 */
	public Cliente(String nombre, int edad, String numeroTelefono) {
		super(nombre, edad);
		this.numeroTelefono = numeroTelefono;
	}

	/**
	 * Es el constructor de la clase Cliente sin parámetros
	 */
	public Cliente() {
	}

	/**
	 * Obtiene el número de teléfono del cliente
	 * 
	 * @return
	 */
	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	/**
	 * Cambia el número de teléfono del cliente
	 * 
	 * @param numeroTelefono
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	@Override
	public String toString() {
		return "Cliente [numeroTelefono=" + numeroTelefono + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numeroTelefono == null) ? 0 : numeroTelefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (numeroTelefono == null) {
			if (other.numeroTelefono != null)
				return false;
		} else if (!numeroTelefono.equals(other.numeroTelefono))
			return false;
		return true;
	}
}