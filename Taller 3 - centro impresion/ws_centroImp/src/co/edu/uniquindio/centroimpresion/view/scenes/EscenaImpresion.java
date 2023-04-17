package co.edu.uniquindio.centroimpresion.view.scenes;

import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.model.centro.Impresora;
import co.edu.uniquindio.centroimpresion.model.centro.Relacion;
import co.edu.uniquindio.centroimpresion.view.print.PanelImpresionVolver;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EscenaImpresion extends Scene {

	public EscenaImpresion(Relacion<Impresora, Documento> root, Stage stage, Scene escenaAnterior) {
		super(new PanelImpresionVolver(root, stage, escenaAnterior), 1000d, 600d);
	}

}
