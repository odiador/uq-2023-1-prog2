package co.edu.uniquindio.taller2.model;

import co.edu.uniquindio.taller2.exceptions.CuentaException;

public abstract class Cuenta {
	protected String codigo;
	protected float saldo = 0f;
	protected int numConsignaciones = 0;
	protected int numRetiros = 0;
	protected float tasaAnual = 0f;
	protected float comisionMensual = 0f;

	/**
	 * Es el constructor de la cuenta
	 *
	 * @param codigo
	 * @param saldo
	 * @param tasaAnual
	 */
	public Cuenta(String codigo, float saldo, float tasaAnual) {
		this.codigo = codigo;
		this.saldo = saldo;
		this.tasaAnual = tasaAnual;
	}

	/**
	 * Consigna una cantidad de dinero al saldo actual, puede mostrar un error
	 * dependiendo del tipo de cuenta
	 *
	 * @param saldo
	 * @throws CuentaException
	 */
	public void consignarDinero(float saldo) throws CuentaException {
		setSaldo(getSaldo() + saldo);
		setNumConsignaciones(getNumConsignaciones() + 1);
	}

	/**
	 * Retira el dinero de una cuenta, muestra un error si el saldo a retirar
	 * sobrepasa el saldo actual
	 *
	 * @param saldo
	 * @throws CuentaException
	 */
	public void retirarDinero(float saldo) throws CuentaException {
		if (getSaldo() < saldo) {
			throw new CuentaException("El saldo a retirar no puede sobrepasar el saldo actual");
		}
		setSaldo(getSaldo() - saldo);
		setNumRetiros(getNumRetiros() + 1);
	}

	/**
	 * Calcula los intereses de la cuenta y los agrega al saldo actual
	 */
	public void calcularIntereses() {
		float tasaMensual = getTasaAnual() / 12;
		float interesMensual = saldo * tasaMensual;
		setSaldo(getSaldo() + interesMensual);
	}

	/**
	 * Extrae mensualmente una comisión y luego agrega los intereses a la cuenta
	 *
	 * @throws CuentaException en caso de que no se pueda extraer la comisión
	 */
	public void extractoMensual() throws CuentaException {
		retirarDinero(getComisionMensual());
		setNumRetiros(getNumRetiros() - 1);
		calcularIntereses();
	}

	public abstract String imprimir();

	/**
	 * Obtiene el saldo de la cuenta
	 *
	 * @return
	 */
	public float getSaldo() {
		return saldo;
	}

	/**
	 * Cambia el saldo de la cuenta
	 *
	 * @param saldo
	 */
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	/**
	 * Obtiene el número de consignaciones de la cuenta
	 *
	 * @return
	 */
	public int getNumConsignaciones() {
		return numConsignaciones;
	}

	/**
	 * Cambia el número de consignaciones de la cuenta
	 *
	 * @param numConsignaciones
	 */
	public void setNumConsignaciones(int numConsignaciones) {
		this.numConsignaciones = numConsignaciones;
	}

	/**
	 * Obtiene el número de retiros de la cuenta
	 *
	 * @return
	 */
	public int getNumRetiros() {
		return numRetiros;
	}

	/**
	 * Obtiene el número de retiros de la cuenta
	 *
	 * @param numRetiros
	 */
	public void setNumRetiros(int numRetiros) {
		this.numRetiros = numRetiros;
	}

	/**
	 * Obtiene la tasa anual de la cuenta
	 *
	 * @return
	 */
	public float getTasaAnual() {
		return tasaAnual;
	}

	/**
	 * Cambia la tasa anual de la cuenta
	 *
	 * @param tasaAnual
	 */
	public void setTasaAnual(float tasaAnual) {
		this.tasaAnual = tasaAnual;
	}

	/**
	 * Obtiene la comisión mensual de la cuenta
	 *
	 * @return
	 */
	public float getComisionMensual() {
		return comisionMensual;
	}

	/**
	 * Cambia la comisión mensual de la cuenta
	 *
	 * @param comisionMensual
	 */
	public void setComisionMensual(float comisionMensual) {
		this.comisionMensual = comisionMensual;
	}

	/**
	 * Obtiene el codigo de la cuenta
	 *
	 * @return
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Cambia el codigo de la cuenta
	 *
	 * @param codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getNumTransacciones() {
		return getNumConsignaciones() + getNumRetiros();
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
		Cuenta other = (Cuenta) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cuenta [saldo=" + saldo + ", numConsignaciones=" + numConsignaciones + ", numRetiros=" + numRetiros
				+ ", tasaAnual=" + tasaAnual + ", comisionMensual=" + comisionMensual + "]";
	}
}
