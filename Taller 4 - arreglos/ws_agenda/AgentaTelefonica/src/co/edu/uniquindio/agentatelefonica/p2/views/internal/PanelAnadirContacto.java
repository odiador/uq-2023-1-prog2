package co.edu.uniquindio.agentatelefonica.p2.views.internal;

import co.edu.uniquindio.centroimpresion.util.Boton;
import co.edu.uniquindio.centroimpresion.util.Utility;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelAnadirContacto extends BorderPane {
	public PanelAnadirContacto(EventHandler<? super MouseEvent> eventoVolver) {
		VBox vbox = new VBox(20);
		TextField tfNombre = new TextField();
		TextField tfAlias = new TextField();
		TextField tfDireccion = new TextField();
		TextField tfTelefono = new TextField();
		TextField tfEmail = new TextField();
		Boton botonAgregar = new Boton("Agregar", e -> {
			System.out.println("agregado");
		});
		vbox.setId("centered-box");
		vbox.getChildren().add(Utility.generarHBox("Escribe un nombre", tfNombre));
		vbox.getChildren().add(Utility.generarHBox("Escribe un alias", tfAlias));
		vbox.getChildren().add(Utility.generarHBox("Escribe una direccion", tfDireccion));
		vbox.getChildren().add(Utility.generarHBox("Escribe un telefono", tfTelefono));
		vbox.getChildren().add(Utility.generarHBox("Escribe un email", tfEmail));
		vbox.getChildren().add(botonAgregar);
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		setCenter(vbox);
		setBottom(botonVolver);
	}
}
