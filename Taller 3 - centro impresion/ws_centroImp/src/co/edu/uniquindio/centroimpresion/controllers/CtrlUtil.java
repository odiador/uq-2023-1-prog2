package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.ArchivoNoObtenidoException;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.NoSePuedeLeerException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjectNotExists;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.Documento;
import co.edu.uniquindio.centroimpresion.model.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.model.Impresora;
import co.edu.uniquindio.centroimpresion.model.ImpresoraCartucho;
import co.edu.uniquindio.centroimpresion.model.ImpresoraLaser;
import co.edu.uniquindio.centroimpresion.util.Utility;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.herramientas.PanelUpdateImpCartucho;
import co.edu.uniquindio.centroimpresion.view.herramientas.PanelUpdateImpLaser;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuUpdate;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class CtrlUtil {
	/**
	 * Mueve paneles para ir a actualizar una impresora por medio de su codigo,
	 * muestra alertas en caso de que no se den las cosas correctamente
	 * 
	 * @param panelMenuUpdate
	 * @param code
	 * @param eventoVolver    lo que pasa cuando se oprima el boton de volver en el
	 *                        panel de actualizar
	 */
	public static void irAActualizarImpresora(PanelMenuUpdate panelMenuUpdate, String code,
			EventHandler<? super MouseEvent> eventoVolver) {
		try {
			actualizarImpresoraThrows(panelMenuUpdate, code, eventoVolver);
		} catch (CentroImpresionException | TextIsEmptyException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * Mueve el panel para ir a actualizar impresoras, muestra errores en caso de
	 * que no se den las cosas correctamente
	 * 
	 * @param panelMenuUpdate
	 * @param code
	 * @param eventoVolver
	 * @throws CentroImpresionException en caso de que no se encuentre la impresora
	 * @throws TextIsEmptyException     si el codigo esta vacio
	 */
	private static void actualizarImpresoraThrows(PanelMenuUpdate panelMenuUpdate, String code,
			EventHandler<? super MouseEvent> eventoVolver) throws CentroImpresionException, TextIsEmptyException {
		Utility.throwIfEmpty(code, "codigo");
		SerializedData data = new SerializedData();
		Impresora impresora = data.getCentroImpresion().buscarImpresoraThrows(code);
		if (impresora instanceof ImpresoraLaser)
			irAActualizarImpresoraLaser(panelMenuUpdate, (ImpresoraLaser) impresora, eventoVolver);
		if (impresora instanceof ImpresoraCartucho)
			irAActualizarImpresoraCartucho(panelMenuUpdate, (ImpresoraCartucho) impresora, eventoVolver);
	}

	/**
	 * Va al panel de actualizar impresora laser
	 * 
	 * @param panelMenuUpdate
	 * @param impresora
	 * @param eventoVolver
	 */
	private static void irAActualizarImpresoraLaser(PanelMenuUpdate panelMenuUpdate, ImpresoraLaser impresora,
			EventHandler<? super MouseEvent> eventoVolver) {
		BorderPane pane = new BorderPane(new PanelUpdateImpLaser(impresora));
		pane.setBottom(new Boton("Volver", eventoVolver, "btn-volver"));
		panelMenuUpdate.setCenter(pane);
	}

	/**
	 * Va al panel de actualizar impresora de cartucho
	 * 
	 * @param panelMenuUpdate
	 * @param impresora
	 * @param eventoVolver
	 */
	private static void irAActualizarImpresoraCartucho(PanelMenuUpdate panelMenuUpdate, ImpresoraCartucho impresora,
			EventHandler<? super MouseEvent> eventoVolver) {
		BorderPane pane = new BorderPane(new PanelUpdateImpCartucho(impresora));
		pane.setBottom(new Boton("Volver", eventoVolver, "btn-volver"));

		panelMenuUpdate.setCenter(pane);
	}

	/**
	 * Recarga una impresora, dependiendo del tipo que sea se va a hacer alguna cosa
	 * o cualquier otra diferente, muestra alertas en caso de que no se den las
	 * cosas correctamente
	 * 
	 * 
	 * @param code
	 */
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

	/**
	 * Selecciona una impresora especifica, muestra alertas en caso de que no se den
	 * las cosas correctamente
	 * 
	 * @param code
	 */
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

	/**
	 * Actualiza un documento, dependiendo de que si se quiere editar tambien el
	 * titulo y contenido se va a abrir un filechooser o no, muestra alertas en caso
	 * de que no se den las cosas correctamente
	 * 
	 * 
	 * @param code
	 * @param prioridadString
	 * @param editarContenido
	 */
	public static void actualizarDocumento(String code, String prioridadString, boolean editarContenido) {
		try {
			actualizarDocumentoThrows(code, prioridadString, editarContenido);
			new Alert(AlertType.CONFIRMATION, "El documento con codigo " + code + " ha sido actualizado").show();
		} catch (TextIsEmptyException | FueraRangoException | ArchivoNoObtenidoException | NoSePuedeLeerException
				| ObjectNotExists | CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * Cambia el estado de una impresora epecifica, muestra alertas en caso de que
	 * no se den las cosas correctamente
	 * 
	 * @param code
	 * @param estado
	 */
	public static void cambiarEstadoImpresora(String code, String estado) {
		try {
			cambiarEstadoImpresoraThrows(code, estado);
			new Alert(AlertType.CONFIRMATION, "La impresora con codigo " + code + " ahora tiene estado " + estado)
					.show();
		} catch (TextIsEmptyException | CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * Actualiza un documento con sus parametros, en caso de que se quiera editar el
	 * contenido abre un filechooser; muestra alertas en caso de que no se den las
	 * cosas correctamente
	 * 
	 * @param code
	 * @param prioridadString
	 * @param editarContenido
	 * @throws TextIsEmptyException       en caso de que el codigo este vacio
	 * @throws FueraRangoException        en caso de que la prioridad este fuera de
	 *                                    rango
	 * @throws ArchivoNoObtenidoException en caso de que el archivo no sea obtenido
	 * @throws NoSePuedeLeerException     en caso de que no se pueda leer
	 * @throws ObjectNotExists            en caso de que un objeto sea null
	 * @throws CentroImpresionException   en caso de que no se pueda actualizar el
	 *                                    documento
	 */
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

	/**
	 * Actualiza un documento
	 * 
	 * @param doc
	 * @throws CentroImpresionException en caso de que no se pueda encontrar
	 */
	private static void actualizarDocumento(Documento doc) throws CentroImpresionException {
		SerializedData data = new SerializedData();
		data.getCentroImpresion().actualizarDocumento(doc);
		data.updateCentroImpresion();
	}

	/**
	 * Cambia el estado de una impresora x, muestra errores en caso de que no se den
	 * las cosas correctamente
	 * 
	 * @param code
	 * @param estado
	 * @throws TextIsEmptyException     si faltan por llenar datos
	 * @throws CentroImpresionException en caso de que no se encuentre la impresora
	 */
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
