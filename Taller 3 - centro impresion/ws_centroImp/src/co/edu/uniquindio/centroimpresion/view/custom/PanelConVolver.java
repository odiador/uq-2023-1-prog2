package co.edu.uniquindio.centroimpresion.view.custom;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public abstract class PanelConVolver extends BorderPane {
	private Label label;

	public PanelConVolver() {
		initComp();
	}

	public void initComp() {
		label = new Label("Volver");
		label.setOnMouseReleased(evento -> volverPresionado());
		label.setId("btn-volver");
		setBottom(label);
	}

	public abstract void volverPresionado();

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
}
