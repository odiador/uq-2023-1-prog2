package co.edu.uniquindio.centroimpresion.view.menu;

import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.herramientas.PanelActualizarDoc;
import co.edu.uniquindio.centroimpresion.view.herramientas.PanelCambiarEstado;
import co.edu.uniquindio.centroimpresion.view.herramientas.PanelRellenarImp;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelMenuUpdate extends BorderPane {
	private TipoEmpleado tipoEmpleado;

	public PanelMenuUpdate(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
		initComponents();
	}

	public void initComponents() {
		VBox vbox = new VBox();

		vbox.setId("centered-box");
		Boton botonRecargar = new Boton("Recargar Impresora", e -> {
			setCenter(new PanelRellenarImp(this));
		}, "boton-opcion");

		vbox.getChildren().add(botonRecargar);

		VBox.setMargin(botonRecargar, new Insets(20));

		if (tipoEmpleado.puedeActualizarDocumento()) {
			Boton botonActualizar = new Boton("Actualizar Doc", e -> {
				setCenter(new PanelActualizarDoc(this));
			}, "boton-opcion");

			vbox.getChildren().add(botonActualizar);
			VBox.setMargin(botonActualizar, new Insets(20));
		}
		if (tipoEmpleado.puedeSeleccionarImpresora()) {
			Boton botonSeleccionar = new Boton("Seleccionar Impresora", e -> {
				setCenter(new PanelActualizarDoc(this));
			}, "boton-opcion");
			vbox.getChildren().add(botonSeleccionar);
			VBox.setMargin(botonSeleccionar, new Insets(20));
		}
		Boton botonCambiarEstado = new Boton("Cambiar Estado a Impresora", e -> {
			setCenter(new PanelCambiarEstado(this));
		}, "boton-opcion");
		vbox.getChildren().add(botonCambiarEstado);
		VBox.setMargin(botonCambiarEstado, new Insets(20));
		setCenter(vbox);
	}
}
