package co.edu.uniquindio.centroimpresion.view.print;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPrintDoc;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelPrintDoc extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;
	private Stage stage;

	public PanelPrintDoc(PanelMenuOpcionObjetos panel, Stage stage) {
		this.panel = panel;
		this.stage = stage;
		initComp();
	}

	public void initComp() {
		super.initComp();

		VBox vbox = new VBox(30);
		Boton btnImprimir = new Boton("Imprimir", evt -> CtrlPrintDoc.irAPedirImpresoraImpresion(panel, this, stage));
		Boton btnVerCola = new Boton("Ver cola", evt -> CtrlPrintDoc.verDocEnCola(stage));

		vbox.setId("centered-box");

		vbox.getChildren().add(btnImprimir);
		vbox.getChildren().add(btnVerCola);
		setCenter(vbox);
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}
}
