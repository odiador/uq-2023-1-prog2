package co.edu.uniquindio.p2.agentatelefonica.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Reunion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Contacto[] listaContactos;
	private String descripcion;
	private LocalDateTime fechaHora;

	/**
	 * Es el constructor de la clase reunion
	 * 
	 * @param nombre
	 * @param listaContactos
	 */
	public Reunion(String nombre, String descripcion, LocalDateTime fechaHora, Contacto[] listaContactos) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaHora = fechaHora;
		this.listaContactos = listaContactos;
	}

	/**
	 * Es el constructor de la clase reunion sin atributos
	 */
	public Reunion() {
		super();
	}

	@Override
	public String toString() {
		return "Reunion [nombre=" + nombre + ", listaContactos=" + Arrays.toString(listaContactos) + "]";
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
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
		Reunion other = (Reunion) obj;
		return Objects.equals(nombre, other.nombre);
	}
}
