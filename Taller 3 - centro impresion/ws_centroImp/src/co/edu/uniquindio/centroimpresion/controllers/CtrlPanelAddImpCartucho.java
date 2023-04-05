package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjectNotExists;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.archivos.SerializedData;
import co.edu.uniquindio.centroimpresion.model.centro.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraCartucho;

public class CtrlPanelAddImpCartucho {

	/**
	 *
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param paginasPorMinutoString
	 * @param capacidadCartuchoString
	 * @param desgasteCartuchoString
	 * @throws CentroImpresionException
	 * @throws TextIsEmptyException
	 * @throws ObjectNotExists
	 * @throws NumberFormatException
	 * @throws FueraRangoException
	 */
	public static void agregarImpresoraCartucho(String code, String marca, String estadoString, boolean esAColor,
			String paginasPorMinutoString, String capacidadCartuchoString, String desgasteCartuchoString)
			throws CentroImpresionException, TextIsEmptyException, ObjectNotExists, NumberFormatException,
			FueraRangoException {
		ImpresoraCartucho impresoraCartucho = obtenerImpresoraCartucho(code, marca, estadoString, esAColor,
				paginasPorMinutoString, capacidadCartuchoString, desgasteCartuchoString);
		SerializedData data = new SerializedData();
		if (impresoraCartucho.exists()) {
			data.getCentroImpresion().agregarImpresoraCartucho(impresoraCartucho.getCode(),
					impresoraCartucho.getMarca(), impresoraCartucho.getEstado(), impresoraCartucho.isEsAColor(),
					impresoraCartucho.getPaginasPorMinuto(), impresoraCartucho.getCapacidadCartucho(),
					impresoraCartucho.getCapacidadCartucho());
			data.updateCentroImpresion();
		}
	}

	/**
	 *
	 * @param code
	 * @param marca
	 * @param estadoString
	 * @param esAColor
	 * @param paginasPorMinutoString
	 * @param capacidadCartuchoString
	 * @param desgasteCartuchoString
	 * @return
	 * @throws TextIsEmptyException
	 * @throws ObjectNotExists
	 * @throws NumberFormatException
	 * @throws FueraRangoException
	 */
	private static ImpresoraCartucho obtenerImpresoraCartucho(String code, String marca, String estadoString,
			boolean esAColor, String paginasPorMinutoString, String capacidadCartuchoString,
			String desgasteCartuchoString)
			throws TextIsEmptyException, ObjectNotExists, NumberFormatException, FueraRangoException {
		throwIfEmpty(code, "codigo");
		throwIfEmpty(marca, "marca");
		throwIfEmpty(estadoString, "estado");
		throwIfEmpty(paginasPorMinutoString, "paginas por minuto");
		throwIfEmpty(capacidadCartuchoString, "capacidad de cartucho");
		throwIfEmpty(desgasteCartuchoString, "descaste de cartucho");

		EstadoImpresora estadoImpresora = obtenerEstadoImpresora(estadoString);
		double paginasPorMinuto = obtenerPagPerMinute(paginasPorMinutoString);
		double capacidadCartucho = obtenerCapacidad(capacidadCartuchoString);
		double desgasteCartucho = obtenerDesgaste(desgasteCartuchoString);

		ImpresoraCartucho impresoraCartucho = new ImpresoraCartucho(code, marca, estadoImpresora, esAColor,
				paginasPorMinuto, capacidadCartucho, desgasteCartucho);
		return impresoraCartucho;
	}

	private static void throwIfEmpty(String texto, String tipo) throws TextIsEmptyException {
		if (texto.isEmpty())
			throw new TextIsEmptyException(tipo);
	}

	private static EstadoImpresora obtenerEstadoImpresora(String estadoString) throws ObjectNotExists {
		EstadoImpresora estadoImpresora = EstadoImpresora.obtenerEstado(estadoString);
		if (estadoImpresora == null)
			throw new ObjectNotExists(EstadoImpresora.class);
		return estadoImpresora;
	}

	private static double obtenerDesgaste(String desgasteString) throws FueraRangoException, NumberFormatException {
		double desgasteCartucho = Double.parseDouble(desgasteString);
		if (desgasteCartucho <= 0)
			throw new FueraRangoException("El desgaste tiene que ser mayor que 0");
		return desgasteCartucho;
	}

	private static double obtenerPagPerMinute(String paginasPorMinutoString)
			throws FueraRangoException, NumberFormatException {
		double paginasPorMinuto = Double.parseDouble(paginasPorMinutoString);
		if (paginasPorMinuto <= 0)
			throw new FueraRangoException("Las paginas por minuto tienen que ser mayor a 0");
		return paginasPorMinuto;
	}

	private static double obtenerCapacidad(String capacidadString) throws FueraRangoException, NumberFormatException {
		double capacidadCartucho = Double.parseDouble(capacidadString);
		if (capacidadCartucho <= 0)
			throw new FueraRangoException("Las capacidad tiene que ser mayor a 0");
		return capacidadCartucho;
	}

}
