package co.edu.uniquindio.agentatelefonica.p2.views.internal;

import co.edu.uniquindio.agentatelefonica.p2.controllers.CtrlContacto;
import co.edu.uniquindio.centroimpresion.util.Boton;
import co.edu.uniquindio.centroimpresion.util.Utility;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PanelBuscarContacto extends BorderPane {
	public PanelBuscarContacto(EventHandler<? super MouseEvent> eventoVolver) {
		VBox vbox = new VBox(20);
		TextField tfNombre = new TextField();
		TextField tfTelefono = new TextField();
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		Boton botonBuscar = new Boton("Buscar", e -> {
			CtrlContacto.buscarContacto(tfNombre.getText(), tfTelefono.getText());
		});
		HBox botonesBox = new HBox(botonBuscar, botonVolver);

		Utility.setAsNumberTextfield(tfTelefono);
		Utility.setMaximumTextLength(tfTelefono, 11);

		vbox.setId("centered-box");
		vbox.getChildren().add(Utility.generarHBox("Escribe un nombre para buscar el contacto", tfNombre));
		vbox.getChildren().add(Utility.generarHBox("Escribe su numero de telefono", tfTelefono));

		HBox.setHgrow(botonBuscar, Priority.ALWAYS);
		HBox.setHgrow(botonVolver, Priority.ALWAYS);

		setCenter(vbox);
		setBottom(botonesBox);
	}
}
