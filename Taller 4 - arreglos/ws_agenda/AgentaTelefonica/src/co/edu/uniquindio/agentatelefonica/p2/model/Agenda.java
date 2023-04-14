package co.edu.uniquindio.agentatelefonica.p2.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Agenda implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private Contacto[] listaContactos;
	private Reunion[] listaReuniones;
	private Grupo[] listaGrupos;

	public Agenda(String nombre, Contacto[] listaContactos, Reunion[] listaReuniones, Grupo[] listaGrupos) {
		super();
		this.nombre = nombre;
		this.listaContactos = listaContactos;
		this.listaReuniones = listaReuniones;
		this.listaGrupos = listaGrupos;
	}

	public Agenda() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Contacto[] getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(Contacto[] listaContactos) {
		this.listaContactos = listaContactos;
	}

	public Reunion[] getListaReuniones() {
		return listaReuniones;
	}

	public void setListaReuniones(Reunion[] listaReuniones) {
		this.listaReuniones = listaReuniones;
	}

	public Grupo[] getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(Grupo[] listaGrupos) {
		this.listaGrupos = listaGrupos;
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
		Agenda other = (Agenda) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return String.format("Agenda [nombre=%s, listaContactos=%s, listaReuniones=%s, listaGrupos=%s]", nombre,
				Arrays.toString(listaContactos), Arrays.toString(listaReuniones), Arrays.toString(listaGrupos));
	}

}
