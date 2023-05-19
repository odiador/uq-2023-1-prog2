package co.edu.uniquindio.p2.empresaenergia.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniquindio.p2.empresaenergia.exceptions.ClienteException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.FacturaException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.NullException;

public class EmpresaEnergia implements Serializable, ClienteManagement, FacturaManagement {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	private String ubicacion;
	private List<Cliente> listaClientes;
	private List<Factura> listaFacturas;

	/**
	 * Es el constructor de la empresa de energía, esta empresa tiene un nombre y
	 * una ubicacion
	 * 
	 * @param nombre
	 * @param ubicacion
	 */
	public EmpresaEnergia(String nombre, String ubicacion) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.listaClientes = new ArrayList<>();
		this.listaFacturas = new ArrayList<>();
	}

	public Cliente buscarCliente(String id) {
		return listaClientes.stream().filter(cliente -> cliente.tieneId(id)).findFirst().orElse(null);
	}

	public boolean validarCliente(String id) {
		return buscarCliente(id) != null;
	}

	/**
	 * Agrega un cliente a la empresa de energía, manda errores en caso de que hayan
	 * problemas:
	 * 
	 * @param cliente el cliente a agregar
	 * @throws NullException    en caso de que el cliente enviado sea null
	 * @throws ClienteException en caso de que al cliente le falten atributos o ya
	 *                          exista dentro de la lista
	 */
	public void agregarCliente(Cliente cliente) throws NullException, ClienteException {
		if (cliente == null)
			throw new NullException("El cliente enviado es null");

		if (!cliente.tieneTodoLleno())
			throw new ClienteException("Al cliente le faltan atributos");
		if (validarCliente(cliente.getId())) {
			throw new ClienteException("El cliente ya existe, no se puede agregar");
		}
		listaClientes.add(cliente);
	}

	/**
	 * Elimina un cliente a la empresa de energía, manda errores en caso de que
	 * hayan problemas:
	 * 
	 * @param cliente el cliente a agregar
	 * @throws NullException    en caso de que el cliente enviado sea null
	 * @throws ClienteException en caso de que al cliente le falten atributos o ya
	 *                          exista dentro de la lista
	 */
	public void eliminarCliente(Cliente cliente) throws NullException, ClienteException {
		if (cliente == null)
			throw new NullException("El cliente enviado es null");
		if (!cliente.existeId())
			throw new ClienteException("Al cliente le falta la identificacion");
		if (!validarCliente(cliente.getId())) {
			throw new ClienteException("El cliente no existe, no se puede eliminar");
		}
		listaClientes.remove(cliente);
	}

	/**
	 * Registra una factura a la empresa de energía, suelta errores en caso de que
	 * no hallan pasado las cosas de la manera correcta
	 * 
	 * @param factura
	 * @throws NullException    en caso de que la factura enviada sea null
	 * @throws FacturaException en caso de que le falte algun atributo a la factura
	 *                          o en caso de que ya exista en la lista de facturas
	 *                          de le empresa
	 */
	@Override
	public void registrarFactura(Factura factura) throws NullException, FacturaException {
		if (factura == null)
			throw new NullException("La factura enviada es null");
		if (factura.getCliente() == null) {
			throw new FacturaException("El cliente no fue econtrado");
		}
		if (!factura.tieneTodoLleno())
			throw new FacturaException("A la factura le faltan atributos por llenar");
		if (validarFactura(factura.getCodigo())) {
			throw new FacturaException("La factura ya está registrada, intenta con otro código");
		}
		listaFacturas.add(factura);
	}

	/**
	 * Valida si la factura con un codigo especifico se encuentra o no en la lista
	 * de facturas de la empresa
	 * 
	 * @param codigo
	 * @return
	 */
	@Override
	public boolean validarFactura(String codigo) {
		return buscarFactura(codigo) != null;
	}

	/**
	 * Busca una factura a partir del codigo, si no se encuentra se retorna un nunll
	 * 
	 * @param codigo
	 * @return la factura encontrada, null si no se encuentra
	 */
	@Override
	public Factura buscarFactura(String codigo) {
		return listaFacturas.stream().filter(factura -> factura.tieneCodigo(codigo)).findFirst().orElse(null);
	}

	/**
	 * @return el nombre de la mpresa
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre es el nombre de la empresa a cambiar
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return la ubicacion de la empresa
	 */
	public String getUbicacion() {
		return ubicacion;
	}

	/**
	 * @param ubicacion la ubicacion de la empresa a cambiar
	 */
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	/**
	 * @return la lista de clientes de la empresa
	 */
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	/**
	 * @param listaClientes la lista de clientes de la empresa a cambiar
	 */
	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	/**
	 * @return la lista de facturas de la empresa
	 */
	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}

	/**
	 * @param listaFacturas the listaFacturas to set
	 */
	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public void actualizarCliente(Cliente cliente) throws NullException, ClienteException {
		if (cliente == null)
			throw new NullException("El cliente enviado es null");
		int indice = listaClientes.indexOf(cliente);
		if (indice == -1) {
			throw new ClienteException("El cliente con esa id no fue encontrado");
		}
		if (!cliente.tieneTodoLleno()) {
			throw new ClienteException("Al cliente le faltan campos por llenar");
		}
		listaClientes.set(indice, cliente);
	}

	public void eliminarFactura(Factura factura) throws NullException, FacturaException {
		if (factura == null)
			throw new NullException("El la factura enviada es null");
		if (!factura.existeCodigo())
			throw new FacturaException("A la factura le falta el codigo");
		if (!validarCliente(factura.getCodigo())) {
			throw new FacturaException("La factura no existe, no se puede eliminar");
		}
		listaFacturas.remove(factura);
	}
}
