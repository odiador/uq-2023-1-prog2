package co.edu.uniquindio.taller.punto2.tests;

import co.edu.uniquindio.taller.punto1.Empresa;
import co.edu.uniquindio.taller.punto2.model.BankAccount;

public class Test1 {
    public static void main(String[] args) {
        Empresa e = new Empresa("uq");
        e.getListaPersonas();
        BankAccount b = new BankAccount();
        b.setBalance(1000d);
        System.out.println(b.getFormattedBalance());
        System.out.println(b);
    }
}
