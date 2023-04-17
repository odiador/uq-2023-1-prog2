package co.edu.uniquindio.centroimpresion.model;

public enum TipoEmpleado {
	ADMINISTRADOR("Administrador", true, true, true, true, true, true, true, true, true),
	SUPERVISOR("Supervisor", false, true, false, true, true, true, false, true, true),
	TRABAJADOR("Trabajador", false, false, false, false, false, false, false, false, false);

	private boolean puedeAgregarImpresora;
	private boolean puedeEliminarDocumentos;
	private boolean puedeEliminarImpresoras;
	private boolean puedeVerImpresoras;
	private boolean puedeVerDocs;
	private boolean puedeImprimirDocEsprcifico;
	private String text;
	private boolean puedeSeleccionarImpresora;
	private boolean puedePuedeActualizarDocumento;
	private boolean puedeActualizarImpresora;

	/**
	 * Es el constructor del tipo de empleado, cada empleado tiene una capacidad
	 * diferente de hacer cada cosa
	 * 
	 * @param text
	 * @param puedeAgregarImpresora
	 * @param puedeEliminarDocumentos
	 * @param puedeEliminarImpresoras
	 * @param puedeVerImpresoras
	 * @param puedeVerDocs
	 * @param puedeImprimirDocEsprcifico
	 * @param puedePuedeActualizarDocumento
	 * @param puedeSeleccionarImpresora
	 * @param puedeActualizarImpresora
	 */
	private TipoEmpleado(String text, boolean puedeAgregarImpresora, boolean puedeEliminarDocumentos,
			boolean puedeEliminarImpresoras, boolean puedeVerImpresoras, boolean puedeVerDocs,
			boolean puedeImprimirDocEsprcifico, boolean puedePuedeActualizarDocumento,
			boolean puedeSeleccionarImpresora, boolean puedeActualizarImpresora) {
		this.text = text;
		this.puedeAgregarImpresora = puedeAgregarImpresora;
		this.puedeEliminarDocumentos = puedeEliminarDocumentos;
		this.puedeEliminarImpresoras = puedeEliminarImpresoras;
		this.puedeVerImpresoras = puedeVerImpresoras;
		this.puedeVerDocs = puedeVerDocs;
		this.puedeImprimirDocEsprcifico = puedeImprimirDocEsprcifico;
		this.puedePuedeActualizarDocumento = puedePuedeActualizarDocumento;
		this.puedeSeleccionarImpresora = puedeSeleccionarImpresora;
		this.puedeActualizarImpresora = puedeActualizarImpresora;
	}

	/**
	 * Obtiene los valores del texto de enumeracion
	 * 
	 * @return
	 */
	public static String[] textValues() {
		TipoEmpleado[] values = TipoEmpleado.values();
		String[] stringValues = new String[values.length];
		for (int i = 0; i < values.length; i++)
			stringValues[i] = values[i].getText();
		return stringValues;
	}

	/**
	 * Obtiene el valor del tipo del empleado a partir de su texto
	 * 
	 * @param texto
	 * @return
	 */
	public static TipoEmpleado obtenerTipoTexto(String texto) {
		TipoEmpleado[] values = TipoEmpleado.values();
		for (TipoEmpleado tipoEmpleado : values)
			if (tipoEmpleado.getText().equals(texto))
				return tipoEmpleado;

		return null;
	}

	/**
	 * Obtiene el texto del tipo de empleado
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * Determina si el empleado puede agregar impresora
	 * 
	 * @return
	 */
	public boolean puedeAgregarImpresora() {
		return puedeAgregarImpresora;
	}

	/**
	 * Determina si el empleado puede eliminar documentos
	 * 
	 * @return
	 */
	public boolean puedeEliminarDocumentos() {
		return puedeEliminarDocumentos;
	}

	/**
	 * Determina si el empleado puede agregar impresoras
	 * 
	 * @return
	 */
	public boolean puedeEliminarImpresoras() {
		return puedeEliminarImpresoras;
	}

	/**
	 * Determina si el empleado puede ver impresoras
	 * 
	 * @return
	 */
	public boolean puedeVerImpresoras() {
		return puedeVerImpresoras;
	}

	/**
	 * 
	 * Determina si el empleado puede ver los documentos
	 * 
	 * @return
	 */
	public boolean puedeVerDocs() {
		return puedeVerDocs;
	}

	/**
	 * 
	 * Determina si el empleado puede imprimir un doc especifico
	 * 
	 * @return
	 */
	public boolean puedeImprimirDocEspecifico() {
		return puedeImprimirDocEsprcifico;
	}

	/**
	 * Determina si el empleado puede seleccionar impresoras
	 * 
	 * @return
	 */
	public boolean puedeSeleccionarImpresora() {
		return puedeSeleccionarImpresora;
	}

	/**
	 * 
	 * Determina si el empleado puede actualizar documentos
	 * 
	 * @return
	 */
	public boolean puedeActualizarDocumento() {
		return puedePuedeActualizarDocumento;
	}

	/**
	 * Determina si el empleado puede actualizar impresoras
	 * 
	 * @return
	 */
	public boolean puedeActualizarImpresora() {
		return puedeActualizarImpresora;
	}
}
