package co.edu.uniquindio.agentatelefonica.p2.views.internal;

import co.edu.uniquindio.centroimpresion.util.Boton;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelFuncExtendida extends BorderPane {
	private EventHandler<? super MouseEvent> eventoVolver;

	public PanelFuncExtendida(EventHandler<? super MouseEvent> eventoVolver) {
		this.eventoVolver = eventoVolver;
		initComponents();
	}

	public void initComponents() {

		VBox vbox = new VBox(20);
		Boton botonVolver = new Boton("Volver", eventoVolver, "btn-volver");
		Boton botonAgregarReunion = new Boton("Agregar Reunion", e -> {
			setCenter(new PanelAnadirReunion(eventoVolver));
			setBottom(null);
		});
		Boton botonEliminarReunion = new Boton("Eliminar Reunion", e -> {
			System.out.println("reunion eliminada");
		});
		Boton botonAgregarGrupo = new Boton("Agregar Grupo", e -> {
			System.out.println("grupo agregado");
		});
		Boton botonAgregarContactoGrupo = new Boton("Agregar Contacto a Grupo", e -> {
			System.out.println("contacto agregado al grupo");
		});

		Boton botonEliminarGrupo = new Boton("Eliminar grupo", e -> {
			System.out.println("grupo eliminado");
		});

		vbox.setId("centered-box");
		vbox.getChildren().addAll(botonAgregarReunion, botonEliminarReunion, botonAgregarGrupo,
				botonAgregarContactoGrupo, botonEliminarGrupo);

		Insets insets = new Insets(0, 80, 0, 80);

		VBox.setMargin(botonAgregarReunion, insets);
		VBox.setMargin(botonEliminarReunion, insets);
		VBox.setMargin(botonAgregarGrupo, insets);
		VBox.setMargin(botonAgregarContactoGrupo, insets);
		VBox.setMargin(botonEliminarGrupo, insets);

		setCenter(vbox);
		setBottom(botonVolver);
	}

}
