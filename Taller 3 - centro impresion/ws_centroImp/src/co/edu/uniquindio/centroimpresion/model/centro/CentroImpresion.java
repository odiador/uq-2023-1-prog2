package co.edu.uniquindio.centroimpresion.model.centro;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class CentroImpresion implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -2839899210301744900L;
	private final Set<Impresora> listaImpresoras = new HashSet<Impresora>();
	private final TreeSet<Documento> listaDocumentos = new TreeSet<Documento>();

	public CentroImpresion() {
	}

	public boolean agregarDocumento(String code, String titulo, int prioridad, String contenido) {
		Documento documento = new Documento(code, titulo, prioridad, contenido, LocalDateTime.now());
		return listaDocumentos.add(documento);
	}

	public boolean agregarImpresoraCartucho(String code, String marca, EstadoImpresora estado, boolean esAColor,
			double paginasPorMinuto, double capacidadCartucho, double desgasteCartucho) {
		ImpresoraCartucho impresora = new ImpresoraCartucho(code, marca, estado, esAColor, paginasPorMinuto,
				capacidadCartucho, desgasteCartucho);
		return listaImpresoras.add(impresora);
	}

	public Documento buscarDocumento(String code) {
		return listaDocumentos.stream().filter(doc -> doc.getCode().equals(code)).findAny().orElse(null);
	}

	public Impresora buscarImpresora(String code) {
		return listaImpresoras.stream().filter(doc -> doc.getCode().equals(code)).findAny().orElse(null);
	}

	public Documento obtenerPrimerElementoDocumento() {
		return listaDocumentos.stream().findFirst().orElse(null);

	}

	public Impresora obtenerPrimerElementoImpresora() {
		return listaImpresoras.stream().findFirst().orElse(null);
	}

	public boolean deleteDocumento(String code) {
		return listaDocumentos.remove(buscarDocumento(code));
	}

	public boolean actualizarImpresora(Impresora impresora) {
		if (listaImpresoras.remove(impresora))
			return listaImpresoras.add(impresora);

		return false;
	}

	public boolean imprimirDocumento() {
		Impresora impresora = obtenerPrimerElementoImpresora();
		Documento documento = obtenerPrimerElementoDocumento();
		if (imprimir(impresora, documento)) {
			return actualizarImpresora(impresora);
		}
		return false;
	}

	private boolean imprimir(Impresora impresora, Documento documento) {
		if (impresora == null || documento == null)
			return false;
		return impresora.imprimirDocumento(documento);
	}

	public boolean imprimirDocumento(String codeImpresora) {
		Impresora impresora = buscarImpresora(codeImpresora);
		Documento documento = obtenerPrimerElementoDocumento();

		if (imprimir(impresora, documento)) {
			return actualizarImpresora(impresora);
		}
		return false;
	}

	public boolean imprimirDocumento(String codeImpresora, String codeDocumento) {
		Impresora impresora = buscarImpresora(codeImpresora);
		Documento documento = buscarDocumento(codeDocumento);

		if (imprimir(impresora, documento)) {
			return actualizarImpresora(impresora);
		}
		return false;
	}

	@Override
	public String toString() {
		return "CentroImpresion [listaImpresoras=" + listaImpresoras + ", listaDocumentos=" + listaDocumentos + "]";
	}

}
