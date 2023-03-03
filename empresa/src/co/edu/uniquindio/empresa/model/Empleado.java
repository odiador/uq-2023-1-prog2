package co.edu.uniquindio.empresa.model;

import java.util.ArrayList;
import java.util.List;

public class Empleado extends Persona {

	/**
	 * Este atrinuto es un valor que represnta el salario del empleado
	 */
	private double sueldoBruto;
	private List<Directivo> listaDirectores = new ArrayList<Directivo>();

	/**
	 * Es el constructor del empleado
	 * 
	 * @param nombre
	 * @param edad
	 * @param sueldoBruto
	 */
	public Empleado(String nombre, int edad, double sueldoBruto) {
		super(nombre, edad);
		this.sueldoBruto = sueldoBruto;
	}

	/**
	 * Es el constructor del empleado sin parámetros
	 */
	public Empleado() {
	}

	/**
	 * Obtiene el sueldo bruto del empleado
	 * 
	 * @return
	 */
	public double getSueldoBruto() {
		return sueldoBruto;
	}

	/**
	 * Cambia el sueldo bruto del empleado
	 * 
	 * @param sueldoBruto
	 */
	public void setSueldoBruto(double sueldoBruto) {
		this.sueldoBruto = sueldoBruto;
	}

	/**
	 * Obtiene la lista de Directores del empleado
	 * 
	 * @return
	 */
	public List<Directivo> getListaDirectores() {
		return listaDirectores;
	}

	/**
	 * Cambia la lista de Directores del empleado
	 * 
	 * @param listaDirectores
	 */
	public void setListaDirectores(List<Directivo> listaDirectores) {
		this.listaDirectores = listaDirectores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(sueldoBruto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		if (Double.doubleToLongBits(sueldoBruto) != Double.doubleToLongBits(other.sueldoBruto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empleado [sueldoBruto=" + sueldoBruto + "]";
	}

}
