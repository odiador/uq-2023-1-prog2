package co.edu.uniquindio.centroimpresion.view.see;

import java.util.Collection;

import co.edu.uniquindio.centroimpresion.controllers.CtrlSeeDocs;
import co.edu.uniquindio.centroimpresion.model.Documento;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class PanelSeeDocs extends BorderPane {
	private boolean verImpresos;
	private Collection<Documento> listaDocs = null;

	public PanelSeeDocs(boolean verImpresos, Collection<Documento> listaDocs) {
		this.verImpresos = verImpresos;
		this.listaDocs = listaDocs;
		initComp();
	}

	public PanelSeeDocs(boolean verImpresos) {
		this(verImpresos, null);
	}

	public PanelSeeDocs(Collection<Documento> listaDocs) {
		this(false, listaDocs);
	}

	public void initComp() {
		TableView<Documento> tableView = new TableView<Documento>();
		TableColumn<Documento, String> colPrioridad = new TableColumn<Documento, String>("Prioridad");
		TableColumn<Documento, String> colCodigo = new TableColumn<Documento, String>("Codigo");
		TableColumn<Documento, String> colTitulo = new TableColumn<Documento, String>("Titulo");
		TableColumn<Documento, String> colContenido = new TableColumn<>("Contenido");
		TableColumn<Documento, String> colFechaAgregado = new TableColumn<>("Fecha Agregado");
		TableColumn<Documento, String> colFechaImpreso = new TableColumn<>("Fecha Impreso");

		colPrioridad.setCellValueFactory(CtrlSeeDocs.obtenerCallbackPrioridad());
		colTitulo.setCellValueFactory(CtrlSeeDocs.obtenerCallbackTitulo());
		colCodigo.setCellValueFactory(CtrlSeeDocs.obtenerCallbackCodigo());
		colFechaAgregado.setCellValueFactory(CtrlSeeDocs.obtenerCallbackFechaAgregado());
		colFechaImpreso.setCellValueFactory(CtrlSeeDocs.obtenerCallbackFechaImpresion());
		colContenido.setCellFactory(CtrlSeeDocs.obtenerCallbackContenido());

		tableView.setRowFactory(CtrlSeeDocs.obtenerDisenioFilas());

		tableView.getColumns().add(colCodigo);
		tableView.getColumns().add(colPrioridad);
		tableView.getColumns().add(colTitulo);
		tableView.getColumns().add(colFechaAgregado);
		tableView.getColumns().add(colFechaImpreso);
		tableView.getColumns().add(colContenido);
		if (listaDocs == null)
			if (verImpresos)
				tableView.setItems(FXCollections.observableArrayList(CtrlSeeDocs.obtenerListaImpresos()));
			else
				tableView.setItems(FXCollections.observableArrayList(CtrlSeeDocs.obtenerListaCola()));
		else
			tableView.setItems(FXCollections.observableArrayList(listaDocs));
		setCenter(tableView);
	}
}
