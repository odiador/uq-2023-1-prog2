package co.edu.uniquindio.centroimpresion.view.add;

import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;

public class PanelAddImpLaser extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;

	public PanelAddImpLaser(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
		initComp();
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}
}
