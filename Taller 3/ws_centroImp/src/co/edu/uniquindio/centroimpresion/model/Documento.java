package co.edu.uniquindio.centroimpresion.model;

public class Documento {
	private String code;
	private String titulo;
	private int prioridad;

	/**
	 *
	 * @param code
	 * @param titulo
	 * @param prioridad
	 */
	public Documento(String code, String titulo, int prioridad) {
		this.code = code;
		this.titulo = titulo;
		this.prioridad = prioridad;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

}
