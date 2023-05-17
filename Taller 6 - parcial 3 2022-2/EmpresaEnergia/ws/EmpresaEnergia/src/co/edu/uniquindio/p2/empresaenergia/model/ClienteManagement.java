package co.edu.uniquindio.p2.empresaenergia.model;

import co.edu.uniquindio.p2.empresaenergia.exceptions.ClienteException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.NullException;

public interface ClienteManagement {
	public void agregarCliente(Cliente cliente) throws NullException, ClienteException;

	public void eliminarCliente(Cliente cliente) throws NullException, ClienteException;

	public Cliente buscarCliente(String id);
	
	public boolean validarCliente(String id);

}
