package co.edu.uniquindio.centroimpresion.view.add;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPanelAddDoc;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelAddDoc extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;

	public PanelAddDoc(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
	}

	public void initComp() {
		super.initComp();
		VBox vBox = new VBox(20);
		Label btnAgregar = new Label("Agregar archivo");
		BorderPane agregarCase = new BorderPane(btnAgregar);
		TextField tfCode = new TextField();
		TextField tfPrior = new TextField();

		tfCode.setPromptText("Escribe un código");
		tfPrior.setPromptText("5");

		vBox.setId("centered-box");
		btnAgregar.setId("btn");
		tfCode.setId("textfield");
		tfPrior.setId("textfield");

		vBox.getChildren().add(generarHBox("Escribe el código del documento", tfCode));
		vBox.getChildren().add(generarHBox("Escribe la prioridad del documento", tfPrior));


		agregarCase.setId("btn-case");
		vBox.getChildren().add(agregarCase);
		setCenter(vBox);
		Utility.setAsNumberTextfield(tfPrior);
		btnAgregar.setOnMouseReleased(evento -> CtrlPanelAddDoc.agregarDocumento(tfCode.getText(), tfPrior.getText()));
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}

}
