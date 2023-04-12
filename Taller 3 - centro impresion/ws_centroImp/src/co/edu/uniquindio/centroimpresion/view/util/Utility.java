package co.edu.uniquindio.centroimpresion.view.util;

import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class Utility {

	public static void setAsNumberTextfield(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tf.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
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

}
