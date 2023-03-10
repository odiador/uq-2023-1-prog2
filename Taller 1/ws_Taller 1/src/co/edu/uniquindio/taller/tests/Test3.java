package co.edu.uniquindio.taller.tests;

import java.util.Collections;

import co.edu.uniquindio.taller.ejercicio2a3.model.AccountType;
import co.edu.uniquindio.taller.ejercicio2a3.model.Bank;

public class Test3 {
    public static void main(String[] args) {
        Bank banco = new Bank("Bancolombia", "0001");
        try {
            imprimirMsg(
                    banco.addBankAccount("Cuenta 2", AccountType.AHORROS));
        } catch (Exception e) {
            imprimirMsg(e.getMessage());
        }
        try {
            imprimirMsg(
                    banco.addBankAccount("Cuenta 1", AccountType.CORRIENTE));
        } catch (Exception e) {
            imprimirMsg(e.getMessage());
        }
        try {
            imprimirMsg(
                    banco.addBankAccount("Cuenta 3", AccountType.AHORROS));
        } catch (Exception e) {
            imprimirMsg(e.getMessage());
        }
        imprimirMsg("La primera cuenta es:");
        banco.getBankAccountList().stream().map(bankAccount -> bankAccount.getAccountNumber()).peek(System.out::println)
                .findFirst();

        imprimirMsg("Consignación por medio de un foreach y Math.random:");

        banco.getBankAccountList().stream().forEach(bankAccount -> {
            try {
                imprimirMsg(bankAccount.consignBalance(100d * (int) ((Math.random()) * 100)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        imprimirMsg("Filtro por solo cuentas de ahorro:");

        banco.getBankAccountList().stream()
                .filter(bankAccount -> bankAccount.getAccountType().equals(AccountType.AHORROS))
                .forEach(System.out::println);

        imprimirMsg("Las cuentas de ahorro son ordenadas por numero de cuenta:");

        Collections.sort(banco.getBankAccountList());
        banco.getBankAccountList().stream().forEach(System.out::println);

        imprimirMsg("Las cuentas de ahorro son ordenadas al reves:");
        Collections.sort(banco.getBankAccountList(), Collections.reverseOrder());
        banco.getBankAccountList().stream().forEach(System.out::println);

    }

    public static void imprimirMsg(Object msg) {
        System.out.println(msg);
    }
}
