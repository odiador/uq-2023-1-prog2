package co.edu.uniquindio.centroimpresion.view.menu;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PanelAcercaDe extends BorderPane {

	public PanelAcercaDe() {
		setCenter(new Label("Hola"));
	}
	public PanelAcercaDe(String cad) {
		setCenter(new Label(cad));
	}

}
