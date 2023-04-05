package co.edu.uniquindio.centroimpresion.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

import co.edu.uniquindio.centroimpresion.exceptions.ArchivoNoObtenidoException;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.exceptions.DocumentoEnProcesoException;
import co.edu.uniquindio.centroimpresion.exceptions.NoSePuedeLeerException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.TipoCentroException;
import co.edu.uniquindio.centroimpresion.model.archivos.FiltroExtension;
import co.edu.uniquindio.centroimpresion.model.archivos.SerializedData;
import co.edu.uniquindio.centroimpresion.model.centro.Documento;

public class CtrlPanelAddDoc {
	public static boolean seEstaPidiendo = false;

	/**
	 * Obtiene un documento del panel
	 *
	 * @param textoCodigo
	 * @param textoPrioridad
	 * @return
	 * @throws DocumentoEnProcesoException
	 * @throws ArchivoNoObtenidoException
	 * @throws NoSePuedeLeerException
	 * @throws FueraRangoException
	 * @throws TextIsEmptyException
	 */
	public static Documento pedirDocumento(String textoCodigo, String textoPrioridad)
			throws DocumentoEnProcesoException, ArchivoNoObtenidoException, NoSePuedeLeerException, FueraRangoException,
			TextIsEmptyException {
		if (seEstaPidiendo)
			throw new DocumentoEnProcesoException();
		seEstaPidiendo = true;
		int prioridad = 5;
		try {
			prioridad = Integer.parseInt(textoPrioridad);
		} catch (NumberFormatException e) {
		}
		throwIfEmpty(textoCodigo);
		throwCaseNotInRange(prioridad);
		Documento documento = pedirDocumento(textoCodigo, prioridad, "Agregar Documento",
				new FiltroExtension("Documentos de texto", "*.txt"), new FiltroExtension("Todos los archivos", "*.*"));
		seEstaPidiendo = false;
		return documento;
	}

	private static void throwIfEmpty(String textoCodigo) throws TextIsEmptyException {
		if (textoCodigo.isEmpty()) {
			seEstaPidiendo = false;
			throw new TextIsEmptyException("codigo");
		}
	}

	private static void throwCaseNotInRange(int prioridad) throws FueraRangoException {
		if (prioridad < 0 || prioridad > 10) {
			seEstaPidiendo = false;
			throw new FueraRangoException("La prioridad tiene que ser entre 0 y 10");
		}
	}

	/**
	 * Permite agregar un documento a partir de su código, prioridad y el título
	 * de la ventana
	 *
	 * @param code
	 *            es el código del documento
	 * @param prioridad
	 *            es la prioridad del documento
	 * @param tituloVentana
	 *            es el titulo de la ventana a abrir
	 * @return null si no se puede leer el documento
	 * @throws ArchivoNoObtenidoException
	 *             si no se pudo obtener el documento
	 * @throws NoSePuedeLeerException
	 */
	public static Documento pedirDocumento(String code, int prioridad, String tituloVentana, FiltroExtension... filtros)
			throws ArchivoNoObtenidoException, NoSePuedeLeerException {
		File file = CtrlObtenerArchivo.obtenerArchivo(tituloVentana,
				CtrlObtenerArchivo.obtenerExtensionFiltersDeFiltroExtension(filtros));
		if (file == null) {
			seEstaPidiendo = false;
			throw new ArchivoNoObtenidoException();
		}
		return obtenerDocumentoArchivo(code, file, prioridad);
	}

	/**
	 * Obtiene un documento a partir de su archivo, código y prioridad; del
	 * archivo sale el titulo y el contenido
	 *
	 * @param code
	 * @param archivo
	 * @param prioridad
	 * @return null si el archivo no se puede leer
	 * @throws NoSePuedeLeerException
	 * @throws FileNotFoundException
	 */
	public static Documento obtenerDocumentoArchivo(String code, File archivo, int prioridad)
			throws NoSePuedeLeerException {
		if (!archivo.canRead()) {
			seEstaPidiendo = false;
			throw new NoSePuedeLeerException();
		}
		String contenido = "";
		try {
			Scanner conexionArchivo = new Scanner(new FileInputStream(archivo));
			if (conexionArchivo.hasNextLine()) {
				contenido += conexionArchivo.nextLine();
			}
			while (conexionArchivo.hasNextLine())
				contenido += "\n" + conexionArchivo.nextLine();

			conexionArchivo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Documento(code, CtrlObtenerArchivo.quitarExtension(archivo.getName()), prioridad, contenido,
				LocalDateTime.now());
	}

	/**
	 * Agrega un documento por medio de un FileChooser, abre una ventana para
	 * obtener el documento y luego lo intenta a gregar al centro de impresion
	 *
	 * @param textoCodigo
	 * @param textoPrioridad
	 * @throws DocumentoEnProcesoException
	 * @throws ArchivoNoObtenidoException
	 * @throws CentroImpresionException
	 * @throws NoSePuedeLeerException
	 * @throws FueraRangoException
	 * @throws TextIsEmptyException
	 */
	public static void agregarDocumento(String textoCodigo, String textoPrioridad)
			throws DocumentoEnProcesoException, ArchivoNoObtenidoException, CentroImpresionException,
			NoSePuedeLeerException, FueraRangoException, TextIsEmptyException {

		throwifDocExist(textoCodigo);
		Documento doc = pedirDocumento(textoCodigo, textoPrioridad);

		SerializedData data = new SerializedData();
		data.getCentroImpresion().agregarDocumento(doc.getCode(), doc.getTitulo(), doc.getPrioridad(),
				doc.getContenido());
		data.updateCentroImpresion();
	}

	public static void throwifDocExist(String code) throws CentroImpresionException {
		SerializedData data = new SerializedData();
		if (data.getCentroImpresion().validarDocumento(code))
			throw new CentroImpresionException(TipoCentroException.ADD, Documento.class);

	}
}
