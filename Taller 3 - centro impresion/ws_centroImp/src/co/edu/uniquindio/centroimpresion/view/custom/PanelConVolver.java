package co.edu.uniquindio.centroimpresion.view.custom;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public abstract class PanelConVolver extends BorderPane implements EventHandler<Event> {
	private Label label;

	public PanelConVolver() {
		initComp();
	}

	public void initComp() {
		label = new Label("Volver");
		label.setOnMouseReleased(this);
		label.setId("btn-inferior");
		setBottom(label);
	}

	public abstract void volverPresionado();

	@Override
	public void handle(Event event) {
		if (event.getSource() == label) {
			volverPresionado();
		}
	}
}
