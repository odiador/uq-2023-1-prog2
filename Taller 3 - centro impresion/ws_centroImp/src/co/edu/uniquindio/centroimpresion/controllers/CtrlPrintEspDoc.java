package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.ImpresoraException;
import co.edu.uniquindio.centroimpresion.exceptions.NoHayColaImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.archivos.SerializedData;
import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.model.centro.Impresora;
import co.edu.uniquindio.centroimpresion.model.centro.Relacion;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.print.PanelImprimirEspDatos;
import co.edu.uniquindio.centroimpresion.view.print.PanelPrintEspDoc;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CtrlPrintEspDoc {

	public static void imprimirDocumento(Stage stage, String codigoImpresora, String codigoDocumento) {
		try {
			Relacion<Impresora, Documento> relacion = imprimirDocumentoThrows(codigoImpresora, codigoDocumento);
			CtrlPrintDoc.mostrarPanelImpresion(stage, relacion);
		} catch (CentroImpresionException | NoHayColaImpresionException | ImpresoraException | TextIsEmptyException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	private static Relacion<Impresora, Documento> imprimirDocumentoThrows(String codigoImpresora,
			String codigoDocumento)
			throws TextIsEmptyException, CentroImpresionException, NoHayColaImpresionException, ImpresoraException {
		if (codigoDocumento.isEmpty())
			throw new TextIsEmptyException("codigo del documento");
		if (codigoImpresora.isEmpty())
			return imprimirDocumentoThrows(codigoDocumento);

		SerializedData data = new SerializedData();
		Relacion<Impresora, Documento> relacion = data.getCentroImpresion().imprimirDocumento(codigoImpresora,
				codigoDocumento);
		data.updateCentroImpresion();
		return relacion;
	}

	private static Relacion<Impresora, Documento> imprimirDocumentoThrows(String codigoDocumento)
			throws CentroImpresionException, NoHayColaImpresionException, ImpresoraException {
		SerializedData data = new SerializedData();
		Relacion<Impresora, Documento> relacion = data.getCentroImpresion().imprimirDocumentoSoloDoc(codigoDocumento);
		data.updateCentroImpresion();
		return relacion;
	}

	public static void irAPedirDatos(PanelMenuOpcionObjetos panel, PanelPrintEspDoc panelPrintEspDoc, Stage stage) {
		panel.setCenter(new PanelImprimirEspDatos(panel, panelPrintEspDoc, stage));
	}

}
