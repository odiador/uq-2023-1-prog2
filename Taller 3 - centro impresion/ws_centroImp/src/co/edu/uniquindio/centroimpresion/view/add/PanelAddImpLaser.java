package co.edu.uniquindio.centroimpresion.view.add;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPanelAddImpLaser;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.ObjectNotExists;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.centro.EstadoImpresora;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelAddImpLaser extends PanelConVolver {
	private PanelMenuOpcionObjetos panel;
	private VBox vBox;
	private Label btnAgregar;
	private TextField tfCode, tfMarca, tfVel, tfVelDecimal, tfDuracion;
	private CheckBox checkColor;
	private ComboBox<String> comboEstados;

	public PanelAddImpLaser(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
	}

	@Override
	public void initComp() {
		super.initComp();
		vBox = new VBox(20);
		tfCode = new TextField();
		tfMarca = new TextField();
		tfVel = new TextField();
		tfVelDecimal = new TextField();
		tfDuracion = new TextField();
		btnAgregar = new Label("Agregar Impresora");

		tfCode.setPromptText("Escribe un código");
		tfMarca.setPromptText("Escribe una marca");
		tfVel.setPromptText("Escribe un numero");
		tfVelDecimal.setPromptText("Escribe un decimal (PPM)");
		tfDuracion.setPromptText("Escribe la duración del tóner");

		vBox.setId("centered-box");
		tfCode.setId("textfield");
		tfMarca.setId("textfield");
		tfVel.setId("textfield");
		tfDuracion.setId("textfield");
		btnAgregar.setId("btn");

		HBox.setMargin(tfVel, new Insets(0, 10, 0, 10));
		HBox.setMargin(tfVelDecimal, new Insets(0, 5, 0, 10));
		comboEstados = new ComboBox<String>();
		checkColor = new CheckBox();
		checkColor.setSelected(true);
		comboEstados.setItems(FXCollections.observableArrayList(EstadoImpresora.stringValues()));

		comboEstados.setId("combobox");
		checkColor.setId("checkbox");

		vBox.getChildren().add(generarHBox("Escribe el código de la impresora", tfCode));
		vBox.getChildren().add(generarHBox("Escribe la marca de la impresora", tfMarca));
		vBox.getChildren().add(generarHBox("Elige el estado de la impresora", comboEstados));
		vBox.getChildren().add(generarHBox("¿La impresora es a color?", checkColor));
		vBox.getChildren().add(generarHBox(0, "Escribe la vel de la impresora (paginas por minuto)", tfVel,
				new Label(","), tfVelDecimal));
		vBox.getChildren().add(generarHBox("Escribe la duración del tóner de la impresora", tfDuracion));

		BorderPane agregarCase = new BorderPane(btnAgregar);

		agregarCase.setId("btn-case");
		vBox.getChildren().add(agregarCase);
		setCenter(vBox);
		addListeners();
	}

	private void addListeners() {
		convertirATextfieldNumerico(tfVel);
		convertirATextfieldNumerico(tfVelDecimal);
		convertirATextfieldNumerico(tfDuracion);
		btnAgregar.setOnMouseReleased(event -> {
			try {
				CtrlPanelAddImpLaser.agregarImpresoraLaser(tfCode.getText(), tfMarca.getText(), comboEstados.getValue(),
						checkColor.isSelected(), juntarCadenasParaDoble(tfVel.getText(), tfVelDecimal.getText()),
						tfDuracion.getText());
			} catch (NumberFormatException e) {
				new Alert(AlertType.WARNING, "Rellena todos los campos").show();
			} catch (CentroImpresionException e) {
				new Alert(AlertType.WARNING, "Ya existe una impresora con ese código").show();
			} catch (TextIsEmptyException e) {
				new Alert(AlertType.WARNING, "Rellena todos los campos (" + e.getTipoTexto() + ")").show();
			} catch (ObjectNotExists e) {
				new Alert(AlertType.WARNING, "Rellena todos los campos (" + e.getClase().getSimpleName() + ")").show();
			} catch (FueraRangoException e) {
				new Alert(AlertType.WARNING, e.getMessage()).show();
			}
		});
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}
}
