package co.edu.uniquindio.centroimpresion.view.print;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPrintEspDoc;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelImprimirEspDatos extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;
	private PanelPrintEspDoc panelPrintEspDoc;
	private Stage stage;

	public PanelImprimirEspDatos(PanelMenuOpcionObjetos panel, PanelPrintEspDoc panelPrintEspDoc, Stage stage) {
		this.panel = panel;
		this.panelPrintEspDoc = panelPrintEspDoc;
		this.stage = stage;
		initComp();
	}

	@Override
	public void initComp() {
		super.initComp();

		VBox vbox = new VBox(30);
		TextField tfImpresora = new TextField();
		TextField tfDocumento = new TextField();
		Label bImprimir = new Label("Imprimir");
		BorderPane bImprimirCase = new BorderPane(bImprimir);
		Label label = new Label("Deja el campo de la impresora vacio para usar la primer impresora");

		vbox.setId("centered-box");
		bImprimir.setId("btn");
		bImprimirCase.setId("btn-case");
		label.setId("label");

		tfImpresora.setPromptText("Primer impresora");
		tfDocumento.setPromptText("Escribe un codigo");

		vbox.getChildren().add(Utility.generarHBox("Escribe el codigo de la impresora", tfImpresora));
		vbox.getChildren().add(Utility.generarHBox("Escribe el codigo del documento", tfDocumento));
		vbox.getChildren().add(bImprimirCase);
		vbox.getChildren().add(label);

		setCenter(vbox);
		bImprimir.setOnMouseReleased(
				e -> CtrlPrintEspDoc.imprimirDocumento(stage, tfImpresora.getText(), tfDocumento.getText()));
	}

	@Override
	public void volverPresionado() {
		panel.setCenter(panelPrintEspDoc);
	}

}
