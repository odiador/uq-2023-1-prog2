package co.edu.uniquindio.centroimpresion.view.remove;

import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;

public class PanelRemoveDoc extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;

	public PanelRemoveDoc(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}
}
