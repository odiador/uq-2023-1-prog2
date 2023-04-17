package co.edu.uniquindio.centroimpresion.view.see;

import java.util.List;

import co.edu.uniquindio.centroimpresion.controllers.CtrlSeeImps;
import co.edu.uniquindio.centroimpresion.controllers.SerializedData;
import co.edu.uniquindio.centroimpresion.model.Impresora;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuSee;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class PanelSeeImps extends PanelConVolver {
	private PanelMenuSee panelMenuSee;
	private Stage stage;

	public PanelSeeImps(PanelMenuSee panelMenuSee, Stage stage) {
		this.panelMenuSee = panelMenuSee;
		this.stage = stage;
		initComp();
	}

	@Override
	public void initComp() {
		super.initComp();

		TableView<Impresora> tabla = new TableView<Impresora>();
		TableColumn<Impresora, String> colCodigo = new TableColumn<Impresora, String>("Codigo");
		TableColumn<Impresora, String> colMarca = new TableColumn<Impresora, String>("Marca");
		TableColumn<Impresora, String> colEstado = new TableColumn<Impresora, String>("Estado");
		TableColumn<Impresora, String> colColor = new TableColumn<Impresora, String>("Es a Color");
		TableColumn<Impresora, String> colVelocidad = new TableColumn<Impresora, String>("Velocidad");
		TableColumn<Impresora, String> colCantidad = new TableColumn<Impresora, String>("Veces usada");
		TableColumn<Impresora, String> colDocs = new TableColumn<Impresora, String>("Ver Documentos");

		tabla.getColumns().add(colCodigo);
		tabla.getColumns().add(colMarca);
		tabla.getColumns().add(colEstado);
		tabla.getColumns().add(colColor);
		tabla.getColumns().add(colVelocidad);
		tabla.getColumns().add(colCantidad);
		tabla.getColumns().add(colDocs);

		colCodigo.setCellValueFactory(CtrlSeeImps.obtenerCallbackCode());
		colMarca.setCellValueFactory(CtrlSeeImps.obtenerCallbackMarca());
		colEstado.setCellValueFactory(CtrlSeeImps.obtenerCallbackEstado());
		colColor.setCellValueFactory(CtrlSeeImps.obtenerCallbackEsAColor());
		colVelocidad.setCellValueFactory(CtrlSeeImps.obtenerCallbackVelocidad());
		colCantidad.setCellValueFactory(CtrlSeeImps.obtenerCallbackCantidad());
		Scene escena = stage.getScene();
		colDocs.setCellFactory(CtrlSeeImps.obtenerCallbackDocumentos(stage, e -> stage.setScene(escena)));

		tabla.setRowFactory(CtrlSeeImps.obtenerDisenioFilas());

		SerializedData data = new SerializedData();
		List<Impresora> listaImpresoras = data.getCentroImpresion().getListaImpresoras();
		tabla.setItems(FXCollections.observableArrayList(listaImpresoras));

		setCenter(tabla);
	}

	@Override
	public void volverPresionado() {
		panelMenuSee.initComponents();
	}

}
