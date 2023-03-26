package co.edu.uniquindio.taller2.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import co.edu.uniquindio.taller2.model.Banco;
import co.edu.uniquindio.taller2.model.CuentaException;

public class TestBankTrue {

	public Banco bank = new Banco("Bancolombia", "Cra 9 # 23 - 01", "AB-001");

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void TestBancoAgregarCuenta1() throws CuentaException {

		// Agrega una cuenta de ahorros
		bank.agregarCuentaAhorros("A001", 0, 0);
		// Agrega una cuenta de ahorros con diferente c�digo
		bank.agregarCuentaAhorros("A002", 0, 0);
	}

	@Test
	public void TestBancoAgregarCuenta2() throws CuentaException {
		expectedException.expect(CuentaException.class);
		// Agrega una cuenta de ahorros
		bank.agregarCuentaAhorros("A001", 0, 0);
		// Agrega una cuenta de ahorros con el mismo c�digo (error)
		bank.agregarCuentaAhorros("A001", 0, 0);
	}
}