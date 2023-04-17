package co.edu.uniquindio.centroimpresion.view.print;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPrintDoc;
import co.edu.uniquindio.centroimpresion.util.Utility;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
		Label label = new Label("Deja el campo vacio para usar la primer impresora");
		Boton bImprimir = new Boton("Imprimir",
				e -> CtrlPrintDoc.imprimirDocumentoConCodigo(textField.getText(), stage));

		vbox.setId("centered-box");
		label.setId("label");

		textField.setPromptText("Primer impresora");

		vbox.getChildren().add(Utility.generarHBox("Escribe el codigo de la impresora", textField));
		vbox.getChildren().add(bImprimir);
		vbox.getChildren().add(label);

		setCenter(vbox);
	}

	@Override
	public void volverPresionado() {
		panel.setCenter(panelPrintDoc);
	}

}
