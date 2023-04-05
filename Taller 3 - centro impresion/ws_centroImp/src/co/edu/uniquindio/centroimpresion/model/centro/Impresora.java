package co.edu.uniquindio.centroimpresion.model.centro;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

import co.edu.uniquindio.centroimpresion.exceptions.ImpresoraException;

public abstract class Impresora implements Serializable {

	private final String code;
	private String marca;
	private EstadoImpresora estado;
	private final TreeSet<Documento> listaDocumentos = new TreeSet<Documento>();
	private double paginasPorMinuto;
	private boolean esAColor;
	private int paginasImpresas;

	public Impresora(String code, String marca, EstadoImpresora estado, boolean esAColor, double paginasPorMinuto) {
		this.code = code;
		this.marca = marca;
		this.estado = estado;
		this.esAColor = esAColor;
		this.paginasPorMinuto = paginasPorMinuto;
	}

	public Impresora(String code) {
		this.code = code;
	}

	public void addDocumento(String code, String titulo, int prioridad, String contenido, LocalDateTime fechaAgregado)
			throws ImpresoraException {
		throwIfNotActive();

		if (!getListaDocumentos().add(new Documento(code, titulo, prioridad, contenido, fechaAgregado)))
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

	private void throwIfNotActive() throws ImpresoraException {
		if (!estaActiva())
			throw new ImpresoraException("La impresora no esta activa");
	}

	public abstract boolean imprimirDocumento();

	public abstract boolean imprimirDocumento(Documento documento);

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

	public double getPaginasPorMinuto() {
		return paginasPorMinuto;
	}

	public void setPaginasPorMinuto(double paginasPorMinuto) {
		this.paginasPorMinuto = paginasPorMinuto;
	}

	public boolean isEsAColor() {
		return esAColor;
	}

	public void setEsAColor(boolean esAColor) {
		this.esAColor = esAColor;
	}

	public int getPaginasImpresas() {
		return paginasImpresas;
	}

	public void setPaginasImpresas(int paginasImpresas) {
		this.paginasImpresas = paginasImpresas;
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
				+ listaDocumentos + ", paginasPorMinuto=" + paginasPorMinuto + ", esAColor=" + esAColor
				+ ", paginasImpresas=" + paginasImpresas + "]";
	}

}
