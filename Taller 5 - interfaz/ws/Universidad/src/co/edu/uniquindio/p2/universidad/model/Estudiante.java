package co.edu.uniquindio.p2.universidad.model;

import java.io.Serializable;

import co.edu.uniquindio.p2.universidad.exceptions.NullException;

public class Estudiante implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String id;
	private String nombre;
	private String carrera;
	private int edad;
	private int numSemestre;

	/**
	 * Es el constructor del estudiante
	 *
	 * @param id
	 * @param nombre
	 * @param carrera
	 * @param edad
	 * @param numSemestre
	 */
	public Estudiante(String id, String nombre, String carrera, int edad, int numSemestre) {
		this.id = id;
		this.nombre = nombre;
		this.carrera = carrera;
		this.edad = edad;
		this.numSemestre = numSemestre;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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
	 * @return the carrera
	 */
	public String getCarrera() {
		return carrera;
	}

	/**
	 * @param carrera
	 *            the carrera to set
	 */
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	/**
	 * @return the edad
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * @param edad
	 *            the edad to set
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * @return the numSemestre
	 */
	public int getNumSemestre() {
		return numSemestre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombreONada(String nombre) {
		if (nombre == null)
			return;
		setNombre(nombre);
	}

	public void setCarreraONada(String carrera) {
		if (carrera == null)
			return;
		setCarrera(carrera);
	}

	/**
	 * @param numSemestre
	 *            the semestre to set
	 */
	public void setNumSemestre(int numSemestre) {
		this.numSemestre = numSemestre;
	}

	public void setEdadONada(int edad) {
		if (numSemestre <= 0)
			return;
		setEdad(edad);
	}

	public void setNumSemestreONada(int numSemestre) {
		if (numSemestre <= 0)
			return;
		setNumSemestre(numSemestre);
	}

	public void actualizarDatos(String nombre, String carrera, int numSemestre, int edad) throws NullException {
		setNombreONada(nombre);
		setCarreraONada(carrera);
		setNumSemestreONada(numSemestre);
		setEdadONada(edad);
	}

	/**
	 * Determina si un estudiante existe o no
	 *
	 * @return
	 */
	public boolean existe() {
		return getNumSemestre() > 0 && getEdad() > 0 && getCarrera() != null && getNombre() != null && existeId();
	}

	public boolean existeId() {
		return getId() != null;
	}

	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombre=" + nombre + ", carrera=" + carrera + ", edad=" + edad
				+ ", numSemestre=" + numSemestre + "]";
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

}
