package co.edu.uniquindio.centroimpresion.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

import co.edu.uniquindio.centroimpresion.exceptions.ImpresoraException;

public abstract class Impresora {

	private final String code;
	private String marca;
	private EstadoImpresora estado;
	private final TreeSet<Documento> listaDocumentos = new TreeSet<Documento>();

	public Impresora(String code, String marca, EstadoImpresora estado) {
		this.code = code;
		this.marca = marca;
		this.estado = estado;
	}

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

	public void addDocumento(String code, String titulo, int prioridad, String contenido, LocalDateTime fechaAgregado)
			throws ImpresoraException {
		throwIfNotActive();

		if (!getListaDocumentos().add(new Documento(code, titulo, prioridad, contenido, fechaAgregado)))
			throw new ImpresoraException("El documento ya existe");
	}

	public Documento buscarDoc(String code) {
		return getListaDocumentos().stream().filter(doc -> doc.getCode().equals(code)).findAny()
				.orElse(new Documento());
	}

	public void deleteDocumento(String code) throws ImpresoraException {
		throwIfNotActive();

		if (!getListaDocumentos().remove(buscarDoc(code)))
			throw new ImpresoraException("El documento no existe");
	}

	private void throwIfNotActive() throws ImpresoraException {
		if (!estaActiva())
			throw new ImpresoraException("La impresora no esta activa");
	}

	public abstract boolean imprimir();

}
