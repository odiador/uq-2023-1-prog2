package co.edu.uniquindio.centroimpresion.view.herramientas;

import co.edu.uniquindio.centroimpresion.controllers.CtrlUtil;
import co.edu.uniquindio.centroimpresion.util.Utility;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.menu.PanelMenuUpdate;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PanelActualizarDoc extends PanelConVolver {
	private PanelMenuUpdate panelMenuUpdate;

	public PanelActualizarDoc(PanelMenuUpdate panelMenuUpdate) {
		this.panelMenuUpdate = panelMenuUpdate;
		initComp();
	}

	@Override
	public void initComp() {
		super.initComp();
		VBox vbox = new VBox(30);
		TextField tfCode = new TextField();
		TextField tfPrior = new TextField();
		CheckBox checkCambiarFile = new CheckBox();

		vbox.setId("centered-box");
		tfCode.setId("textfield");
		tfPrior.setId("textfield");
		checkCambiarFile.setId("checkbox");

		tfCode.setPromptText("Escribe un código");
		tfPrior.setPromptText("5");

		vbox.getChildren().add(Utility.generarHBox("Escribe el codigo del documento", tfCode));
		vbox.getChildren().add(Utility.generarHBox("Escribe la prioridad del documento a editar", tfPrior));
		vbox.getChildren().add(Utility.generarHBox("Deseas cambiar el archivo?", checkCambiarFile));

		vbox.getChildren().add(new Boton("Actualizar Documento",
				e -> CtrlUtil.actualizarDocumento(tfCode.getText(), tfPrior.getText(), checkCambiarFile.isSelected())));

		Utility.setAsNumberTextfield(tfPrior);
		Utility.setMaximumTextLength(tfPrior, 2);

		setCenter(vbox);
	}

	@Override
	public void volverPresionado() {
		panelMenuUpdate.initComponents();
	}

}
