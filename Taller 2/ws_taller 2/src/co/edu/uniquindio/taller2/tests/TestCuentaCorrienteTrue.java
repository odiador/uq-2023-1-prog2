package co.edu.uniquindio.taller2.tests;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import co.edu.uniquindio.taller2.exceptions.CuentaException;
import co.edu.uniquindio.taller2.model.Banco;
import co.edu.uniquindio.taller2.model.Cuenta;

public class TestCuentaCorrienteTrue {
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
	public void testCuentaCorrienteConsignacion() throws CuentaException {
		System.out.println("******************");
		System.out.println("*    Cuenta 3    *");
		System.out.println("******************");
		System.out.println("");
		System.out.println("****************");
		System.out.println("* Consignación *");
		System.out.println("****************");
		System.out.println("");
		System.out.println("Se consigna 1000 a la cuenta 3");
		bank.consignarDineroCuenta("3", 1000);
		System.out.println(bank.buscarCuenta("3"));

		System.out.println("Aumenta el dinero");
		bank.consignarDineroCuenta("3", 100000);
		System.out.println(bank.buscarCuenta("3"));
	}

	@Test
	public void testCuentaCorrienteSobregiro() throws CuentaException {
		System.out.println("*************");
		System.out.println("* Sobregiro *");
		System.out.println("*************");
		System.out.println("");
		System.out.println("Se consigna 1000 a la cuenta 3");
		bank.consignarDineroCuenta("3", 1000);
		System.out.println(bank.buscarCuenta("3"));

		System.out.println("Retira 10000 de la cuenta");
		bank.retirarDineroCuenta("3", 10000f);
		System.out.println(bank.buscarCuenta("3"));

		System.out.println("Retira 2000 de la cuenta");
		bank.retirarDineroCuenta("3", 2000f);
		System.out.println(bank.buscarCuenta("3"));

		System.out.println("Consignamos 20000 a la cuenta");
		bank.consignarDineroCuenta("3", 20000f);
		System.out.println(bank.buscarCuenta("3"));
	}

	@Test
	public void testCuentaCorrienteInteres() throws CuentaException {
		System.out.println("********************");
		System.out.println("* Calcular interes *");
		System.out.println("********************");
		System.out.println("");
		System.out.println(bank.buscarCuenta("3"));

		actualizarComisionTasaCuentaCorriente();

		System.out.println("Aumenta el dinero de la cuenta gracias al inter�s");
		bank.calcularInteresesCuenta("3");
		System.out.println(bank.buscarCuenta("3"));
	}

	@Test
	public void testCuentaCorrienteExtracto() throws CuentaException {
		System.out.println("********************");
		System.out.println("* Extracto mensual *");
		System.out.println("********************");

		actualizarComisionTasaCuentaCorriente();

		System.out.println(
				"Calcula y extrae la comision que tiene la cuenta, luego le agrega unos intereses a la cuenta");
		bank.extraerMensualCuenta("3");
		System.out.println(bank.buscarCuenta("3"));
	}

	@Test
	public void testCuentaCorrienteActivaError() throws CuentaException {
		System.out.println("*****************");
		System.out.println("* Cuenta Activa *");
		System.out.println("*****************");
		System.out.println("");
		System.out.println("Espera un error de tipo cuenta");
		expectedException.expect(CuentaException.class);
		System.out.println("");
		System.out.println("Obtiene si una cuenta corriente está activa (error)");
		bank.estaActivaCuenta("3");
	}

	public void actualizarComisionTasaCuentaCorriente() throws CuentaException {
		Cuenta cuenta = bank.buscarCuenta("3");
		System.out.println("Cambia la comision mensual por 1000");
		cuenta.setComisionMensual(1000f);
		System.out.println("Cambia la tasa anual por 1");
		cuenta.setTasaAnual(1f);
		System.out.println("Actualiza la cuenta");
		bank.actualizarCuenta(cuenta);
		System.out.println("Determina si la cuenta est� activa: " + bank.estaActivaCuenta("2"));
		System.out.println(bank.buscarCuenta("3"));
	}
}
