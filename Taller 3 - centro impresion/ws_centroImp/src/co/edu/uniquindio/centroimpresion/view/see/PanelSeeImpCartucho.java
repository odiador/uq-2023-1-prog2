package co.edu.uniquindio.centroimpresion.view.see;

import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;

public class PanelSeeImpCartucho extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;

	public PanelSeeImpCartucho(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
	}

	public void volverPresionado() {
		panel.initComp();
	}

}