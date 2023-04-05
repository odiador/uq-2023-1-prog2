package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjectNotExists;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.centro.EstadoImpresora;

public class CtrlPanelAddImpCartucho {

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
