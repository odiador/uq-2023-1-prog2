package co.edu.uniquindio.centroimpresion.view.see;

import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;

public class PanelSeeDocs extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;

	public PanelSeeDocs(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}
}
