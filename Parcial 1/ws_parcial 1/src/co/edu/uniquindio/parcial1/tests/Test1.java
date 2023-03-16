package co.edu.uniquindio.parcial1.tests;

import java.time.LocalDate;

import co.edu.uniquindio.parcial1.model.*;

public class Test1 {
    public static void main(String[] args) {
        Library biblioteca = new Library("Biblioteca UQ", "Cra 23 # 23 - 50", "1232313");
        try {
            System.out.println(biblioteca.addEmployer("Pablo", 20000d, "", 0));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(biblioteca.addEmployer("Pablo", 20000d, "", 0));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(biblioteca.addEmployer("Simón", 20000d, "", 1));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(biblioteca.addLending(LocalDate.now(), LocalDate.now().plusWeeks(1), "1000",
                    biblioteca.searchEmployerOrThrow("Pablo")));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(biblioteca.addLending(LocalDate.now(), LocalDate.now().plusWeeks(1), "1001",
                    biblioteca.searchEmployerOrThrow("Antonio")));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(biblioteca.addLending(LocalDate.now(), LocalDate.now().plusWeeks(1), "1000",
                    biblioteca.searchEmployerOrThrow("Pablo")));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(biblioteca.addBook("Santiago el esquizofrénico", "Ozuna", "I0001"));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(biblioteca.addBook("Santiago el esquizofrénico", "Amador", "I0001"));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(biblioteca.addBook("Santiago el esquizofrénico", "Amador", "I0002"));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            Book libroDeOzuna = biblioteca.searchBookOrThrow("I0001");
            biblioteca.addLendingDetail("1000", 20000d, 3, libroDeOzuna);
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            Book libroDeOzuna = biblioteca.searchBookOrThrow("I0001");
            biblioteca.addLendingDetail("1000", 20000d, 3, libroDeOzuna);
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {

            Book libroDeOzuna = biblioteca.searchBookOrThrow("I0001");
            System.out.println(biblioteca.addLendingDetail("1000", 10000d, 3, libroDeOzuna));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(biblioteca.addLendingDetail("1000", 10000d, 3, new Book("ibn", "name", "ozuna")));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            Employer empleadeishon = biblioteca.getEmployerByISBNBookofLending("I0002");
            System.out.println("El empleado fue obtenido epicamente: " + empleadeishon.toString());
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }
}