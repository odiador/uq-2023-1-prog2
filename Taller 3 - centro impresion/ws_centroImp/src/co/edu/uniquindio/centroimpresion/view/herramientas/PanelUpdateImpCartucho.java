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
	private boolean sePuedeEditar;

	public PanelUpdateImpCartucho(ImpresoraCartucho impresora, boolean sePuedeEditar) {
		this.impresora = impresora;
		this.sePuedeEditar = sePuedeEditar;
		initComponents();
	}

	public PanelUpdateImpCartucho(ImpresoraCartucho impresora) {
		this(impresora, true);
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
		CheckBox checkColor = new CheckBox() {
			@Override
			public void arm() {
				if (sePuedeEditar)
					super.arm();
			}
		};
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

		vBox.getChildren().add(Utility.generarHBox("Codigo de la impresora", tfCode));
		vBox.getChildren().add(Utility.generarHBox("Marca de la impresora", tfMarca));
		vBox.getChildren().add(Utility.generarHBox("Estado de la impresora", comboEstados));
		if (sePuedeEditar) {
			vBox.getChildren().add(Utility.generarHBox("Estado de la impresora", comboEstados));
		} else {
			vBox.getChildren().add(Utility.generarHBox("Estado de la impresora", new Label(comboEstados.getValue())));
		}
		vBox.getChildren().add(Utility.generarHBox("¿La impresora es a color?", checkColor));
		vBox.getChildren().add(Utility.generarHBox(0, "Velocidad de la impresora (letras por segundo)", tfVel,
				new Label(","), tfVelDecimal));
		vBox.getChildren().add(Utility.generarHBox(0, "Capacidad de cartucho de la impresora", tfCapacidad,
				new Label(","), tfCapacidadDecimal));
		vBox.getChildren().add(Utility.generarHBox(0, "Desgaste del cartucho de la impresora", tfDesgaste,
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

		if (!sePuedeEditar) {
			tfMarca.setEditable(false);
			tfVel.setEditable(false);
			tfVelDecimal.setEditable(false);
			tfCapacidad.setEditable(false);
			tfCapacidadDecimal.setEditable(false);
			tfDesgaste.setEditable(false);
			tfDesgasteDecimal.setEditable(false);
			comboEstados.getEditor().setEditable(false);
			btnAgregar.setVisible(false);
		}
	}
}