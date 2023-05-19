package co.edu.uniquindio.p2.empresaenergia.controllers;

import java.io.IOException;

import co.edu.uniquindio.p2.empresaenergia.utility.FxUtility;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ControlMenuPrincipal {
	public static final int IR_NATURAL = 0;
	public static final int IR_JURIDICA = 1;
	public static final int IR_FACTURAS = 2;

	@FXML
	private Label lblNombreEmpleado;
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
		imgLogo.fitHeightProperty().bind(menuIzq.widthProperty());
		imgLogo.fitWidthProperty().bind(menuIzq.widthProperty());
	}

	@FXML
	void extenderMenuIzqEvent(MouseEvent event) {
		cambiarTamanoPanel(1);
	}

	@FXML
	void comprimirMenuIzqEvent(MouseEvent event) {
		cambiarTamanoPanel(-1);
	}

	private void cambiarTamanoPanel(double valor) {
		try {
			mainPane.getDividers().forEach(divider -> {
				KeyValue kv1 = new KeyValue(divider.positionProperty(), divider.getPosition() + valor);
				KeyFrame keyFrame1 = new KeyFrame(Duration.millis(1000), kv1);
				Timeline timeline = new Timeline(keyFrame1);
				timeline.play();
			});
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

	@FXML
	void acercaDeEvent(ActionEvent event) {
		acercaDeAction();
	}

	private void acercaDeAction() {
		FxUtility.mostrarMensaje("Acerca De", "Made by: Juan Manuel Amador Roa",
				"Hecha para seguimiento de programación II para el profesor Robinson Arias", AlertType.INFORMATION);
	}

	@FXML
	void gestionarNaturalesEvent(ActionEvent event) {
		gestionarNatrualesAction();
	}

	private void gestionarNatrualesAction() {
		cambiarPanel(IR_NATURAL);
	}

	@FXML
	void gestionarJuridicosEvent(ActionEvent event) {
		gestionarJuridicosAction();
	}

	private void gestionarJuridicosAction() {
		cambiarPanel(IR_JURIDICA);
	}

	@FXML
	void gestionarFacturasEvent(ActionEvent event) {
		gestionarFacturasActon();
	}

	private void gestionarFacturasActon() {
		cambiarPanel(IR_FACTURAS);
	}

	private void cambiarPanel(int option) {
		String ruta = "";
		String msg = "";
		Stage stage = (Stage) mainPane.getScene().getWindow();
		switch (option) {
		case IR_NATURAL:
			msg = "Gestion de Clientes Naturales";
			ruta = "GestionClienteNatural";
			break;
		case IR_JURIDICA:
			msg = "Gestion de Clientes Juridicos";
			ruta = "GestionClienteJuridico";
			break;
		case IR_FACTURAS:
			msg = "Gestion de Facturas";
			ruta = "GestionFacturas";
			break;
		default:
			break;
		}
		try {
			SplitPane load = FXMLLoader.load(getClass().getResource("../view/" + ruta + ".fxml"));
			stage.setTitle(
					msg + " | " + ModelFactoryController.getInstance().obtenerNombreEmpresa() + " | J Amador Roa");
			panelDinamico.setCenter(load);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene el label del nombre del empleado
	 * 
	 * @return
	 */
	public Label getLblNombreEmpleado() {
		return lblNombreEmpleado;
	}
}
