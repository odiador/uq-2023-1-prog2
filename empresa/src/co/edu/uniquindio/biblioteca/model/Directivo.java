package co.edu.uniquindio.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

public class Directivo extends Empleado {

	private Categoria categoria;
	private List<Empleado> listaSubordinados = new ArrayList<Empleado>();
	
	public Directivo(String nombre, int edad, double sueldoBruto, Categoria categoria,
			List<Empleado> listaSubordinados) {
		super(nombre, edad, sueldoBruto);
		this.categoria = categoria;
		this.listaSubordinados = listaSubordinados;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Empleado> getListaSubordinados() {
		return listaSubordinados;
	}

	public void setListaSubordinados(List<Empleado> listaSubordinados) {
		this.listaSubordinados = listaSubordinados;
	}

	@Override
	public String toString() {
		return "Directivo [categoria=" + categoria + ", listaSubordinados=" + listaSubordinados + "]";
	}
	
	
	
}
