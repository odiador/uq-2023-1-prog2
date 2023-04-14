package co.edu.uniquindio.centroimpresion.view.see;

import java.util.HashSet;

import co.edu.uniquindio.centroimpresion.controllers.CtrlSeeImpLaser;
import co.edu.uniquindio.centroimpresion.model.archivos.SerializedData;
import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraLaser;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PanelSeeImpLaser extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;

	public PanelSeeImpLaser(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
		initComponents();
	}

	private void initComponents() {
		TableView<ImpresoraLaser> tabla = new TableView<ImpresoraLaser>();
		TableColumn<ImpresoraLaser, String> colCodigo = new TableColumn<ImpresoraLaser, String>("Codigo");
		TableColumn<ImpresoraLaser, String> colMarca = new TableColumn<ImpresoraLaser, String>("Marca");
		TableColumn<ImpresoraLaser, String> colEstado = new TableColumn<ImpresoraLaser, String>("Estado");
		TableColumn<ImpresoraLaser, String> colColor = new TableColumn<ImpresoraLaser, String>("Es a Color");
		TableColumn<ImpresoraLaser, String> colVelocidad = new TableColumn<ImpresoraLaser, String>("Velocidad");
		TableColumn<ImpresoraLaser, String> colDuracion = new TableColumn<ImpresoraLaser, String>("Duracion Toner");
		TableColumn<ImpresoraLaser, String> colNivel = new TableColumn<ImpresoraLaser, String>("Nivel Toner");
		TableColumn<ImpresoraLaser, String> colCantidad = new TableColumn<ImpresoraLaser, String>("Veces usada");

		tabla.getColumns().add(colCodigo);
		tabla.getColumns().add(colMarca);
		tabla.getColumns().add(colEstado);
		tabla.getColumns().add(colColor);
		tabla.getColumns().add(colVelocidad);
		tabla.getColumns().add(colDuracion);
		tabla.getColumns().add(colNivel);
		tabla.getColumns().add(colCantidad);

		colCodigo.setCellValueFactory(CtrlSeeImpLaser.obtenerCallbackCode());
		colMarca.setCellValueFactory(CtrlSeeImpLaser.obtenerCallbackCode());
		colEstado.setCellValueFactory(CtrlSeeImpLaser.obtenerCallbackCode());
		colColor.setCellValueFactory(CtrlSeeImpLaser.obtenerCallbackEsAColor());
		colVelocidad.setCellValueFactory(CtrlSeeImpLaser.obtenerCallbackVelocidad());
		colDuracion.setCellValueFactory(CtrlSeeImpLaser.obtenerCallbackDuracionToner());
		colNivel.setCellValueFactory(CtrlSeeImpLaser.obtenerCallbackNivelToner());
		colCantidad.setCellValueFactory(CtrlSeeImpLaser.obtenerCallbackCantidad());

		tabla.setRowFactory(CtrlSeeImpLaser.obtenerDisenioFilas());

		SerializedData data = new SerializedData();
		HashSet<ImpresoraLaser> listaImpresoras = data.getCentroImpresion().getListaImpresorasLaser();
		tabla.setItems(FXCollections.observableArrayList(listaImpresoras));

		setCenter(tabla);
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}

}
