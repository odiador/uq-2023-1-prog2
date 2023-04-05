package co.edu.uniquindio.centroimpresion.model.archivos;

public class FiltroExtension {
	private String nombre;
	private String[] archivosAAbrir;

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
}
