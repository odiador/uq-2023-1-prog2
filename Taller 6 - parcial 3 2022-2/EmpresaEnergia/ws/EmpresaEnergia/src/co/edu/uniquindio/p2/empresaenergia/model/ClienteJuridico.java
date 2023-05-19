package co.edu.uniquindio.p2.empresaenergia.model;

public class ClienteJuridico extends Cliente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nit;
	private String telefono;
	private String tipoEmpresa;

	/**
	 * Es el constructor de la clase ClienteNatural
	 * 
	 * @param id
	 * @param nombre
	 * @param apellidos
	 * @param estrato
	 */
	public ClienteJuridico(String id, String nombre, String telefono, String tipoEmpresa) {
		super(id, nombre);
		this.nit = id;
		this.telefono = telefono;
		this.tipoEmpresa = tipoEmpresa;
	}

	/**
	 * @return el nit del cliente juridico
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit el nit del cliente juridico a cambiar
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	/**
	 * @return el telefono del cliente juridico
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono el telefono del cliente juridico a cambiar
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return el tipo de empresa del cliente juridico
	 */
	public String getTipoEmpresa() {
		return tipoEmpresa;
	}

	/**
	 * @param tipoEmpresa el tipo de empresa del cliente juridico a cambiar
	 */
	public void setTipoEmpresa(String tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	@Override
	protected boolean tieneTodoLleno() {
		return id != null && nombre != null && nit != null && telefono != null && tipoEmpresa != null && !id.isEmpty()
				&& !nombre.isEmpty() && !nit.isEmpty() && !telefono.isEmpty();
	}

	@Override
	public String toString() {
		return String.format("ClienteJuridico [id=%s, nombre=%s, nit=%s, telefono=%s, tipoEmpresa=%s]", id, nombre, nit,
				telefono, tipoEmpresa);
	}

	@Override
	public String getTipoPersona() {
		return "Juridico";
	}

}
