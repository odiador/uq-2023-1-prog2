package co.edu.uniquindio.p2.empresaenergia.model;

import co.edu.uniquindio.p2.empresaenergia.exceptions.NullException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.PersonaException;

public interface EmpleadoManagement {
	public Empleado buscarEmpleado(String id);

	public boolean validarEmpleado(String id);

	public void agregarEmpleado(Empleado empleado) throws NullException, PersonaException;

	public void eliminarEmpleado(Empleado empleado) throws NullException, PersonaException;
}
