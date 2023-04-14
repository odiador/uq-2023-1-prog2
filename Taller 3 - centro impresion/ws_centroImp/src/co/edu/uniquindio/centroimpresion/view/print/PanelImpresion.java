package co.edu.uniquindio.centroimpresion.view.print;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPrintDoc;
import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class PanelImpresion extends BorderPane {
	private Documento doc;
	private double letrasSeg;

	public PanelImpresion(Documento doc, double letrasSeg) {
		this.doc = doc;
		this.letrasSeg = letrasSeg;
		initComp();
	}

	private void initComp() {
		TextArea textoContenido = new TextArea();
		textoContenido.setEditable(false);
		setCenter(textoContenido);
		new Thread(CtrlPrintDoc.generarTareaImpresion(doc.getContenido(), letrasSeg,
				caracter -> textoContenido.appendText(caracter + ""))).start();
	}
}
