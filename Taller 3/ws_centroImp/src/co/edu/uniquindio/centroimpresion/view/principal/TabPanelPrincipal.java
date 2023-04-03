package co.edu.uniquindio.centroimpresion.view.principal;

import co.edu.uniquindio.centroimpresion.view.principalmenu.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class TabPanelPrincipal extends BorderPane {

	private OpcionPrincipal[] opciones;
	private Pane[] panelesOpciones;

	public TabPanelPrincipal(OpcionPrincipal[] opciones) {
		this.opciones = opciones;
		initComp();
	}

	public void initComp() {
		panelesOpciones = new Pane[opciones.length];
		addOptions();
	}

	public void addOptions() {
		for (int i = 0; i < opciones.length; i++) {
			panelesOpciones[i] = createPanelByOption(opciones[i]);
		}
	}

	public Pane createPanelByOption(OpcionPrincipal opcion) {
		switch (opcion) {
		case ACERCA_DE:
			return new PanelAcercaDe();
		case AGREGAR:
			return new PanelAdd();
		case ELIMINAR:
			return new PanelDelete();
		case IMPRIMIR:
			return new PanelImprimir();
		case VER:
			return new PanelVer();
		default:
			break;
		}
		Label label = new Label("Error");
		label.setId("lbl-error");
		return new BorderPane(label);
	}

	public void updateView(OpcionPrincipal source) {
		for (int i = 0; i < opciones.length; i++)
			if (opciones[i].equals(source))
				setCenter(panelesOpciones[i]);

	}

}
