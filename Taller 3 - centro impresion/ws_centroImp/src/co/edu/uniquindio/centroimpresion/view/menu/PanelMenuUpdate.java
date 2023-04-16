package co.edu.uniquindio.centroimpresion.view.menu;

import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PanelMenuUpdate extends BorderPane {
	private TipoEmpleado tipoEmpleado;

	public PanelMenuUpdate(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
		initComponents();
	}

	public void initComponents() {
		VBox vbox = new VBox(30);

		vbox.setId("centered-box");
		vbox.getChildren().add(new Boton("Rellenar", e -> {
		}, "boton-opcion"));
		if (tipoEmpleado.puedeActualizarDocumento()) {
			vbox.getChildren().add(new Boton("Actualizar Doc", e -> {
			}, "boton-opcion"));
		}
		if (tipoEmpleado.puedeSeleccionarImpresora()) {
			vbox.getChildren().add(new Boton("Seleccionar Impresora", e -> {
			}, "boton-opcion"));
		}
		setCenter(vbox);
	}
}
