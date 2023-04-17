package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjetoFaltanteException;
import co.edu.uniquindio.centroimpresion.model.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.model.ImpresoraLaser;
import co.edu.uniquindio.centroimpresion.util.Utility;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlPanelAddImpLaser {

	/**
	 * Agrega una impresora laser con sus atributos, muestra alertas en caso de que
	 * no se den las cosas correctamente
	 * 
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param letrasPorSegundoString
	 * @param duracionTonerString
	 */
	public static void agregarImpresora(String code, String marca, String estadoString, boolean esAColor,
			String letrasPorSegundoString, String duracionTonerString) {
		try {
			agregarImpresoraLaser(code, marca, estadoString, esAColor, letrasPorSegundoString, duracionTonerString);
			new Alert(AlertType.CONFIRMATION, "La impresora ha sido agregada con éxito").show();
		} catch (NumberFormatException e) {
			new Alert(AlertType.WARNING, "Rellena todos los campos").show();
		} catch (CentroImpresionException | ObjetoFaltanteException | FueraRangoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * Agrega una impresora laser con sus atributos, muestra errores en caso de que
	 * no se den las cosas correctamente
	 * 
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param letrasPorSegundoString
	 * @param duracionTonerString
	 * @throws CentroImpresionException en caso de que ya se encuentre
	 * @throws ObjetoFaltanteException  en caso de que algun campo este vacio
	 * @throws NumberFormatException    en caso de que no se haya enviado un dato
	 *                                  numerico como numero
	 * @throws FueraRangoException      en caso de que algo este fuera de rango
	 */
	private static void agregarImpresoraLaser(String code, String marca, String estadoString, boolean esAColor,
			String letrasPorSegundoString, String duracionTonerString)
			throws CentroImpresionException, ObjetoFaltanteException, NumberFormatException, FueraRangoException {
		ImpresoraLaser impresoraLaser = obtenerImpresoraLaser(code, marca, estadoString, esAColor,
				letrasPorSegundoString, duracionTonerString);
		SerializedData data = new SerializedData();
		data.getCentroImpresion().agregarImpresoraLaser(impresoraLaser.getCode(), impresoraLaser.getMarca(),
				impresoraLaser.getEstado(), impresoraLaser.esAColor(), impresoraLaser.getLetrasPorSegundo(),
				impresoraLaser.getDuracionToner());
		data.updateCentroImpresion();

	}

	/**
	 * Obtiene una impresora de cartucho por medio de sus parametros
	 *
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param letrasPorSegundo
	 * @param capacidadCartuchoString
	 * @param desgasteCartuchoString
	 * @return la impresora laser
	 * @throws ObjetoFaltanteException en caso de que algun campo no este lleno
	 * @throws NumberFormatException   en caso de que no se haya podido parsear un
	 *                                 dato a numero
	 * @throws FueraRangoException     en caso de que algo este fuera de rango
	 */
	public static ImpresoraLaser obtenerImpresoraLaser(String code, String marca, String estadoString, boolean esAColor,
			String letrasPorSegundo, String duracionTonerString)
			throws ObjetoFaltanteException, NumberFormatException, FueraRangoException {
		Utility.throwIfEmpty(code, "codigo");
		Utility.throwIfEmpty(marca, "marca");
		Utility.throwIfNull(estadoString, "estado");
		Utility.throwIfEmpty(estadoString, "estado");
		Utility.throwIfEmpty(letrasPorSegundo, "letras por segundo");

		EstadoImpresora estadoImpresora = EstadoImpresora.obtenerEstadoThrows(estadoString);
		double letrasSeg = Utility.obtenerDoublelimitarMayorCero(letrasPorSegundo);
		int duracionToner = Utility.obtenerIntlimitarMayorCero(duracionTonerString);

		ImpresoraLaser impresoraCartucho = new ImpresoraLaser(code, marca, estadoImpresora, esAColor, letrasSeg,
				duracionToner);
		return impresoraCartucho;
	}

}
