package co.edu.uniquindio.agentatelefonica.p2.views.internal;

import co.edu.uniquindio.agentatelefonica.p2.controllers.CtrlAgenda;
import co.edu.uniquindio.centroimpresion.util.Boton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelHerramientas extends BorderPane {
	private EventHandler<? super MouseEvent> eventoVolver;

	public PanelHerramientas(EventHandler<? super MouseEvent> eventoVolver) {
		this.eventoVolver = eventoVolver;
		initComponents();
	}

	private void initComponents() {
		VBox vbox = new VBox(20);
		Boton btnListar = new Boton("Listar Contactos", e -> {
			System.out.println("listado");
		});
		Boton btnHuecosLibres = new Boton("Indicar Huecos libres", e -> {
			CtrlAgenda.huecosLibres();
		});
		vbox.setId("centered-box");
		vbox.getChildren().add(btnHuecosLibres);
		vbox.getChildren().add(btnListar);

		Insets instets = new Insets(0, 80, 0, 80);

		VBox.setMargin(btnHuecosLibres, instets);
		VBox.setMargin(btnListar, instets);
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		setCenter(vbox);
		setBottom(botonVolver);
	}
}
