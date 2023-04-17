package co.edu.uniquindio.centroimpresion.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

import co.edu.uniquindio.centroimpresion.application.Main;
import co.edu.uniquindio.centroimpresion.exceptions.ArchivoNoObtenidoException;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.NoSePuedeLeerException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjetoFaltanteException;
import co.edu.uniquindio.centroimpresion.model.Documento;
import co.edu.uniquindio.centroimpresion.util.Utility;
import co.edu.uniquindio.centroimpresion.view.scenes.EscenaVerDoc;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class CtrlPanelAddDoc {

	/**
	 * Agrega un documento por medio de un codigo y una prioridad, abre un
	 * {@code Filechooser} para pedir el archivo del documento y al final lo agrega
	 * <br>
	 * Muestra alertas en caso de que no se den las cosas correctamente
	 * 
	 * @see
	 *      <li>{@link #obtenerDocArchivoThrow(String, String)}
	 *      <li>{@link #pedirDocumento(String, String)}
	 * @param stage
	 * @param textoCodigo
	 * @param textoPrioridad
	 */
	public static void agregarDocumento(Stage stage, String textoCodigo, String textoPrioridad) {
		try {
			Documento documentoAgregado = obtenerDocArchivoThrow(textoCodigo, textoPrioridad);
			Alert alertaExito = new Alert(AlertType.INFORMATION,
					"El documento se ha agregado con éxito (" + textoCodigo + ")" + "\n" + "¿Deseas ver el documento?",
					ButtonType.CANCEL, ButtonType.OK);
			ButtonType button = alertaExito.showAndWait().orElse(null);
			// abre el documento
			if (button == ButtonType.OK) {
				EscenaVerDoc escenaVerDoc = new EscenaVerDoc(stage, stage.getScene(), documentoAgregado, 800, 600);
				escenaVerDoc.getStylesheets().add(Main.css.toExternalForm());
				stage.setScene(escenaVerDoc);
			}

		} catch (ArchivoNoObtenidoException e) {
			new Alert(AlertType.ERROR, "El archivo no pudo ser obtenido").show();
		} catch (CentroImpresionException e) {
			ButtonType buttonType = new Alert(AlertType.WARNING,
					"Ya se encuentra un documento con tal código\n" + "¿Deseas ver el documento?", ButtonType.OK,
					ButtonType.CANCEL).showAndWait().orElse(null);
			// abre el documento
			if (buttonType == ButtonType.OK) {
				EscenaVerDoc escenaVerDoc = new EscenaVerDoc(stage, stage.getScene(), (Documento) e.getSource(), 800,
						600);
				escenaVerDoc.getStylesheets().add(Main.css.toExternalForm());
				stage.setScene(escenaVerDoc);
			}
		} catch (NoSePuedeLeerException e) {
			new Alert(AlertType.ERROR, "El archivo no se puede leer").show();
		} catch (FueraRangoException e) {
			new Alert(AlertType.WARNING, "La prioridad debe de estar entre 0 y 10").show();
		} catch (ObjetoFaltanteException e) {
			new Alert(AlertType.WARNING, "El código está vacío").show();
		}
	}

	static void throwCaseNotInRange(int prioridad) throws FueraRangoException {
		if (prioridad < 0 || prioridad > 10) {
			throw new FueraRangoException("La prioridad tiene que ser entre 0 y 10");
		}
	}

	/**
	 * Permite agregar un documento a partir de su c�digo, prioridad y el t�tulo de
	 * la ventana
	 *
	 * @param code          es el c�digo del documento
	 * @param prioridad     es la prioridad del documento
	 * @param tituloVentana es el titulo de la ventana a abrir
	 * @return null si no se puede leer el documento
	 * @throws ArchivoNoObtenidoException si no se pudo obtener el documento
	 * @throws NoSePuedeLeerException
	 */
	static Documento pedirDocumento(String code, int prioridad, String tituloVentana, FiltroExtension... filtros)
			throws ArchivoNoObtenidoException, NoSePuedeLeerException {
		File file = CtrlObtenerArchivo.obtenerArchivo(tituloVentana,
				FiltroExtension.obtenerExtensionFiltersDeFiltroExtension(filtros));
		if (file == null) {
			throw new ArchivoNoObtenidoException();
		}
		return obtenerDocumentoArchivo(code, file, prioridad);
	}

	/**
	 * Obtiene un documento a partir de su archivo, c�digo y prioridad; del archivo
	 * sale el titulo y el contenido
	 *
	 * @param code
	 * @param archivo
	 * @param prioridad
	 * @return null si el archivo no se puede leer
	 * @throws NoSePuedeLeerException
	 * @throws FileNotFoundException
	 */
	private static Documento obtenerDocumentoArchivo(String code, File archivo, int prioridad)
			throws NoSePuedeLeerException {
		if (!archivo.canRead()) {
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
	 * @throws ObjetoFaltanteException
	 */
	private static Documento obtenerDocArchivoThrow(String textoCodigo, String textoPrioridad)
			throws ArchivoNoObtenidoException, CentroImpresionException, NoSePuedeLeerException, FueraRangoException,
			ObjetoFaltanteException {

		throwifDocExist(textoCodigo);
		int prioridad = 5;
		try {
			prioridad = Integer.parseInt(textoPrioridad);
		} catch (NumberFormatException e) {
		}
		Utility.throwIfEmpty(textoCodigo, "codigo");
		throwCaseNotInRange(prioridad);
		Documento documento = pedirDocumento(textoCodigo, prioridad, "Agregar Documento",
				new FiltroExtension("Documentos de texto", "*.txt"), new FiltroExtension("Todos los archivos", "*.*"));

		SerializedData data = new SerializedData();
		data.getCentroImpresion().agregarDocumento(documento);
		data.updateCentroImpresion();
		return documento;
	}

	/**
	 * Muestra un error en caso de que el documento exxista en el centro de
	 * impresion
	 * 
	 * @param code
	 * @throws CentroImpresionException
	 */
	private static void throwifDocExist(String code) throws CentroImpresionException {
		SerializedData data = new SerializedData();
		if (data.getCentroImpresion().validarDocumento(code))
			throw new CentroImpresionException("El documento ya existe", code);

	}
}
