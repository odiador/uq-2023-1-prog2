package co.edu.uniquindio.centroimpresion.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

import co.edu.uniquindio.centroimpresion.exceptions.ArchivoNoObtenidoException;
import co.edu.uniquindio.centroimpresion.exceptions.DocumentoEnProcesoException;
import co.edu.uniquindio.centroimpresion.model.archivos.FiltroExtension;
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
	 */
	public static Documento pedirDocumento(String textoCodigo, String textoPrioridad)
			throws DocumentoEnProcesoException, ArchivoNoObtenidoException {
		if (seEstaPidiendo)
			throw new DocumentoEnProcesoException();
		seEstaPidiendo = true;
		int prioridad = 5;
		try {
			prioridad = Integer.parseInt(textoPrioridad);
		} catch (NumberFormatException e) {
		}
		Documento documento = pedirDocumento(textoCodigo, prioridad, "Agregar Documento",
				new FiltroExtension("Documentos de texto", "*.txt"), new FiltroExtension("Todos los archivos", "*.*"));
		seEstaPidiendo = false;
		return documento;
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
	 */
	public static Documento pedirDocumento(String code, int prioridad, String tituloVentana, FiltroExtension... filtros)
			throws ArchivoNoObtenidoException {
		File file = CtrlAgregarDocumento.obtenerArchivo(tituloVentana,
				CtrlAgregarDocumento.obtenerExtensionFiltersDeFiltroExtension(filtros));
		if (file == null)
			throw new ArchivoNoObtenidoException();
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
	 * @throws FileNotFoundException
	 */
	public static Documento obtenerDocumentoArchivo(String code, File archivo, int prioridad) {
		if (!archivo.canRead()) {
			return null;
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
		return new Documento(code, CtrlAgregarDocumento.quitarExtension(archivo.getName()), prioridad, contenido,
				LocalDateTime.now());
	}
}
