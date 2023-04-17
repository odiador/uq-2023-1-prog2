package co.edu.uniquindio.centroimpresion.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Documento implements Comparable<Documento>, Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -1693757473754531044L;
	private final String code;
	private String titulo;
	private int prioridad;
	private String contenido;
	private LocalDateTime fechaAgregado;
	private LocalDateTime fechaImpresion = null;

	/**
	 * Es el constructor del documento
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

	/**
	 * Es el constructor del documento sin parametros
	 */
	public Documento() {
		this.code = "";
	}

	/**
	 * Es el constructor del documento solo con el codigo
	 * 
	 * @param code
	 */
	public Documento(String code) {
		this.code = code;
	}

	/**
	 * Determina si el documento fue impreso o no a partir de que la fecha de
	 * impresion sea o no null
	 * 
	 * @return
	 */
	public boolean fueImpreso() {
		return fechaImpresion != null;
	}

	/**
	 * Obtiene el codigo de la impresora
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Obtiene el titulo de la impresora
	 * 
	 * @return
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Cambia el titulo de la impresora
	 * 
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtiene la prioridad de la impresora
	 * 
	 * @return
	 */
	public int getPrioridad() {
		return prioridad;
	}

	/**
	 * 
	 * Cambia la prioridad de la impresora
	 * 
	 * @param prioridad
	 */
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * 
	 * Obtiene el contenido de la impresora
	 * 
	 * @return
	 */
	public String getContenido() {
		return contenido;
	}

	/**
	 * Cambia el contenido de la impresora
	 * 
	 * @param cuerpo
	 */
	public void setContenido(String cuerpo) {
		this.contenido = cuerpo;
	}

	/**
	 * Obtiene la fecha de agregacion de la impresora
	 * 
	 * @return
	 */
	public LocalDateTime getFechaAgregado() {
		return fechaAgregado;
	}

	/**
	 * Cambia la fecha de agregacion de la impresora
	 * 
	 * @param fechaAgregado
	 */
	public void setFechaAgregado(LocalDateTime fechaAgregado) {
		this.fechaAgregado = fechaAgregado;
	}

	/**
	 * Cambia la fecha de agregacion de la impresora
	 * 
	 * @return
	 */
	public LocalDateTime getFechaImpresion() {
		return fechaImpresion;
	}

	/**
	 * Cambia la fecha de impresion de la impresora
	 * 
	 * @param fechaImpresion
	 */
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
		return String.format(
				"Documento [code=%s, titulo=%s, prioridad=%s, contenido=%s, fechaAgregado=%s, fechaImpresion=%s]", code,
				titulo, prioridad, contenido, fechaAgregado, fechaImpresion);
	}
}
