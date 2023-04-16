package co.edu.uniquindio.centroimpresion.view.herramientas;

import co.edu.uniquindio.centroimpresion.controllers.CtrlActualizarImpresora;
import co.edu.uniquindio.centroimpresion.model.centro.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraCartucho;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelUpdateImpCartucho extends BorderPane {
	private ImpresoraCartucho impresora;

	public PanelUpdateImpCartucho(ImpresoraCartucho impresora) {
		this.impresora = impresora;
		initComponents();
	}

	private void initComponents() {
		VBox vBox = new VBox(20);
		TextField tfCode = new TextField();
		TextField tfMarca = new TextField();
		TextField tfVel = new TextField();
		TextField tfVelDecimal = new TextField();
		TextField tfCapacidad = new TextField();
		TextField tfCapacidadDecimal = new TextField();
		TextField tfDesgaste = new TextField();
		TextField tfDesgasteDecimal = new TextField();
		ComboBox<String> comboEstados = new ComboBox<String>();
		CheckBox checkColor = new CheckBox();
		Boton btnAgregar = new Boton("Actualizar Impresora", event -> {
			CtrlActualizarImpresora.actualizarImpresoraCartucho(tfCode.getText(), tfMarca.getText(),
					comboEstados.getValue(), checkColor.isSelected(),
					Utility.juntarCadenasParaDoble(tfVel.getText(), tfVelDecimal.getText()),
					Utility.juntarCadenasParaDoble(tfCapacidad.getText(), tfCapacidadDecimal.getText()),
					Utility.juntarCadenasParaDoble(tfDesgaste.getText(), tfDesgasteDecimal.getText()));
		});

		tfCode.setEditable(false);

		tfCode.setPromptText("Escribe un codigo");
		tfMarca.setPromptText("Escribe una marca");
		tfVel.setPromptText("0");
		tfVelDecimal.setPromptText("0 letras/s");
		tfCapacidad.setPromptText("0");
		tfCapacidadDecimal.setPromptText("0 ml");
		tfDesgaste.setPromptText("0");
		tfDesgasteDecimal.setPromptText("0 ml");

		vBox.setId("centered-box");
		tfCode.setId("textfield");
		tfMarca.setId("textfield");
		tfVel.setId("textfield");
		tfCapacidad.setId("textfield");
		tfDesgaste.setId("textfield");

		HBox.setMargin(tfVel, new Insets(0, 10, 0, 10));
		HBox.setMargin(tfVelDecimal, new Insets(0, 5, 0, 10));
		HBox.setMargin(tfCapacidad, new Insets(10, 10, 10, 10));
		HBox.setMargin(tfCapacidadDecimal, new Insets(0, 5, 0, 10));
		HBox.setMargin(tfDesgaste, new Insets(10, 10, 10, 10));
		HBox.setMargin(tfDesgasteDecimal, new Insets(0, 5, 0, 10));
		checkColor.setSelected(true);
		comboEstados.setItems(FXCollections.observableArrayList(EstadoImpresora.stringValues()));

		comboEstados.setId("combobox");
		checkColor.setId("checkbox");

		vBox.getChildren().add(Utility.generarHBox("Codigo de la impresora", tfCode));
		vBox.getChildren().add(Utility.generarHBox("Escribe la marca de la impresora", tfMarca));
		vBox.getChildren().add(Utility.generarHBox("Elige el estado de la impresora", comboEstados));
		vBox.getChildren().add(Utility.generarHBox("¿La impresora es a color?", checkColor));
		vBox.getChildren().add(Utility.generarHBox(0, "Escribe la vel de la impresora (letras por segundo)", tfVel,
				new Label(","), tfVelDecimal));
		vBox.getChildren().add(Utility.generarHBox(0, "Escribe la capacidad de cartucho de la impresora", tfCapacidad,
				new Label(","), tfCapacidadDecimal));
		vBox.getChildren().add(Utility.generarHBox(0, "Escribe el desgaste del cartucho de la impresora", tfDesgaste,
				new Label(","), tfDesgasteDecimal));

		vBox.getChildren().add(btnAgregar);

		setCenter(vBox);

		Utility.setAsNumberTextfield(tfVel);
		Utility.setAsNumberTextfield(tfVelDecimal);
		Utility.setAsNumberTextfield(tfCapacidad);
		Utility.setAsNumberTextfield(tfCapacidadDecimal);
		Utility.setAsNumberTextfield(tfDesgaste);
		Utility.setAsNumberTextfield(tfDesgasteDecimal);

		Utility.setMaximumTextLength(tfCapacidadDecimal, 2);
		Utility.setMaximumTextLength(tfDesgasteDecimal, 2);
		Utility.setMaximumTextLength(tfVelDecimal, 2);

		tfCode.setText(impresora.getCode());
		tfMarca.setText(impresora.getMarca());
		tfVel.setText(Utility.obtenerParteEnteraDouble(impresora.getLetrasPorSegundo()) + "");
		tfVelDecimal.setText(Utility.obtenerDecimalesDouble(impresora.getLetrasPorSegundo()) + "");
		tfCapacidad.setText(Utility.obtenerParteEnteraDouble(impresora.getCapacidadCartucho()) + "");
		tfCapacidadDecimal.setText(Utility.obtenerDecimalesDouble(impresora.getCapacidadCartucho()) + "");
		tfDesgaste.setText(Utility.obtenerParteEnteraDouble(impresora.getDesgasteCartucho()) + "");
		tfDesgasteDecimal.setText(Utility.obtenerDecimalesDouble(impresora.getDesgasteCartucho()) + "");
		comboEstados.setValue(impresora.getEstado().getTexto());
		checkColor.setSelected(impresora.esAColor());

	}
}