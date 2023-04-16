package co.edu.uniquindio.centroimpresion.view.herramientas;

import co.edu.uniquindio.centroimpresion.controllers.CtrlUtil;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuUpdate;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PanelSeleccionarImp extends PanelConVolver {
	private PanelMenuUpdate panelMenuUpdate;

	public PanelSeleccionarImp(PanelMenuUpdate panelMenuUpdate) {
		this.panelMenuUpdate = panelMenuUpdate;
		initComp();
	}

	@Override
	public void initComp() {
		super.initComp();
		VBox vbox = new VBox(30);
		TextField tfCode = new TextField();
		Boton btnSeleccionar = new Boton("Seleccionar Impresora", e -> CtrlUtil.seleccionarImpresora(tfCode.getText()));

		vbox.setId("centered-box");
		tfCode.setId("textfield");

		vbox.getChildren().add(Utility.generarHBox("Escribe el codigo de la impresora", tfCode));
		vbox.getChildren().add(btnSeleccionar);

		setCenter(vbox);
	}

	@Override
	public void volverPresionado() {
		panelMenuUpdate.initComponents();
	}

}
