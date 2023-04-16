package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjectNotExists;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.centro.Impresora;
import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraCartucho;
import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraLaser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlActualizarImpresora {
	public static void actualizarImpresoraLaser(String code, String marca, String estadoString, boolean esAColor,
			String letrasPorSegundo, String duracionTonerString) {
		try {
			actualizarImpresoraLaserThrows(code, marca, estadoString, esAColor, letrasPorSegundo, duracionTonerString);
			new Alert(AlertType.CONFIRMATION, "La impresora ha sido actualizada con exito").show();
		} catch (NumberFormatException | TextIsEmptyException | ObjectNotExists | FueraRangoException
				| CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void actualizarImpresoraCartucho(String code, String marca, String estadoString, boolean esAColor,
			String letrasSegString, String capacidadCartuchoString, String desgasteCartuchoString) {
		try {
			actualizarImpresoraCartuchoThrows(code, marca, estadoString, esAColor, letrasSegString,
					capacidadCartuchoString, desgasteCartuchoString);
			new Alert(AlertType.CONFIRMATION, "La impresora ha sido actualizada con exito").show();
		} catch (NumberFormatException | TextIsEmptyException | ObjectNotExists | FueraRangoException
				| CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	private static void actualizarImpresoraLaserThrows(String code, String marca, String estadoString, boolean esAColor,
			String letrasPorSegundo, String duracionTonerString) throws NumberFormatException, TextIsEmptyException,
			ObjectNotExists, FueraRangoException, CentroImpresionException {
		ImpresoraLaser impresoraLaser = CtrlPanelAddImpLaser.obtenerImpresoraLaser(code, marca, estadoString, esAColor,
				letrasPorSegundo, duracionTonerString);
		actualizarImpresoracentroImpresion(impresoraLaser);
	}

	private static void actualizarImpresoracentroImpresion(Impresora impresora) throws CentroImpresionException {
		SerializedData data = new SerializedData();
		data.getCentroImpresion().actualizarImpresora(impresora);
		data.updateCentroImpresion();
	}

	public static void actualizarImpresoraCartuchoThrows(String code, String marca, String estadoString,
			boolean esAColor, String letrasSegString, String capacidadCartuchoString, String desgasteCartuchoString)
			throws NumberFormatException, TextIsEmptyException, ObjectNotExists, FueraRangoException,
			CentroImpresionException {
		ImpresoraCartucho impresoraCartucho = CtrlPanelAddImpCartucho.obtenerImpresoraCartucho(code, marca,
				estadoString, esAColor, letrasSegString, capacidadCartuchoString, desgasteCartuchoString);
		actualizarImpresoracentroImpresion(impresoraCartucho);
	}
}
