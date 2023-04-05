package co.edu.uniquindio.centroimpresion.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

import co.edu.uniquindio.centroimpresion.model.Documento;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CtrlAgregarDocumento {

	/**
	 * Obtiene un archivo a traves de un file chooser (se abre una ventana de
	 * abrir archivo)
	 *
	 * @param tituloVentana
	 *            es el titulo de la ventana
	 * @param extensionFilters
	 *            son los filtros de extension, va primero el nombre y luego el
	 *            tipo de archivo "*.*" para todos los archivos
	 * @return null si se cancela
	 */
	public static File obtenerArchivo(String tituloVentana, FileChooser.ExtensionFilter... extensionFilters) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(tituloVentana);
		fileChooser.setInitialDirectory(obtenerRutaPrincipal());
		fileChooser.getExtensionFilters().addAll(extensionFilters);
		return fileChooser.showOpenDialog(new Stage());
	}

	/**
	 * Obtiene una ruza general para abrir el archivo, si no se encuentra busca
	 * otras mas y si no, retorna la ruta del usuario
	 *
	 * @return
	 */
	public static File obtenerRutaPrincipal() {
		String property = System.getProperty("user.home");
		File recordsDir = new File(property, "Desktop");
		if (recordsDir.exists())
			return recordsDir;
		recordsDir = new File(property, "OneDrive/Escritorio");
		if (recordsDir.exists())
			return recordsDir;
		recordsDir = new File(property, "Downloads");
		if (recordsDir.exists())
			return recordsDir;
		return new File(System.getProperty("user.home"));
	}
}
