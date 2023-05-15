package co.edu.uniquindio.p2.empresaenergia.model;

import java.time.LocalDate;

public class Factura {
	private String codigo;
	private LocalDate fechaFacturacion;
	private Double total;

	/**
	 * Es el constructor de la clase Factura
	 * 
	 * @param codigo
	 * @param fechaFacturacion
	 * @param total
	 */
	public Factura(String codigo, LocalDate fechaFacturacion, Double total) {
		this.codigo = codigo;
		this.fechaFacturacion = fechaFacturacion;
		this.total = total;
	}

	/**
	 * Determina si la factura tiene todos los atributos llenos
	 * 
	 * @return true si no le falta algo
	 */
	public boolean tieneTodoLleno() {
		return codigo != null && fechaFacturacion != null && total != null;
	}

	/**
	 * @return el codigo de la factura
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo el codigo de la factura a cambiar
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return la fecha de acturacion de la factura
	 */
	public LocalDate getFechaFacturacion() {
		return fechaFacturacion;
	}

	/**
	 * @param fechaFacturacion la fecha de facturacion de la factura a cambiar
	 */
	public void setFechaFacturacion(LocalDate fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	/**
	 * @return el total de la factura
	 */
	public Double getTotal() {
		return total;
	}

	/**
	 * @param total el total de la factura a cambiar
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	public boolean tieneCodigo(String codigo) {
		return this.codigo.equals(codigo);
	}

}
