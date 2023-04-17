package co.edu.uniquindio.centroimpresion.view.principal;

import co.edu.uniquindio.centroimpresion.controllers.ControlLogin;
import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelMain extends BorderPane {
	public PanelMain(Stage stage) {
		TextField tfName = new TextField();
		ComboBox<String> comboTipoEmpleado = new ComboBox<String>();
		comboTipoEmpleado.setItems(FXCollections.observableArrayList(TipoEmpleado.textValues()));
		VBox vbox = new VBox(30);
		Boton botonLogin = new Boton("Entrar", e -> {
			ControlLogin.hacerLogin(stage, tfName.getText(), comboTipoEmpleado.getValue());
		});

		vbox.setId("centered-box");
		tfName.setId("textfield");

		vbox.getChildren().add(Utility.generarHBox("Escribe tu nombre", tfName));
		vbox.getChildren().add(Utility.generarHBox("Elige el tipo de empleado que eres", comboTipoEmpleado));
		vbox.getChildren().add(botonLogin);

		setCenter(vbox);
	}
}
