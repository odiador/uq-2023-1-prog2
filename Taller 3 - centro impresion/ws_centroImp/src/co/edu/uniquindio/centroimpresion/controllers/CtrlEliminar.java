package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CtrlEliminar {
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

	public static void eliminarDocumentoThrow(String code) throws TextIsEmptyException, CentroImpresionException {
		Utility.throwIfEmpty(code, "codigo");
		SerializedData data = new SerializedData();
		data.getCentroImpresion().deleteDocumento(code);
		data.updateCentroImpresion();
	}

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

	public static void eliminarImpresoraThrow(String code) throws TextIsEmptyException, CentroImpresionException {
		Utility.throwIfEmpty(code, "codigo");
		SerializedData data = new SerializedData();
		data.getCentroImpresion().deleteImpresora(code);
		data.updateCentroImpresion();
	}
}
