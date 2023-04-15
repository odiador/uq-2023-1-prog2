package co.edu.uniquindio.centroimpresion.view.print;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPrintDoc;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelPrintPedirImpresora extends PanelConVolver {
	private Stage stage;
	private PanelPrintDoc panelPrintDoc;
	private PanelMenuOpcionObjetos panel;

	public PanelPrintPedirImpresora(PanelMenuOpcionObjetos panel, PanelPrintDoc panelPrintDoc, Stage stage) {
		this.panel = panel;
		this.panelPrintDoc = panelPrintDoc;
		this.stage = stage;
		initComp();
	}

	@Override
	public void initComp() {
		super.initComp();

		VBox vbox = new VBox(30);
		TextField textField = new TextField();
		Label bImprimir = new Label("Imprimir");
		BorderPane bImprimirCase = new BorderPane(bImprimir);
		Label label = new Label("Deja el campo vacio para usar la primer impresora");

		vbox.setId("centered-box");
		bImprimir.setId("btn");
		bImprimirCase.setId("btn-case");
		label.setId("label");

		textField.setPromptText("Primer impresora");

		vbox.getChildren().add(Utility.generarHBox("Escribe el codigo de la impresora", textField));
		vbox.getChildren().add(bImprimirCase);
		vbox.getChildren().add(label);

		setCenter(vbox);
		bImprimir.setOnMouseReleased(e -> CtrlPrintDoc.imprimirDocumentoConCodigo(textField.getText(), stage));
	}

	@Override
	public void volverPresionado() {
		panel.setCenter(panelPrintDoc);
	}

}
