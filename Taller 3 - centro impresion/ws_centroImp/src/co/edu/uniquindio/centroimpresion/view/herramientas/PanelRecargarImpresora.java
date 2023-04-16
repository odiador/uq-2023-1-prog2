package co.edu.uniquindio.centroimpresion.view.herramientas;

import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelRecargarImpresora extends PanelConVolver {
	public PanelRecargarImpresora() {
		initComp();
	}

	@Override
	public void initComp() {
		super.initComp();
		VBox vbox = new VBox(30);
		TextField textField = new TextField();
		Label bAceptar = new Label("Aceptar");
		BorderPane borderPane = new BorderPane(bAceptar);

		vbox.setId("centered-box");
		textField.setId("textfield");
		borderPane.setId("btn-case");
		bAceptar.setId("btn");

		vbox.getChildren().add(Utility.generarHBox("Escribe el codigo de la impresora", textField));
		vbox.getChildren().add(borderPane);
		bAceptar.setOnMouseReleased(evt -> {

		});
		setCenter(vbox);
	}

	@Override
	public void volverPresionado() {
		// TODO Auto-generated method stub

	}

}
