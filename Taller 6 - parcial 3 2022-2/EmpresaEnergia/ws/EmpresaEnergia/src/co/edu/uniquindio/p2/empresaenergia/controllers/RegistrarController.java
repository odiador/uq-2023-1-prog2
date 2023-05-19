package co.edu.uniquindio.p2.empresaenergia.controllers;

import java.io.IOException;
import java.net.URL;

import co.edu.uniquindio.p2.empresaenergia.exceptions.NullException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.PersonaException;
import co.edu.uniquindio.p2.empresaenergia.model.Empleado;
import co.edu.uniquindio.p2.empresaenergia.utility.FxUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RegistrarController {

	@FXML
	private TextField txtNombre;
	@FXML
	private BorderPane mainPane;

	@FXML
	private PasswordField txtContrasena;

	@FXML
	private TextField txtIdentificacion;

	@FXML
	void volverEvent(ActionEvent event) {
		volverAction();
	}

	@FXML
	void registrarEvent(ActionEvent event) {
		Empleado empleado = new Empleado(txtIdentificacion.getText(), txtNombre.getText(), txtContrasena.getText());
		try {
			ModelFactoryController.getInstance().agregarEmpleado(empleado);
			FxUtility.mostrarMensaje("Informacion", "Te has registrado con éxito", "Te has registrado con éxito",
					AlertType.INFORMATION);
		} catch (NullException | PersonaException e) {
			FxUtility.mostrarMensaje("Advertencia", "No te has podido registrar", e.getMessage(), AlertType.ERROR);
		}
	}

	private void volverAction() {
		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setResizable(true);

		FXMLLoader loader = new FXMLLoader();
		URL resource = getClass().getResource("../view/InicioSesion.fxml");
		loader.setLocation(resource);
		try {
			Scene scene = new Scene(loader.load(), 600, 400);
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
