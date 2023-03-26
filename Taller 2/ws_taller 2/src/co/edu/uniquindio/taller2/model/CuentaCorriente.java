package co.edu.uniquindio.taller2.model;

public class CuentaCorriente extends Cuenta {

	protected float sobregiro = 0f;

	/**
	 * Es el constructor de la cuenta corriente
	 *
	 * @param codigo
	 * @param saldo
	 * @param tasaAnual
	 */
	public CuentaCorriente(String codigo, float saldo, float tasaAnual) {
		super(codigo, saldo, tasaAnual);
	}

	/**
	 * Obtiene el sobregiro de la cuenta corriente
	 *
	 * @return
	 */
	public float getSobregiro() {
		return sobregiro;
	}

	/**
	 * Cambia el sobregiro de la cuenta corriente
	 *
	 * @param sobregiro
	 */
	public void setSobregiro(float sobregiro) {
		this.sobregiro = sobregiro;
	}

	@Override
	public void consignarDinero(float saldo) throws CuentaException {
		if (haySobregiro()) {
			if (getSobregiro() >= saldo) {
				setSobregiro(getSobregiro() - saldo);
			} else {
				setSaldo(saldo - getSobregiro());
				setSobregiro(0f);
			}
		} else {
			super.consignarDinero(saldo);
		}
	}

	@Override
	public void retirarDinero(float saldo) throws CuentaException {
		if (haySobregiro()) {
			setSobregiro(getSobregiro() + saldo);
		} else {
			if (saldo > getSaldo()) {
				setSobregiro(saldo - getSaldo());
				setSaldo(0f);
			}
		}
	}

	/**
	 * Determina si hay sobregiro o no
	 *
	 * @return
	 */
	private boolean haySobregiro() {
		return getSobregiro() > 0;
	}

	@Override
	public String imprimir() {
		return String.format("CuentaCorriente [saldo=%s, comisionMensual=%s, numTransacciones=%s, sobregiro=%s]", saldo,
				comisionMensual, getNumTransacciones(), sobregiro);
	}

	@Override
	public String toString() {
		return String.format(
				"CuentaCorriente [sobregiro=%s, codigo=%s, saldo=%s, numConsignaciones=%s, numRetiros=%s, tasaAnual=%s, comisionMensual=%s]",
				sobregiro, codigo, saldo, numConsignaciones, numRetiros, tasaAnual, comisionMensual);
	}

}
