package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.application.Main;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.ImpresoraException;
import co.edu.uniquindio.centroimpresion.exceptions.NoHayColaImpresionException;
import co.edu.uniquindio.centroimpresion.model.archivos.SerializedData;
import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.model.centro.Impresora;
import co.edu.uniquindio.centroimpresion.model.centro.Relacion;
import co.edu.uniquindio.centroimpresion.model.scenes.EscenaImpresion;
import co.edu.uniquindio.centroimpresion.model.scenes.EscenaVerDoc;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.print.PanelImpresionVolver;
import co.edu.uniquindio.centroimpresion.view.print.PanelPrintDoc;
import co.edu.uniquindio.centroimpresion.view.print.PanelPrintPedirImpresora;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CtrlPrintDoc {

	private static void imprimirPrimerDocumento(Stage stage) {
		try {
			Relacion<Impresora, Documento> relacion = imprimirPrimerDocumentoThrows();
			mostrarPanelImpresion(stage, relacion.obtenerCampo2(), relacion.obtenerCampo1().getLetrasPorSegundo());
		} catch (CentroImpresionException | NoHayColaImpresionException | ImpresoraException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void imprimirDocumentoConCodigo(String codigoImpresora, Stage stage) {
		if (codigoImpresora.isEmpty()) {
			imprimirPrimerDocumento(stage);
			return;
		}
		try {
			Relacion<Impresora, Documento> relacion = imprimirDocumentoThrows(codigoImpresora);
			mostrarPanelImpresion(stage, relacion.obtenerCampo2(), relacion.obtenerCampo1().getLetrasPorSegundo());
		} catch (CentroImpresionException | NoHayColaImpresionException | ImpresoraException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	static void mostrarPanelImpresion(Stage stage, Documento documento, double tiempo) {
		Scene escenaAnterior = stage.getScene();
		Scene escenaNueva = new EscenaImpresion(new PanelImpresionVolver(documento, tiempo, stage, escenaAnterior),
				stage, escenaAnterior);
		escenaNueva.getStylesheets().add(Main.css.toExternalForm());
		stage.setScene(escenaNueva);
	}

	private static Relacion<Impresora, Documento> imprimirPrimerDocumentoThrows()
			throws CentroImpresionException, NoHayColaImpresionException, ImpresoraException {
		SerializedData data = new SerializedData();
		Relacion<Impresora, Documento> relacion = data.getCentroImpresion().imprimirDocumento();
		data.updateCentroImpresion();
		return relacion;
	}

	private static Relacion<Impresora, Documento> imprimirDocumentoThrows(String codeImpresora)
			throws CentroImpresionException, NoHayColaImpresionException, ImpresoraException {
		SerializedData data = new SerializedData();
		Relacion<Impresora, Documento> relacion = data.getCentroImpresion().imprimirDocumento(codeImpresora);
		data.updateCentroImpresion();
		return relacion;
	}

	public static Task<Void> generarTareaImpresion(String contenido, double letrasSeg,
			PuedeAgregarCaracter puedeAgregarCaracter) {
		double letrasMiliseg = 1000 / letrasSeg;
		return new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				Timeline timeline = new Timeline();
				for (int i = 0; i < contenido.length(); i++) {
					final int indice = i;
					KeyFrame key = new KeyFrame(Duration.millis(i * letrasMiliseg),
							evt -> puedeAgregarCaracter.agregarCaracter(contenido.charAt(indice)));
					timeline.getKeyFrames().add(key);
				}
				timeline.play();
				return null;
			}
		};
	}

	public static void verPrimerDocumento(Stage stage) {
		Scene escenaAnterior = stage.getScene();
		SerializedData data = new SerializedData();
		Documento documento;
		try {
			documento = data.getCentroImpresion().obtenerPrimerElementoDocumento();
			EscenaVerDoc escenaVerDoc = new EscenaVerDoc(stage, escenaAnterior, documento, 1000, 800);
			escenaVerDoc.getStylesheets().add(Main.css.toExternalForm());
			stage.setScene(escenaVerDoc);
		} catch (NoHayColaImpresionException e) {
			new Alert(AlertType.ERROR, e.getMessage()).show();
		}
	}

	public static void irAPedirImpresora(PanelMenuOpcionObjetos panel, PanelPrintDoc panelImprimirMain, Stage stage) {
		panel.setCenter(new PanelPrintPedirImpresora(panel, panelImprimirMain, stage));
	}

	public static interface PuedeAgregarCaracter {
		public void agregarCaracter(char caracter);
	}

}
