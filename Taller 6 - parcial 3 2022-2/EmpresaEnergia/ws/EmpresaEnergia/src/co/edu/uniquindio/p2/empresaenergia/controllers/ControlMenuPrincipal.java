package co.edu.uniquindio.p2.empresaenergia.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class ControlMenuPrincipal {
	public static final int IR_NATURAL = 0;
	public static final int IR_JURIDICA = 1;
	public static final int IR_FACTURAS = 2;

	@FXML
	private BorderPane panelDinamico;

	@FXML
	void gestionarNaturalesEvent(ActionEvent event) {
		cambiarPanel(IR_NATURAL);
	}

	@FXML
	void gestionarJuridicosEvent(ActionEvent event) {
		cambiarPanel(IR_JURIDICA);
	}

	@FXML
	void gestionarFacturasEvent(ActionEvent event) {
		cambiarPanel(IR_FACTURAS);
	}

	private void cambiarPanel(int option) {
		String msg = "";
		switch (option) {
		case IR_NATURAL:
			msg = "GestionClienteNatural";
			break;
		case IR_JURIDICA:
			msg = "GestionClienteJuridico";
			break;
		case IR_FACTURAS:
			msg = "GestionFacturas";
			break;
		default:
			break;
		}
		try {
			SplitPane load = FXMLLoader.load(getClass().getResource("../view/" + msg + ".fxml"));
			panelDinamico.setCenter(load);
		} catch (IOException e) {
		}
	}
}
