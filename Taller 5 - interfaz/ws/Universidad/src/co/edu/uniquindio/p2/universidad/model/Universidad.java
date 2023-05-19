package co.edu.uniquindio.p2.universidad.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.p2.universidad.exceptions.EstudianteException;
import co.edu.uniquindio.p2.universidad.exceptions.FueraRangoException;
import co.edu.uniquindio.p2.universidad.exceptions.NullException;

public class Universidad implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String ruta;
	private List<Estudiante> listaEstudiantes;

	/**
	 * Es el constructor de la universidad
	 *
	 * @param nombre
	 * @param ruta
	 */
	public Universidad(String nombre, String ruta) {
		this.nombre = nombre;
		this.ruta = ruta;
		this.listaEstudiantes = new ArrayList<Estudiante>();
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the ruta
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * @param ruta
	 *            the ruta to set
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * @return the listaEstudiantes
	 */
	public List<Estudiante> getListaEstudiantes() {
		return listaEstudiantes;
	}

	/**
	 * @param listaEstudiantes
	 *            the listaEstudiantes to set
	 */
	public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
		this.listaEstudiantes = listaEstudiantes;
	}

	/**
	 * Agrega un estudiante en la universidad, da errror si no se dan las cosas
	 * correctamente
	 *
	 * @param estudiante
	 * @throws NullException
	 *             en caso de que el estudiante parámetro sea null o en caso de
	 *             que falte algún atributo del estudiante usando el método
	 *             {@link Estudiante#existe()} del estudiante
	 * @throws EstudianteException
	 *             en caso de que no se encuentre el estudiante
	 */
	public void agregarEstudiante(Estudiante estudiante) throws NullException, EstudianteException {
		if (estudiante == null)
			throw new NullException("El estudiante a agregar no existe");
		if (!estudiante.existe())
			throw new NullException("El estudiante a agregar no existe");
		if (validarExisteEstudiante(estudiante))
			throw new EstudianteException("El estudiante ya se encuentra, no se puede agregar");
		listaEstudiantes.add(estudiante);
	}

	/**
	 * Elimina un estudiante en la universidad, da error si no se dan las cosas
	 * correctamente
	 *
	 * @param estudiante
	 * @throws NullException
	 *             en caso de que el estudiante parámetro sea null o en caso de
	 *             que no tenga identificación, usa el método
	 *             {@link Estudiante#existeId()} del estudiante para validar
	 * @throws EstudianteException
	 */
	public void eliminarEstudiante(Estudiante estudiante) throws NullException, EstudianteException {
		if (estudiante == null)
			throw new NullException("El estudiante a eliminar no existe");
		if (!estudiante.existeId())
			throw new NullException("El estudiante a eliminar no existe");
		if (!validarExisteEstudiante(estudiante))
			throw new EstudianteException("El estudiante no fue encontrado, no se puede eliminar");
		listaEstudiantes.remove(estudiante);
	}

	/**
	 * Actualizar un estudiante en la universidad, da error si no se dan las
	 * cosas correctamente
	 *
	 * @param estudiante
	 * @throws NullException
	 * @throws EstudianteException
	 * @throws FueraRangoException
	 */
	public void actualizarEstudiante(Estudiante estudiante)
			throws NullException, EstudianteException {
		if (estudiante == null)
			throw new NullException("El estudiante a actualizar no existe");
		if (!estudiante.existeId())
			throw new NullException("El estudiante a actualizar no existe");
		if (!validarExisteEstudiante(estudiante))
			throw new EstudianteException("El estudiante no fue encontrado, no se puede actualizar");
		int index = listaEstudiantes.indexOf(estudiante);
		listaEstudiantes.get(index).actualizarDatos(estudiante.getNombre(), estudiante.getCarrera(),
				estudiante.getNumSemestre(), estudiante.getEdad());
	}

	/**
	 * Valida si un estudiante existe o no en la lista de estudiantes de la
	 * universidad
	 *
	 * @param estudiante
	 * @return
	 */
	private boolean validarExisteEstudiante(Estudiante estudiante) {
		return buscarEstudiante(estudiante) != null;
	}

	/**
	 * Busca un estudiante con respecto a su identificacion retorna un null si
	 * no se encuentra
	 *
	 * @param estudiante
	 * @return
	 */
	private Estudiante buscarEstudiante(Estudiante estudiante) {
		return listaEstudiantes.stream().filter(cadaEstudiante -> cadaEstudiante.equals(estudiante)).findFirst()
				.orElse(null);
	}
}
