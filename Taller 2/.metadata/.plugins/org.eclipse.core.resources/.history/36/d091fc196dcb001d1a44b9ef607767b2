package co.edu.uniquindio.taller2.tests;

import org.junit.Assert;

import org.junit.Test;

import co.edu.uniquindio.taller2.model.*;

public class TestEqualsTrue {

	@Test
	public void testCuentaAhorrosEquals1() {

		CuentaAhorros cuentaAhorros = new CuentaAhorros("0001", 0f, 0f);
		CuentaAhorros cuentaAhorros2 = new CuentaAhorros("0001", 100, 100);
		Assert.assertTrue(cuentaAhorros.equals(cuentaAhorros2));
	}

	@Test
	public void testBancoEquals1() {
		// Tienen el mismo código de banco
		Banco banco = new Banco("Bancolombia", "Cra 10N # 5-30", "AB-001");
		Banco banco2 = new Banco("NuBank", "Cra 5N # 10-30", "AB-001");
		Assert.assertEquals(banco, banco2);
	}
}
