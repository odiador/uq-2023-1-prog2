package co.edu.uniquindio.centroimpresion.view.add;

import co.edu.uniquindio.centroimpresion.controllers.CtrlPanelAddDoc;
import co.edu.uniquindio.centroimpresion.exceptions.ArchivoNoObtenidoException;
import co.edu.uniquindio.centroimpresion.exceptions.CentroImpresionException;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.exceptions.DocumentoEnProcesoException;
import co.edu.uniquindio.centroimpresion.exceptions.NoSePuedeLeerException;
import co.edu.uniquindio.centroimpresion.exceptions.FueraRangoException;
import co.edu.uniquindio.centroimpresion.view.custom.PanelConVolver;
import co.edu.uniquindio.centroimpresion.view.custom.PanelMenuOpcionObjetos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelAddDoc extends PanelConVolver implements EventHandler<Event> {
	private PanelMenuOpcionObjetos panel;
	private HBox hBox;
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
		btnAgregar = new Label("Agregar archivo");
		btnAgregar.setId("");
		tfCode = new TextField();
		tfPrior = new TextField();
		Label labelEscribe = new Label("Escribe el código del documento");

		labelEscribe.setId("label");
		vBox.setId("centered-box");

		hBox = new HBox(20, labelEscribe, tfCode);
		hBox.setId("centered-box");
		vBox.getChildren().add(hBox);

		labelEscribe = new Label("Escribe la prioridad del documento");
		labelEscribe.setId("label");
		hBox = new HBox(20, labelEscribe, tfPrior);
		hBox.setId("centered-box");
		vBox.getChildren().add(hBox);

		hBox = new HBox(20, btnAgregar);
		hBox.setId("centered-box");
		vBox.getChildren().add(hBox);
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
				CtrlPanelAddDoc.agregarDocumento(tfCode.getText(), tfPrior.getText());
				new Alert(AlertType.INFORMATION, "El documento se ha agregado con éxito(" + tfCode.getText() + ")")
						.show();
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

}
