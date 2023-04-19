package co.edu.uniquindio.agentatelefonica.p2.views.internal;

import co.edu.uniquindio.centroimpresion.util.Boton;
import co.edu.uniquindio.centroimpresion.util.Utility;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelEliminarContacto extends BorderPane {
	public PanelEliminarContacto(EventHandler<? super MouseEvent> eventoVolver) {
		VBox vbox = new VBox(20);
		TextField tfNombre = new TextField();
		TextField tfTelefono = new TextField();
		Boton botonEliminar = new Boton("Eliminar", e -> {
			System.out.println("Eliminado");
		});
		vbox.setId("centered-box");
		vbox.getChildren().add(Utility.generarHBox("Escribe el nombre del contacto", tfNombre));
		vbox.getChildren().add(Utility.generarHBox("Escribe el telefono del contacto", tfTelefono));
		vbox.getChildren().add(botonEliminar);
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		setCenter(vbox);
		setBottom(botonVolver);
	}
}
