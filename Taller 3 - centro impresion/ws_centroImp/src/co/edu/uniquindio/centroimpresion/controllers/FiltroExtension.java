package co.edu.uniquindio.centroimpresion.controllers;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FiltroExtension {
	private String nombre;
	private String[] archivosAAbrir;

	/**
	 * 
	 * @param nombre
	 * @param archivosAAbrir
	 */
	public FiltroExtension(String nombre, String... archivosAAbrir) {
		this.nombre = nombre;
		this.archivosAAbrir = archivosAAbrir;
	}

	public String getNombre() {
		return nombre;
	}

	public String[] getArchivosAAbrir() {
		return archivosAAbrir;
	}

	/**
	 * Crea un arreglo de ExtensionFilter para obtener los filtros de extension de
	 * un archivo, por meido de un arreglo de filtros {@code filtros}
	 * 
	 * @param filtros
	 * @return el arreglo
	 */
	public static FileChooser.ExtensionFilter[] obtenerExtensionFiltersDeFiltroExtension(FiltroExtension... filtros) {
		ExtensionFilter[] arr = new FileChooser.ExtensionFilter[filtros.length];
		for (int i = 0; i < arr.length; i++) {
			FiltroExtension filtroExtension = filtros[i];
			arr[i] = new FileChooser.ExtensionFilter(filtroExtension.getNombre(), filtroExtension.getArchivosAAbrir());
		}
		return arr;
	}
}
