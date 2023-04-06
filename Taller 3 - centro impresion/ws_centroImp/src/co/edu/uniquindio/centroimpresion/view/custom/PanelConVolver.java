package co.edu.uniquindio.centroimpresion.view.custom;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public abstract class PanelConVolver extends BorderPane implements EventHandler<Event> {
	private Label label;

	public PanelConVolver() {
		initComp();
	}

	public void initComp() {
		label = new Label("Volver");
		label.setOnMouseReleased(this);
		label.setId("btn-volver");
		setBottom(label);
	}

	public abstract void volverPresionado();

	@Override
	public void handle(Event event) {
		if (event.getSource() == label) {
			volverPresionado();
		}
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

	public static void convertirATextfieldNumerico(TextField tf) {
		tf.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tf.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}
}
