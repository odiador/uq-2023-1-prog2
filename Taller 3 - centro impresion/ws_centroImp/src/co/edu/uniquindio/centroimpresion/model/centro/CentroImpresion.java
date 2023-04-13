package co.edu.uniquindio.centroimpresion.model.centro;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.TipoCentroException;

public class CentroImpresion implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -2839899210301744900L;
	private final Set<Impresora> listaImpresoras = new HashSet<Impresora>();
	private final List<Documento> listaDocumentos = new ArrayList<Documento>();

	public CentroImpresion() {
	}

	public void agregarDocumento(String code, String titulo, int prioridad, String contenido,
			LocalDateTime fechaAgregado) throws CentroImpresionException {
		agregarDocumento(new Documento(code, titulo, prioridad, contenido, fechaAgregado));
	}

	public void agregarDocumento(String code, String titulo, int prioridad, String contenido)
			throws CentroImpresionException {
		agregarDocumento(code, titulo, prioridad, contenido, LocalDateTime.now());

	}

	public void agregarDocumento(Documento documento) throws CentroImpresionException {
		if (!listaDocumentos.add(documento))
			throw new CentroImpresionException(TipoCentroException.ADD, documento);
		Collections.sort(listaDocumentos);
	}

	public void agregarImpresoraCartucho(String code, String marca, EstadoImpresora estado, boolean esAColor,
			double paginasPorMinuto, double capacidadCartucho, double desgasteCartucho)
			throws CentroImpresionException {
		ImpresoraCartucho impresora = new ImpresoraCartucho(code, marca, estado, esAColor, paginasPorMinuto,
				capacidadCartucho, desgasteCartucho);
		if (!listaImpresoras.add(impresora))
			throw new CentroImpresionException(TipoCentroException.ADD, impresora);
	}

	public Documento buscarDocumento(String code) {
		return listaDocumentos.stream().filter(doc -> doc.getCode().equals(code)).findAny().orElse(null);
	}

	public boolean validarDocumento(String code) {
		return buscarDocumento(code) != null;
	}

	public Impresora buscarImpresora(String code) {
		return listaImpresoras.stream().filter(doc -> doc.getCode().equals(code)).findAny().orElse(null);
	}

	public boolean validarImpresora(String code) {
		return buscarImpresora(code) != null;
	}

	public Documento obtenerPrimerElementoDocumento() {
		return listaDocumentos.stream().findFirst().orElse(null);

	}

	public Impresora obtenerPrimerElementoImpresora() {
		return listaImpresoras.stream().findFirst().orElse(null);
	}

	public void deleteDocumento(String code) throws CentroImpresionException {
		if (!listaDocumentos.remove(buscarDocumento(code)))
			throw new CentroImpresionException(TipoCentroException.REMOVE, Documento.class);
		Collections.sort(listaDocumentos);
	}

	public void deleteImpresora(String code) throws CentroImpresionException {
		if (!listaImpresoras.remove(buscarImpresora(code)))
			throw new CentroImpresionException(TipoCentroException.REMOVE, Impresora.class);
	}

	public void actualizarImpresora(Impresora impresora) throws CentroImpresionException {

		if (listaImpresoras.remove(impresora)) {
			listaImpresoras.add(impresora);
		}
		throw new CentroImpresionException(TipoCentroException.UPDATE, impresora);
	}

	public boolean imprimirDocumento() throws CentroImpresionException {
		Impresora impresora = obtenerPrimerElementoImpresora();
		Documento documento = obtenerPrimerElementoDocumento();
		if (imprimir(impresora, documento)) {
			actualizarImpresora(impresora);
		}
		return false;
	}

	private boolean imprimir(Impresora impresora, Documento documento) throws CentroImpresionException {
		if (impresora == null)
			throw new CentroImpresionException(TipoCentroException.NULL, impresora);
		if (documento == null)
			throw new CentroImpresionException(TipoCentroException.NULL, documento);
		return impresora.imprimirDocumento(documento);
	}

	public boolean imprimirDocumento(String codeImpresora) throws CentroImpresionException {
		Impresora impresora = buscarImpresora(codeImpresora);
		Documento documento = obtenerPrimerElementoDocumento();

		if (imprimir(impresora, documento)) {
			actualizarImpresora(impresora);
		}
		return false;
	}

	public boolean imprimirDocumento(String codeImpresora, String codeDocumento) throws CentroImpresionException {
		Impresora impresora = buscarImpresora(codeImpresora);
		Documento documento = buscarDocumento(codeDocumento);

		if (imprimir(impresora, documento)) {
			actualizarImpresora(impresora);
		}
		return false;
	}

	@Override
	public String toString() {
		return "CentroImpresion [listaImpresoras=" + listaImpresoras + ", listaDocumentos=" + listaDocumentos + "]";
	}

	public void agregarImpresoraLaser(String code, String marca, EstadoImpresora estado, boolean esAColor,
			double paginasPorMinuto, int duracionToner) throws CentroImpresionException {
		ImpresoraLaser impresora = new ImpresoraLaser(code, marca, estado, esAColor, paginasPorMinuto, duracionToner);
		if (!listaImpresoras.add(impresora))
			throw new CentroImpresionException(TipoCentroException.ADD, impresora);
	}

	public Set<Impresora> getListaImpresoras() {
		return listaImpresoras;
	}

	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

}
