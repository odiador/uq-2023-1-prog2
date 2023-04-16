package co.edu.uniquindio.centroimpresion.view.see;

import co.edu.uniquindio.centroimpresion.controllers.CtrlSeeDocs;
import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuSee;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PanelSeeDocs extends PanelConVolver {
	private PanelMenuSee panel;
	private boolean verImpresos;

	public PanelSeeDocs(PanelMenuSee panelMenuSee) {
		this(panelMenuSee, false);
	}

	public PanelSeeDocs(PanelMenuSee panelMenuSee, boolean verImpresos) {
		this.panel = panelMenuSee;
		this.verImpresos = verImpresos;
		initComp();
	}

	public void initComp() {
		super.initComp();
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

		if (verImpresos)
			tableView.setItems(FXCollections.observableArrayList(CtrlSeeDocs.obtenerListaImpresos()));
		else
			tableView.setItems(FXCollections.observableArrayList(CtrlSeeDocs.obtenerListaCola()));
		setCenter(tableView);
	}

	@Override
	public void volverPresionado() {
		panel.initComponents();
	}

}
