package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.ArchivoNoObtenidoException;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.NoSePuedeLeerException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjectNotExists;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.model.centro.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.model.centro.Impresora;
import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraCartucho;
import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraLaser;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.herramientas.PanelUpdateImpCartucho;
import co.edu.uniquindio.centroimpresion.view.herramientas.PanelUpdateImpLaser;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuUpdate;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class CtrlUtil {

	public static void irAActualizarImpresora(PanelMenuUpdate panelMenuUpdate, String text,
			EventHandler<? super MouseEvent> eventoVolver) {
		try {
			actualizarImpresoraThrows(panelMenuUpdate, text, eventoVolver);
		} catch (CentroImpresionException | TextIsEmptyException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	private static void actualizarImpresoraThrows(PanelMenuUpdate panelMenuUpdate, String code,
			EventHandler<? super MouseEvent> eventoVolver) throws CentroImpresionException, TextIsEmptyException {
		SerializedData data = new SerializedData();
		Impresora impresora = data.getCentroImpresion().buscarImpresoraThrows(code);
		if (impresora instanceof ImpresoraLaser)
			irAActualizarImpresoraLaser(panelMenuUpdate, (ImpresoraLaser) impresora, eventoVolver);
		if (impresora instanceof ImpresoraCartucho)
			irAActualizarImpresoraCartucho(panelMenuUpdate, (ImpresoraCartucho) impresora, eventoVolver);
	}

	private static void irAActualizarImpresoraLaser(PanelMenuUpdate panelMenuUpdate, ImpresoraLaser impresora,
			EventHandler<? super MouseEvent> eventoVolver) {
		BorderPane pane = new BorderPane(new PanelUpdateImpLaser(impresora));
		pane.setBottom(new Boton("Volver", eventoVolver, "btn-volver"));
		panelMenuUpdate.setCenter(pane);
	}

	private static void irAActualizarImpresoraCartucho(PanelMenuUpdate panelMenuUpdate, ImpresoraCartucho impresora,
			EventHandler<? super MouseEvent> eventoVolver) {
		BorderPane pane = new BorderPane(new PanelUpdateImpCartucho(impresora));
		pane.setBottom(new Boton("Volver", eventoVolver, "btn-volver"));

		panelMenuUpdate.setCenter(pane);
	}

	public static void recargarImpresora(String code) {
		SerializedData data = new SerializedData();
		try {
			data.getCentroImpresion().recargarImpresora(code);
			data.updateCentroImpresion();
			new Alert(AlertType.CONFIRMATION, "La impresora con codigo " + code + " ha sido recargada").show();
		} catch (CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void seleccionarImpresora(String code) {
		SerializedData data = new SerializedData();
		try {
			data.getCentroImpresion().seleccionarImpresora(code);
			data.updateCentroImpresion();
			new Alert(AlertType.CONFIRMATION, "La impresora con codigo " + code + " ha sido seleccionada").show();
		} catch (CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void actualizarDocumento(String code, String prioridadString, boolean editarContenido) {
		try {
			actualizarDocumentoThrows(code, prioridadString, editarContenido);
			new Alert(AlertType.CONFIRMATION, "El documento con codigo " + code + " ha sido actualizado").show();
		} catch (TextIsEmptyException | FueraRangoException | ArchivoNoObtenidoException | NoSePuedeLeerException
				| ObjectNotExists | CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void cambiarEstadoImpresora(String code, String estado) {
		try {
			cambiarEstadoImpresoraThrows(code, estado);
			new Alert(AlertType.CONFIRMATION, "La impresora con codigo " + code + " ahora tiene estado " + estado)
					.show();
		} catch (TextIsEmptyException | CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	private static void actualizarDocumentoThrows(String code, String prioridadString, boolean editarContenido)
			throws TextIsEmptyException, FueraRangoException, ArchivoNoObtenidoException, NoSePuedeLeerException,
			ObjectNotExists, CentroImpresionException {
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

	private static void actualizarDocumento(Documento doc) throws CentroImpresionException {
		SerializedData data = new SerializedData();
		data.getCentroImpresion().actualizarDocumento(doc);
		data.updateCentroImpresion();
	}

	private static void cambiarEstadoImpresoraThrows(String code, String estado)
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
