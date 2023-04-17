package co.edu.uniquindio.centroimpresion.model.scenes;

import co.edu.uniquindio.centroimpresion.view.principal.PanelMain;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EscenaPrincipal extends Scene {

	public EscenaPrincipal(Stage stage) {
		super(new PanelMain(stage), 800, 600);
	}
}
