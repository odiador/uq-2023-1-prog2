package co.edu.uniquindio.agentatelefonica.p2.views.internal;

import co.edu.uniquindio.centroimpresion.util.Boton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelHerramientas extends BorderPane {
	public PanelHerramientas(EventHandler<? super MouseEvent> eventoVolver) {
		VBox vbox = new VBox(0);
		Boton btnListar = new Boton("Listar Contactos", e -> {
			System.out.println("listado");
		});
		Boton btnHuecosLibres = new Boton("Indicar Huecos libres", e -> {
			System.out.println("huecos");
		});
		vbox.setId("centered-box");
		vbox.getChildren().add(btnHuecosLibres);
		vbox.getChildren().add(btnListar);

		VBox.setMargin(btnHuecosLibres, new Insets(0, 80, 20, 80));
		VBox.setMargin(btnListar, new Insets(0, 80, 0, 80));
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		setCenter(vbox);
		setBottom(botonVolver);
	}
}
