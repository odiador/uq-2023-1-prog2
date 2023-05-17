package co.edu.uniquindio.p2.empresaenergia.controllers;

import co.edu.uniquindio.p2.empresaenergia.exceptions.ClienteException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.NullException;
import co.edu.uniquindio.p2.empresaenergia.model.Cliente;
import co.edu.uniquindio.p2.empresaenergia.model.ClienteJuridico;
import co.edu.uniquindio.p2.empresaenergia.model.ClienteNatural;
import co.edu.uniquindio.p2.empresaenergia.utility.FxUtility;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionClienteJuridicoController {
	@FXML
	private TextField txtTipoEmpresa;

	@FXML
	private TableView<Cliente> tableClientes;

	@FXML
	private TextField txtNombre;

	@FXML
	private TableColumn<Cliente, String> columnId;

	@FXML
	private TextField txtTelefono;

	@FXML
	private TextField txtNit;

	@FXML
	private TableColumn<Cliente, String> columnNombre;

	@FXML
	private SplitPane mainPane;

	@FXML
	private TableColumn<Cliente, String> columnTipoCliente;

	@FXML
	void initialize() {
		columnId.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getId()));
		columnNombre.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getNombre()));
		columnTipoCliente.setCellValueFactory(e -> {
			String tipoCliente = e.getValue() instanceof ClienteNatural ? "Natural" : "Juridico";
			return new ReadOnlyStringWrapper(tipoCliente);
		});
		FxUtility.setAsNumberTextfield(txtTelefono);
		actualizarTabla();
	}

	/**
	 * Obtiene un cliente natural a partir de los campos de texto
	 * 
	 * @return
	 */
	private ClienteJuridico obtenerClienteJuridicoCampos() {
		return new ClienteJuridico(txtNit.getText(), txtNombre.getText(), txtTelefono.getText(),
				txtTipoEmpresa.getText());
	}

	@FXML
	void agregarEvent(ActionEvent event) {
		agregarAction();
	}

	/**
	 * Agrega un cliente a la empresa de energía
	 */
	private void agregarAction() {
		try {
			ClienteJuridico clienteJuridicoCampos = obtenerClienteJuridicoCampos();
			ModelFactoryController.getInstance().agregarCliente(clienteJuridicoCampos);
			actualizarTabla();
			FxUtility.mostrarMensaje("Informacion", "El cliente ha sido agregado con éxito", "",
					AlertType.CONFIRMATION);
		} catch (NullException | ClienteException e) {
			FxUtility.mostrarMensaje("Advertencia", "No se pudo agregar el cliente", e.getMessage(), AlertType.WARNING);
		}
	}

	@FXML
	void actualizarEvent(ActionEvent event) {

	}

	@FXML
	void vaciarCamposEvent(ActionEvent event) {
		vaciarCamposAction();
	}

	private void vaciarCamposAction() {
		FxUtility.generarAdvertenciaSobreescritura(() -> {
			txtNit.setText("");
			txtNombre.setText("");
			txtTelefono.setText("");
			txtTipoEmpresa.setText("");
		});
	}

	@FXML
	void cargarDatosEvent(ActionEvent event) {
		cargarDatosAction();
	}

	private void cargarDatosAction() {
		Cliente cliente = tableClientes.getSelectionModel().getSelectedItem();
		if (cliente == null) {
			FxUtility.mostrarMensaje("Advertencia", "Selecciona un elemento en la tabla", "", AlertType.ERROR);
			return;
		}
		if (!(cliente instanceof ClienteJuridico)) {
			FxUtility.mostrarMensaje("Advertencia", "El cliente no es jurídico", "", AlertType.ERROR);
			return;
		}
		FxUtility.generarAdvertenciaSobreescritura(() -> {
			ClienteJuridico clienteNatural = (ClienteJuridico) cliente;
			txtNit.setText(clienteNatural.getNit());
			txtTelefono.setText(clienteNatural.getTelefono());
			txtTipoEmpresa.setText(clienteNatural.getTipoEmpresa());
			txtNombre.setText(clienteNatural.getNombre());
		});
	}

	@FXML
	void eliminarSelecionEvent(ActionEvent event) {
		Cliente cliente = tableClientes.getSelectionModel().getSelectedItem();
		if (cliente == null) {
			FxUtility.mostrarMensaje("Advertencia", "Selecciona un elemento en la tabla", "", AlertType.ERROR);
			return;
		}
		try {
			ModelFactoryController.getInstance().eliminarCliente(cliente);
			actualizarTabla();
			FxUtility.mostrarMensaje("Informacion", "El cliente fue eliminado con éxito", "", AlertType.CONFIRMATION);
		} catch (NullException | ClienteException e) {
			FxUtility.mostrarMensaje("Informacion", "El cliente no pudo ser eliminado", e.getMessage(), AlertType.CONFIRMATION);
		}
	}

	private void actualizarTabla() {
		tableClientes
				.setItems(FXCollections.observableArrayList(ModelFactoryController.getInstance().getListaClientes()));
		tableClientes.refresh();
	}
}
