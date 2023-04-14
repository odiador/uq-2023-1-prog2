package co.edu.uniquindio.agentatelefonica.p2.model;

import java.util.Arrays;
import java.util.Objects;

public class Grupo {
	private String nombre;
	private Categoria categoria;
	private Contacto[] listaContactos;

	public Grupo(String nombre, Categoria categoria, Contacto[] listaContactos) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.listaContactos = listaContactos;
	}

	public Grupo(String nombre, Categoria categoria) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.listaContactos = new Contacto[10];
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
