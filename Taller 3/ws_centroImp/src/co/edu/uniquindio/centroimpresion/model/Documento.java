package co.edu.uniquindio.centroimpresion.model;

import java.time.LocalDateTime;

public class Documento implements Comparable<Documento> {
	private final String code;
	private String titulo;
	private int prioridad;
	private String contenido;
	private LocalDateTime fechaAgregado;
	private LocalDateTime fechaImpresion = null;

	/**
	 *
	 * @param code
	 * @param titulo
	 * @param prioridad
	 * @param contenido
	 */
	public Documento(String code, String titulo, int prioridad, String contenido, LocalDateTime fechaAgregado) {
		this.code = code;
		this.titulo = titulo;
		this.prioridad = prioridad;
		this.contenido = contenido;
		this.fechaAgregado = fechaAgregado;
	}

	public Documento() {
		this.code = "";
	}

	public int obtenerCantidadPaginas() {
		return 1;
	}

	public boolean estadoImpresion() {
		return fechaImpresion != null;
	}

	public String getCode() {
		return code;
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

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String cuerpo) {
		this.contenido = cuerpo;
	}

	public LocalDateTime getFechaAgregado() {
		return fechaAgregado;
	}

	public void setFechaAgregado(LocalDateTime fechaAgregado) {
		this.fechaAgregado = fechaAgregado;
	}

	public LocalDateTime getFechaImpresion() {
		return fechaImpresion;
	}

	public void setFechaImpresion(LocalDateTime fechaImpresion) {
		this.fechaImpresion = fechaImpresion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public int compareTo(Documento o) {
		return o.getPrioridad() - getPrioridad();
	}

	@Override
	public String toString() {
		return "Documento [code=" + code + ", titulo=" + titulo + ", prioridad=" + prioridad + ", contenido="
				+ contenido + ", fechaAgregado=" + fechaAgregado + ", fechaImpresion=" + fechaImpresion + "]";
	}

}
