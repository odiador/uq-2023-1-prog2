package co.edu.uniquindio.centroimpresion.model;

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
import co.edu.uniquindio.centroimpresion.util.Relacion;

public class CentroImpresion implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -2839899210301744900L;
	private final List<Impresora> listaImpresoras = new ArrayList<Impresora>();
	private final List<Documento> listaDocumentos = new ArrayList<Documento>();
	private final List<Documento> listaDocumentosImpresos = new ArrayList<Documento>();

	/**
	 * Es el constructor del centro de impresion
	 */
	public CentroImpresion() {
		super();
	}

	/**
	 * Agrega un documento con los parametros base
	 * 
	 * @param code
	 * @param titulo
	 * @param prioridad
	 * @param contenido
	 * @param fechaAgregado
	 * @throws CentroImpresionException en caso de que el documetno ya exista
	 */
	public void agregarDocumento(String code, String titulo, int prioridad, String contenido,
			LocalDateTime fechaAgregado) throws CentroImpresionException {
		agregarDocumento(new Documento(code, titulo, prioridad, contenido, fechaAgregado));
	}

	/**
	 * Agrega un documento con los parametros base sin contar la fecha de agregado,
	 * colocandolo como la fecha exacta
	 * 
	 * @param code
	 * @param titulo
	 * @param prioridad
	 * @param contenido
	 * @throws CentroImpresionException en caso de que el documetno ya exista
	 */
	public void agregarDocumento(String code, String titulo, int prioridad, String contenido)
			throws CentroImpresionException {
		agregarDocumento(code, titulo, prioridad, contenido, LocalDateTime.now());

	}

	/**
	 * Agrega un documento {@code documento}
	 * 
	 * @param documento
	 * @throws CentroImpresionException en caso de que el documetno ya exista
	 */
	public void agregarDocumento(Documento documento) throws CentroImpresionException {
		if (validarDocumento(documento.getCode()))
			throw new CentroImpresionException("El documento ya existe", documento);
		listaDocumentos.add(documento);
		Collections.sort(listaDocumentos);
	}

	/**
	 * Agrega una impresora de cartucho con sus atributos
	 * 
	 * @param code
	 * @param marca
	 * @param estado
	 * @param esAColor
	 * @param paginasPorMinuto
	 * @param capacidadCartucho
	 * @param desgasteCartucho
	 * @throws CentroImpresionException en caso de que la impresora ya exista
	 */
	public void agregarImpresoraCartucho(String code, String marca, EstadoImpresora estado, boolean esAColor,
			double paginasPorMinuto, double capacidadCartucho, double desgasteCartucho)
			throws CentroImpresionException {
		ImpresoraCartucho impresora = new ImpresoraCartucho(code, marca, estado, esAColor, paginasPorMinuto,
				capacidadCartucho, desgasteCartucho);
		if (!listaImpresoras.add(impresora))
			throw new CentroImpresionException("La impresora ya existe", impresora);
	}

	/**
	 * Busca un documento por medio de un codigo {@code code}, si no se encuentra
	 * retorna un null
	 * 
	 * @param code
	 * @return
	 */
	public Documento buscarDocumento(String code) {
		return listaDocumentos.stream().filter(doc -> doc.getCode().equals(code)).findAny().orElse(null);
	}

	/**
	 * Busca un documento por medio de un codigo {@code code}, si no se encuentra
	 * muestra un error
	 * 
	 * @param code
	 * @return el documento
	 * @throws CentroImpresionException
	 */
	public Documento buscarDocumentoThrows(String code) throws CentroImpresionException {
		return listaDocumentos.stream().filter(doc -> doc.getCode().equals(code)).findAny()
				.orElseThrow(() -> new CentroImpresionException("El documento no fue encontrado", code));
	}

	/**
	 * Valida si un documento existe o no
	 * 
	 * @param code
	 * @return true si existe
	 */
	public boolean validarDocumento(String code) {
		return buscarDocumento(code) != null;
	}

	/**
	 * Busca una impresora por medio de un codigo {@code code}, si no se encuentra
	 * retorna un null
	 * 
	 * @param code
	 * @return
	 */
	public Impresora buscarImpresora(String code) {
		return listaImpresoras.stream().filter(doc -> doc.getCode().equals(code)).findAny().orElse(null);
	}

	/**
	 * Busca una impresora por medio de un codigo {@code code}, si no se encuentra
	 * muestra un error
	 * 
	 * @param code
	 * @return
	 * @throws CentroImpresionException
	 */
	public Impresora buscarImpresoraThrows(String code) throws CentroImpresionException {
		return listaImpresoras.stream().filter(doc -> doc.getCode().equals(code)).findAny()
				.orElseThrow(() -> new CentroImpresionException("La impresora no fue encontrada", code));
	}

	/**
	 * Valida si la impresora existe o no a partir de su codigo {@code code}
	 * 
	 * @param code
	 * @return true si se encuentra
	 */
	public boolean validarImpresora(String code) {
		return buscarImpresora(code) != null;
	}

	/**
	 * Obtiene el primer documento en cola
	 * 
	 * @return
	 * @throws NoHayColaImpresionException si no hay cola de excepcion
	 */
	public Documento obtenerPrimerElementoDocumento() throws NoHayColaImpresionException {
		return getListaDocumentos().stream().findFirst().orElseThrow(NoHayColaImpresionException::new);
	}

	/**
	 * Obtiene la primer impresora en la lista
	 * 
	 * @return la primer impresora, null si no se encuentra
	 */
	public Impresora obtenerPrimerElementoImpresora() {
		return listaImpresoras.stream().findFirst().orElse(null);
	}

	/**
	 * Obtiene la primer impresora en la lista
	 * 
	 * @return la primer impresora
	 * @throws CentroImpresionException si no hay impresoras
	 */
	public Impresora obtenerPrimerElementoImpresoraThrows() throws CentroImpresionException {
		return listaImpresoras.stream().findFirst().orElseThrow(() -> {
			CentroImpresionException centroImpresionException = new CentroImpresionException(
					"No hay impresoras en el centor de impresion", Impresora.class);
			return centroImpresionException;
		});
	}

	/**
	 * Elimina un documento de la cola por medio de su codigo {@code code}
	 * 
	 * @param code
	 * @throws CentroImpresionException en caso de que no se encuentre
	 */
	public void deleteDocumento(String code) throws CentroImpresionException {
		Documento doc = buscarDocumento(code);
		if (doc == null)
			throw new CentroImpresionException("El documento no fue encontrado", Documento.class);
		listaDocumentos.remove(doc);
		Collections.sort(listaDocumentos);
	}

	/**
	 * Elimina una impresora por medio de su codigo {@code code}
	 * 
	 * @param code
	 * @throws CentroImpresionException en caso de que no se encuentre
	 */
	public void deleteImpresora(String code) throws CentroImpresionException {
		Impresora impresora = buscarImpresora(code);
		if (impresora == null)
			throw new CentroImpresionException("La impresora no fue encontrada", Impresora.class);
		listaImpresoras.remove(impresora);
	}

	/**
	 * Actualiza un documento {@code documento}
	 * 
	 * @param documento
	 * @throws CentroImpresionException en caso de que no se encuentre el documento
	 *                                  para actualizar
	 */
	public void actualizarDocumento(Documento documento) throws CentroImpresionException {
		deleteDocumento(documento.getCode());
		listaDocumentos.add(documento);
	}

	/**
	 * Actualiza una impresora {@code impresora}
	 * 
	 * @param impresora
	 * @throws CentroImpresionException en caso de que no se encuentre la impresora
	 *                                  para actualizar
	 */
	public void actualizarImpresora(Impresora impresora) throws CentroImpresionException {
		int index = listaImpresoras.indexOf(buscarImpresora(impresora.getCode()));
		if (index == -1)
			throw new CentroImpresionException("La impresora no fue encontrada", impresora);

		listaImpresoras.set(index, impresora);
	}

	/**
	 * Imprime un documento especifico en la primer impresora que se encuentre
	 * 
	 * @param codeDocumento
	 * @return
	 * @throws CentroImpresionException    en caso de que no se encuentre algo
	 * @throws NoHayColaImpresionException en caso de que no haya cola
	 * @throws ImpresoraException          en caso de que el nivel de la impresora
	 *                                     no alcance
	 */
	public Relacion<Impresora, Documento> imprimirDocumentoSoloDoc(String codeDocumento)
			throws CentroImpresionException, NoHayColaImpresionException, ImpresoraException {
		Impresora impresora = obtenerPrimerElementoImpresora();
		Documento documento = buscarDocumento(codeDocumento);
		return imprimir(impresora, documento);
	}

	/**
	 * Imprime el primer documento en la cola en la primer impresora que se
	 * encuentre
	 * 
	 * @return
	 * @throws CentroImpresionException    en caso de que no se encuentre algo
	 * @throws NoHayColaImpresionException en caso de que no haya cola
	 * @throws ImpresoraException          en caso de que el nivel de la impresora
	 *                                     no alcance
	 */
	public Relacion<Impresora, Documento> imprimirDocumento()
			throws CentroImpresionException, NoHayColaImpresionException, ImpresoraException {
		Impresora impresora = obtenerPrimerElementoImpresora();
		Documento documento = obtenerPrimerElementoDocumento();
		return imprimir(impresora, documento);
	}

	/**
	 * Imprime el primer documento en la cola en una impresora especifica
	 * 
	 * @param codeImpresora
	 * @return
	 * @throws CentroImpresionException    en caso de que no se encuentre algo
	 * @throws NoHayColaImpresionException en caso de que no haya cola
	 * @throws ImpresoraException          en caso de que el nivel de la impresora
	 *                                     no alcance
	 */
	public Relacion<Impresora, Documento> imprimirDocumento(String codeImpresora)
			throws CentroImpresionException, ImpresoraException, NoHayColaImpresionException {
		Impresora impresora = buscarImpresora(codeImpresora);
		Documento documento = obtenerPrimerElementoDocumento();

		return imprimir(impresora, documento);
	}

	/**
	 * Imprime un documento espeficio en una impresora especifica
	 * 
	 * @param codeImpresora
	 * @param codeDocumento
	 * @return
	 * @throws CentroImpresionException en caso de que no se encuentre algo
	 * @throws ImpresoraException       en caso de que el nivel de la impresora no
	 *                                  alcance
	 */
	public Relacion<Impresora, Documento> imprimirDocumento(String codeImpresora, String codeDocumento)
			throws CentroImpresionException, ImpresoraException {
		Impresora impresora = buscarImpresora(codeImpresora);
		Documento documento = buscarDocumento(codeDocumento);

		return imprimir(impresora, documento);
	}

	/**
	 * Actualia la lista de documentos despues de imprimir
	 * 
	 * @param documento
	 */
	private void actualizarListasDocumentos(Documento documento) {
		Documento documentoEncontrado = buscarDocumento(documento.getCode());
		if (documentoEncontrado != null) {
			listaDocumentos.remove(documentoEncontrado);
			listaDocumentosImpresos.add(documento);
			Collections.sort(listaDocumentosImpresos);
		}
	}

	/**
	 * Imprime un documento con una impresora
	 * 
	 * @param impresora
	 * @param documento
	 * @return
	 * @throws CentroImpresionException en caso de que algo no se encuentre
	 * @throws ImpresoraException       en caso de que el nivel de la impresora no
	 *                                  alcance
	 */
	private Relacion<Impresora, Documento> imprimir(Impresora impresora, Documento documento)
			throws CentroImpresionException, ImpresoraException {
		if (impresora == null)
			throw new CentroImpresionException("La impresora no fue encontrada", Impresora.class);
		if (documento == null)
			throw new CentroImpresionException("El documento no fue encontrada", new Documento());
		impresora.imprimirDocumento(LocalDateTime.now(), documento);
		actualizarImpresora(impresora);
		actualizarListasDocumentos(documento);

		return new Relacion<>(impresora, documento);
	}

	@Override
	public String toString() {
		return "CentroImpresion [listaImpresoras=" + listaImpresoras + ", listaDocumentos=" + listaDocumentos + "]";
	}

	/**
	 * Agrega una impresora laser con sus atributos
	 * 
	 * @param code
	 * @param marca
	 * @param estado
	 * @param esAColor
	 * @param paginasPorMinuto
	 * @param duracionToner
	 * @throws CentroImpresionException en caso de que ya se encuentre muestra un
	 *                                  error
	 */
	public void agregarImpresoraLaser(String code, String marca, EstadoImpresora estado, boolean esAColor,
			double paginasPorMinuto, int duracionToner) throws CentroImpresionException {
		ImpresoraLaser impresora = new ImpresoraLaser(code, marca, estado, esAColor, paginasPorMinuto, duracionToner);
		if (!listaImpresoras.add(impresora))
			throw new CentroImpresionException("La impresora ya existe", impresora);
	}

	/**
	 * Obtiene la lista de impresoras
	 * 
	 * @return
	 */
	public List<Impresora> getListaImpresoras() {
		return listaImpresoras;
	}

	/**
	 * Obtiene la lista de impresoras laser
	 * 
	 * @return
	 */
	public HashSet<ImpresoraLaser> getListaImpresorasLaser() {
		return listaImpresoras.stream().filter(impresora -> impresora instanceof ImpresoraLaser)
				.map(impresora -> (ImpresoraLaser) impresora).collect(Collectors.toCollection(HashSet::new));
	}

	/**
	 * 
	 * Obtiene la lista de impresoras de cartucho
	 * 
	 * @return
	 */
	public HashSet<ImpresoraCartucho> getListaImpresorasCartucho() {
		return listaImpresoras.stream().filter(impresora -> impresora instanceof ImpresoraCartucho)
				.map(impresora -> (ImpresoraCartucho) impresora).collect(Collectors.toCollection(HashSet::new));
	}

	/**
	 * Obtiene la cola de impresion
	 * 
	 * @return
	 */
	public List<Documento> getListaDocumentos() {
		return listaDocumentos;
	}

	/**
	 * Recarga una impresora obteniendola a partir del codigo {@code code}
	 * 
	 * @param code
	 * @throws CentroImpresionException en caso de que no sea encontrada
	 */
	public void recargarImpresora(String code) throws CentroImpresionException {
		Impresora impresora = buscarImpresora(code);
		if (impresora == null)
			throw new CentroImpresionException("La impresora no fue encontrada", Impresora.class);
		impresora.recargar();
	}

	/**
	 * Selecciona una impresora para la imprenta en caso de que no se seleccione un
	 * codigo de impresora
	 * 
	 * @param code
	 * @throws CentroImpresionException en caso de que ya este seleccionada o si no
	 *                                  fue encontrada
	 */
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

	/**
	 * Cambia el estado de una impresora por medio de su codigo y estado
	 * 
	 * @param code
	 * @param estadoImpresora
	 * @throws CentroImpresionException en caso de que no sea encontrada
	 */
	public void cambiarEstadoImpresora(String code, EstadoImpresora estadoImpresora) throws CentroImpresionException {
		Impresora impresora = buscarImpresora(code);
		if (impresora == null)
			throw new CentroImpresionException("La impresora no fue encontrada", Impresora.class);
		impresora.setEstado(estadoImpresora);
		actualizarImpresora(impresora);

	}

	public List<Documento> getListaDocumentosImpresos() {
		return listaDocumentosImpresos;
	}

}
