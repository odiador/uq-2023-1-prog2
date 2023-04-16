package co.edu.uniquindio.centroimpresion.controllers;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class CtrlObtenerArchivo {

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
	private static File obtenerRutaPrincipal() {
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

	/**
	 * Le quita la extension a un archivo haciendo uso de stringbuilders
	 *
	 * @param nombre
	 * @return
	 */
	public static String quitarExtension(String nombre) {
		// crea un nuevo stringbuioder con el nombre del documento
		StringBuilder sb = new StringBuilder(nombre);
		// se voltea la cadena para que encuentre el primer . al reves
		sb.reverse();
		// busca el indice en el que se encuentra el .
		int indice = sb.indexOf(".");
		// si no encuentra el . que se retorne la cadena original
		if (indice < 0)
			return sb.reverse().toString();
		// encuentra el indice del que se parte para eliminar
		int numero = sb.length() - indice - 1;
		// vuelve al estado originial la cadena
		sb.reverse();
		try {
			// elimina parte de la cadena, puede haber un error aqui
			sb.delete(numero, sb.length());
		} catch (StringIndexOutOfBoundsException e) {
			// que siga si pasa este error
		}
		return sb.toString();
	}

	public static FileChooser.ExtensionFilter[] obtenerExtensionFiltersDeFiltroExtension(FiltroExtension... filtros) {
		ExtensionFilter[] arr = new FileChooser.ExtensionFilter[filtros.length];
		for (int i = 0; i < arr.length; i++) {
			FiltroExtension filtroExtension = filtros[i];
			arr[i] = new FileChooser.ExtensionFilter(filtroExtension.getNombre(), filtroExtension.getArchivosAAbrir());
		}
		return arr;
	}
}
