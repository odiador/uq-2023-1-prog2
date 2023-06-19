package co.edu.uniquindio.p2.diplomado.controllers;

import java.io.IOException;

import co.edu.uniquindio.p2.diplomado.utility.FxUtility;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MenuPrincipalController {
	@FXML
	private ImageView imgLogo;

	@FXML
	private SplitPane mainPane;
	@FXML
	private BorderPane menuIzq;

	@FXML
	private BorderPane panelDinamico;

	@FXML
	void initialize() {
		imgLogo.fitHeightProperty().bind(menuIzq.widthProperty().multiply(0.95));
		imgLogo.fitWidthProperty().bind(menuIzq.widthProperty().multiply(0.95));
		Platform.runLater(() -> ((Stage) mainPane.getScene().getWindow()).setTitle("Menu Principal | Diplomado"));
	}

	@FXML
	void extenderMenuIzqEvent(MouseEvent event) {
		cambiarTamanoPanel(1);
		imgLogo.setImage(new Image("/resources/images/Diplomado logo.png"));
	}

	@FXML
	void comprimirMenuIzqEvent(MouseEvent event) {
		cambiarTamanoPanel(-1);
	}

	@FXML
	void acercaDeEvent(ActionEvent event) {
		acercaDeAction();
	}

	@FXML
	void gestionarEstudiantesEvent(ActionEvent event) {
		cambiarPanel("Gestion de Estudiantes", "../view/GestionDiplomado.fxml", new GestionEstudiantesController());
	}

	@FXML
	void verEstadisticasEvent(ActionEvent event) {
		cambiarPanel("Estadisticas", "../view/Estadisticas.fxml", new EstadisticasController());
	}

	private void cambiarTamanoPanel(double valor) {
		try {
			mainPane.getDividers().forEach(divider -> {

				EventHandler<ActionEvent> evento = event -> imgLogo.setImage(new Image("/resources/images/"
						+ (valor != 1 ? "Logo No Text.png" : "Diplomado logo.png")));
				KeyValue kv1 = new KeyValue(divider.positionProperty(), divider.getPosition() + valor);
				KeyFrame keyFrame1 = new KeyFrame(Duration.millis(100), evento);
				KeyFrame keyFrame2 = new KeyFrame(Duration.millis(1000), kv1);
				Timeline timeline = new Timeline(keyFrame1, keyFrame2);
				timeline.play();
			});
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	private void acercaDeAction() {
		FxUtility.mostrarMensaje("Acerca De",
				"Nombre de la Empresa: " + ModelFactoryController.getInstance().obtenerNombreDiplomado(),
				"Hecha para seguimiento de programación II para el profesor Robinson Arias", AlertType.INFORMATION);
	}

	private void cambiarPanel(String titulo, String nombreArchivo, Object controller) {
		Stage stage = (Stage) mainPane.getScene().getWindow();
		try {
			stage.setTitle(
					titulo + " | " + ModelFactoryController.getInstance().obtenerNombreDiplomado() + " | J Amador Roa");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(nombreArchivo));
			loader.setController(controller);
			panelDinamico.setCenter(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
