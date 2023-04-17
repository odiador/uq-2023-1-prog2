package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.application.Main;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.ImpresoraException;
import co.edu.uniquindio.centroimpresion.exceptions.NoHayColaImpresionException;
import co.edu.uniquindio.centroimpresion.model.Documento;
import co.edu.uniquindio.centroimpresion.model.Impresora;
import co.edu.uniquindio.centroimpresion.services.ImpresionTerminada;
import co.edu.uniquindio.centroimpresion.services.PuedeAgregarCaracter;
import co.edu.uniquindio.centroimpresion.util.Relacion;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.print.PanelPrintDoc;
import co.edu.uniquindio.centroimpresion.view.print.PanelPrintPedirImpresora;
import co.edu.uniquindio.centroimpresion.view.scenes.EscenaImpresion;
import co.edu.uniquindio.centroimpresion.view.scenes.EscenaVerDoc;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CtrlPrintDoc {

	/**
	 * Pasa la escena a una nueva mostrando el primer documento en cola, muestra
	 * alertas en caso de que no haya cola de impresion
	 * 
	 * @param stage
	 */
	public static void verDocEnCola(Stage stage) {
		Scene escenaAnterior = stage.getScene();
		SerializedData data = new SerializedData();
		Documento documento;
		try {
			documento = data.getCentroImpresion().obtenerPrimerElementoDocumento();
			EscenaVerDoc escenaVerDoc = new EscenaVerDoc(stage, escenaAnterior, documento, 1000, 800);
			escenaVerDoc.getStylesheets().add(Main.css.toExternalForm());
			stage.setScene(escenaVerDoc);
		} catch (NoHayColaImpresionException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * 
	 * Mueve un panel para que se muestre otro para imprimir
	 * 
	 * @param panel
	 * @param panelImprimirMain
	 * @param stage
	 */
	public static void irAPedirImpresoraImpresion(PanelMenuOpcionObjetos panel, PanelPrintDoc panelImprimirMain,
			Stage stage) {
		panel.setCenter(new PanelPrintPedirImpresora(panel, panelImprimirMain, stage));
	}

	/**
	 * Imprime el primer documento con la primera impresora, muestra alertas en caso
	 * de que no se den las cosas correctamente
	 * 
	 * @param stage
	 */
	private static void imprimirPrimerDocumento(Stage stage) {
		try {
			Relacion<Impresora, Documento> relacion = imprimirPrimerDocumentoThrows();
			mostrarPanelImpresion(stage, relacion);
		} catch (CentroImpresionException | NoHayColaImpresionException | ImpresoraException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * Imprime el primer documento en la impresora con un codigo
	 * {@code codigoImpresora}, imprime con la primera impresora en caso de que el
	 * codigo este vacio, muestra alertas en caso de que no se den las cosas
	 * correctamente
	 * 
	 * 
	 * @see
	 *      <li>{@link #imprimirDocumentoThrows(String)
	 *      <li>{@link #imprimirPrimerDocumento(Stage)
	 *      <li>{@link #mostrarPanelImpresion(Stage, Relacion)
	 * @param codigoImpresora
	 * @param stage
	 */
	public static void imprimirDocumentoConCodigo(String codigoImpresora, Stage stage) {
		if (codigoImpresora.isEmpty()) {
			imprimirPrimerDocumento(stage);
			return;
		}
		try {
			Relacion<Impresora, Documento> relacion = imprimirDocumentoThrows(codigoImpresora);
			mostrarPanelImpresion(stage, relacion);
		} catch (CentroImpresionException | NoHayColaImpresionException | ImpresoraException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * Cambia la escena del stage por una nueva de impresion
	 * 
	 * @param stage
	 * @param relacion
	 */
	static void mostrarPanelImpresion(Stage stage, Relacion<Impresora, Documento> relacion) {
		Scene escenaAnterior = stage.getScene();
		Scene escenaNueva = new EscenaImpresion(relacion, stage, escenaAnterior);
		escenaNueva.getStylesheets().add(Main.css.toExternalForm());
		stage.setScene(escenaNueva);
	}

	/**
	 * Imprime con el documento en cola y la primer impresora
	 * 
	 * @return
	 * @throws CentroImpresionException    en caso de que no se encuentre algo
	 * @throws NoHayColaImpresionException en caso de que no haya cola
	 * @throws ImpresoraException          en caso de que el nivel de la impresora
	 *                                     no alcance
	 */
	private static Relacion<Impresora, Documento> imprimirPrimerDocumentoThrows()
			throws CentroImpresionException, NoHayColaImpresionException, ImpresoraException {
		SerializedData data = new SerializedData();
		Relacion<Impresora, Documento> relacion = data.getCentroImpresion().imprimirDocumento();
		data.updateCentroImpresion();
		return relacion;
	}

	/**
	 * 
	 * Imprime con el documento en cola y una impresora con el codigo especifico
	 * 
	 * @param codeImpresora
	 * @return
	 * @throws CentroImpresionException    en caso de que no se encuentre algo
	 * @throws NoHayColaImpresionException en caso de que no haya cola
	 * @throws ImpresoraException          en caso de que el nivel de la impresora
	 *                                     no alcance
	 */
	private static Relacion<Impresora, Documento> imprimirDocumentoThrows(String codeImpresora)
			throws CentroImpresionException, NoHayColaImpresionException, ImpresoraException {
		SerializedData data = new SerializedData();
		Relacion<Impresora, Documento> relacion = data.getCentroImpresion().imprimirDocumento(codeImpresora);
		data.updateCentroImpresion();
		return relacion;
	}

	/**
	 * Genera la tarea de impresion, esta tarea permite ejecutar un metodo
	 * {@link PuedeAgregarCaracter#agregarCaracter(char)} cada x tiempo, ese tiempo
	 * esta determinado por el contenido del documento de la relacion y la velocidad
	 * (letras/seg) de la impresora de la relacion, y termina cuando se pasa por
	 * todo el contenido del documento ejecutando
	 * {@link ImpresionTerminada#impresionTerminada()} y mostrando una alerta de
	 * impresion terminada
	 * 
	 * @see
	 *      <li>{@link ImpresionTerminada#impresionTerminada()}
	 *      <li>{@link PuedeAgregarCaracter#agregarCaracter(char)}
	 *      <li>{@link Relacion}
	 * @param relacion
	 * @param puedeAgregarCaracter
	 * @param impresionTerminada
	 * @return la tarea, que se puede almacenar en un hilo
	 */
	public static Task<Void> generarTareaImpresion(Relacion<Impresora, Documento> relacion,
			PuedeAgregarCaracter puedeAgregarCaracter, ImpresionTerminada impresionTerminada) {
		double letrasMiliseg = 1000 / relacion.obtenerCampo1().getLetrasPorSegundo();
		String contenido = relacion.obtenerCampo2().getContenido();
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
				timeline.setOnFinished(evt -> {
					impresionTerminada.impresionTerminada();
					new Alert(AlertType.CONFIRMATION, "La impresion ha sido finalizada").show();
				});
				timeline.play();
				return null;
			}
		};
	}

	/**
	 * Agrega un listener que hace que cada vez que se actualize un color, se cambie
	 * el estilo de un text area (la impresion)
	 * 
	 * @param textoContenido
	 * @return
	 */
	public static ChangeListener<? super Color> generarGradianteRgb(TextArea textoContenido) {
		return (obs, oldColor, newColor) -> {
			// %02x hace referencia al formato hexagecimal
			textoContenido.setStyle(String.format("-gradient-base: #%02x%02x%02x; ", (int) (newColor.getRed() * 255),
					(int) (newColor.getGreen() * 255), (int) (newColor.getBlue() * 255)));
		};
	}

	/**
	 * Genera una linea de tiempo de colores con varios colores claves con un ciclo
	 * infinito de colores
	 * 
	 * @param baseColor
	 * @return la timeline
	 */
	public static Timeline generarTimelineRGB(ObjectProperty<Color> baseColor) {

		KeyValue keyValue1 = new KeyValue(baseColor, new Color(0.118, 0.357, 0.91, 1));
		KeyValue keyValue2 = new KeyValue(baseColor, new Color(0.91, 0.208, 0.49, 1));
		KeyValue keyValue3 = new KeyValue(baseColor, new Color(0.91, 0.647, 0.27, 1));
		KeyValue keyValue4 = new KeyValue(baseColor, new Color(0.075, 0.91, 0.118, 1));
		KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
		KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyValue2);
		KeyFrame keyFrame3 = new KeyFrame(Duration.millis(1000), keyValue3);
		KeyFrame keyFrame4 = new KeyFrame(Duration.millis(1500), keyValue4);
		Timeline timeline = new Timeline(keyFrame1, keyFrame2, keyFrame3, keyFrame4);
		timeline.setAutoReverse(true);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		return timeline;
	}
}
