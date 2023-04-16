package co.edu.uniquindio.centroimpresion.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

import co.edu.uniquindio.centroimpresion.exceptions.ArchivoNoObtenidoException;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.NoSePuedeLeerException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjectNotExists;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.archivos.FiltroExtension;
import co.edu.uniquindio.centroimpresion.model.archivos.SerializedData;
import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.model.centro.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlUtil {

	public static void recargarImpresora(String code) {
		SerializedData data = new SerializedData();
		try {
			data.getCentroImpresion().recargarImpresora(code);
			data.updateCentroImpresion();
		} catch (CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void seleccionarImpresora(String code) {
		SerializedData data = new SerializedData();
		try {
			data.getCentroImpresion().seleccionarImpresora(code);
			data.updateCentroImpresion();
		} catch (CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void actualizarDocumento(String code, String prioridadString, boolean editarContenido) {
		try {
			actualizarDocumentoThrows(code, prioridadString, editarContenido);
		} catch (TextIsEmptyException | FueraRangoException | ArchivoNoObtenidoException | NoSePuedeLeerException
				| ObjectNotExists e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void actualizarDocumentoThrows(String code, String prioridadString, boolean editarContenido)
			throws TextIsEmptyException, FueraRangoException, ArchivoNoObtenidoException, NoSePuedeLeerException,
			ObjectNotExists {
		Utility.throwIfEmpty(code, "codigo");
		int prioridad = 5;
		try {
			prioridad = Integer.parseInt(prioridadString);
		} catch (NumberFormatException e) {
		}
		CtrlPanelAddDoc.throwCaseNotInRange(prioridad);
		if (editarContenido) {
			Documento documento = CtrlPanelAddDoc.pedirDocumento(code, prioridad, "Actualizar Documento",
					new FiltroExtension("Documentos de texto", "*.txt"),
					new FiltroExtension("Todos los archivos", "*.*"));
			actualizarDocumento(documento);
		} else {
			SerializedData data = new SerializedData();
			Documento buscarDocumentoThrows = data.getCentroImpresion().buscarDocumentoThrows(code);
			buscarDocumentoThrows.setPrioridad(prioridad);
			actualizarDocumento(buscarDocumentoThrows);

		}
	}

	public static Documento pedirDocumento(String code, int prioridad, String tituloVentana, FiltroExtension... filtros)
			throws ArchivoNoObtenidoException, NoSePuedeLeerException {
		File file = CtrlObtenerArchivo.obtenerArchivo(tituloVentana,
				CtrlObtenerArchivo.obtenerExtensionFiltersDeFiltroExtension(filtros));
		if (file == null) {
			throw new ArchivoNoObtenidoException();
		}
		if (!file.canRead()) {
			throw new NoSePuedeLeerException();
		}
		String contenido = "";
		try {
			Scanner conexionArchivo = new Scanner(new FileInputStream(file));
			if (conexionArchivo.hasNextLine()) {
				contenido += conexionArchivo.nextLine();
			}
			while (conexionArchivo.hasNextLine())
				contenido += "\n" + conexionArchivo.nextLine();

			conexionArchivo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return new Documento(code, CtrlObtenerArchivo.quitarExtension(file.getName()), prioridad, contenido,
				LocalDateTime.now());
	}

	private static void actualizarDocumento(Documento doc) {
		SerializedData data = new SerializedData();
		try {
			data.getCentroImpresion().actualizarDocumento(doc);
			data.updateCentroImpresion();
		} catch (CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage());
		}
	}

	public static void cambiarEstadoImpresora(String code, String estado) {
		try {
			cambiarEstadoImpresoraThrpws(code, estado);
		} catch (TextIsEmptyException | CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void cambiarEstadoImpresoraThrpws(String code, String estado)
			throws TextIsEmptyException, CentroImpresionException {
		Utility.throwIfEmpty(code, "codigo");
		Utility.throwIfNull(estado, "estado");
		EstadoImpresora estadoImpresora = EstadoImpresora.obtenerEstado(estado);

		Utility.throwIfNull(estadoImpresora, "estado");

		SerializedData data = new SerializedData();
		data.getCentroImpresion().cambiarEstadoImpresora(code, estadoImpresora);
		data.updateCentroImpresion();
	}
}
