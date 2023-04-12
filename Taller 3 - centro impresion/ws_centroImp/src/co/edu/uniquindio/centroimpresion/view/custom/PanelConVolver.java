package co.edu.uniquindio.centroimpresion.view.custom;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

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
}
