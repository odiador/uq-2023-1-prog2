package co.edu.uniquindio.taller2.tests;

import org.junit.Test;

import co.edu.uniquindio.taller2.model.Banco;
import co.edu.uniquindio.taller2.model.Cuenta;
import co.edu.uniquindio.taller2.model.CuentaException;

public class TestAllBankTrue {
	public Banco bank = new Banco("Bancolombia", "Cra 9 # 23 - 01", "AB-001");

	@Test
	public void testCuentaAhorrosEquals1() throws CuentaException {
		System.err.println("Crea 4 cuentas, 2 de ahorros y 2 corrientes con códigos del 1 al 4 respectivamente");
		bank.agregarCuentaAhorros("1", 0, 0);
		bank.agregarCuentaAhorros("2", 0, 0);
		bank.agregarCuentaCorriente("3", 0, 0);
		bank.agregarCuentaCorriente("4", 0, 0);

		System.err.println("Imprime la lista de cuenta, inicialmente en ceros todo");
		bank.getListaCuentas().forEach(System.out::println);
		System.err.println("==================");
		System.err.println("=    Cuenta 1    =");
		System.err.println("==================");
		System.err.println("");
		System.err.println("==================");
		System.err.println("=  1. Activa     =");
		System.err.println("==================");
		System.err.println("Aumenta el dinero");
		bank.consignarDineroCuenta("1", 1000);
		System.out.println(bank.buscarCuenta("1"));

		System.err.println("Determina si la cuenta está activa");
		System.err.println(bank.estaActivaCuenta("1"));

		System.out.println(bank.buscarCuenta("1"));

		System.err.println("Aumenta el dinero");
		bank.consignarDineroCuenta("1", 100000);
		System.out.println(bank.buscarCuenta("1"));

		System.err.println("Determina si la cuenta está activa");
		System.err.println(bank.estaActivaCuenta("1"));
		System.out.println(bank.buscarCuenta("1"));

		System.err.println("Retira 100000 de la cuenta");
		bank.retirarDineroCuenta("1", 100000f);
		System.out.println(bank.buscarCuenta("1"));

		System.err.println("Determina si la cuenta está activa");
		System.err.println(bank.estaActivaCuenta("1"));
		System.out.println(bank.buscarCuenta("1"));

		System.err.println("==================");
		System.err.println("=    Cuenta 2    =");
		System.err.println("==================");
		System.err.println("");
		System.err.println("=====================");
		System.err.println("=2. Extracto mensual=");
		System.err.println("=====================");
		System.err.println("Aumenta el dinero");
		bank.consignarDineroCuenta("2", 100000);
		System.out.println(bank.buscarCuenta("2"));

		System.err.println("Determina si la cuenta está activa");
		System.err.println(bank.estaActivaCuenta("2"));
		Cuenta cuenta = bank.buscarCuenta("2");
		System.out.println(cuenta);
		System.err.println("Cambia la comision mensual por 1000");
		cuenta.setComisionMensual(1000f);
		System.err.println("Cambia la tasa anual por 1");
		cuenta.setTasaAnual(1f);
		System.err.println("Actualiza la cuenta");
		bank.actualizarCuenta(cuenta);
		System.out.println(bank.buscarCuenta("2"));

		System.err.println(
				"Calcula y extrae la comision que tiene la cuenta, luego le agrega unos intereses a la cuenta");
		bank.extraerMensualCuenta("2");
		System.out.println(bank.buscarCuenta("2"));

		System.err.println("Aumenta el dinero de la cuenta gracias al interés");
		bank.calcularInteresesCuenta("2");
		System.out.println(bank.buscarCuenta("2"));

	}
}
