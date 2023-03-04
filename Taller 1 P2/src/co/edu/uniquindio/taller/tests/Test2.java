package co.edu.uniquindio.taller.tests;

import co.edu.uniquindio.taller.ejercicio2a3.model.AccountType;
import co.edu.uniquindio.taller.ejercicio2a3.model.BankAccount;

public class Test2 {
    public static void main(String[] args) {
        BankAccount cuentaBancaria = new BankAccount("Juan", "Amador", "1000", AccountType.AHORROS);
        BankAccount cuentaBancaria2 = new BankAccount("Manuel", "Roa", "1002", AccountType.AHORROS);
        System.out.println("Se crean las cuentas bancarias:");
        System.out.println(cuentaBancaria);
        System.out.println(cuentaBancaria2);
        System.out.println("Intenta agregarle $1000 a la cuenta de Juan");
        try {
            cuentaBancaria.consignBalance(1000d);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("La de Juan pasa a tener $1000:");

        System.out.println(cuentaBancaria);
        System.out.println(cuentaBancaria2);

        System.out.println("Intenta quitarle $2000 a la cuenta de Juan");
        try {
            cuentaBancaria.withDrawBalance(2000d);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Queda igual porque no puede quitarle $2000 a $1000");
        System.out.println(cuentaBancaria);
        System.out.println(cuentaBancaria2);
        System.out.println("Intenta quitarle $500 a la cuenta de Juan");
        try {
            cuentaBancaria.withDrawBalance(500d);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("La cuenta de Juan ahora tiene $500");
        System.out.println(cuentaBancaria);
        System.out.println(cuentaBancaria2);

        System.out.println("Intenta agregarle $100000 a la cuenta de Manuel");
        try {
            cuentaBancaria2.consignBalance(100000d);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("La cuenta de Manuel Queda en $100000");
        System.out.println(cuentaBancaria);
        System.out.println(cuentaBancaria2);
        System.out.println("Manuel envía $10000 a Juan");

        try {
            cuentaBancaria2.sendBalance(cuentaBancaria, 10000d);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println("La cuenta de Manuel queda en $90000 y la de Juan queda en $10500");
        System.out.println(cuentaBancaria);
        System.out.println(cuentaBancaria2);

        System.out.println("Juan envía $11000 a Manuel");
        try {
            cuentaBancaria.sendBalance(cuentaBancaria2, 11000d);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("No se puede porque la cuenta de Juan no cuenta con suficientes fondos");
        System.out.println(cuentaBancaria);
        System.out.println(cuentaBancaria2);
        System.out.println("Se crea una nueva cuenta bancaria sin datos: ");
        BankAccount cuentaBancaria3 = new BankAccount();
        System.out.println(cuentaBancaria3);
        System.out.println(
                "Muestra \"CuentaBancaria [?]\" porque no tiene suficientes datos, \n" +
                        "además no se va a poder ni agregar ni quitar saldo de allí");

        try {
            cuentaBancaria3.consignBalance(1000d);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}