package co.edu.uniquindio.centroimpresion.util;

import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class Utility {

	public static void setAsNumberTextfield(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tf.setText(newValue.replaceAll("[^\\d]", ""));
					final ContextMenu menu = new ContextMenu();
					menu.getItems().add(new MenuItem("Este campo solo puede tener numeros"));
					menu.show(tf, Side.BOTTOM, 0, 0);

					Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evento -> {
						menu.hide();
					}));
					timeline.play();
				}
			}
		});
	}

	public static void setMaximumTextLength(TextField tf, int tamanio) {
		tf.setTextFormatter(new TextFormatter<String>(cambio -> {
			if (cambio.isContentChange()) {
				if (cambio.getControlNewText().length() > tamanio) {
					final ContextMenu menu = new ContextMenu();
					menu.getItems().add(new MenuItem("Este campo tiene maximo \n" + tamanio + " caracteres"));
					menu.show(cambio.getControl(), Side.BOTTOM, 0, 0);

					Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evento -> {
						menu.hide();
					}));
					timeline.play();
					return null;
				}
			}
			return cambio;
		}));
	}

	public static HBox generarHBox(String msg, Node... nodos) {
		Label label = new Label(msg);
		label.setId("label");
		HBox hbox = new HBox(20, label);
		hbox.getChildren().addAll(nodos);
		hbox.setId("centered-box");
		return hbox;
	}

	public static HBox generarHBox(int spacing, String msg, Node... nodos) {
		Label label = new Label(msg);
		label.setId("label");
		HBox hbox = new HBox(spacing, label);
		hbox.getChildren().addAll(nodos);
		hbox.setId("centered-box");
		return hbox;
	}

	public static String juntarCadenasParaDoble(String inicial, String fainal) {
		String concatenacion = "";
		if (inicial.isEmpty()) {
			concatenacion += "0" + (fainal.isEmpty() ? "" : "." + fainal);
		} else {
			concatenacion += inicial + (fainal.isEmpty() ? "" : "." + fainal);
		}

		return concatenacion;
	}

	public static void throwIfEmpty(String texto, String tipo) throws TextIsEmptyException {
		if (texto.isEmpty())
			throw new TextIsEmptyException(tipo);
	}

	public static void throwIfNull(String estadoString, String msg) throws TextIsEmptyException {
		if (estadoString == null)
			throw new TextIsEmptyException(msg);
	}

	public static void throwIfNull(Object obj, String msg) throws TextIsEmptyException {
		if (obj == null)
			throw new TextIsEmptyException(msg);
	}

	public static double obtenerDoublelimitarMayorCero(String desgasteString)
			throws FueraRangoException, NumberFormatException {
		double desgasteCartucho = Double.parseDouble(desgasteString);
		if (desgasteCartucho <= 0)
			throw new FueraRangoException("El desgaste tiene que ser mayor que 0");
		return desgasteCartucho;
	}

	public static int obtenerIntlimitarMayorCero(String duracionTonerString)
			throws FueraRangoException, NumberFormatException {

		int duracionToner = Integer.parseInt(duracionTonerString);
		if (duracionToner <= 0)
			throw new FueraRangoException("La duraci�n tiene que ser mayor o igual a 0");
		return duracionToner;
	}

	public static int obtenerParteEnteraDouble(double numDoble) {
		return new Double(numDoble).intValue();
	}

	public static int obtenerDecimalesDouble(double numDoble) {
		int cantidadDecimales = obtenerCantDecimales(numDoble);
		double potenciaDecimales = (int) Math.pow(10, cantidadDecimales);
		int numSinDecimales = (int) (numDoble * potenciaDecimales);
		int decimales = (int) (numSinDecimales % potenciaDecimales);
		return decimales;
	}

	private static int obtenerCantDecimales(double numDoble) {
		String cadenaNum = numDoble + "";
		String[] split = cadenaNum.replace('.', ',').split(",");
		return split[1].length();
	}
}
