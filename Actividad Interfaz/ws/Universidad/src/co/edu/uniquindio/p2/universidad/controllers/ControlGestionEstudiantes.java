package co.edu.uniquindio.p2.universidad.controllers;

import co.edu.uniquindio.p2.universidad.exceptions.EstudianteException;
import co.edu.uniquindio.p2.universidad.exceptions.NullException;
import co.edu.uniquindio.p2.universidad.model.Estudiante;
import co.edu.uniquindio.p2.universidad.utility.FxUtility;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControlGestionEstudiantes {

	@FXML
	private SplitPane mainPane;

	@FXML
	private ComboBox<String> comboCarrera;

	@FXML
	private TextField txtNombre;

	@FXML
	private ComboBox<String> comboSemestre;

	@FXML
	private ComboBox<String> comboEdad;

	@FXML
	private TableView<Estudiante> tablaEstudiantes;

	@FXML
	private TableColumn<Estudiante, String> columnaIdentificacion;

	@FXML
	private TextField txtIdentificacion;

	@FXML
	private TableColumn<Estudiante, String> columnaNombre;

	@FXML
	void agregarEvent(ActionEvent event) {
		agregarAction();
	}

	@FXML
	void actualizarEvent(ActionEvent event) {
		actualizarAction();
	}

	@FXML
	void cargarEstudianteEvent(ActionEvent event) {
		cargarEstudianteAction();
	}

	@FXML
	void vaciarEvent(ActionEvent event) {
		vaciarAction();
	}

	@FXML
	void eliminarEvent(ActionEvent event) {
		eliminarAction();
	}

	@FXML
	void initialize() {
		FxUtility.cambiarTituloStage(mainPane, "Gestion estudiantes | Universidad");
		columnaIdentificacion.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId()));
		columnaNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		comboCarrera.setItems(FXCollections.observableArrayList("Ingeniería de Sistemas y Computación",
				"Ingeniería Civil", "Ingeniería Topográfica", "Zoología"));
		comboSemestre.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
		comboEdad.setItems(FXCollections.observableArrayList("16", "18", "20", "22", "24", "26", "32", "40", "50", "64",
				"80", "96"));
		FxUtility.setAsNumberTextfield(txtIdentificacion);
		FxUtility.setAsIntegerTextfield(comboSemestre.getEditor(), 1, 11);
		FxUtility.setAsIntegerTextfield(comboEdad.getEditor(), 1, 120);
		tablaEstudiantes.setItems(
				FXCollections.observableArrayList(ModelFactoryController.getInstance().getListaEstudiantes()));

	}

	void actualizarInformacion() {
		ModelFactoryController.saveInstance();
		actualizarTabla();
	}

	boolean validarCampos() {
		try {
			Integer.parseInt(comboSemestre.getValue());
			Integer.parseInt(comboEdad.getValue());
		} catch (Exception e) {
			return false;
		}
		return !txtIdentificacion.getText().isEmpty() && !txtNombre.getText().isEmpty()
				&& !comboCarrera.getValue().isEmpty();
	}

	boolean validarId() {
		return !txtIdentificacion.getText().isEmpty();
	}

	void actualizarTabla() {
		tablaEstudiantes.setItems(
				FXCollections.observableArrayList(ModelFactoryController.getInstance().getListaEstudiantes()));
		tablaEstudiantes.refresh();
	}

	Estudiante obtenerEstudianteCampos() {
		int semestre = -1;
		try {
			semestre = Integer.parseInt(comboSemestre.getValue());
		} catch (NumberFormatException e) {
		}
		int edad = -1;
		try {
			edad = Integer.parseInt(comboEdad.getValue());
		} catch (NumberFormatException e) {
		}

		return new Estudiante(txtIdentificacion.getText(), txtNombre.getText(), comboCarrera.getValue(), edad,
				semestre);
	}

	void agregarAction() {
		if (validarCampos())
			agregarEstudiante();
		else
			FxUtility.mostrarMensaje("Advertencia", "No se pudo agregar el estudiante", "Rellena todos los campos",
					AlertType.WARNING);
	}

	void agregarEstudiante() {
		try {
			ModelFactoryController.getInstance().agregarEstudiante(obtenerEstudianteCampos());
			actualizarInformacion();
			FxUtility.mostrarMensaje("Informacion", "El estudiante ha sido agregado",
					"El estudiante con identificación: " + txtIdentificacion.getText() + " ha sido agregado con éxito",
					AlertType.CONFIRMATION);
		} catch (NumberFormatException | NullException | EstudianteException e) {
			FxUtility.mostrarMensaje("Error", "No se pudo agregar el estudiante", e.getMessage(), AlertType.ERROR);
		}
	}

	void eliminarAction() {
		ButtonType showAndWait = FxUtility.crearDecisionAlerta("Advertencia", "Advertencia de eliminación de elemento",
				"Confirma la eliminación", AlertType.WARNING, ButtonType.OK, ButtonType.CLOSE);
		if (showAndWait == ButtonType.OK)
			eliminarEstudiante();
	}

	void eliminarEstudiante() {
		try {
			Estudiante estudianteEliminar = tablaEstudiantes.getSelectionModel().getSelectedItem();
			ModelFactoryController.getInstance().eliminarEstudiante(estudianteEliminar);
			actualizarInformacion();
			FxUtility.mostrarMensaje("Informacion", "El estudiante ha sido eliminado",
					"El estudiante con identificación: " + estudianteEliminar.getId() + " ha sido eliminado con éxito",
					AlertType.CONFIRMATION);
		} catch (NullException | EstudianteException e) {
			FxUtility.mostrarMensaje("Advertencia", "No se pudo eliminar el estudiante", e.getMessage(),
					AlertType.WARNING);
		}
	}

	void actualizarAction() {
		if (validarId())
			actualizarEstudiante();
		else
			FxUtility.mostrarMensaje("Advertencia", "No se pudo actualizar el estudiante", "Rellena todos los campos",
					AlertType.WARNING);
	}

	void actualizarEstudiante() {
		try {
			ModelFactoryController.getInstance().actualizarEstudiante(obtenerEstudianteCampos());
			actualizarInformacion();
			FxUtility
					.mostrarMensaje("Informacion",
							"El estudiante ha sido actualizado", "El estudiante con identificación: "
									+ txtIdentificacion.getText() + " ha sido actualizado con éxito",
							AlertType.CONFIRMATION);
		} catch (NullException | EstudianteException e) {
			FxUtility.mostrarMensaje("Error", "No se pudo actualizar el estudiante", e.getMessage(), AlertType.ERROR);
		}
	}

	void cargarEstudianteAction() {
		ButtonType showAndWait = FxUtility.crearDecisionAlerta("Advertencia", "Advertencia de sobreescritura de campos",
				"Se pueden sobreescribir campos, confirma para efectuar la acción", AlertType.WARNING, ButtonType.OK,
				ButtonType.CLOSE);
		if (showAndWait == ButtonType.OK)
			cargarEstudianteCampos();
	}

	void cargarEstudianteCampos() {
		Estudiante estudianteCargado = tablaEstudiantes.getSelectionModel().getSelectedItem();
		if (estudianteCargado != null && estudianteCargado.existe()) {
			comboCarrera.setValue(estudianteCargado.getCarrera());
			txtIdentificacion.setText(estudianteCargado.getId());
			txtNombre.setText(estudianteCargado.getNombre());
			comboEdad.setValue(estudianteCargado.getEdad() + "");
			comboSemestre.setValue(estudianteCargado.getNumSemestre() + "");
		} else
			FxUtility.mostrarMensaje("Error", "El estudiante no fue cargado",
					"El estudiante no se encontró o no tiene sus datos completos", AlertType.ERROR);
	}

	void vaciarAction() {
		ButtonType showAndWait = FxUtility.crearDecisionAlerta("Advertencia", "Advertencia de sobreescritura de campos",
				"Se pueden sobreescribir campos, confirma para efectuar la acción", AlertType.WARNING, ButtonType.OK,
				ButtonType.CLOSE);
		if (showAndWait == ButtonType.OK)
			vaciarCampos();
	}

	void vaciarCampos() {
		comboCarrera.setValue("");
		txtIdentificacion.setText("");
		txtNombre.setText("");
		comboEdad.setValue("");
		comboSemestre.setValue("");

	}
}
