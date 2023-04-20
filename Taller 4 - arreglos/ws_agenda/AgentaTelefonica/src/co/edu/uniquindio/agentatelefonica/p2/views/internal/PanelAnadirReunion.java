package co.edu.uniquindio.agentatelefonica.p2.views.internal;

import static co.edu.uniquindio.centroimpresion.util.Utility.generarHBox;

import co.edu.uniquindio.agentatelefonica.p2.controllers.CtrlAnadirReunion;
import co.edu.uniquindio.centroimpresion.util.Boton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PanelAnadirReunion extends BorderPane {
	private EventHandler<? super MouseEvent> eventoVolver;
	private CtrlAnadirReunion ctrlAnadirReunion = new CtrlAnadirReunion();

	public PanelAnadirReunion(EventHandler<? super MouseEvent> eventoVolver) {
		this.eventoVolver = eventoVolver;
		initComponents();
	}

	private void initComponents() {
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		Boton botonAnadirContacto = new Boton("Añadir Contacto", e -> {
			PanelBuscarContacto buscarContacto = new PanelBuscarContacto(eventoirAnadir -> {
				initComponents();
			});
			buscarContacto.setComportamientoBotonBuscar(evtBuscar -> {
				ctrlAnadirReunion.anadirContacto(buscarContacto.getTfNombre().getText(),
						buscarContacto.getTfTelefono().getText());
			});
			setCenter(buscarContacto);
			setBottom(null);
		});
		TextField tfNombre = new TextField();
		TextField tfDescripcion = new TextField();
		DatePicker datePicker = new DatePicker();
		Boton botonAnadir = new Boton("Añadir Reunion", e -> {
		});
		VBox vbox = new VBox(20);
		vbox.getChildren().add(generarHBox("Escribe el nombre de la reunion", tfNombre));
		vbox.getChildren().add(generarHBox("Escribe la descripcion de la reunion", tfDescripcion));
		vbox.getChildren().add(generarHBox("Escribe la fecha y hora contacto", datePicker));
		vbox.getChildren().add(botonAnadirContacto);

		VBox.setMargin(botonAnadirContacto, new Insets(0, 50, 0, 50));

		HBox hbox = new HBox();
		hbox.getChildren().addAll(botonAnadir, botonVolver);
		HBox.setHgrow(botonAnadir, Priority.ALWAYS);
		HBox.setHgrow(botonVolver, Priority.ALWAYS);
		setCenter(vbox);
		setBottom(hbox);
	}
}
