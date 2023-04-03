package co.edu.uniquindio.centroimpresion.view.add;

import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;

public class PanelAddDoc extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;

	public PanelAddDoc(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
		initComp();
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}
}
