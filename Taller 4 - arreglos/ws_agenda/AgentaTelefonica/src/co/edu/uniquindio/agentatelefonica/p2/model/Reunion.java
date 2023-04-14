package co.edu.uniquindio.agentatelefonica.p2.model;

import java.util.Arrays;
import java.util.Objects;

public class Reunion {
	private String nombre;
	private Contacto[] listaContactos;

	public Reunion(String nombre, Contacto[] listaContactos) {
		super();
		this.nombre = nombre;
		this.listaContactos = listaContactos;
	}

	public Reunion() {
		super();
	}

	@Override
	public String toString() {
		return "Reunion [nombre=" + nombre + ", listaContactos=" + Arrays.toString(listaContactos) + "]";
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
