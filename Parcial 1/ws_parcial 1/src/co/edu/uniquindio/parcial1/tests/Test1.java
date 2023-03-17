package co.edu.uniquindio.parcial1.tests;

import java.time.LocalDate;

import co.edu.uniquindio.parcial1.model.*;

public class Test1 {
    public static void main(String[] args) {
        Library biblioteca = new Library("Biblioteca UQ", "Cra 23 # 23 - 50", "1232313");
        System.out.println("Intenta crear un empleado con identificación 0001:");
        try {
            System.out.println(biblioteca.addEmployer("0001", "Pablo", 20000d, "", 0));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Intenta crear otro empleado con identificación 0001:");
        try {
            System.out.println(biblioteca.addEmployer("0001", "Pablo", 20000d, "", 0));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Intenta crear otro empleado con identificación 0002:");
        try {
            System.out.println(biblioteca.addEmployer("0002", "Simón", 20000d, "", 1));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Intenta crear un estudiante con identificación 0001:");

        try {

            System.out.println(
                    biblioteca.addStudent("Juan", "Amador", 17, "I. S.", "314231463", "tamo activo papi", "0001"));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Intenta crear otro estudiante con identificación 0001:");

        try {
            System.out.println(
                    biblioteca.addStudent("Juan", "Amador", 17, "I. S.", "314231463", "tamo activo papi", "0001"));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Intenta crear otro estudiante con identificación 0002:");

        try {
            System.out.println(
                    biblioteca.addStudent("Fabian", "Valencia", 17, "I. S.", "314231463", "tamo activo papi", "0002"));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(
                "Intenta crear un préstamo con código 1000 al empleado con id 0001 y el estudiante con id 0002:");
        try {
            System.out.println(biblioteca.addLending(LocalDate.now(), LocalDate.now().plusWeeks(1), "1000",
                    biblioteca.searchEmployerOrThrow("0001"), biblioteca.searchStudentOrThrow("0002")));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(
                "Intenta crear otro préstamo con código 1000 al empleado con id 0001 y el estudiante con id 0002:");
        try {
            System.out.println(biblioteca.addLending(LocalDate.now(), LocalDate.now().plusWeeks(1), "1000",
                    biblioteca.searchEmployerOrThrow("0001"), biblioteca.searchStudentOrThrow("0002")));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(biblioteca.addLending(LocalDate.now(), LocalDate.now().plusWeeks(1), "1000",
                    biblioteca.searchEmployerOrThrow("0001"), biblioteca.searchStudentOrThrow("0002")));
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