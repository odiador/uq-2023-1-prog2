package co.edu.uniquindio.centroimpresion.view.add;

import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelAddDoc extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;
	private HBox hBox;
	private VBox vBox;
	private Label btnAgregar;
	private TextField tfCode, tfPrior;

	public PanelAddDoc(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
		initComp();
	}

	public void initComp() {
		super.initComp();

		vBox = new VBox(20);
		btnAgregar = new Label("Agregar archivo");
		btnAgregar.setId("");
		tfCode = new TextField();
		tfPrior = new TextField();
		Label labelEscribe = new Label("Escribe el código del documento");

		labelEscribe.setId("label");
		vBox.setId("centered-box");

		hBox = new HBox(20, labelEscribe, tfCode);
		hBox.setId("centered-box");
		vBox.getChildren().add(hBox);

		labelEscribe = new Label("Escribe la prioridad del documento");
		labelEscribe.setId("label");
		hBox = new HBox(20, labelEscribe, tfPrior);
		hBox.setId("centered-box");
		vBox.getChildren().add(hBox);

		hBox = new HBox(20, btnAgregar);
		hBox.setId("centered-box");
		vBox.getChildren().add(hBox);
		setCenter(vBox);
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}

}
