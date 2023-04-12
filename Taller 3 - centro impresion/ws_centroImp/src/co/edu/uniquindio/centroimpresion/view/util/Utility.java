package co.edu.uniquindio.centroimpresion.view.util;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

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

}
