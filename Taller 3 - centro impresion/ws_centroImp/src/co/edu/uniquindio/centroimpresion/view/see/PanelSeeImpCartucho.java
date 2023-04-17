package co.edu.uniquindio.centroimpresion.view.see;

import java.util.HashSet;

import co.edu.uniquindio.centroimpresion.controllers.CtrlSeeImpCartucho;
import co.edu.uniquindio.centroimpresion.controllers.SerializedData;
import co.edu.uniquindio.centroimpresion.model.ImpresoraCartucho;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuSee;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class PanelSeeImpCartucho extends PanelConVolver {
	private PanelMenuSee panel;
	private Stage stage;

	public PanelSeeImpCartucho(PanelMenuSee panelMenuSee, Stage stage) {
		this.panel = panelMenuSee;
		this.stage = stage;
		initComp();
	}

	public void initComp() {
		super.initComp();

		TableView<ImpresoraCartucho> tabla = new TableView<ImpresoraCartucho>();
		TableColumn<ImpresoraCartucho, String> colCodigo = new TableColumn<ImpresoraCartucho, String>("Codigo");
		TableColumn<ImpresoraCartucho, String> colMarca = new TableColumn<ImpresoraCartucho, String>("Marca");
		TableColumn<ImpresoraCartucho, String> colEstado = new TableColumn<ImpresoraCartucho, String>("Estado");
		TableColumn<ImpresoraCartucho, String> colColor = new TableColumn<ImpresoraCartucho, String>("Es a Color");
		TableColumn<ImpresoraCartucho, String> colVelocidad = new TableColumn<ImpresoraCartucho, String>("Velocidad");
		TableColumn<ImpresoraCartucho, String> colCapacidad = new TableColumn<ImpresoraCartucho, String>(
				"Duracion Cartucho");
		TableColumn<ImpresoraCartucho, String> colNivel = new TableColumn<ImpresoraCartucho, String>("Nivel Cartucho");
		TableColumn<ImpresoraCartucho, String> colDesgaste = new TableColumn<ImpresoraCartucho, String>(
				"Desgaste Cartucho");
		TableColumn<ImpresoraCartucho, String> colCantidad = new TableColumn<ImpresoraCartucho, String>("Veces usada");
		TableColumn<ImpresoraCartucho, String> colDocs = new TableColumn<ImpresoraCartucho, String>("Ver Documentos");

		tabla.getColumns().add(colCodigo);
		tabla.getColumns().add(colMarca);
		tabla.getColumns().add(colEstado);
		tabla.getColumns().add(colColor);
		tabla.getColumns().add(colVelocidad);
		tabla.getColumns().add(colCapacidad);
		tabla.getColumns().add(colNivel);
		tabla.getColumns().add(colDesgaste);
		tabla.getColumns().add(colCantidad);
		tabla.getColumns().add(colDocs);

		colCodigo.setCellValueFactory(CtrlSeeImpCartucho.obtenerCallbackCode());
		colMarca.setCellValueFactory(CtrlSeeImpCartucho.obtenerCallbackMarca());
		colEstado.setCellValueFactory(CtrlSeeImpCartucho.obtenerCallbackEstado());
		colColor.setCellValueFactory(CtrlSeeImpCartucho.obtenerCallbackEsAColor());
		colVelocidad.setCellValueFactory(CtrlSeeImpCartucho.obtenerCallbackVelocidad());
		colCapacidad.setCellValueFactory(CtrlSeeImpCartucho.obtenerCallbackCapacidad());
		colNivel.setCellValueFactory(CtrlSeeImpCartucho.obtenerCallbackNivel());
		colDesgaste.setCellValueFactory(CtrlSeeImpCartucho.obtenerCallbackDesgaste());
		colCantidad.setCellValueFactory(CtrlSeeImpCartucho.obtenerCallbackCantidad());
		Scene escenaCartucho = stage.getScene();
		colDocs.setCellFactory(CtrlSeeImpCartucho.obtenerCallbackDocumentos(stage, e -> {
			stage.setScene(escenaCartucho);
		}));

		tabla.setRowFactory(CtrlSeeImpCartucho.obtenerDisenioFilas());

		SerializedData data = new SerializedData();
		HashSet<ImpresoraCartucho> listaImpresoras = data.getCentroImpresion().getListaImpresorasCartucho();
		tabla.setItems(FXCollections.observableArrayList(listaImpresoras));

		setCenter(tabla);
	}

	public void volverPresionado() {
		panel.initComponents();
	}

}