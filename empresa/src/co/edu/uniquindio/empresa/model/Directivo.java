package co.edu.uniquindio.empresa.model;

import java.util.ArrayList;
import java.util.List;

public class Directivo extends Empleado {

	private Categoria categoria;
	private List<Empleado> listaSubordinados = new ArrayList<Empleado>();

	/**
	 * Es el constructor de la clase Directivo
	 * 
	 * @param nombre
	 * @param edad
	 * @param sueldoBruto
	 * @param categoria
	 * @param listaSubordinados
	 */
	public Directivo(String nombre, int edad, double sueldoBruto, Categoria categoria,
			List<Empleado> listaSubordinados) {
		super(nombre, edad, sueldoBruto);
		this.categoria = categoria;
		this.listaSubordinados = listaSubordinados;
	}

	/**
	 * Es el constructor de la clase Directivo sin parámetros
	 */
	public Directivo() {
	}

	/**
	 * Obtiene la categoría del directivo
	 * 
	 * @return
	 */
	public Categoria getCategoria() {
		return categoria;
	}

	/**
	 * Cambia la categoría del directivo
	 * 
	 * @param categoria
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * Obtiene la lista de subordinados del directivo
	 * 
	 * @return
	 */
	public List<Empleado> getListaSubordinados() {
		return listaSubordinados;
	}

	/**
	 * Cambia la lista de subordinados del directivo
	 * 
	 * @param listaSubordinados
	 */
	public void setListaSubordinados(List<Empleado> listaSubordinados) {
		this.listaSubordinados = listaSubordinados;
	}

	@Override
	public String toString() {
		return "Directivo [categoria=" + categoria + ", listaSubordinados=" + listaSubordinados + "]";
	}
}