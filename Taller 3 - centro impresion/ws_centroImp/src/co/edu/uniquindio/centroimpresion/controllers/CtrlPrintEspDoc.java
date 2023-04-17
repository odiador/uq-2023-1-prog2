package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.ImpresoraException;
import co.edu.uniquindio.centroimpresion.exceptions.NoHayColaImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.Documento;
import co.edu.uniquindio.centroimpresion.model.Impresora;
import co.edu.uniquindio.centroimpresion.util.Relacion;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.print.PanelImprimirEspDatos;
import co.edu.uniquindio.centroimpresion.view.print.PanelPrintEspDoc;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CtrlPrintEspDoc {

	/**
	 * Imprime un documento especifico con un codigo {@code codigoDocumento}, y en
	 * una impresora determinada, si el codigo de la impresora esta vacio, lo hace
	 * con la primera que encuentre (la seleccionada), muestra alertas en caso de
	 * que no se den las cosas correctamente
	 * 
	 * 
	 * @param stage
	 * @param codigoImpresora
	 * @param codigoDocumento
	 */
	public static void imprimirDocumento(Stage stage, String codigoImpresora, String codigoDocumento) {
		try {
			Relacion<Impresora, Documento> relacion = imprimirDocumentoThrows(codigoImpresora, codigoDocumento);
			CtrlPrintDoc.mostrarPanelImpresion(stage, relacion);
		} catch (CentroImpresionException | NoHayColaImpresionException | ImpresoraException | TextIsEmptyException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * Imprime un documento especifico con un codigo {@code codigoDocumento}, y en
	 * una impresora determinada, si el codigo de la impresora esta vacio, lo hace
	 * con la primera que encuentre (la seleccionada)
	 * 
	 * @param codigoImpresora
	 * @param codigoDocumento
	 * @return
	 * @throws TextIsEmptyException        si algo esta vacio, no aplica para el
	 *                                     codigo de la impresora
	 * @throws CentroImpresionException    en caso de que algo no se encuentre
	 * @throws NoHayColaImpresionException en caso de que la cola este vacia
	 * @throws ImpresoraException          en caso de que el nivel de la impresora
	 *                                     no alcance
	 */
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

	/**
	 * Imprime un documento especifico con un codigo {@code codigoDocumento} en la
	 * primera impresora que encuentre (la seleccionada)
	 * 
	 * @param codigoDocumento
	 * @return
	 * @throws CentroImpresionException    en caso de que no se encuentre algo
	 * @throws NoHayColaImpresionException en caso de que no haya cola
	 * @throws ImpresoraException          en caso de que el nivel de la impresora
	 *                                     no alcance
	 */
	private static Relacion<Impresora, Documento> imprimirDocumentoThrows(String codigoDocumento)
			throws CentroImpresionException, NoHayColaImpresionException, ImpresoraException {
		SerializedData data = new SerializedData();
		Relacion<Impresora, Documento> relacion = data.getCentroImpresion().imprimirDocumentoSoloDoc(codigoDocumento);
		data.updateCentroImpresion();
		return relacion;
	}

	/**
	 * Mueve el panel para ir a pedir los datos para imprimir un documento
	 * 
	 * @param panel
	 * @param panelPrintEspDoc
	 * @param stage
	 */
	public static void irAPedirDatos(PanelMenuOpcionObjetos panel, PanelPrintEspDoc panelPrintEspDoc, Stage stage) {
		panel.setCenter(new PanelImprimirEspDatos(panel, panelPrintEspDoc, stage));
	}

}
