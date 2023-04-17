package co.edu.uniquindio.centroimpresion.model.centro;

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

	public static String[] textValues() {
		TipoEmpleado[] values = TipoEmpleado.values();
		String[] stringValues = new String[values.length];
		for (int i = 0; i < values.length; i++)
			stringValues[i] = values[i].getText();
		return stringValues;
	}

	public static TipoEmpleado obtenerTipoTexto(String texto) {
		TipoEmpleado[] values = TipoEmpleado.values();
		for (TipoEmpleado tipoEmpleado : values)
			if (tipoEmpleado.getText().equals(texto))
				return tipoEmpleado;

		return null;
	}

	public String getText() {
		return text;
	}

	public boolean puedeAgregarImpresora() {
		return puedeAgregarImpresora;
	}

	public boolean puedeEliminarDocumentos() {
		return puedeEliminarDocumentos;
	}

	public boolean puedeEliminarImpresoras() {
		return puedeEliminarImpresoras;
	}

	public boolean puedeVerImpresoras() {
		return puedeVerImpresoras;
	}

	public boolean puedeVerDocs() {
		return puedeVerDocs;
	}

	public boolean puedeImprimirDocEspecifico() {
		return puedeImprimirDocEsprcifico;
	}

	public boolean puedeSeleccionarImpresora() {
		return puedeSeleccionarImpresora;
	}

	public boolean puedeActualizarDocumento() {
		return puedePuedeActualizarDocumento;
	}

	public boolean puedeActualizarImpresora() {
		return puedeActualizarImpresora;
	}
}
