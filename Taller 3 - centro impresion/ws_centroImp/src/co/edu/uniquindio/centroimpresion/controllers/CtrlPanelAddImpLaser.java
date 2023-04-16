package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjectNotExists;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.archivos.SerializedData;
import co.edu.uniquindio.centroimpresion.model.centro.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraLaser;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlPanelAddImpLaser {

	public static void agregarImpresora(String code, String marca, String estadoString, boolean esAColor,
			String letrasPorSegundoString, String duracionTonerString) {
		try {
			agregarImpresoraLaser(code, marca, estadoString, esAColor, letrasPorSegundoString, duracionTonerString);
			new Alert(AlertType.CONFIRMATION, "La impresora ha sido agregada con éxito").show();
		} catch (NumberFormatException e) {
			new Alert(AlertType.WARNING, "Rellena todos los campos").show();
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

	private static void agregarImpresoraLaser(String code, String marca, String estadoString, boolean esAColor,
			String letrasPorSegundoString, String duracionTonerString) throws CentroImpresionException,
			TextIsEmptyException, ObjectNotExists, NumberFormatException, FueraRangoException {
		ImpresoraLaser impresoraLaser = obtenerImpresoraLaser(code, marca, estadoString, esAColor,
				letrasPorSegundoString, duracionTonerString);
		SerializedData data = new SerializedData();
		if (impresoraLaser.exists()) {
			data.getCentroImpresion().agregarImpresoraLaser(impresoraLaser.getCode(), impresoraLaser.getMarca(),
					impresoraLaser.getEstado(), impresoraLaser.isEsAColor(), impresoraLaser.getLetrasPorSegundo(),
					impresoraLaser.getDuracionToner());
			data.updateCentroImpresion();
		}
	}

	/**
	 *
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param letrasPorSegundo
	 * @param capacidadCartuchoString
	 * @param desgasteCartuchoString
	 * @return
	 * @throws TextIsEmptyException
	 * @throws ObjectNotExists
	 * @throws NumberFormatException
	 * @throws FueraRangoException
	 */
	public static ImpresoraLaser obtenerImpresoraLaser(String code, String marca, String estadoString, boolean esAColor,
			String letrasPorSegundo, String duracionTonerString)
			throws TextIsEmptyException, ObjectNotExists, NumberFormatException, FueraRangoException {
		Utility.throwIfEmpty(code, "codigo");
		Utility.throwIfEmpty(marca, "marca");
		Utility.throwIfNull(estadoString, "estado");
		Utility.throwIfEmpty(estadoString, "estado");
		Utility.throwIfEmpty(letrasPorSegundo, "letras por segundo");

		EstadoImpresora estadoImpresora = EstadoImpresora.obtenerEstadoImpresora(estadoString);
		double paginasPorMinuto = Utility.obtenerDoublelimitarMayorCero(letrasPorSegundo);
		int duracionToner = Utility.obtenerIntlimitarMayorCero(duracionTonerString);

		ImpresoraLaser impresoraCartucho = new ImpresoraLaser(code, marca, estadoImpresora, esAColor, paginasPorMinuto,
				duracionToner);
		return impresoraCartucho;
	}

}
