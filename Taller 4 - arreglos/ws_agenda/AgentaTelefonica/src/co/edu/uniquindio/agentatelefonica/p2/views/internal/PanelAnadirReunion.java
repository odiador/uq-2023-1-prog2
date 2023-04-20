package co.edu.uniquindio.agentatelefonica.p2.views.internal;

import static co.edu.uniquindio.centroimpresion.util.Utility.generarHBox;

import java.time.LocalDate;

import co.edu.uniquindio.agentatelefonica.p2.controllers.CtrlAnadirReunion;
import co.edu.uniquindio.centroimpresion.util.Boton;
import co.edu.uniquindio.centroimpresion.util.Utility;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class PanelAnadirReunion extends BorderPane {
	private EventHandler<? super MouseEvent> eventoVolver;
	private CtrlAnadirReunion ctrlAnadirReunion = new CtrlAnadirReunion();
	private TextField tfNombre;
	private TextField tfDescripcion;
	private DatePicker datePicker;
	private TextField tfHora;
	private TextField tfMinutos;

	public PanelAnadirReunion(EventHandler<? super MouseEvent> eventoVolver) {
		this.eventoVolver = eventoVolver;
		tfNombre = new TextField();
		tfDescripcion = new TextField();
		datePicker = new DatePicker();
		tfHora = new TextField();
		tfMinutos = new TextField();
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
		Boton botonAnadir = new Boton("Añadir Reunion", e -> {
			CtrlAnadirReunion.anadirReunion(tfNombre.getText(), tfDescripcion.getText(),
					ctrlAnadirReunion.getListaContactos(), datePicker.getValue(), tfHora.getText(),
					tfMinutos.getText());
		});

		Utility.setAsNumberTextfield(tfHora);
		Utility.setAsNumberTextfield(tfMinutos);
		Utility.setMaximumTextLength(tfHora, 2);
		Utility.setMaximumTextLength(tfMinutos, 2);
		VBox vbox = new VBox(20);
		vbox.getChildren().add(generarHBox("Escribe el nombre de la reunion", tfNombre));
		vbox.getChildren().add(generarHBox("Escribe la descripcion de la reunion", tfDescripcion));
		vbox.getChildren().add(generarHBox("Escribe la fecha de la reunion", datePicker));
		vbox.getChildren().add(generarHBox(0, "Escribe la hora de la reunion", tfHora, new Label(","), tfMinutos));
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
