package co.edu.uniquindio.p2.diplomado.utility;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FxUtility {
	public static void setAsNumberTextfield(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tf.setText(newValue.replaceAll("[^\\d]", ""));
					abrirContextMenu(tf, "Este campo solo puede tener numeros");
				}
			}

		});
	}

	private static void abrirContextMenu(Node nodo, String msg) {
		final ContextMenu menu = new ContextMenu();
		menu.getItems().add(new MenuItem(msg));
		menu.show(nodo, Side.BOTTOM, 0, 0);

		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), evento -> {
			menu.hide();
		}));
		timeline.play();
	}

	public static void setAsIntegerTextfield(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty())
					return;
				try {
					Integer.parseInt(tf.getText());
				} catch (Exception e) {
					observable.removeListener(this);
					tf.setText(oldValue);
					observable.addListener(this);
					abrirContextMenu(tf, "Este campo solo puede tener numeros enteros");
				}
			}
		});
	}

	public static void setAsIntegerTextfield(TextField tf, int minValue, int maxValue) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (newValue.isEmpty())
					return;
				try {
					int num = Integer.parseInt(tf.getText());
					if (num >= minValue && num <= maxValue)
						return;
				} catch (Exception e) {
				}
				observable.removeListener(this);
				tf.setText(oldValue);
				observable.addListener(this);
				abrirContextMenu(tf, "Este campo solo puede tener numeros entre " + minValue + " y " + maxValue);
			}
		});
	}

	public static void cambiarTituloStage(Parent parent, String title) {
		parent.sceneProperty().addListener(((observableScene, oldScene, newScene) -> {
			parent.getScene().windowProperty().addListener((observableWindow, oldWindow, newWindow) -> {
				Stage stage = (Stage) parent.getScene().getWindow();
				stage.setTitle(title);
			});
		}));

	}

	public static ButtonType crearDecisionAlerta(String titulo, String header, String contenido, AlertType alertType,
			ButtonType... buttonTypes) {
		Alert alerta = new Alert(alertType, contenido, buttonTypes);
		alerta.setTitle(titulo);
		alerta.setHeaderText(header);
		return alerta.showAndWait().orElse(null);
	}

	public static void mostrarMensaje(String titulo, String header, String contenido, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(titulo);
		alert.setHeaderText(header);
		alert.setContentText(contenido);
		alert.show();
	}

	public static void generarAdvertenciaSobreescritura(Runnable metodo) {
		ButtonType boton = crearDecisionAlerta("Decision", "Sobreescritura de campos",
				"Advertencia de sobreescritura de campos", AlertType.INFORMATION, ButtonType.OK, ButtonType.CLOSE);
		if (boton == ButtonType.OK)
			metodo.run();
	}
}
