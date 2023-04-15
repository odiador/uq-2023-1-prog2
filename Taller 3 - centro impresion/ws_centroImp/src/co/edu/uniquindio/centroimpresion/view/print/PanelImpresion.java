package co.edu.uniquindio.centroimpresion.view.print;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPrintDoc;
import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.model.centro.Impresora;
import co.edu.uniquindio.centroimpresion.model.centro.Relacion;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class PanelImpresion extends BorderPane {
	private Documento doc;
	private Impresora impresora;

	public PanelImpresion(Relacion<Impresora, Documento> relacion) {
		this.doc = relacion.obtenerCampo2();
		this.impresora = relacion.obtenerCampo1();
		initComp();
	}

	private void initComp() {
		TextArea textoContenido = new TextArea();
		textoContenido.setEditable(false);
		setCenter(textoContenido);
		new Thread(CtrlPrintDoc.generarTareaImpresion(doc.getContenido(), impresora.getLetrasPorSegundo(),
				caracter -> textoContenido.appendText(caracter + ""))).start();
	}
}
