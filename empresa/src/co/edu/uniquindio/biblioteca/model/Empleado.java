package co.edu.uniquindio.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Empleado extends Persona{
	
	/**
	 * Este atrinuto es un valor que represnta el salario del empleado 
	 */
	private double sueldoBruto;
	private List<Directivo> listaDirectores = new ArrayList<Directivo>();
	
	
	
	public Empleado(String nombre, int edad, double sueldoBruto) {
		super(nombre, edad);
		this.sueldoBruto = sueldoBruto;
	}
	public double getSueldoBruto() {
		return sueldoBruto;
	}
	/**
	 * 
	 * @param sueldoBruto
	 */
	public void setSueldoBruto(double sueldoBruto) {
		this.sueldoBruto = sueldoBruto;
	}
	public List<Directivo> getListaDirectores() {
		return listaDirectores;
	}
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
