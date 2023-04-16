package co.edu.uniquindio.centroimpresion.view.herramientas;

import co.edu.uniquindio.centroimpresion.controllers.CtrlUtil;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuUpdate;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PanelActualizarImpresora extends PanelConVolver {
	private PanelMenuUpdate panelMenuUpdate;

	public PanelActualizarImpresora(PanelMenuUpdate panelMenuUpdate) {
		this.panelMenuUpdate = panelMenuUpdate;
		initComp();
	}

	@Override
	public void initComp() {
		super.initComp();
		TextField tfCode = new TextField();
		VBox vbox = new VBox(30);
		vbox.setId("centered-box");
		vbox.getChildren().add(Utility.generarHBox("Escribe el codigo de la impresora", tfCode));
		vbox.getChildren().add(new Boton("Actualizar impresora", e -> {
			CtrlUtil.irAActualizarImpresora(panelMenuUpdate, tfCode.getText(), eventoVolver -> {
				panelMenuUpdate.setCenter(this);
			});
		}));

		setCenter(vbox);
	}

	@Override
	public void volverPresionado() {
		panelMenuUpdate.initComponents();
	}

}
