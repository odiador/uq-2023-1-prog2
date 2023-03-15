package co.edu.uniquindio.parcial1.tests;

import java.time.LocalDate;

import co.edu.uniquindio.parcial1.model.*;

public class Test1 {
    public static void main(String[] args) {
        Library biblioteca = new Library("Biblioteca UQ", "Cra 23 # 23 - 50", "1232313");
        try {
            System.out.println(biblioteca.addEmployer("Pablo", 20000d, ""));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(biblioteca.addEmployer("Pablo", 20000d, ""));
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
            System.out.println(biblioteca.addBook("Santiago el esquizofrénico", "Amador", "I0001"));
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
            System.out.println(biblioteca.addLendingDetail("1000", 10000d, 3, new Book("ISBN", "name", "ozuna")));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(biblioteca.addLendingDetail("1000", 10000d, 3, new Book("ibn", "name", "ozuna")));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            Employer empleadeishon = biblioteca.getEmployerByISBNBookofLending("ISBN2");
            System.out.println("si sirvio");
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
    }
}