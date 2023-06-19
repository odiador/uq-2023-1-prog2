package co.edu.uniquindio.p2.diplomado.controllers;

import java.io.IOException;

import co.edu.uniquindio.p2.diplomado.exceptions.EstudianteException;
import co.edu.uniquindio.p2.diplomado.exceptions.NullException;
import co.edu.uniquindio.p2.diplomado.model.Estudiante;
import co.edu.uniquindio.p2.diplomado.utility.FxUtility;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GestionEstudiantesController {

	@FXML
	private TableColumn<Estudiante, String> colPromedio;

	@FXML
	private TableColumn<Estudiante, String> colIdentificacion;

	@FXML
	private TableColumn<Estudiante, String> colNombre;

	@FXML
	private TableView<Estudiante> tableEstudiantes;

	@FXML
	void agregarEvent(ActionEvent event) {
		agregarAction();
	}

	@FXML
	void verDetalleEvent(ActionEvent event) {
		verDetalleAction();
	}

	@FXML
	void eliminarEvent(ActionEvent event) {
		eliminarAction();
	}

	@FXML
	void initialize() {
		colPromedio
				.setCellValueFactory(e -> new ReadOnlyStringWrapper(String.valueOf(e.getValue().getPromedioNotas())));
		colIdentificacion.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId()));
		colNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		actualizarTabla();
	}

	private void agregarAction() {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/AdicionEstudiante.fxml"));
		loader.setController(new AdicionEstudianteController(() -> stage.close(), () -> actualizarTabla()));
		stage.setTitle("Adicion de Estudiante | " + ModelFactoryController.getInstance().obtenerNombreDiplomado()
				+ " | J Amador Roa");
		stage.getIcons().add(new Image("/resources/images/Logo Window.png"));
		try {
			stage.setScene(new Scene(loader.load()));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void eliminarAction() {
		ButtonType decision = FxUtility.crearDecisionAlerta("Decision", "¿Deseas eliminar un estudiante?",
				"¿Deseas eliminar un estudiante?", AlertType.INFORMATION, ButtonType.OK, ButtonType.NO);
		if (decision != ButtonType.OK)
			return;
		ButtonType botonSeleccion = new ButtonType("Seleccion");
		ButtonType botonId = new ButtonType("Identificacion");
		decision = FxUtility.crearDecisionAlerta("Decision", "¿Deseas eliminar el estudiante seleccionado?",
				"¿Deseas eliminar el estudiante seleccionado o deseas eliminar por identificacion?",
				AlertType.INFORMATION, botonSeleccion, botonId);
		if (decision == botonSeleccion) {
			eliminarSeleccionAction();
		} else if (decision == botonId) {
			eliminarIdAction();
		}

	}

	private void eliminarIdAction() {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/EliminacionEstudiante.fxml"));
		loader.setController(new EliminacionEstudianteController(() -> stage.close(), () -> actualizarTabla()));
		stage.setTitle("Eliminacion de Estudiante | " + ModelFactoryController.getInstance().obtenerNombreDiplomado()
				+ " | J Amador Roa");
		stage.getIcons().add(new Image("/resources/images/Logo Window.png"));
		try {
			stage.setScene(new Scene(loader.load()));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void eliminarSeleccionAction() {
		Estudiante estudiante = tableEstudiantes.getSelectionModel().getSelectedItem();
		if (estudiante == null) {
			FxUtility.mostrarMensaje("Advertencia", "No se pudo eliminar el estudiante",
					"Recuerda seleccionar un estudiante", AlertType.WARNING);
			return;
		}
		try {
			ModelFactoryController.getInstance().eliminarEstudiante(estudiante.getId());
			actualizarTabla();
		} catch (EstudianteException | NullException e) {
			FxUtility.mostrarMensaje("Advertencia", "No se pudo eliminar el estudiante", e.getMessage(),
					AlertType.WARNING);
		}
	}

	private void verDetalleAction() {
		Estudiante estudiante = tableEstudiantes.getSelectionModel().getSelectedItem();
		if (estudiante == null) {
			FxUtility.mostrarMensaje("Advertencia", "No se pudo abrir el detalle del estudiante",
					"Recuerda seleccionar un estudiante", AlertType.WARNING);
			return;
		}
		verDetalleEstudiante(estudiante);
	}

	private void verDetalleEstudiante(Estudiante estudiante) {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../view/DetalleEstudiante.fxml"));
		loader.setController(new DetalleController(estudiante, () -> stage.close(), () -> actualizarTabla()));
		stage.setTitle("Detalle de Estudiante | " + ModelFactoryController.getInstance().obtenerNombreDiplomado()
				+ " | J Amador Roa");
		stage.getIcons().add(new Image("/resources/images/Logo Window.png"));
		try {
			stage.setScene(new Scene(loader.load()));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void actualizarTabla() {
		tableEstudiantes
				.setItems(FXCollections.observableArrayList(ModelFactoryController.getInstance().listarEstudiantes()));
		tableEstudiantes.refresh();
	}
}
