package co.edu.uniquindio.p2.empresaenergia.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Factura implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private LocalDate fechaFacturacion;
	private Double total;
	private Cliente cliente;

	/**
	 * Es el constructor de la clase Factura
	 * 
	 * @param codigo
	 * @param fechaFacturacion
	 * @param total
	 */
	public Factura(String codigo, LocalDate fechaFacturacion, Double total, Cliente cliente) {
		this.codigo = codigo;
		this.fechaFacturacion = fechaFacturacion;
		this.total = total;
		this.cliente = cliente;
	}

	/**
	 * Determina si la factura tiene todos los atributos llenos
	 * 
	 * @return true si no le falta algo
	 */
	public boolean tieneTodoLleno() {
		return codigo != null && fechaFacturacion != null && total != null && cliente != null
				&& cliente.tieneTodoLleno();
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
	 * Obtiene el total de la factura con un formato de dinero con 2 decimales:<br>
	 * <li>Ejemplos:
	 * <ul>
	 * <b>15000</b> -> $15000.00<br>
	 * <b>3000</b> -> $3000.00<br>
	 * <b>1555,3</b> -> $1555.30<br>
	 * <b>1234,56</b> -> $1234.56<br>
	 * <b>1234,561</b> -> $1234.56<br>
	 * 
	 * @return
	 */
	public String getTotalConFormato() {
		return "$" + String.format("%.2f", getTotal()).replace(',', '.');
	}

	/**
	 * @param total el total de la factura a cambiar
	 */
	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * Determina si la factura tiene un codigo especifico
	 * 
	 * @param codigo
	 * @return
	 */
	public boolean tieneCodigo(String codigo) {
		return this.codigo.equals(codigo);
	}

	/**
	 * Obtiene el cliente de la factura
	 * 
	 * @return
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Cambia el cliente de la factura
	 * 
	 * @param cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Determina si el codigo de la factura existe o no
	 * 
	 * @return
	 */
	public boolean existeCodigo() {
		return getCodigo() != null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Factura other = (Factura) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Factura [codigo=%s, fechaFacturacion=%s, total=%s, cliente=%s]", codigo, fechaFacturacion,
				total, cliente);
	}

}
