package co.edu.uniquindio.centroimpresion.view.see;

import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PanelDocVolver extends PanelConVolver {
	private Documento documento;
	private Stage stage;
	private Scene scene;

	public PanelDocVolver(Documento documento, Stage stage, Scene scene) {
		this.documento = documento;
		this.stage = stage;
		this.scene = scene;
		initComp();
	}

	public void initComp() {
		super.initComp();
		setCenter(new PanelDoc(documento));
	}

	@Override
	public void volverPresionado() {
		stage.setScene(scene);
	}
}
