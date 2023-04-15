package co.edu.uniquindio.centroimpresion.view.remove;

import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;

public class PanelRemoveImp extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;

	public PanelRemoveImp(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
		initComp();
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}
}
