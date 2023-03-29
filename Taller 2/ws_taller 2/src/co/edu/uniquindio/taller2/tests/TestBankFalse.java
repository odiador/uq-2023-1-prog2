package co.edu.uniquindio.taller2.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import co.edu.uniquindio.taller2.exceptions.CuentaException;
import co.edu.uniquindio.taller2.model.Banco;

public class TestBankFalse {
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void TestBancoAgregarCuenta2() throws CuentaException {
		Banco bank = new Banco("Bancolombia", "Cra 9 # 23 - 01", "AB-001");
		// Agrega una cuenta de ahorros
		bank.agregarCuentaAhorros("A001", 0, 0);
		// Agrega una cuenta de ahorros con el mismo código (error)
		bank.agregarCuentaAhorros("A001", 0, 0);
	}
}
