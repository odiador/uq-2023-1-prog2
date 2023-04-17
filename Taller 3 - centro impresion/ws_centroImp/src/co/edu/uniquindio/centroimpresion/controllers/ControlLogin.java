package co.edu.uniquindio.centroimpresion.controllers;

import co.edu.uniquindio.centroimpresion.application.Main;
import co.edu.uniquindio.centroimpresion.exceptions.TextIsEmptyException;
import co.edu.uniquindio.centroimpresion.model.TipoEmpleado;
import co.edu.uniquindio.centroimpresion.util.Utility;
import co.edu.uniquindio.centroimpresion.view.scenes.EscenaMenu;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ControlLogin {

	/**
	 * Hace el login a la aplicacion, muestra alertas en caso de que algun dato este
	 * vacío o si es null
	 * 
	 * @param stage
	 * @param name
	 * @param value
	 */
	public static void hacerLogin(Stage stage, String name, String value) {
		try {
			hacerLoginThrows(stage, name, value);
		} catch (TextIsEmptyException e) {
			new Alert(AlertType.WARNING, e.getMessage()).show();
		}
	}

	/**
	 * 
	 * Hace el login a la aplicacion, muestra un error en caso de que algun dato
	 * este vacío o si es null
	 * 
	 * @param stage
	 * @param name
	 * @param value
	 * @throws TextIsEmptyException
	 */
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
