package co.edu.uniquindio.centroimpresion.view.custom;

import javafx.scene.layout.BorderPane;

public abstract class PanelConVolver extends BorderPane {

	public PanelConVolver() {
	}

	public void initComp() {
		setBottom(new Boton("Volver", evento -> volverPresionado()));
	}

	public abstract void volverPresionado();
}
