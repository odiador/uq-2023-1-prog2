package co.edu.uniquindio.centroimpresion.view.print;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPrintDoc;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelPrintDoc extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;
	private Stage stage;

	public PanelPrintDoc(PanelMenuOpcionObjetos panel, Stage stage) {
		this.panel = panel;
		this.stage = stage;
		initComponents();
	}

	public void initComponents() {
		VBox vbox = new VBox(30);
		Label btnImprimir = new Label("Imprimir");
		BorderPane btnImprimirCase = new BorderPane(btnImprimir);

		vbox.setId("centered-box");
		btnImprimir.setId("btn");
		btnImprimirCase.setId("btn-case");

		btnImprimir.setOnMouseReleased(evt -> CtrlPrintDoc.imprimirPrimerDocumento(stage));
		vbox.getChildren().add(btnImprimirCase);
		setCenter(vbox);
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}
}
