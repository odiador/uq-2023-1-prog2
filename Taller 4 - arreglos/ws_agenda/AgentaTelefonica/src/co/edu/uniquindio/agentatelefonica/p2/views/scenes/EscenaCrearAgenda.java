package co.edu.uniquindio.agentatelefonica.p2.views.scenes;

import co.edu.uniquindio.agentatelefonica.p2.views.principal.PanelCrearAgenda;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EscenaCrearAgenda extends Scene {

	public EscenaCrearAgenda(Stage stage, Stage stageMain) {
		super(new PanelCrearAgenda(stage, stageMain), 800, 600);
	}

}
