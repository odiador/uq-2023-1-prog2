package co.edu.uniquindio.centroimpresion.model;

public enum TipoEmpleado {
	GERENTE(true, true, true, true, true, true), SUPERVISOR(false, true, false, true, true, true), TRABAJADOR(false,
			false, false, false, false, false);
	private boolean puedeAgregarImpresora;
	private boolean puedeEliminarDocumentos;
	private boolean puedeEliminarImpresoras;
	private boolean puedeVerImpresoras;
	private boolean puedeVerDocs;
	private boolean puedeImprimirDocEsprcifico;

	private TipoEmpleado(boolean puedeAgregarImpresora, boolean puedeEliminarDocumentos,
			boolean puedeEliminarImpresoras, boolean puedeVerImpresoras, boolean puedeVerDocs,
			boolean puedeImprimirDocEsprcifico) {
		this.puedeAgregarImpresora = puedeAgregarImpresora;
		this.puedeEliminarDocumentos = puedeEliminarDocumentos;
		this.puedeEliminarImpresoras = puedeEliminarImpresoras;
		this.puedeVerImpresoras = puedeVerImpresoras;
		this.puedeVerDocs = puedeVerDocs;
		this.puedeImprimirDocEsprcifico = puedeImprimirDocEsprcifico;
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
}
