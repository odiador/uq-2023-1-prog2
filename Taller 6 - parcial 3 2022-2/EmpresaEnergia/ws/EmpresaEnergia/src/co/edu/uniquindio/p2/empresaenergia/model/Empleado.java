package co.edu.uniquindio.p2.empresaenergia.model;

public class Empleado extends Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String contrasena;

	/**
	 * Es el constructor de la clase empleado
	 * 
	 * @param id
	 * @param nombre
	 * @param contrasena
	 */
	public Empleado(String id, String nombre, String contrasena) {
		super(id, nombre);
		this.contrasena = contrasena;
	}

	@Override
	protected boolean tieneTodoLleno() {
		return id != null && nombre != null && contrasena != null && !id.isEmpty() && !nombre.isEmpty()
				&& !contrasena.isEmpty();
	}

	/**
	 * @return la contrasena del empleado
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @param contrasena cambia la contraseña del empleado
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
