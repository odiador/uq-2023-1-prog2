package co.edu.uniquindio.centroimpresion.view.herramientas;

import co.edu.uniquindio.centroimpresion.controllers.CtrlUtil;
import co.edu.uniquindio.centroimpresion.model.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.util.Utility;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuUpdate;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PanelCambiarEstado extends PanelConVolver {
	private PanelMenuUpdate panelMenuUpdate;

	public PanelCambiarEstado(PanelMenuUpdate panelMenuUpdate) {
		this.panelMenuUpdate = panelMenuUpdate;
		initComp();
	}

	@Override
	public void initComp() {
		super.initComp();
		VBox vbox = new VBox(30);
		TextField tfCode = new TextField();
		ComboBox<String> comboEstados = new ComboBox<String>();

		comboEstados.setId("combobox");
		vbox.setId("centered-box");
		tfCode.setId("textfield");

		comboEstados.setItems(FXCollections.observableArrayList(EstadoImpresora.stringValues()));
		vbox.getChildren().add(Utility.generarHBox("Escribe el codigo de la impresora", tfCode));
		vbox.getChildren().add(Utility.generarHBox("Escribe el estado nuevo", comboEstados));
		vbox.getChildren().add(new Boton("Cambiar Estado",
				e -> CtrlUtil.cambiarEstadoImpresora(tfCode.getText(), comboEstados.getValue())));
		setCenter(vbox);
	}

	@Override
	public void volverPresionado() {
		panelMenuUpdate.initComponents();
	}

}
