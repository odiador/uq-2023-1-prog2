package co.edu.uniquindio.centroimpresion.view.scenes;

import co.edu.uniquindio.centroimpresion.model.Documento;
import co.edu.uniquindio.centroimpresion.model.Impresora;
import co.edu.uniquindio.centroimpresion.util.Relacion;
import co.edu.uniquindio.centroimpresion.view.print.PanelImpresionVolver;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EscenaImpresion extends Scene {

	public EscenaImpresion(Relacion<Impresora, Documento> root, Stage stage, Scene escenaAnterior) {
		super(new PanelImpresionVolver(root, stage, escenaAnterior), 1000d, 600d);
	}

}
