package co.edu.uniquindio.parcial1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lending {
    private LocalDate date;
    private LocalDate deliveryDate;
    private String code;
    private final List<LendingDetail> lendingDetailList = new ArrayList<LendingDetail>();
    private Employer employer;

    /**
     * Es el constructor de la clase prestamo
     *
     * @param date
     * @param total2
     * @param deliveryDate
     * @param code
     */
    public Lending(LocalDate date, LocalDate deliveryDate, String code, Employer employer) {
        this.date = date;
        this.deliveryDate = deliveryDate;
        this.code = code;
        this.employer = employer;
    }

    /**
     * Es el constructor de la clase prestamo sin parámetros
     */
    public Lending() {
    }

    /**
     * Obtiene la fecha de la clase prestamo
     *
     * @return la fecha
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Cambia la fecha de la clase prestamo
     *
     * @param date la fecha
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Obtiene el total del prestamo por médio de cada detalle del préstamo
     *
     * @return el total
     */
    public Double getTotal() {
        Double total = 0d;
        for (LendingDetail eachlendingDetail : getLendingDetailList()) {
            total += eachlendingDetail.getSubTotal();
        }
        return total;
    }

    /**
     * Obtiene la fecha de entrega de la clase prestamo
     *
     * @return la fecha de entrega
     */
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Cambia la fecha de entrega de la clase prestamo
     *
     * @param deliveryDate la fecha de entrega
     */
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * Obtiene el codigo de prestamo de la clase prestamo
     *
     * @return el codigo
     */
    public String getCode() {
        return code;
    }

    /**
     * Cambia el codigo de prestamo de la clase prestamo
     *
     * @param code el código
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Obtiene la lista de detalles de préstamo del préstamo
     * 
     * @return la lista de detalles de préstamo
     */
    public List<LendingDetail> getLendingDetailList() {
        return lendingDetailList;
    }

    /**
     * Obtiene el empleado del préstamo
     * 
     * @return el empleado
     */
    public Employer getEmployer() {
        return employer;
    }

    /**
     * Cambia el empleado del préstamo
     * 
     * @param employer es el empleado
     */
    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    /**
     * Obtiene el empleado que ha realizado un préstamo de un Libro a partir del
     * isbn {@code isbn} del libro.
     * 
     * @param isbn es el isbn del libro
     * @return el empleado, si no se encuentra se retorna un empleado sin atributos
     *         (inexistente)
     */
    public Employer getEmployerByIsbn(String isbn) {
        Employer employer = new Employer();
        for (LendingDetail eachLendingDetail : getLendingDetailList()) {
            if (eachLendingDetail.hasIsbn(isbn)) {
                employer = getEmployer();
            }
        }
        return employer;
    }

    /**
     * Determina si un préstamo existe o no dependiendo de que su código, dia, dia
     * de entrega y empleado son diferentes de null
     * 
     * @return true si ninguna es null
     */
    public boolean getExists() {
        return getCode() != null && getDate() != null && getDeliveryDate() != null && getEmployer() != null;
    }
}