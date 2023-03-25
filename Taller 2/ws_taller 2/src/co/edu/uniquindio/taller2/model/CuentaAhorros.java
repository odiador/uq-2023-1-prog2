package co.edu.uniquindio.taller2.model;

public class CuentaAhorros extends Cuenta {

	/**
	 * Es el constructor de la cuenta de ahorros
	 *
	 * @param saldo
	 * @param tasaAnual
	 */
	public CuentaAhorros(float saldo, float tasaAnual) {
		super(saldo, tasaAnual);
	}

	/**
	 * Determina si la cuenta de ahorros está activa o no
	 *
	 * @return true si el saldo es mayor o igual a 10000
	 */
	public boolean estaActiva() {
		return getSaldo() >= 10000f;
	}

	@Override
	public void consignarDinero(float saldo) throws CuentaException {
		throwIfNotActive("No se pudo consignar el dinero (cuenta no activa)");
		super.consignarDinero(saldo);
	}

	public void throwIfNotActive(String msg) throws CuentaException {
		if (!estaActiva())
			throw new CuentaException(msg);
	}

	@Override
	public void retirarDinero(float saldo) throws CuentaException {
		throwIfNotActive("No se pudo retirar el dinero (cuenta no activa)");
		super.retirarDinero(saldo);
	}

	@Override
	public void extractoMensual() throws CuentaException {
		if (getNumRetiros() > 4)
			setComisionMensual(getComisionMensual() + (getNumRetiros() - 4) * 1000f);
		super.extractoMensual();
	}

	public String imprimir() {
		return toString();
	}

	@Override
	public String toString() {
		return "CuentaAhorros[saldo:" + darFormatoDinero(saldo) + ", comision: " + darFormatoDinero(comisionMensual)
				+ ", numero de transacciones: " + getNumTransacciones() + "]";
	}

}