package co.edu.uniquindio.centroimpresion.view.remove;

import co.edu.uniquindio.centroimpresion.controllers.CtrlEliminar;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PanelRemoveImp extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;

	public PanelRemoveImp(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
		initComp();
	}

	@Override
	public void initComp() {
		super.initComp();
		VBox vBox = new VBox(30);
		TextField tfCode = new TextField();
		Boton bEliminar = new Boton("Eliminar Impresora", evento -> CtrlEliminar.eliminarImpresora(tfCode.getText()));

		vBox.setId("centered-box");
		tfCode.setId("textfield");

		vBox.getChildren().add(Utility.generarHBox("Escribe el codigo de la impresora a eliminar: ", tfCode));
		vBox.getChildren().add(bEliminar);

		setCenter(vBox);
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}
}
