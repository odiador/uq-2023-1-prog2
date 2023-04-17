package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.application.Main;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.centro.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.view.scenes.EscenaMenu;
import co.edu.uniquindio.centroimpresion.view.util.Utility;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ControlLogin {

	public static void hacerLogin(Stage stage, String name, String value) {
		try {
			hacerLoginThrows(stage, name, value);
		} catch (TextIsEmptyException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	public static void hacerLoginThrows(Stage stage, String name, String value) throws TextIsEmptyException {
		Utility.throwIfEmpty(name, "nombre");
		Utility.throwIfNull(value, "tipo de empleado");
		Utility.throwIfEmpty(value, "tipo de empleado");
		TipoEmpleado tipoEmpleado = TipoEmpleado.obtenerTipoTexto(value);
		Utility.throwIfNull(tipoEmpleado, "tipo de empleado");
		Scene escena = new EscenaMenu(stage, name, tipoEmpleado);
		escena.getStylesheets().add(Main.css.toExternalForm());
		stage.setScene(escena);
	}

}
