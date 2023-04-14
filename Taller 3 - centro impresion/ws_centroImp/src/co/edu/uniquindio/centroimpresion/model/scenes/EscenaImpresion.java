package co.edu.uniquindio.centroimpresion.model.scenes;

import co.edu.uniquindio.centroimpresion.view.print.PanelImpresionVolver;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EscenaImpresion extends Scene {

	public EscenaImpresion(PanelImpresionVolver root, Stage stage, Scene escenaAnterior) {
		super(root, 1000d, 600d);
	}

}
