package co.edu.uniquindio.p2.empresaenergia.controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import co.edu.uniquindio.p2.empresaenergia.exceptions.FacturaException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.NullException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.PersonaException;
import co.edu.uniquindio.p2.empresaenergia.model.Cliente;
import co.edu.uniquindio.p2.empresaenergia.model.Empleado;
import co.edu.uniquindio.p2.empresaenergia.model.EmpresaEnergia;
import co.edu.uniquindio.p2.empresaenergia.model.Factura;
import co.edu.uniquindio.p2.empresaenergia.model.Persona;

public class ModelFactoryController {
	/**
	 * Es la ruta en la que está almacenada la información de la universidad
	 */
	private static final String RUTA = "data.dat";
	private EmpresaEnergia empresaEnergia = null;

	/**
	 * Es la clase singleton que controla la universidad
	 * 
	 * @author juana
	 *
	 */
	private static class SingletonHolder {
		private final static ModelFactoryController eINSTANCE = new ModelFactoryController();
	}

	/**
	 * Obtiene un objeto de clase {@link #ModelFactoryController()} que permite usar
	 * métodos de la universidad
	 * 
	 * @return
	 */
	public static ModelFactoryController getInstance() {
		return SingletonHolder.eINSTANCE;
	}

	/**
	 * Obtiene la universidad del ModelFactory, si no se encuentra (es null) se
	 * intenta leer {@link #readEmpresaEnergia()}, y si no se puede leer se crea una
	 * nueva universidad y se guarda en el archivo ({@link #saveEmpresaEnergia()})
	 * 
	 * @return la universidad
	 */
	public EmpresaEnergia getEmpresaEnergia() {
		if (empresaEnergia == null)
			readEmpresaEnergia();
		return empresaEnergia;
	}

	/**
	 * Lee la universidad de un archivo de ruta {@link #RUTA}, si no se encuentra,
	 * se crea una nueva universidad y se guarda en la ruta
	 * ({@link #saveEmpresaEnergia()}
	 */
	public void readEmpresaEnergia() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA));
			empresaEnergia = (EmpresaEnergia) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			empresaEnergia = new EmpresaEnergia("Edeq", "Quindio");
			saveEmpresaEnergia();
		}
	}

	/**
	 * Guarda la empresa de energía en un archivo de una ruta específica
	 * {@link #RUTA}
	 */
	public void saveEmpresaEnergia() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA));
			oos.writeObject(empresaEnergia);
			oos.close();
		} catch (IOException e) {
		}
	}

	/**
	 * Agrega un cliente a la empresa de energía, manda errores en caso de que hayan
	 * problemas.<br>
	 * Usa el metodo {@link EmpresaEnergia#agregarCliente(Persona)}
	 * 
	 * @param cliente
	 * @throws NullException
	 * @throws PersonaException
	 */
	public void agregarCliente(Cliente cliente) throws NullException, PersonaException {
		getEmpresaEnergia().agregarCliente(cliente);
		saveEmpresaEnergia();
	}

	/**
	 * Elimina un cliente a la empresa de energía, manda errores en caso de que
	 * hayan problemas.<br>
	 * Usa el metodo {@link EmpresaEnergia#eliminarCliente(Persona)}
	 * 
	 * 
	 * @param cliente
	 * @throws NullException
	 * @throws PersonaException
	 */
	public void eliminarCliente(Cliente cliente) throws NullException, PersonaException {
		getEmpresaEnergia().eliminarCliente(cliente);
		saveEmpresaEnergia();
	}

	/**
	 * @return {@link EmpresaEnergia#agregarCliente(Persona)}
	 */
	public List<Cliente> getListaClientes() {
		return getEmpresaEnergia().getListaClientes();
	}

	/**
	 * @return la lista de facturas de la empresa
	 */
	public List<Factura> getListaFacturas() {
		return getEmpresaEnergia().getListaFacturas();
	}

	/**
	 * Registra una factura a la empresa de energía, suelta errores en caso de queno
	 * hallan pasado las cosas de la manera correcta.<br>
	 * Usa el metodo {@link EmpresaEnergia#registrarFactura(Factura)} de la empresa
	 * de energia
	 * 
	 * @param factura
	 * @throws NullException
	 * @throws FacturaException
	 */
	public void registrarFactura(Factura factura) throws NullException, FacturaException {
		getEmpresaEnergia().registrarFactura(factura);
		saveEmpresaEnergia();
	}

	public Cliente buscarCliente(String id) {
		return getEmpresaEnergia().buscarCliente(id);

	}

	public void actualizarCliente(Cliente cliente) throws NullException, PersonaException {
		getEmpresaEnergia().actualizarCliente(cliente);
		saveEmpresaEnergia();
	}

	public void eliminarFactura(Factura factura) throws NullException, FacturaException {
		getEmpresaEnergia().eliminarFactura(factura);
		saveEmpresaEnergia();
	}

	public Empleado iniciarSesion(String id, String pass) {
		return getEmpresaEnergia().iniciarSesion(id, pass);
	}

	public void agregarEmpleado(Empleado empleado) throws NullException, PersonaException {
		getEmpresaEnergia().agregarEmpleado(empleado);
		saveEmpresaEnergia();
	}

	/**
	 * Obtiene el nombre de la empresa de energía
	 * 
	 * @return
	 */
	public String obtenerNombreEmpresa() {
		return getEmpresaEnergia().getNombre();
	}

	public void eliminarEmpleado(Empleado empleado) throws NullException, PersonaException {
		getEmpresaEnergia().eliminarEmpleado(empleado);
		saveEmpresaEnergia();
	}

	public List<Empleado> getListaEmpleados() {
		return getEmpresaEnergia().getListaEmpleados();
	}

}
