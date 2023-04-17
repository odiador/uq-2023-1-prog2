package co.edu.uniquindio.centroimpresion.view.add;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPanelAddImpLaser;
import co.edu.uniquindio.centroimpresion.model.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.util.Utility;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelAddImpLaser extends PanelConVolver {

	private PanelMenuOpcionObjetos panel;

	public PanelAddImpLaser(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
		initComp();
	}

	@Override
	public void initComp() {
		super.initComp();

		VBox vBox = new VBox(20);
		CheckBox checkColor = new CheckBox();
		ComboBox<String> comboEstados = new ComboBox<String>();
		TextField tfCode = new TextField();
		TextField tfMarca = new TextField();
		TextField tfVel = new TextField();
		TextField tfVelDecimal = new TextField();
		TextField tfDuracion = new TextField();

		Boton btnAgregar = new Boton("Agregar Impresora",
				event -> CtrlPanelAddImpLaser.agregarImpresora(tfCode.getText(), tfMarca.getText(),
						comboEstados.getValue(), checkColor.isSelected(),
						Utility.juntarCadenasParaDoble(tfVel.getText(), tfVelDecimal.getText()), tfDuracion.getText()));

		tfCode.setPromptText("Escribe un codigo");
		tfMarca.setPromptText("Escribe una marca");
		tfVel.setPromptText("0");
		tfVelDecimal.setPromptText("0 letras/s");
		tfDuracion.setPromptText("0 unidades");

		vBox.setId("centered-box");
		tfCode.setId("textfield");
		tfMarca.setId("textfield");
		tfVel.setId("textfield");
		tfDuracion.setId("textfield");

		HBox.setMargin(tfVel, new Insets(0, 10, 0, 10));
		HBox.setMargin(tfVelDecimal, new Insets(0, 5, 0, 10));
		checkColor.setSelected(true);
		comboEstados.setItems(FXCollections.observableArrayList(EstadoImpresora.stringValues()));

		comboEstados.setId("combobox");
		checkColor.setId("checkbox");

		vBox.getChildren().add(Utility.generarHBox("Escribe el codigo de la impresora", tfCode));
		vBox.getChildren().add(Utility.generarHBox("Escribe la marca de la impresora", tfMarca));
		vBox.getChildren().add(Utility.generarHBox("Elige el estado de la impresora", comboEstados));
		vBox.getChildren().add(Utility.generarHBox("¿La impresora es a color?", checkColor));
		vBox.getChildren().add(Utility.generarHBox(0, "Escribe la vel de la impresora (letras por segundo)", tfVel,
				new Label(","), tfVelDecimal));
		vBox.getChildren().add(Utility.generarHBox("Escribe la duracion del toner de la impresora", tfDuracion));

		vBox.getChildren().add(btnAgregar);
		setCenter(vBox);

		Utility.setAsNumberTextfield(tfVel);
		Utility.setAsNumberTextfield(tfVelDecimal);
		Utility.setAsNumberTextfield(tfDuracion);

	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}
}
