package co.edu.uniquindio.p2.diplomado.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import co.edu.uniquindio.p2.diplomado.exceptions.AtributosFaltantesException;
import co.edu.uniquindio.p2.diplomado.exceptions.EstudianteException;
import co.edu.uniquindio.p2.diplomado.exceptions.NotaException;
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
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DetalleController {

	@FXML
	private TextField txtNombre;

	@FXML
	private TableView<String> tableNotas;

	@FXML
	private TableColumn<String, String> colNotas;

	@FXML
	private BorderPane mainPane;

	@FXML
	private TextField txtIdentificacion;

	@FXML
	private TextField txtGenero;

	@FXML
	void initialize() {
		txtIdentificacion.setText(estudiante.getId());
		txtGenero.setText(estudiante.getGenero().getText());
		txtNombre.setText(estudiante.getNombre());
		colNotas.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue()));
		actualizarTabla();
	}

	@FXML
	void volverEvent(ActionEvent event) {
		volverAction();
	}

	private Estudiante estudiante;

	private Runnable cerrarRunnable;

	private Runnable actualizarTablaRunnable;

	private void volverAction() {
		cerrarRunnable.run();
	}

	public DetalleController(Estudiante estudiante, Runnable cerrarRunnable, Runnable actualizarTablaRunnable) {
		this.estudiante = estudiante;
		this.cerrarRunnable = cerrarRunnable;
		this.actualizarTablaRunnable = actualizarTablaRunnable;
	}

	@FXML
	void agregarNotaEvent(ActionEvent event) {
		agregarNotaAction();
	}

	@FXML
	void eliminarNotaEvent(ActionEvent event) {
		eliminarNotaAction();
	}

	@FXML
	void editarNotaEvent(ActionEvent event) {
		editarNotaAction();
	}

	private void eliminarNotaAction() {
		ButtonType decision = FxUtility.crearDecisionAlerta("Decision", "¿Deseas eliminar una nota?",
				"¿Deseas eliminar la nota seleccionada?", AlertType.INFORMATION, ButtonType.NO, ButtonType.OK);
		if (decision != ButtonType.OK)
			return;

		String selectedItem = tableNotas.getSelectionModel().getSelectedItem();
		int selectedIndex = tableNotas.getSelectionModel().getSelectedIndex();
		float nota = Float.parseFloat(selectedItem != null ? selectedItem : "-1.0");
		if (nota < 0) {
			FxUtility.mostrarMensaje("Advertencia", "No se puede eliminar la nota", "Elige una nota",
					AlertType.WARNING);
			return;
		}
		try {
			estudiante.eliminarNotaIndex(selectedIndex, nota);
			ModelFactoryController.getInstance().actualizarEstudiante(estudiante);
			actualizarTablas();
			FxUtility.mostrarMensaje("Informacion", "La nota ha sido eliminada", "La nota ha sido eliminada",
					AlertType.CONFIRMATION);
		} catch (NotaException | EstudianteException | NullException | AtributosFaltantesException e) {
			FxUtility.mostrarMensaje("Error", "No se pudo eliminar la nota", e.getMessage(), AlertType.ERROR);
		}
	}

	private void actualizarTablas() {
		actualizarTabla();
		actualizarTablaRunnable.run();
	}

	private void editarNotaAction() {
		Scene escenaAnterior = mainPane.getScene();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AccionNota.fxml"));

		TableViewSelectionModel<String> selectionModel = tableNotas.getSelectionModel();
		float nota = Float.parseFloat(selectionModel.getSelectedItem());
		int index = selectionModel.getSelectedIndex();
		loader.setController(new EdicionNotaController(index, nota, estudiante, () -> stage.setScene(escenaAnterior),
				() -> actualizarTablas()));
		try {
			stage.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void agregarNotaAction() {
		Scene escenaAdicionEstudiante = mainPane.getScene();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AccionNota.fxml"));
		loader.setController(new AdicionNotaController(estudiante, () -> stage.setScene(escenaAdicionEstudiante),
				() -> actualizarTablas()));
		try {
			stage.setScene(new Scene(loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void actualizarTabla() {
		try {
			tableNotas.setItems(FXCollections
					.observableArrayList(ModelFactoryController.getInstance().listarNotasEstudiante(estudiante.getId())
							.stream().map((nota -> String.valueOf(nota))).collect(Collectors.toList())));
			tableNotas.refresh();
		} catch (EstudianteException e) {
			e.printStackTrace();
		}
	}
}
