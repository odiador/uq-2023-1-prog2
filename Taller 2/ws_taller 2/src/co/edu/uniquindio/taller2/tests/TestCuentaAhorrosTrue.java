package co.edu.uniquindio.taller2.tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import co.edu.uniquindio.taller2.model.Banco;
import co.edu.uniquindio.taller2.model.Cuenta;
import co.edu.uniquindio.taller2.model.CuentaException;

public class TestCuentaAhorrosTrue {
	public Banco bank = new Banco("Bancolombia", "Cra 9 # 23 - 01", "AB-001");

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void antes() throws CuentaException {
		System.out.println("Crea 4 cuentas, 2 de ahorros y 2 corrientes con c�digos del 1 al 4 respectivamente");
		bank.agregarCuentaAhorros("1", 0, 0);
		bank.agregarCuentaAhorros("2", 100000, 0);
		bank.agregarCuentaCorriente("3", 0, 0);
		bank.agregarCuentaCorriente("4", 0, 0);
	}

	@Test
	public void testCuentaAhorrosConsignacionyRetiro() throws CuentaException {
		System.out.println("******************");
		System.out.println("*    Cuenta 1    *");
		System.out.println("******************");
		System.out.println("");
		System.out.println("****************");
		System.out.println("* Consignación *");
		System.out.println("****************");
		System.out.println("");
		System.out.println("Se consigna un dinero a la cuenta 1");
		bank.consignarDineroCuenta("1", 1000);
		System.out.println(bank.buscarCuenta("1"));

		System.out.println("Determina si la cuenta est� activa: " + bank.estaActivaCuenta("1"));

		System.out.println(bank.buscarCuenta("1"));

		System.out.println("Aumenta el dinero");
		bank.consignarDineroCuenta("1", 100000);
		System.out.println(bank.buscarCuenta("1"));

		System.out.println("Determina si la cuenta est� activa: " + bank.estaActivaCuenta("1"));
		System.out.println(bank.buscarCuenta("1"));

		System.out.println("**********");
		System.out.println("* Retiro *");
		System.out.println("**********");
		System.out.println("");
		System.out.println("Retira 100000 de la cuenta");
		bank.retirarDineroCuenta("1", 100000f);
		System.out.println(bank.buscarCuenta("1"));
		System.out.println("Determina si la cuenta est� activa: " + bank.estaActivaCuenta("1"));
		System.out.println(bank.buscarCuenta("1"));
	}

	@Test
	public void testCuentaAhorrosInteres() throws CuentaException {
		System.out.println("******************");
		System.out.println("*    Cuenta 2    *");
		System.out.println("******************");
		System.out.println("");
		System.out.println("********************");
		System.out.println("* Calcular interes *");
		System.out.println("********************");
		System.out.println(bank.buscarCuenta("2"));

		actualizarComisionTasaCuentaAhorros();

		System.out.println("Aumenta el dinero de la cuenta gracias al inter�s");
		bank.calcularInteresesCuenta("2");
		System.out.println(bank.buscarCuenta("2"));
	}

	@Test
	public void testCuentaAhorrosInteresExtracto() throws CuentaException {
		System.out.println("******************");
		System.out.println("*    Cuenta 2    *");
		System.out.println("******************");
		System.out.println("");
		System.out.println("********************");
		System.out.println("* Extracto mensual *");
		System.out.println("********************");

		actualizarComisionTasaCuentaAhorros();

		System.out.println(
				"Calcula y extrae la comision que tiene la cuenta, luego le agrega unos intereses a la cuenta");
		bank.extraerMensualCuenta("2");
		System.out.println(bank.buscarCuenta("2"));
	}

	@Test
	public void testCuentaAhorrosConsultarSobregiroError() throws CuentaException {
		System.out.println("******************");
		System.out.println("*    Cuenta 1    *");
		System.out.println("******************");
		System.out.println("");
		System.out.println("*********************");
		System.out.println("* Sobregiro ahorros *");
		System.out.println("*********************");
		System.out.println("");
		System.out.println("Espera un error de tipo cuenta");
		expectedException.expect(CuentaException.class);
		System.out.println("");
		System.out.println("Obtiene el sobregiro de una cuenta de ahorros (error)");
		bank.obtenerSobregiroCuenta("2");
	}

	public void actualizarComisionTasaCuentaAhorros() throws CuentaException {
		Cuenta cuenta = bank.buscarCuenta("2");
		System.out.println("Cambia la comision mensual por 1000");
		cuenta.setComisionMensual(1000f);
		System.out.println("Cambia la tasa anual por 1");
		cuenta.setTasaAnual(1f);
		System.out.println("Actualiza la cuenta");
		bank.actualizarCuenta(cuenta);
		System.out.println("Determina si la cuenta est� activa: " + bank.estaActivaCuenta("2"));
		System.out.println(bank.buscarCuenta("2"));
	}
}
