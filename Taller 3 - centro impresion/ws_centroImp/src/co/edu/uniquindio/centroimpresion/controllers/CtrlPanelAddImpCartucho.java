package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjectNotExists;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.model.ImpresoraCartucho;
import co.edu.uniquindio.centroimpresion.util.Utility;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlPanelAddImpCartucho {

	/**
	 * Agrega una impresora de cartucho con sus atributos, muestra errores en caso
	 * de que no se den las cosas correctamente
	 * 
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param letrasSegString
	 * @param capacidadCartuchoString
	 * @param desgasteCartuchoString
	 * @throws CentroImpresionException en caso de que ya se encuentre
	 * @throws TextIsEmptyException     en caso de que algun campo este vacio
	 * @throws ObjectNotExists          en caso de que algo no exista
	 * @throws NumberFormatException    en caso de que no se haya enviado un dato
	 *                                  numerico como numero
	 * @throws FueraRangoException      en caso de que algo este fuera de rango
	 */
	static void agregarImpresoraCartuchoThrows(String code, String marca, String estadoString, boolean esAColor,
			String letrasSegString, String capacidadCartuchoString, String desgasteCartuchoString)
			throws CentroImpresionException, TextIsEmptyException, ObjectNotExists, NumberFormatException,
			FueraRangoException {
		ImpresoraCartucho impresoraCartucho = obtenerImpresoraCartucho(code, marca, estadoString, esAColor,
				letrasSegString, capacidadCartuchoString, desgasteCartuchoString);
		SerializedData data = new SerializedData();
		if (impresoraCartucho.exists()) {
			data.getCentroImpresion().agregarImpresoraCartucho(impresoraCartucho.getCode(),
					impresoraCartucho.getMarca(), impresoraCartucho.getEstado(), impresoraCartucho.esAColor(),
					impresoraCartucho.getLetrasPorSegundo(), impresoraCartucho.getCapacidadCartucho(),
					impresoraCartucho.getCapacidadCartucho());
			data.updateCentroImpresion();
		}
	}

	/**
	 * Agrega una impresora de cartucho con sus atributos, muestra alertas en caso
	 * de que no se den las cosas correctamente
	 * 
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param velString
	 * @param velDecimalString
	 * @param capacidadString
	 * @param capacidadDecimalString
	 * @param desgasteString
	 * @param desgasteDecimalString
	 */
	public static void agregarImpresoraCartucho(String code, String marca, String estadoString, boolean esAColor,
			String velString, String velDecimalString, String capacidadString, String capacidadDecimalString,
			String desgasteString, String desgasteDecimalString) {
		try {
			CtrlPanelAddImpCartucho.agregarImpresoraCartuchoThrows(code, marca, estadoString, esAColor,
					Utility.juntarCadenasParaDoble(velString, velDecimalString),
					Utility.juntarCadenasParaDoble(capacidadString, capacidadDecimalString),
					Utility.juntarCadenasParaDoble(desgasteString, desgasteDecimalString));
			new Alert(AlertType.CONFIRMATION, "La impresora ha sido agregada con éxito").show();
		} catch (NumberFormatException e) {
			new Alert(AlertType.WARNING, "Solo coloca numeros en la velocidad, capacidad y desgaste").show();
		} catch (CentroImpresionException e) {
			new Alert(AlertType.WARNING, "Ya existe una impresora con ese codigo").show();
		} catch (TextIsEmptyException e) {
			new Alert(AlertType.WARNING, "Rellena todos los campos (" + e.getTipoTexto() + ")").show();
		} catch (ObjectNotExists e) {
			new Alert(AlertType.WARNING, "Rellena todos los campos (" + e.getClase().getSimpleName() + ")").show();
		} catch (FueraRangoException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * Obtiene una impresora de cartucho por medio de sus parametros
	 * 
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param letrasSegString
	 * @param capacidadCartuchoString
	 * @param desgasteCartuchoString
	 * @return la impresora de cartucho
	 * @throws TextIsEmptyException  en caso de que algun campo no este lleno
	 * @throws ObjectNotExists       en caso de que algo sea null
	 * @throws NumberFormatException en caso de que no se haya podido parsear un
	 *                               dato a numero
	 * @throws FueraRangoException   en caso de que algo este fuera de rango
	 */
	static ImpresoraCartucho obtenerImpresoraCartucho(String code, String marca, String estadoString, boolean esAColor,
			String letrasSegString, String capacidadCartuchoString, String desgasteCartuchoString)
			throws TextIsEmptyException, ObjectNotExists, NumberFormatException, FueraRangoException {
		Utility.throwIfEmpty(code, "codigo");
		Utility.throwIfEmpty(marca, "marca");
		Utility.throwIfNull(estadoString, "Elige un estado de impresora");
		Utility.throwIfEmpty(estadoString, "estado");
		Utility.throwIfEmpty(letrasSegString, "letras por segundo");
		Utility.throwIfEmpty(capacidadCartuchoString, "capacidad de cartucho");
		Utility.throwIfEmpty(desgasteCartuchoString, "desgaste de cartucho");

		EstadoImpresora estadoImpresora = EstadoImpresora.obtenerEstadoThrows(estadoString);
		double letrasSeg = Utility.obtenerDoublelimitarMayorCero(letrasSegString);
		double capacidadCartucho = Utility.obtenerDoublelimitarMayorCero(capacidadCartuchoString);
		double desgasteCartucho = Utility.obtenerDoublelimitarMayorCero(desgasteCartuchoString);

		ImpresoraCartucho impresoraCartucho = new ImpresoraCartucho(code, marca, estadoImpresora, esAColor, letrasSeg,
				capacidadCartucho, desgasteCartucho);
		return impresoraCartucho;
	}
}
