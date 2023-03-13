package co.edu.uniquindio.parcial1.model;

import java.time.LocalDate;

public class Lending {
    private LocalDate date;
    private String total;
    private LocalDate deliveryDate;
    private String code;

    /**
     * Es el constructor de la clase prestamo
     *
     * @param date
     * @param total
     * @param deliveryDate
     * @param code
     */
    public Lending(LocalDate date, String total, LocalDate deliveryDate, String code) {
        this.date = date;
        this.total = total;
        this.deliveryDate = deliveryDate;
        this.code = code;
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
     * Obtiene el total del prestamo
     *
     * @return el total
     */
    public String getTotal() {
        return total;
    }

    /**
     * Cambia el total del prestamo
     *
     * @param total es el total
     */
    public void setTotal(String total) {
        this.total = total;
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
}