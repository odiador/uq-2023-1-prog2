package co.edu.uniquindio.taller.ejercicio1.model;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

	private String nombre;
	private List<Persona> listaPersonas = new ArrayList<Persona>();

	/**
	 * Es el constructor de la empresa
	 * 
	 * @param nombre
	 */
	public Empresa(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * Es el constructor de la empresa sin parámetros
	 */
	public Empresa() {
		super();
	}

	/**
	 * Obtiene el nombre de la empresa
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el nombre de la empresa
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la lista de personas de la empresa
	 * 
	 * @return
	 */
	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	/**
	 * Cambia la lista de personas de la empresa
	 * 
	 * @param listaPersonas
	 */
	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	/**
	 * Obtiene la lista de los empleados de la empresa
	 * 
	 * @return
	 */
	public ArrayList<Empleado> getListaEmpleados() {
		ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
		for (Persona personaaux : getListaPersonas()) {
			try {
				Empleado empleado = (Empleado) personaaux;
				listaEmpleados.add(empleado);
			} catch (ClassCastException e) {
			}
		}
		return listaEmpleados;
	}

	/**
	 * Obtiene el salario de los empleados de la empresa
	 * 
	 * @return
	 * @see {@link Empleado}
	 *      <li>{@link Directivo}
	 *      <li>{@link #getListaEmpleados}
	 */
	public ArrayList<Double> getSalarioEmpleados() {
		ArrayList<Empleado> listaEmpleados = getListaEmpleados();
		ArrayList<Double> listaSalarioEmpleados = new ArrayList<Double>();
		for (Empleado empleadoaux : listaEmpleados)
			listaSalarioEmpleados.add(empleadoaux.getSueldoBruto());
		return listaSalarioEmpleados;
	}

	/**
	 * Obtiene la lista de los empleados de la empresa (exclusivamente empleados, no
	 * directivos)
	 * 
	 * @return
	 * @see {@link Empleado}
	 *      <li>{@link Directivo}
	 */
	public ArrayList<Empleado> getListaEmpleados2() {
		ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
		for (Persona personaaux : getListaPersonas())
			if (personaaux.getClass() == ((Empleado) new Empleado()).getClass())
				listaEmpleados.add((Empleado) personaaux);
		return listaEmpleados;
	}

	/**
	 * Obtiene el salario de los empleados de la empresa (exclusivamente empleados,
	 * no directivos)
	 * 
	 * @return
	 * @see {@link Empleado}
	 *      <li>{@link Directivo}
	 *      <li>{@link #getListaEmpleados2}
	 */
	public ArrayList<Double> getSalarioEmpleados2() {
		ArrayList<Empleado> listaEmpleados = getListaEmpleados2();
		ArrayList<Double> listaSalarioEmpleados = new ArrayList<Double>();
		for (Empleado empleadoaux : listaEmpleados)
			listaSalarioEmpleados.add(empleadoaux.getSueldoBruto());
		return listaSalarioEmpleados;
	}

	@Override
	public String toString() {
		return "Empresa [nombre=" + nombre + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Empresa other = (Empresa) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}