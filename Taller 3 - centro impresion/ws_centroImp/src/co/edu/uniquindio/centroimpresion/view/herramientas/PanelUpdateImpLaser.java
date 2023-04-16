package co.edu.uniquindio.centroimpresion.view.herramientas;

import co.edu.uniquindio.centroimpresion.controllers.CtrlActualizarImpresora;
import co.edu.uniquindio.centroimpresion.model.centro.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.model.centro.ImpresoraLaser;
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

public class PanelUpdateImpLaser extends BorderPane {

	private ImpresoraLaser impresora;

	public PanelUpdateImpLaser(ImpresoraLaser impresora) {
		this.impresora = impresora;
		initComponents();
	}

	private void initComponents() {
		VBox vBox = new VBox(20);
		CheckBox checkColor = new CheckBox();
		ComboBox<String> comboEstados = new ComboBox<String>();
		TextField tfCode = new TextField();
		TextField tfMarca = new TextField();
		TextField tfVel = new TextField();
		TextField tfVelDecimal = new TextField();
		TextField tfDuracion = new TextField();

		tfCode.setEditable(false);

		Boton btnAgregar = new Boton("Actualizar Impresora",
				event -> CtrlActualizarImpresora.actualizarImpresoraLaser(tfCode.getText(), tfMarca.getText(),
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

		vBox.getChildren().add(Utility.generarHBox("Codigo de la impresora", tfCode));
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

		tfCode.setText(impresora.getCode());
		tfMarca.setText(impresora.getMarca());
		tfVel.setText(Utility.obtenerParteEnteraDouble(impresora.getLetrasPorSegundo()) + "");
		tfVelDecimal.setText(Utility.obtenerDecimalesDouble(impresora.getLetrasPorSegundo()) + "");
		tfDuracion.setText(impresora.getDuracionToner() + "");
		comboEstados.setValue(impresora.getEstado().getTexto());
		checkColor.setSelected(impresora.esAColor());
	}
}