package co.edu.uniquindio.centroimpresion.view.scenes;

import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.view.see.PanelDoc;
import co.edu.uniquindio.centroimpresion.view.see.PanelDocVolver;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EscenaVerDoc extends Scene {

	public EscenaVerDoc(Stage stageVolver, Scene escenaVolver, Documento documento, double width, double height) {
		super(stageVolver == null || escenaVolver == null ? new PanelDoc(documento)
				: new PanelDocVolver(documento, stageVolver, escenaVolver), width, height);

	}

}
