package co.edu.uniquindio.p2.empresaenergia.controllers;

import static co.edu.uniquindio.p2.empresaenergia.controllers.ModelFactoryController.getInstance;

import java.time.LocalDate;

import co.edu.uniquindio.p2.empresaenergia.exceptions.FacturaException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.NullException;
import co.edu.uniquindio.p2.empresaenergia.model.Persona;
import co.edu.uniquindio.p2.empresaenergia.model.Factura;
import co.edu.uniquindio.p2.empresaenergia.utility.FxUtility;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionFacturaController {
	@FXML
	private TableColumn<Factura, String> columnCodigo;

	@FXML
	private DatePicker dateFechaFacturacion;

	@FXML
	private TableView<Factura> tableFacturas;

	@FXML
	private TextField txtIdentificacion;

	@FXML
	private TextField txtCodigo;

	@FXML
	private SplitPane mainPane;

	@FXML
	private TextField txtTotal;

	@FXML
	private TableColumn<Factura, String> columnTotal;

	@FXML
	void initialize() {
		columnCodigo.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getCodigo()));
		columnTotal.setCellValueFactory(e -> new ReadOnlyStringWrapper(e.getValue().getTotalConFormato()));
		dateFechaFacturacion.setValue(LocalDate.now());
		actualizarTabla();
	}

	private void actualizarTabla() {
		tableFacturas.setItems(FXCollections.observableArrayList(getInstance().getListaFacturas()));
		tableFacturas.refresh();
	}

	private Factura obtenerFacturaCampos() {
		double total = -1d;
		try {
			total = Double.parseDouble(txtTotal.getText());
		} catch (NumberFormatException e) {
		}
		Persona cliente = ModelFactoryController.getInstance().buscarCliente(txtIdentificacion.getText());
		return new Factura(txtCodigo.getText(), dateFechaFacturacion.getValue(), total, cliente);
	}

	@FXML
	void agregarEvent(ActionEvent event) {
		agregarAction();
	}

	private void agregarAction() {
		try {
			ModelFactoryController.getInstance().registrarFactura(obtenerFacturaCampos());
			actualizarTabla();
			FxUtility.mostrarMensaje("Informacion", "La factura ha sido agregada con éxito", "",
					AlertType.CONFIRMATION);
		} catch (NumberFormatException | NullException | FacturaException e) {
			FxUtility.mostrarMensaje("Advertencia", "No se pudo registrar la factura", e.getMessage(),
					AlertType.WARNING);
		}

	}

	@FXML
	void vaciarCamposEvent(ActionEvent event) {
		vaciarCamposAction();
	}

	private void vaciarCamposAction() {
		FxUtility.generarAdvertenciaSobreescritura(() -> {
			txtCodigo.setText("");
			txtTotal.setText("");
			dateFechaFacturacion.setValue(null);
		});
	}

	@FXML
	void cargarDatosEvent(ActionEvent event) {
		cargarDatosAction();

	}

	private void cargarDatosAction() {
		Factura factura = tableFacturas.getSelectionModel().getSelectedItem();
		if (factura == null)
			FxUtility.mostrarMensaje("Advertencia", "", "La factura no existe", AlertType.WARNING);
		txtCodigo.setText(factura.getCodigo());
		txtTotal.setText(factura.getTotal() + "");
		dateFechaFacturacion.setValue(factura.getFechaFacturacion());
		txtIdentificacion.setText(factura.getCliente().getId());
		FxUtility.mostrarMensaje("Informacion", "", "La factura fue cargada exitosamente", AlertType.CONFIRMATION);
	}

	@FXML
	void eliminarSelecionEvent(ActionEvent event) {
		eliminarSeleccionAction();
	}

	private void eliminarSeleccionAction() {
		Factura factura = tableFacturas.getSelectionModel().getSelectedItem();
		if (factura == null)
			FxUtility.mostrarMensaje("Advertencia", "", "La factura no existe", AlertType.WARNING);
		if (factura.getCodigo() != null) {
			try {
				ModelFactoryController.getInstance().eliminarFactura(factura);
				actualizarTabla();
				FxUtility.mostrarMensaje("Informacion", "La factura ha sido eliminada con exito", "",
						AlertType.WARNING);
			} catch (NullException | FacturaException e) {
				FxUtility.mostrarMensaje("Advertencia", "No se pudo eliminar la seleccion", e.getMessage(),
						AlertType.WARNING);
			}
		}
	}

}
