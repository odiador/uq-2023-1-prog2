package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjetoFaltanteException;
import co.edu.uniquindio.centroimpresion.model.Impresora;
import co.edu.uniquindio.centroimpresion.model.ImpresoraCartucho;
import co.edu.uniquindio.centroimpresion.model.ImpresoraLaser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlActualizarImpresora {
	/**
	 * Actualiza una impresora laser por medio de sus parámertros (no actualiza su
	 * codigo) <br>
	 * Muestra alertas en caso de que no se den las cosas correctamente
	 * 
	 * @see {@link #actualizarImpresoraLaserThrows(String, String, String, boolean, String, String)
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param letrasPorSegundo
	 * @param duracionTonerString
	 */
	public static void actualizarImpresoraLaser(String code, String marca, String estadoString, boolean esAColor,
			String letrasPorSegundo, String duracionTonerString) {
		try {
			actualizarImpresoraLaserThrows(code, marca, estadoString, esAColor, letrasPorSegundo, duracionTonerString);
			new Alert(AlertType.CONFIRMATION, "La impresora ha sido actualizada con exito").show();
		} catch (NumberFormatException | ObjetoFaltanteException | FueraRangoException | CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * Actualiza una impresora de cartucho por medio de sus parámertros (no
	 * actualiza su codigo)<br>
	 * Muestra alertas en caso de que no se den las cosas correctamente
	 * 
	 * @see {@link #actualizarImpresoraCartuchoThrows(String, String, String, boolean, String, String, String)}
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param letrasSegString
	 * @param capacidadCartuchoString
	 * @param desgasteCartuchoString
	 */
	public static void actualizarImpresoraCartucho(String code, String marca, String estadoString, boolean esAColor,
			String letrasSegString, String capacidadCartuchoString, String desgasteCartuchoString) {
		try {
			actualizarImpresoraCartuchoThrows(code, marca, estadoString, esAColor, letrasSegString,
					capacidadCartuchoString, desgasteCartuchoString);
			new Alert(AlertType.CONFIRMATION, "La impresora ha sido actualizada con exito").show();
		} catch (NumberFormatException | ObjetoFaltanteException | FueraRangoException | CentroImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * Actualiza una impresora laser por medio de sus parámertros (no actualiza su
	 * codigo)<br>
	 * Muestra error en caso de que no se encuentre la impresora, en caso de que
	 * algun dato no este lleno, o en caso de que algo este fuera de rango
	 * 
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param letrasPorSegundo
	 * @param duracionTonerString
	 * @throws NumberFormatException
	 * @throws ObjetoFaltanteException
	 * @throws ObjectNotExists
	 * @throws FueraRangoException
	 * @throws CentroImpresionException
	 */
	private static void actualizarImpresoraLaserThrows(String code, String marca, String estadoString, boolean esAColor,
			String letrasPorSegundo, String duracionTonerString)
			throws NumberFormatException, ObjetoFaltanteException, FueraRangoException, CentroImpresionException {
		ImpresoraLaser impresoraLaser = CtrlPanelAddImpLaser.obtenerImpresoraLaser(code, marca, estadoString, esAColor,
				letrasPorSegundo, duracionTonerString);
		actualizarImpresoracentroImpresion(impresoraLaser);
	}

	/**
	 * Actualiza una impresora del centro de impresion
	 * 
	 * @param impresora
	 * @throws CentroImpresionException en caso de que no se encuentre
	 */
	private static void actualizarImpresoracentroImpresion(Impresora impresora) throws CentroImpresionException {
		SerializedData data = new SerializedData();
		data.getCentroImpresion().actualizarImpresora(impresora);
		data.updateCentroImpresion();
	}

	/**
	 * Actualiza una impresora de cartucho por medio de sus parámertros (no
	 * actualiza su codigo)<br>
	 * Muestra error en caso de que no se encuentre la impresora, en caso de que
	 * algun dato no este lleno, o en caso de que algo este fuera de rango
	 * 
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param letrasSegString
	 * @param capacidadCartuchoString
	 * @param desgasteCartuchoString
	 * @throws NumberFormatException
	 * @throws ObjetoFaltanteException
	 * @throws FueraRangoException
	 * @throws CentroImpresionException
	 */
	public static void actualizarImpresoraCartuchoThrows(String code, String marca, String estadoString,
			boolean esAColor, String letrasSegString, String capacidadCartuchoString, String desgasteCartuchoString)
			throws NumberFormatException, ObjetoFaltanteException, FueraRangoException, CentroImpresionException {
		ImpresoraCartucho impresoraCartucho = CtrlPanelAddImpCartucho.obtenerImpresoraCartucho(code, marca,
				estadoString, esAColor, letrasSegString, capacidadCartuchoString, desgasteCartuchoString);
		actualizarImpresoracentroImpresion(impresoraCartucho);
	}
}
