package co.edu.uniquindio.centroimpresion.view.custom;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public abstract class PanelConVolver extends BorderPane {

	public PanelConVolver() {
		initComp();
	}

	public void initComp() {
		Label label = new Label("Volver");
		label.setOnMouseReleased(evento -> volverPresionado());
		label.setId("btn-volver");
		setBottom(label);
	}

	public abstract void volverPresionado();
}
