package co.edu.uniquindio.centroimpresion.model.centro;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.ImpresoraException;
import co.edu.uniquindio.centroimpresion.exceptions.NoHayColaImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjectNotExists;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;

public class CentroImpresion implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -2839899210301744900L;
	private final List<Impresora> listaImpresoras = new ArrayList<Impresora>();
	private final List<Documento> listaDocumentos = new ArrayList<Documento>();
	private final List<Documento> listaDocumentosImpresos = new ArrayList<Documento>();

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
			throw new CentroImpresionException("El documento ya existe", documento);
		Collections.sort(listaDocumentos);
	}

	public void agregarImpresoraCartucho(String code, String marca, EstadoImpresora estado, boolean esAColor,
			double paginasPorMinuto, double capacidadCartucho, double desgasteCartucho)
			throws CentroImpresionException {
		ImpresoraCartucho impresora = new ImpresoraCartucho(code, marca, estado, esAColor, paginasPorMinuto,
				capacidadCartucho, desgasteCartucho);
		if (!listaImpresoras.add(impresora))
			throw new CentroImpresionException("La impresora ya existe", impresora);
	}

	public Documento buscarDocumento(String code) {
		return listaDocumentos.stream().filter(doc -> doc.getCode().equals(code)).findAny().orElse(null);
	}

	public Documento buscarDocumentoThrows(String code) throws ObjectNotExists {
		return listaDocumentos.stream().filter(doc -> doc.getCode().equals(code)).findAny().orElseThrow(() -> {
			return new ObjectNotExists(Documento.class);
		});
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

	public Documento obtenerPrimerElementoDocumento() throws NoHayColaImpresionException {
		return getListaDocumentos().stream().findFirst().orElseThrow(NoHayColaImpresionException::new);
	}

	public ArrayList<Documento> obtenerListaOrdenadaTabla() {
		// Clona la lista de documentos pero no la asigna directamente a otra pera
		// evitar problemas
		List<Documento> listaDocumentos = this.listaDocumentos.stream()
				.collect(Collectors.toCollection(ArrayList::new));
		listaDocumentos.addAll(listaDocumentosImpresos);
		return listaDocumentos.stream().collect(Collectors.toCollection(ArrayList::new));
	}

	public Impresora obtenerPrimerElementoImpresora() {
		return listaImpresoras.stream().findFirst().orElse(null);
	}

	public void deleteDocumento(String code) throws CentroImpresionException {
		if (!listaDocumentos.remove(buscarDocumento(code)))
			throw new CentroImpresionException("El documento no fue encontrado", Documento.class);
		Collections.sort(listaDocumentos);
	}

	public void deleteImpresora(String code) throws CentroImpresionException {
		if (!listaImpresoras.remove(buscarImpresora(code)))
			throw new CentroImpresionException("La impresora no fue encontrada", Impresora.class);
	}

	public void actualizarDocumento(Documento documento) throws CentroImpresionException {
		deleteDocumento(documento.getCode());
		listaDocumentos.add(documento);
	}

	public void actualizarImpresora(Impresora impresora) throws CentroImpresionException {

		if (!listaImpresoras.remove(impresora))
			throw new CentroImpresionException("La impresora no fue encontrada", impresora);
		listaImpresoras.add(impresora);

	}

	public Relacion<Impresora, Documento> imprimirDocumentoSoloDoc(String codeDocumento)
			throws CentroImpresionException, NoHayColaImpresionException, ImpresoraException {
		Impresora impresora = obtenerPrimerElementoImpresora();
		Documento documento = buscarDocumento(codeDocumento);
		return imprimir(impresora, documento);
	}

	public Relacion<Impresora, Documento> imprimirDocumento()
			throws CentroImpresionException, NoHayColaImpresionException, ImpresoraException {
		Impresora impresora = obtenerPrimerElementoImpresora();
		Documento documento = obtenerPrimerElementoDocumento();
		return imprimir(impresora, documento);
	}

	public Relacion<Impresora, Documento> imprimirDocumento(String codeImpresora)
			throws CentroImpresionException, ImpresoraException, NoHayColaImpresionException {
		Impresora impresora = buscarImpresora(codeImpresora);
		Documento documento = obtenerPrimerElementoDocumento();

		return imprimir(impresora, documento);
	}

	public Relacion<Impresora, Documento> imprimirDocumento(String codeImpresora, String codeDocumento)
			throws CentroImpresionException, ImpresoraException {
		Impresora impresora = buscarImpresora(codeImpresora);
		Documento documento = buscarDocumento(codeDocumento);

		return imprimir(impresora, documento);
	}

	private void actualizarListas(Documento documento) {
		if (listaDocumentos.remove(buscarDocumento(documento.getCode()))) {
			listaDocumentosImpresos.add(documento);
			Collections.sort(listaDocumentosImpresos);
		}
	}

	private Relacion<Impresora, Documento> imprimir(Impresora impresora, Documento documento)
			throws CentroImpresionException, ImpresoraException {
		if (impresora == null)
			throw new CentroImpresionException("La impresora no fue encontrada", Impresora.class);
		if (documento == null)
			throw new CentroImpresionException("El documento no fue encontrada", new Documento());
		impresora.imprimirDocumento(LocalDateTime.now(), documento);
		actualizarImpresora(impresora);
		actualizarListas(documento);

		return new Relacion<>(impresora, documento);
	}

	@Override
	public String toString() {
		return "CentroImpresion [listaImpresoras=" + listaImpresoras + ", listaDocumentos=" + listaDocumentos + "]";
	}

	public void agregarImpresoraLaser(String code, String marca, EstadoImpresora estado, boolean esAColor,
			double paginasPorMinuto, int duracionToner) throws CentroImpresionException {
		ImpresoraLaser impresora = new ImpresoraLaser(code, marca, estado, esAColor, paginasPorMinuto, duracionToner);
		if (!listaImpresoras.add(impresora))
			throw new CentroImpresionException("La impresora ya existe", impresora);
	}

	public List<Impresora> getListaImpresoras() {
		return listaImpresoras;
	}

	public HashSet<ImpresoraLaser> getListaImpresorasLaser() {
		return listaImpresoras.stream().filter(impresora -> impresora instanceof ImpresoraLaser)
				.map(impresora -> (ImpresoraLaser) impresora).collect(Collectors.toCollection(HashSet::new));
	}

	public HashSet<ImpresoraCartucho> getListaImpresorasCartucho() {
		return listaImpresoras.stream().filter(impresora -> impresora instanceof ImpresoraCartucho)
				.map(impresora -> (ImpresoraCartucho) impresora).collect(Collectors.toCollection(HashSet::new));
	}

	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	public void recargarImpresora(String code) throws CentroImpresionException {
		Impresora impresora = buscarImpresora(code);
		if (impresora == null)
			throw new CentroImpresionException("La impresora no fue encontrada", Impresora.class);
		impresora.recargar();
	}

	public void seleccionarImpresora(String code) throws CentroImpresionException {
		Impresora impresora = buscarImpresora(code);
		if (impresora == null)
			throw new CentroImpresionException("La impresora no fue encontrada", Impresora.class);
		int indiceImpresora = listaImpresoras.indexOf(impresora);
		if (indiceImpresora == 0)
			throw new CentroImpresionException("La impresora ya esa seleccionada", impresora);
		Impresora impresora2 = listaImpresoras.get(0);
		listaImpresoras.set(0, impresora);
		listaImpresoras.set(indiceImpresora, impresora2);
	}

}
