package co.edu.uniquindio.p2.empresaenergia.controllers;

import co.edu.uniquindio.p2.empresaenergia.exceptions.NullException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.PersonaException;
import co.edu.uniquindio.p2.empresaenergia.model.Empleado;
import co.edu.uniquindio.p2.empresaenergia.utility.FxUtility;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public class GestionEmpleadosController {
	@FXML
	private TableView<Empleado> tablaEmpleados;

	@FXML
	private TableColumn<Empleado, String> columnaIdentificacion;

	@FXML
	private BorderPane mainPane;

	@FXML
	private TableColumn<Empleado, String> columnaNombre;

	@FXML
	void eliminarSelectionEvent(ActionEvent event) {
		eliminarSelectionAction();
	}

	@FXML
	void initialize() {
		columnaIdentificacion.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId()));
		columnaNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		actualizarTabla();
	}

	private void eliminarSelectionAction() {
		Empleado empleado = tablaEmpleados.getSelectionModel().getSelectedItem();
		try {
			ModelFactoryController.getInstance().eliminarEmpleado(empleado);
			FxUtility.mostrarMensaje("Informacion", "Empleado eliminado", "El emplado ha sido eliminado con éxito",
					AlertType.CONFIRMATION);
			actualizarTabla();
		} catch (NullException | PersonaException e) {
			FxUtility.mostrarMensaje("Advertencia", "El empleado no se pudo eliminar", e.getMessage(),
					AlertType.WARNING);
		}
	}

	private void actualizarTabla() {
		tablaEmpleados
				.setItems(FXCollections.observableArrayList(ModelFactoryController.getInstance().getListaEmpleados()));
		tablaEmpleados.refresh();
	}
}
