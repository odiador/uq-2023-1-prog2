package co.edu.uniquindio.agentatelefonica.p2.views.internal;

import co.edu.uniquindio.agentatelefonica.p2.controllers.CtrlFuncionesExtras;
import co.edu.uniquindio.agentatelefonica.p2.util.Boton;
import co.edu.uniquindio.agentatelefonica.p2.util.Utility;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PanelAsignarContactos extends BorderPane {
	private EventHandler<? super MouseEvent> eventoVolver;

	public PanelAsignarContactos(EventHandler<? super MouseEvent> eventoVolver) {
		this.eventoVolver = eventoVolver;
		initComponents();
	}

	private void initComponents() {
		VBox vbox = new VBox(20);
		HBox hbox = new HBox();
		ComboBox<String> comboElegirAsignar = new ComboBox<String>();
		comboElegirAsignar.setItems(FXCollections.observableArrayList(CtrlFuncionesExtras.obtenerOpcionesAsignar()));
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		Boton botonIr = new Boton("Ir", e -> {
			CtrlFuncionesExtras.irAASignar(comboElegirAsignar.getValue(), this);
		});

		vbox.setId("centered-box");

		vbox.getChildren().add(Utility.generarHBox("Asignar contactos a: ", comboElegirAsignar));
		hbox.getChildren().addAll(botonIr, botonVolver);

		HBox.setHgrow(botonIr, Priority.ALWAYS);
		HBox.setHgrow(botonVolver, Priority.ALWAYS);

		setCenter(vbox);
		setBottom(hbox);
	}
}
