package co.edu.uniquindio.centroimpresion.view.print;

import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PanelImpresionVolver extends PanelConVolver {
	private Stage stage;
	private Scene escenaAnterior;

	public PanelImpresionVolver(Documento doc, double duracion, Stage stage, Scene escenaAnterior) {
		this.stage = stage;
		this.escenaAnterior = escenaAnterior;
		initComp();
		setCenter(new PanelImpresion(doc, duracion));
	}

	@Override
	public void volverPresionado() {
		stage.setScene(escenaAnterior);
	}

}
