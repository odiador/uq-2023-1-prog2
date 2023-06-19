package co.edu.uniquindio.p2.diplomado.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import co.edu.uniquindio.p2.diplomado.exceptions.AtributosFaltantesException;
import co.edu.uniquindio.p2.diplomado.exceptions.CuposLlenosException;
import co.edu.uniquindio.p2.diplomado.exceptions.EstudianteException;
import co.edu.uniquindio.p2.diplomado.exceptions.NotPresentException;
import co.edu.uniquindio.p2.diplomado.exceptions.NullException;

public class Diplomado implements EstudianteGestionable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Set<Estudiante> listaEstudiantes;
	private String nombre;
	private int cupos;

	public Diplomado(String nombre, int cupos) {
		this.nombre = nombre;
		this.cupos = cupos;
		this.listaEstudiantes = new HashSet<>();
	}

	public float obtenerPromedioEstudiante(String id) throws EstudianteException {
		Estudiante estudiante = buscarEstudiante(id);
		if (estudiante == null)
			throw new EstudianteException("El estudiante no fue encontrado");
		return estudiante.getPromedioNotas();
	}

	/**
	 * Obtiene el promedio de todos los estudiantes del {@link Diplomado}
	 * 
	 * @return
	 * @throws NotPresentException en caso de que no hayan estudiantes
	 */
	public Float obtenerPromedioTodosEstudiantes() throws NotPresentException {
		OptionalDouble promedio = listaEstudiantes.stream().map(Estudiante::getPromedioNotas)
				.flatMapToDouble(DoubleStream::of).average();
		return new Float(
				promedio.orElseThrow(() -> new NotPresentException("No hay estudiantes para hacer el promedio")));
	}

	/**
	 * Obtiene el promedio mas alto de los estudiantes del {@link Diplomado}
	 * 
	 * @return
	 * @throws NotPresentException en caso de que no hayan estudiantes
	 */
	public Float obtenerPromedioMasAltoEstudiantes() throws NotPresentException {
		OptionalDouble maximo = listaEstudiantes.stream().map(Estudiante::getPromedioNotas)
				.flatMapToDouble(DoubleStream::of).max();
		return new Float(
				maximo.orElseThrow(() -> new NotPresentException("No hay estudiantes para hacer el promedio")));
	}

	public Estudiante buscarEstudiantePromAlto() throws NotPresentException {
		return buscarEstudiantePromedio(obtenerPromedioMasAltoEstudiantes());
	}

	/**
	 * Busca el estudiante con un promedio especifico
	 * 
	 * @param prom
	 * @return
	 */
	private Estudiante buscarEstudiantePromedio(Float prom) {
		return listaEstudiantes.stream().filter(estudiante -> estudiante.tieneProm(prom)).findFirst().orElse(null);
	}

	@Override
	public void agregarEstudiante(Estudiante estudiante)
			throws EstudianteException, NullException, AtributosFaltantesException, CuposLlenosException {
		if (estudiante == null)
			throw new NullException("El estudiante enviado es null");
		if (!estudiante.atributosLlenos())
			throw new AtributosFaltantesException("Al estudiante le faltan campos por llenar");
		if (listaEstudiantes.size() == cupos) {
			throw new CuposLlenosException("Ya no hay mas cupos para el diplomado");
		}
		if (validarEstudiante(estudiante.getId()))
			throw new EstudianteException("El estudiante ya existe, no se puede agregar");
		listaEstudiantes.add(estudiante);
	}

	@Override
	public void actualizarEstudiante(Estudiante estudiante)
			throws EstudianteException, NullException, AtributosFaltantesException {
		if (estudiante == null)
			throw new NullException("El estudiante enviado es null");
		if (!estudiante.atributosLlenos())
			throw new AtributosFaltantesException("Al estudiante le faltan campos por llenar");
		if (!validarEstudiante(estudiante.getId()))
			throw new EstudianteException("El estudiante no fue encontrado");
		listaEstudiantes.remove(estudiante);
		listaEstudiantes.add(estudiante);
	}

	@Override
	public void eliminarEstudiante(String id) throws EstudianteException, NullException {
		if (id == null)
			throw new NullException("El id enviado es null");
		Estudiante estudiante = buscarEstudiante(id);
		if (estudiante == null)
			throw new EstudianteException("El estudiante no fue encontrado");
		listaEstudiantes.remove(estudiante);
	}

	@Override
	public Estudiante buscarEstudiante(String id) {
		return listaEstudiantes.stream().filter(estudiante -> estudiante.tieneId(id)).findFirst().orElse(null);
	}

	@Override
	public List<Estudiante> listarEstudiantes() {
		return listaEstudiantes.stream().collect(Collectors.toList());
	}

	public Set<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}

	public void setListaEstudiantes(Set<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCupos() {
		return cupos;
	}

	public void setCupos(int cupos) {
		this.cupos = cupos;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return String.format("Diplomado [listaEstudiantes=%s, nombre=%s, cupos=%s]",
				listaEstudiantes != null ? toString(listaEstudiantes, maxLen) : null, nombre, cupos);
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

	public List<Float> listarNotasEstudiante(String id) throws EstudianteException {
		Estudiante estudiante = buscarEstudiante(id);
		if (estudiante == null)
			throw new EstudianteException("El estudiante no fue encontrado");
		return estudiante.getListaNotas();
	}
}
