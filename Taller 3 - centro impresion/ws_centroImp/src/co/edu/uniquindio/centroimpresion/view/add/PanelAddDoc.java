package co.edu.uniquindio.centroimpresion.view.add;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPanelAddDoc;
import co.edu.uniquindio.centroimpresion.util.Utility;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelAddDoc extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;
	private Stage stage;

	public PanelAddDoc(Stage stage, PanelMenuOpcionObjetos panel) {
		this.stage = stage;
		this.panel = panel;
		initComp();
	}

	public void initComp() {
		super.initComp();
		VBox vBox = new VBox(20);
		TextField tfCode = new TextField();
		TextField tfPrior = new TextField();
		Boton btnAgregar = new Boton("Agregar archivo",
				evento -> CtrlPanelAddDoc.agregarDocumento(stage, tfCode.getText(), tfPrior.getText()));

		tfCode.setPromptText("Escribe un código");
		tfPrior.setPromptText("5");

		vBox.setId("centered-box");
		tfCode.setId("textfield");
		tfPrior.setId("textfield");

		vBox.getChildren().add(Utility.generarHBox("Escribe el código del documento", tfCode));
		vBox.getChildren().add(Utility.generarHBox("Escribe la prioridad del documento", tfPrior));

		vBox.getChildren().add(btnAgregar);
		setCenter(vBox);
		Utility.setAsNumberTextfield(tfPrior);
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}

}
