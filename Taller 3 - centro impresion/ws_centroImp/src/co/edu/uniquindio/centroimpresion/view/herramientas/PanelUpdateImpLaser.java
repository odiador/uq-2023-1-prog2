package co.edu.uniquindio.centroimpresion.view.herramientas;

import co.edu.uniquindio.centroimpresion.controllers.CtrlActualizarImpresora;
import co.edu.uniquindio.centroimpresion.model.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.model.ImpresoraLaser;
import co.edu.uniquindio.centroimpresion.util.Utility;
import co.edu.uniquindio.centroimpresion.view.custom.Boton;
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
	private boolean sePuedeEditar;

	public PanelUpdateImpLaser(ImpresoraLaser impresora, boolean sePuedeEditar) {
		this.impresora = impresora;
		this.sePuedeEditar = sePuedeEditar;
		initComponents();
	}

	public PanelUpdateImpLaser(ImpresoraLaser impresora) {
		this(impresora, true);
	}

	private void initComponents() {
		VBox vBox = new VBox(20);
		ComboBox<String> comboEstados = new ComboBox<String>();
		TextField tfCode = new TextField();
		TextField tfMarca = new TextField();
		TextField tfVel = new TextField();
		TextField tfVelDecimal = new TextField();
		TextField tfDuracion = new TextField();
		CheckBox checkColor = new CheckBox() {
			@Override
			public void arm() {
				if (sePuedeEditar)
					super.arm();
			}
		};

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

		tfCode.setText(impresora.getCode());
		tfMarca.setText(impresora.getMarca());
		tfVel.setText(Utility.obtenerParteEnteraDouble(impresora.getLetrasPorSegundo()) + "");
		tfVelDecimal.setText(Utility.obtenerDecimalesDouble(impresora.getLetrasPorSegundo()) + "");
		tfDuracion.setText(impresora.getDuracionToner() + "");
		comboEstados.setValue(impresora.getEstado().getTexto());
		checkColor.setSelected(impresora.esAColor());

		vBox.getChildren().add(Utility.generarHBox("Codigo de la impresora", tfCode));
		vBox.getChildren().add(Utility.generarHBox("Marca de la impresora", tfMarca));
		if (sePuedeEditar) {
			vBox.getChildren().add(Utility.generarHBox("Estado de la impresora", comboEstados));
		} else {
			vBox.getChildren().add(Utility.generarHBox("Estado de la impresora", new Label(comboEstados.getValue())));
		}
		vBox.getChildren().add(Utility.generarHBox("¿La impresora es a color?", checkColor));
		vBox.getChildren().add(Utility.generarHBox(0, "Velocidad de la impresora (letras por segundo)", tfVel,
				new Label(","), tfVelDecimal));
		vBox.getChildren().add(Utility.generarHBox("Duracion del toner de la impresora", tfDuracion));

		vBox.getChildren().add(btnAgregar);
		setCenter(vBox);

		Utility.setAsNumberTextfield(tfVel);
		Utility.setAsNumberTextfield(tfVelDecimal);
		Utility.setAsNumberTextfield(tfDuracion);

		if (!sePuedeEditar) {
			tfMarca.setEditable(false);
			tfVel.setEditable(false);
			tfVelDecimal.setEditable(false);
			tfDuracion.setEditable(false);
			comboEstados.setEditable(false);
			comboEstados.getEditor().setEditable(false);
			btnAgregar.setVisible(false);
		}
	}
}