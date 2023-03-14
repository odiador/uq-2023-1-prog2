package co.edu.uniquindio.parcial1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Lending {
    private LocalDate date;
    private LocalDate deliveryDate;
    private String code;
    private Employer employer;

    private boolean isEnded = false;
    private final List<LendingDetail> lendingDetailList = new ArrayList<LendingDetail>();

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
     * Agrega un detalle de préstamo al préstamo, muestra un error en caso de que ya
     * esté terminado el préstamo o si ya exista el código
     * 
     * @param code         es el codigo del detalle del préstamo
     * @param unitaryValue es el valor unitario del detalle del préstamo
     * @param quantity     es la cantidad del detalle del préstamo
     * @param book         es el libro del detalle del préstamo
     * @return El detalle del préstamo ha sido añadido ({@code code})
     * @throws LibraryException En caso de que ya exista
     *                          <li>En caso de que ya haya terminado el préstamo
     * @see {@link #isEnded()}
     *      <li>{@link #throwIfEnded()}
     * 
     */
    public String addLendingDetail(String code, Double unitaryValue, Integer quantity, Book book)
            throws LibraryException {
        throwIfEnded();
        if (validateLendingDetail(code)) {
            throw new LibraryException("El detalle del préstamo ya existe (" + code + ")");
        }
        getLendingDetailList().add(new LendingDetail(code, unitaryValue, quantity, book));
        return "El detalle del préstamo ha sido añadido (" + code + ")";
    }

    /**
     * Elimina un detalle del préstamo a partir del código, muestra un error si el
     * préstamo está bloqueado o no lo encuentra
     * 
     * @param code es el codigo del detalle del préstamo
     * @return El detalle del préstamo ha sido eliminado ({@code code})
     * @throws LibraryException En caso de que no exista
     *                          <li>En caso de que ya haya terminado el préstamo
     * @see {@link #isEnded()}
     *      <li>{@link #throwIfEnded()}
     */
    public String removeLendingDetail(String code) throws LibraryException {
        throwIfEnded();
        if (validateLendingDetail(code)) {
            throw new LibraryException("El detalle del préstamo no existe (" + code + ")");
        }
        getLendingDetailList().remove(searchLendingDetail(code));
        return "El detalle del préstamo ha sido eliminado (" + code + ")";
    }

    private void throwIfEnded() throws LibraryException {
        if (isEnded())
            throw new LibraryException("El detalle de préstamo no puede ser cambiado");
    }

    public boolean validateLendingDetail(String code) {
        return searchLendingDetail(code).getExists();
    }

    public LendingDetail searchLendingDetail(String code) {
        return getLendingDetailList().stream().filter(lendingDetail -> lendingDetail.getCode().equals(code)).findFirst()
                .orElse(new LendingDetail());
    }

    /**
     * Determina si un préstamo ya ha sido terminado, en caso de que sí, no se
     * pueden ni agregar ni eliminar detalles
     * 
     * @return true si ya terminó
     */
    public boolean isEnded() {
        return isEnded;
    }

    /**
     * Termina el préstamo y hace que no se puedan agregar o eliminar detalles
     * 
     * @see {@link #isEnded()}
     */
    public void endLending() {
        this.isEnded = true;
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