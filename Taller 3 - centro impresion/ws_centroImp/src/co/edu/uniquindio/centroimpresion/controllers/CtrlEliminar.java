package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.util.Utility;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlEliminar {
	/**
	 * Elimina un documento por medio del codigo {@code code}
	 * 
	 * @see {@link #eliminarDocumentoThrow(String)}
	 * @param code
	 */
	public static void eliminarDocumento(String code) {
		try {
			eliminarDocumentoThrow(code);
			new Alert(AlertType.INFORMATION, "El documento con codigo " + code + " fue eliminado correctamente").show();
		} catch (TextIsEmptyException e) {
			new Alert(AlertType.WARNING, "Rellena todos los campos (" + e.getTipoTexto() + ")").show();
		} catch (CentroImpresionException e) {
			new Alert(AlertType.ERROR, "El documento con codigo " + code + " no fue encontrado").show();
		}
	}

	/**
	 * Elimina un documento por medio del codigo {@code code}
	 * 
	 * @param code
	 * @throws TextIsEmptyException     en caso de que esté vacío el codigo
	 * @throws CentroImpresionException en caso de que no se encuentre
	 */
	public static void eliminarDocumentoThrow(String code) throws TextIsEmptyException, CentroImpresionException {
		Utility.throwIfEmpty(code, "codigo");
		SerializedData data = new SerializedData();
		data.getCentroImpresion().deleteDocumento(code);
		data.updateCentroImpresion();
	}

	/**
	 * Elimina una impresora por medio del codigo {@code code}
	 * 
	 * @see {@link #eliminarImpresoraThrow(String)}
	 * @param code
	 */
	public static void eliminarImpresora(String code) {
		try {
			eliminarImpresoraThrow(code);
			new Alert(AlertType.INFORMATION, "La impresora con codigo " + code + " fue eliminada correctamente").show();
		} catch (TextIsEmptyException e) {
			new Alert(AlertType.WARNING, "Rellena todos los campos (" + e.getTipoTexto() + ")").show();
		} catch (CentroImpresionException e) {
			new Alert(AlertType.ERROR, "La impresora con codigo " + code + " no fue encontrada").show();
		}
	}

	/**
	 * 
	 * Elimina una impresora por medio del codigo {@code code}
	 * 
	 * @param code
	 * @throws TextIsEmptyException     en caso de que esté vacío el codigo
	 * @throws CentroImpresionException en caso de que no se encuentre
	 */
	public static void eliminarImpresoraThrow(String code) throws TextIsEmptyException, CentroImpresionException {
		Utility.throwIfEmpty(code, "codigo");
		SerializedData data = new SerializedData();
		data.getCentroImpresion().deleteImpresora(code);
		data.updateCentroImpresion();
	}
}
