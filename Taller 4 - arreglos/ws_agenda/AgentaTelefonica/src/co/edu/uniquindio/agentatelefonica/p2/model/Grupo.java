package co.edu.uniquindio.agentatelefonica.p2.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Grupo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Categoria categoria;
	private Contacto[] listaContactos;

	/**
	 * Es el constructor del grupo con su nombre y categoria
	 * 
	 * @param nombre
	 * @param categoria
	 */
	public Grupo(String nombre, Categoria categoria) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.listaContactos = new Contacto[10];
	}

	/**
	 * Es el constructor del grupo vacio
	 */
	public Grupo() {

	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the categoria
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the listaContactos
	 */
	public Contacto[] getListaContactos() {
		return listaContactos;
	}

	/**
	 * @param listaContactos the listaContactos to set
	 */
	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Grupo [nombre=" + nombre + ", categoria=" + categoria + ", listaContactos="
				+ Arrays.toString(listaContactos) + "]";
	}

}
