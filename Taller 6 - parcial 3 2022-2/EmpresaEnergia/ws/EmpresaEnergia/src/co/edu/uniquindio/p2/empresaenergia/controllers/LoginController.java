package co.edu.uniquindio.p2.empresaenergia.controllers;

import java.io.IOException;
import java.net.URL;

import co.edu.uniquindio.p2.empresaenergia.model.Empleado;
import co.edu.uniquindio.p2.empresaenergia.utility.FxUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private BorderPane mainPane;

	@FXML
	private PasswordField txtContrasena;

	@FXML
	private TextField txtIdentificacion;

	@FXML
	void cerrarEvent(ActionEvent event) {
		cerrarAction();
	}

	private void cerrarAction() {
		System.exit(0);
	}

	@FXML
	void loginEvent(ActionEvent event) {
		loginAction();
	}

	private void loginAction() {
		Empleado empleadoInicioSesion = ModelFactoryController.getInstance().iniciarSesion(txtIdentificacion.getText(),
				txtContrasena.getText());
		if (empleadoInicioSesion == null) {
			FxUtility.mostrarMensaje("Inicio de Sesion", "No se pudo iniciar sesion",
					"Tu identificacion y/o contraseña es incorrecta", AlertType.WARNING);
		} else {
			Stage stage = (Stage) mainPane.getScene().getWindow();
			stage.setResizable(true);

			FXMLLoader loader = new FXMLLoader();
			URL resource = getClass().getResource("../view/MenuPrncipal.fxml");
			loader.setLocation(resource);
			try {
				Scene scene = new Scene(loader.load(), 1280, 720);
				stage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void registrarEvent(ActionEvent event) {

		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setResizable(true);

		FXMLLoader loader = new FXMLLoader();
		URL resource = getClass().getResource("../view/RegistrarEmpleado.fxml");
		loader.setLocation(resource);
		try {
			Scene scene = new Scene(loader.load(), 600, 400);
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
