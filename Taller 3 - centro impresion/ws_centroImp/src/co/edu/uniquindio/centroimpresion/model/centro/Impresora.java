package co.edu.uniquindio.centroimpresion.model.centro;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

import co.edu.uniquindio.centroimpresion.exceptions.ImpresoraException;

public abstract class Impresora implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5292404516001955132L;
	protected final String code;
	protected String marca;
	protected EstadoImpresora estado;
	protected final TreeSet<Documento> listaDocumentos = new TreeSet<Documento>();
	protected double letrasPorSegundo;
	protected boolean esAColor;
	protected int documentosImpresos;

	public Impresora(String code, String marca, EstadoImpresora estado, boolean esAColor, double letrasPorSegundo) {
		this(code, marca, estado);
		this.esAColor = esAColor;
		this.letrasPorSegundo = letrasPorSegundo;
	}

	public Impresora() {
		code = null;
	}

	public Impresora(String code, String marca, EstadoImpresora estado) {
		this(code);
		this.marca = marca;
		this.estado = estado;
	}

	public Impresora(String code) {
		this.code = code;
	}

	public void addDocumento(String code, String titulo, int prioridad, String contenido, LocalDateTime fechaAgregado)
			throws ImpresoraException {
		addDocumento(new Documento(code, titulo, prioridad, contenido, fechaAgregado));
	}

	public void addDocumento(Documento doc) throws ImpresoraException {
		throwIfNotActive();

		if (!getListaDocumentos().add(doc))
			throw new ImpresoraException("El documento ya existe");
	}

	public Documento buscarDocumento(String code) {
		return getListaDocumentos().stream().filter(doc -> doc.getCode().equals(code)).findAny()
				.orElse(new Documento());
	}

	public void deleteDocumento(String code) throws ImpresoraException {
		throwIfNotActive();

		if (!getListaDocumentos().remove(buscarDocumento(code)))
			throw new ImpresoraException("El documento no existe");
	}

	public void actualizarDocumento(Documento doc) throws ImpresoraException {
		deleteDocumento(doc.getCode());
		addDocumento(code, marca, documentosImpresos, code, null);
	}

	protected void throwIfNotActive() throws ImpresoraException {
		if (!estaActiva())
			throw new ImpresoraException("La impresora no esta activa (Estado: " + estado.getTexto() + ")");
	}

	public abstract void imprimirDocumento(LocalDateTime dateTime, Documento documento) throws ImpresoraException;

	public String getCode() {
		return code;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public EstadoImpresora getEstado() {
		return estado;
	}

	public void setEstado(EstadoImpresora estado) {
		this.estado = estado;
	}

	public boolean estaActiva() {
		return getEstado() == EstadoImpresora.ACTIVO;
	}

	public Set<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	public double getLetrasPorSegundo() {
		return letrasPorSegundo;
	}

	public void setLetrasPorSegundo(double letrasPorSegundo) {
		this.letrasPorSegundo = letrasPorSegundo;
	}

	public boolean isEsAColor() {
		return esAColor;
	}

	public void setEsAColor(boolean esAColor) {
		this.esAColor = esAColor;
	}

	public int getPaginasImpresas() {
		return documentosImpresos;
	}

	public void setPaginasImpresas(int paginasImpresas) {
		this.documentosImpresos = paginasImpresas;
	}

	public boolean exists() {
		return code != null && marca != null && estado != null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Impresora other = (Impresora) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Impresora [code=" + code + ", marca=" + marca + ", estado=" + estado + ", listaDocumentos="
				+ listaDocumentos + ", letrasPorSegundo=" + letrasPorSegundo + ", esAColor=" + esAColor
				+ ", paginasImpresas=" + documentosImpresos + "]";
	}

	protected abstract void recargar();

}
