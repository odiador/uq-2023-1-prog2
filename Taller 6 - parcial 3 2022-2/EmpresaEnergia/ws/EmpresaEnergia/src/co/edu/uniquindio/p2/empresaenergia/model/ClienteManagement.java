package co.edu.uniquindio.p2.empresaenergia.model;

import co.edu.uniquindio.p2.empresaenergia.exceptions.PersonaException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.NullException;

public interface ClienteManagement {
	public void agregarCliente(Cliente cliente) throws NullException, PersonaException;

	public void eliminarCliente(Cliente cliente) throws NullException, PersonaException;

	public void actualizarCliente(Cliente cliente) throws NullException, PersonaException;

	public Cliente buscarCliente(String id);

	public boolean validarCliente(String id);

}
