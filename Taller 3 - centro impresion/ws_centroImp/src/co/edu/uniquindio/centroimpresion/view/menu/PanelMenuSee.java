package co.edu.uniquindio.centroimpresion.view.menu;

import java.util.ArrayList;

import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.see.PanelSeeDocs;
import co.edu.uniquindio.centroimpresion.view.see.PanelSeeImpCartucho;
import co.edu.uniquindio.centroimpresion.view.see.PanelSeeImpLaser;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelMenuSee extends BorderPane {

	private TipoEmpleado tipoEmpleado;

	public PanelMenuSee(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
		initComponents();
	}

	public void initComponents() {
		VBox vbox = new VBox();
		if (tipoEmpleado.puedeVerDocs()) {
			Boton boton = new Boton("Ver Cola", e -> setCenter(new PanelSeeDocs(this)));
			VBox.setMargin(boton, new Insets(20));
			vbox.getChildren().add(boton);
		}
		if (tipoEmpleado.puedeVerDocs()) {
			Boton boton = new Boton("Ver Documentos Impresos", e -> setCenter(new PanelSeeDocs(this, true)));
			VBox.setMargin(boton, new Insets(20));
			vbox.getChildren().add(boton);
		}
		if (tipoEmpleado.puedeVerImpresoras()) {
			Boton boton = new Boton("Ver Impresoras Cartucho", e -> setCenter(new PanelSeeImpCartucho(this)));
			VBox.setMargin(boton, new Insets(20));
			vbox.getChildren().add(boton);
		}
		if (tipoEmpleado.puedeVerImpresoras()) {
			Boton boton = new Boton("Ver Impresoras laser", e -> setCenter(new PanelSeeImpLaser(this)));
			VBox.setMargin(boton, new Insets(20));
			vbox.getChildren().add(boton);
		}
		vbox.setId("centered-box");
		setCenter(vbox);
	}

	public static String[] generarOpciones(TipoEmpleado tipoEmpleado) {
		ArrayList<String> opcionesTxt = new ArrayList<String>();
		if (tipoEmpleado.puedeVerDocs()) {
			opcionesTxt.add("Cola");
		}
		if (tipoEmpleado.puedeVerImpresoras()) {
			opcionesTxt.add("impresoras laser");
			opcionesTxt.add("impresoras cartucho");
		}
		return opcionesTxt.toArray(new String[opcionesTxt.size()]);
	}

}
