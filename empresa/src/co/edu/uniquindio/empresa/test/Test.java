package co.edu.uniquindio.biblioteca.test;

import java.util.ArrayList;

import co.edu.uniquindio.biblioteca.model.Categoria;
import co.edu.uniquindio.biblioteca.model.Cliente;
import co.edu.uniquindio.biblioteca.model.Directivo;
import co.edu.uniquindio.biblioteca.model.Empleado;
import co.edu.uniquindio.biblioteca.model.Empresa;
import co.edu.uniquindio.biblioteca.model.Persona;

public class Test {

	public static void main(String[] args) {

		Empresa empresa = new Empresa("Uq empresa");

		Persona persona = new Empleado("Juan", 23, 23445);

		Cliente cliente = new Cliente("Luz", 23, "345678");

		ArrayList<Empleado> listaSubordinados = new ArrayList<Empleado>();

		listaSubordinados.add((Empleado) persona);

		empresa.getListaPersonas().add(persona);
		empresa.getListaPersonas().add(cliente);

		Directivo directivo = new Directivo("Carlos", 19, 5000000, Categoria.GERENTE, listaSubordinados);

		empresa.getListaPersonas().add(directivo);
		System.out.println(empresa.getListaPersonas());
		System.out.println(empresa.getListaEmpleados());
		System.out.println(empresa.getListaEmpleados2());

		// hacer un metodo que imprima el salriobruot de los empleado debe manipular la
		// lista personas de la empresa

	}

}
