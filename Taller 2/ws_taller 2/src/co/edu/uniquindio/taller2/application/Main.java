package co.edu.uniquindio.taller2.application;

import co.edu.uniquindio.taller2.exceptions.CuentaException;
import co.edu.uniquindio.taller2.model.CuentaAhorros;

public class Main {
	public static void main(String[] args) {
		System.out.println("Crea una cuenta con código AB001, saldo $1000 y tasa anual de $10000");
		CuentaAhorros cuentaAhorros = new CuentaAhorros("AB001", 1000f, 10000f);
		System.out.println("La cuenta no está activa (su saldo es menor a 10000, "
				+ "entonces no se va a poder ni agregar ni retirar saldo");
		System.out.println("Intenta consignar 10000:");
		try {
			cuentaAhorros.consignarDinero(10000f);
		} catch (CuentaException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Intenta retirar 10000:");
		try {
			cuentaAhorros.retirarDinero(10000f);
		} catch (CuentaException e) {
			System.err.println(e.getMessage());
		}
	}
}
