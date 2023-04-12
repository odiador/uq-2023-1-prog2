package co.edu.uniquindio.centroimpresion.view.remove;

import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelRemoveDoc extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;

	public PanelRemoveDoc(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
		initComponents();
	}

	public void initComponents() {
		VBox vBox = new VBox(30);
		Label bEliminar = new Label("Eliminar Documento");
		TextField tfCode = new TextField();
		BorderPane panelCase = new BorderPane(bEliminar);

		vBox.setId("centered-box");
		panelCase.setId("btn-case");
		bEliminar.setId("btn");

		vBox.getChildren().add(Utility.generarHBox("Escribe el codigo del documento a eliminar: ", tfCode));
		vBox.getChildren().add(panelCase);

		setCenter(vBox);
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}
}
