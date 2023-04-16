package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjectNotExists;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.archivos.SerializedData;
import co.edu.uniquindio.centroimpresion.model.centro.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.model.centro.Impresora;
import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraLaser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlPanelAddImpLaser {

	public static void agregarImpresoraLaser(String code, String marca, String estadoString, boolean esAColor,
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
		throwIfEmpty(code, "codigo");
		throwIfEmpty(marca, "marca");
		throwIfNull(estadoString);
		throwIfEmpty(estadoString, "estado");
		throwIfEmpty(letrasPorSegundo, "letras por segundo");

		EstadoImpresora estadoImpresora = EstadoImpresora.obtenerEstadoImpresora(estadoString);
		double paginasPorMinuto = Impresora.obtenerLetrasPorSegundo(letrasPorSegundo);
		int duracionToner = obtenerDuracionToner(duracionTonerString);

		ImpresoraLaser impresoraCartucho = new ImpresoraLaser(code, marca, estadoImpresora, esAColor, paginasPorMinuto,
				duracionToner);
		return impresoraCartucho;
	}

	private static int obtenerDuracionToner(String duracionTonerString)
			throws FueraRangoException, NumberFormatException {

		int duracionToner = Integer.parseInt(duracionTonerString);
		if (duracionToner <= 0)
			throw new FueraRangoException("La duraci�n tiene que ser mayor o igual a 0");
		return duracionToner;
	}

	private static void throwIfNull(String estadoString) throws TextIsEmptyException {
		if (estadoString == null)
			throw new TextIsEmptyException("Elige un estado de impresora");
	}

	private static void throwIfEmpty(String texto, String tipo) throws TextIsEmptyException {
		if (texto.isEmpty())
			throw new TextIsEmptyException(tipo);
	}

	public static void agregarImpresora(String code, String marca, String estadoString, boolean esAColor,
			String letrasPorSegundoString, String duracionTonerString) {
		try {
			agregarImpresoraLaser(code, marca, estadoString, esAColor, letrasPorSegundoString,
					duracionTonerString);
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

}
