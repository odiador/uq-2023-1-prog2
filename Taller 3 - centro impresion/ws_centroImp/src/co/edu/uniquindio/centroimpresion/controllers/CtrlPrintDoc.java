package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.application.Main;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.NoHayCapacidadException;
import co.edu.uniquindio.centroimpresion.exceptions.NoHayColaImpresionException;
import co.edu.uniquindio.centroimpresion.model.archivos.SerializedData;
import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.model.centro.Impresora;
import co.edu.uniquindio.centroimpresion.model.centro.Relacion;
import co.edu.uniquindio.centroimpresion.model.scenes.EscenaImpresion;
import co.edu.uniquindio.centroimpresion.view.print.PanelImpresionVolver;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CtrlPrintDoc {

	public static void imprimirPrimerDocumento(Stage stage) {
		try {
			Relacion<Impresora, Documento> relacion = imprimirPrimerDocumentoThrows();
			mostrarPanelImpresion(stage, relacion.obtenerCampo2(), relacion.obtenerCampo1().getLetrasPorSegundo());
		} catch (CentroImpresionException | NoHayCapacidadException | NoHayColaImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	private static void mostrarPanelImpresion(Stage stage, Documento documento, double tiempo) {
		Scene escenaAnterior = stage.getScene();
		Scene escenaNueva = new EscenaImpresion(new PanelImpresionVolver(documento, tiempo, stage, escenaAnterior),
				stage, escenaAnterior);
		escenaNueva.getStylesheets().add(Main.css.toExternalForm());
		stage.setScene(escenaNueva);
	}

	public static Relacion<Impresora, Documento> imprimirPrimerDocumentoThrows()
			throws CentroImpresionException, NoHayCapacidadException, NoHayColaImpresionException {
		SerializedData data = new SerializedData();
		Relacion<Impresora, Documento> relacion = data.getCentroImpresion().imprimirDocumento();
		data.updateCentroImpresion();
		return relacion;
	}

	public static Task<Void> generarTareaImpresion(String contenido, double letrasSeg,
			PuedeAgregarCaracter puedeAgregarCaracter) {
		double letrasMiliseg = letrasSeg / 1000;
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

	public static interface PuedeAgregarCaracter {
		public void agregarCaracter(char caracter);
	}

}
