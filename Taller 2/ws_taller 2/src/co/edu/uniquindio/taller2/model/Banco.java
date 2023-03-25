package co.edu.uniquindio.taller2.model;

public class Banco {

	protected String nombre;
	protected String direccion;
	protected String codigo;

	public Banco(String nombre, String direccion, String codigo) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.codigo = codigo;
	}

	public Banco() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
