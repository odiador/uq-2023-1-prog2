package co.edu.uniquindio.centroimpresion.view.principal;

import java.util.ArrayList;
import java.util.Arrays;

import co.edu.uniquindio.centroimpresion.model.centro.TipoAccion;
import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.menu.PanelAcercaDe;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuAdd;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuPrint;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuRemove;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuSee;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TabPanelPrincipal extends BorderPane {

	private TipoAccion[] acciones;
	private Pane[] panelesAcciones;
	private TipoEmpleado tipoEmpleado;
	private Stage stage;

	public TabPanelPrincipal(Stage stage, TipoEmpleado tipoEmpleado) {
		this.stage = stage;
		this.tipoEmpleado = tipoEmpleado;
		initComp();
	}

	public void initComp() {
		obtenerOpciones();
		generarPaneles();
		updateView(acciones[0]);
		setId("tabpane");
	}

	public void obtenerOpciones() {
		ArrayList<TipoAccion> listaOpciones = new ArrayList<TipoAccion>(Arrays.asList(TipoAccion.values()));

		if (!tipoEmpleado.puedeEliminarDocumentos() && !tipoEmpleado.puedeEliminarImpresoras()) {
			listaOpciones.remove(TipoAccion.ELIMINAR);
		}
		if (!tipoEmpleado.puedeVerDocs() && !tipoEmpleado.puedeVerImpresoras()) {
			listaOpciones.remove(TipoAccion.VER);
		}
		acciones = listaOpciones.toArray(new TipoAccion[listaOpciones.size()]);
		panelesAcciones = new Pane[acciones.length];
	}

	public void generarPaneles() {
		for (int i = 0; i < acciones.length; i++) {
			panelesAcciones[i] = createPanelByOption(acciones[i]);
		}
	}

	public Pane createPanelByOption(TipoAccion opcion) {
		switch (opcion) {
		case ACERCA_DE:
			return new PanelAcercaDe();
		case AGREGAR:
			return new PanelMenuAdd(stage, tipoEmpleado);
		case ELIMINAR:
			return new PanelMenuRemove(tipoEmpleado);
		case IMPRIMIR:
			return new PanelMenuPrint(tipoEmpleado, stage);
		case VER:
			return new PanelMenuSee(tipoEmpleado);
		}
		Label label = new Label("Error");
		label.setId("lbl-error");
		return new BorderPane(label);
	}

	public void updateView(TipoAccion source) {
		for (int i = 0; i < acciones.length; i++)
			if (acciones[i].equals(source))
				setCenter(panelesAcciones[i]);

	}

}
