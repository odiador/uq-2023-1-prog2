package co.edu.uniquindio.centroimpresion.view.add;

import java.util.Optional;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPanelAddDoc;
import co.edu.uniquindio.centroimpresion.exceptions.ArchivoNoObtenidoException;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.DocumentoEnProcesoException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.exceptions.NoSePuedeLeerException;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.centro.Documento;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import co.edu.uniquindio.centroimpresion.view.see.PanelDoc;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PanelAddDoc extends PanelConVolver implements EventHandler<Event> {
	private PanelMenuOpcionObjetos panel;
	private VBox vBox;
	private Label btnAgregar;
	private TextField tfCode, tfPrior;

	public PanelAddDoc(PanelMenuOpcionObjetos panel) {
		this.panel = panel;
		initComp();
	}

	public void initComp() {
		super.initComp();
		vBox = new VBox(20);
		vBox.setId("centered-box");
		btnAgregar = new Label("Agregar archivo");
		tfCode = new TextField();
		tfPrior = new TextField();

		tfCode.setPromptText("Escribe un código");
		tfPrior.setPromptText("5");

		btnAgregar.setId("btn");
		tfCode.setId("textfield");
		tfPrior.setId("textfield");

		vBox.getChildren().add(generarHBox("Escribe el código del documento", tfCode));
		vBox.getChildren().add(generarHBox("Escribe la prioridad del documento", tfPrior));

		BorderPane agregarCase = new BorderPane(btnAgregar);

		agregarCase.setId("btn-case");
		vBox.getChildren().add(agregarCase);
		setCenter(vBox);
		addlisteners();
	}

	public void addlisteners() {
		btnAgregar.setOnMouseReleased(this);
		tfPrior.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					tfPrior.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

	@Override
	public void volverPresionado() {
		panel.initComp();
	}

	@Override
	public void handle(Event event) {
		super.handle(event);
		if (event.getSource() == btnAgregar) {
			try {

				Documento documentoAgregado = CtrlPanelAddDoc.agregarDocumento(tfCode.getText(), tfPrior.getText());
				ButtonType button = new Alert(AlertType.INFORMATION,
						"El documento se ha agregado con éxito (" + tfCode.getText() + ")" + "\n"
								+ "¿Deseas ver el documento?",
						ButtonType.CANCEL, ButtonType.OK).showAndWait().orElse(null);
				if (button == ButtonType.OK)
					abrirDocumento(documentoAgregado, 1100, 800);

			} catch (ArchivoNoObtenidoException e) {
				new Alert(AlertType.ERROR, "El archivo no pudo ser obtenido").show();
			} catch (DocumentoEnProcesoException e) {
				new Alert(AlertType.WARNING, "Espera a que el documento sea obtenido").show();
			} catch (CentroImpresionException e) {
				new Alert(AlertType.ERROR, "Ya se encuentra un documento con tal código").show();
			} catch (NoSePuedeLeerException e) {
				new Alert(AlertType.ERROR, "El archivo no se puede leer").show();
			} catch (FueraRangoException e) {
				new Alert(AlertType.WARNING, "La prioridad debe de estar entre 0 y 10").show();
			} catch (TextIsEmptyException e) {
				new Alert(AlertType.WARNING, "El código está vacío").show();
			}
		}

	}

	public static void abrirDocumento(Documento documento, double width, double height) {
		Scene scene = new Scene(new PanelDoc(documento), width, height);
		Stage s = new Stage();
		s.setScene(scene);
		s.show();
	}

}
