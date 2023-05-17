package co.edu.uniquindio.p2.empresaenergia.model;

public class ClienteNatural extends Cliente {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cedula;
	private String apellidos;
	private Integer estrato;

	/**
	 * Es el constructor de la clase ClienteNatural
	 * 
	 * @param id
	 * @param nombre
	 * @param apellidos
	 * @param estrato
	 */
	public ClienteNatural(String id, String nombre, String apellidos, Integer estrato) {
		super(id, nombre);
		this.cedula = id;
		this.apellidos = apellidos;
		this.estrato = estrato;
	}

	/**
	 * @return la cedula del cliente natural
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula la cedula del cliente natural a cambiar
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return los apellidos del cliente natural a cambiar
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * @param apellidos los apellidos del cliente natural a cambiar
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return el estrato del cliente natural
	 */
	public Integer getEstrato() {
		return estrato;
	}

	/**
	 * @param estrato el estrato del cliente natural a cambiar
	 */
	public void setEstrato(Integer estrato) {
		this.estrato = estrato;
	}

	@Override
	protected boolean tieneTodoLleno() {
		return id != null && nombre != null && cedula != null && apellidos != null && estrato != null && !id.isEmpty()
				&& !nombre.isEmpty() && !cedula.isEmpty() && !apellidos.isEmpty() && estrato > 0;
	}

	@Override
	public String toString() {
		return String.format("ClieneNatural [id=%s, nombre=%s, cedula=%s, apellidos=%s, estrato=%s]", id, nombre,
				cedula, apellidos, estrato);
	}

}
