package co.edu.uniquindio.p2.diplomado.model;

import java.util.List;
import java.util.stream.DoubleStream;

public class Estudiante {
	private String nombre;
	private Genero genero;
	private String id;
	private List<Float> listaNotas;

	/**
	 * Es el constructor de la clase {@link Estudiante}
	 * 
	 * @param nombre
	 * @param genero
	 * @param id
	 * @param listaNotas
	 */
	public Estudiante(String nombre, Genero genero, String id, List<Float> listaNotas) {
		this.nombre = nombre;
		this.genero = genero;
		this.id = id;
		this.listaNotas = listaNotas;
	}

	public Estudiante(String id) {
		this.id = id;
	}

	/**
	 * Determina si el estudiante tiene los atributos llenos
	 * 
	 * @return
	 */
	public boolean atributosLlenos() {
		return nombre != null && genero != null && id != null && listaNotas != null;
	}

	/**
	 * Determina si el estudiante tiene un id específico
	 * 
	 * @param id
	 * @return
	 */
	public boolean tieneId(String id) {
		return id == null ? (this.id == null ? true : false) : id.equals(this.id);
	}

	public boolean tieneProm(Float prom) {
		return prom == this.getPromedioNotas();
	}

	/**
	 * Obtiene el promedio de notas del estudiante
	 * 
	 * @return
	 */
	public float getPromedioNotas() {
		return new Float(listaNotas.stream().flatMapToDouble(DoubleStream::of).average().orElse(0));
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Float> getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(List<Float> listaNotas) {
		this.listaNotas = listaNotas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Estudiante [nombre=%s, genero=%s, id=%s, listaNotas=%s]", nombre, genero, id, listaNotas);
	}

}
