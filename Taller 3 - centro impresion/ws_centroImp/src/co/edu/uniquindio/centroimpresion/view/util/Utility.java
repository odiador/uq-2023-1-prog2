package co.edu.uniquindio.centroimpresion.view.util;

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

}
