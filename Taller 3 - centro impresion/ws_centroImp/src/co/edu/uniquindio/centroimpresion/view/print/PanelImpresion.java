package co.edu.uniquindio.centroimpresion.view.print;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPrintDoc;
import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.model.centro.Impresora;
import co.edu.uniquindio.centroimpresion.model.centro.Relacion;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class PanelImpresion extends BorderPane {
	private Relacion<Impresora, Documento> relacion;

	public PanelImpresion(Relacion<Impresora, Documento> relacion) {
		this.relacion = relacion;
		initComp();
	}

	private void initComp() {
		TextArea textoContenido = new TextArea();
		textoContenido.setId("impresion");
		textoContenido.setEditable(false);

		ObjectProperty<Color> baseColor = new SimpleObjectProperty<>();
		Timeline timeline = null;

		if (relacion.obtenerCampo1().esAColor()) {
			baseColor.addListener(CtrlPrintDoc.generarGradianteRgb(textoContenido));
			timeline = CtrlPrintDoc.generarTimelineRGB(baseColor);
		} else {
			textoContenido.setStyle("-fx-text-fill: rgb(0.0, 0.0, 0.0);");

		}
		setCenter(textoContenido);

		final Timeline timelinefinal = timeline;
		Task<Void> tareaImpresion = CtrlPrintDoc.generarTareaImpresion(relacion,
				caracter -> textoContenido.appendText(caracter + ""), () -> {

					if (timelinefinal != null)
						timelinefinal.stop();
				});
		new Thread(tareaImpresion).start();
	}
}
