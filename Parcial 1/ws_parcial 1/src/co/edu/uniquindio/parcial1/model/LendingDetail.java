package co.edu.uniquindio.parcial1.model;

public class LendingDetail {
    private String code;
    private Double unitaryValue;
    private Integer quantity;
    private Book book;

    /**
     * Es el constructor del detalle del préstamo
     * 
     * @param code
     * @param unitaryValue
     * @param quantity
     */
    public LendingDetail(final String code, final Double unitaryValue, final Integer quantity, final Book book) {
        this.code = code;
        this.unitaryValue = unitaryValue;
        this.quantity = quantity;
        this.book = book;
    }

    /**
     * Es el constructor del detalle del préstamo sin parámetros
     */
    public LendingDetail() {

    }

    /**
     * Obtiene el codigo de detallle de prestamo
     *
     * @return el codigo de detallle de prestamo
     */
    public String getCode() {
        return code;
    }

    /**
     * Cambia el codigo de detalle de prestamo
     *
     * @param code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Obtiene el subtotal del detalle del préstamo
     * 
     * @return el subtotal
     */
    public Double getSubTotal() {
        return getUnitaryValue() * getQuantity();
    }

    /**
     * Obtiene el valor unitario del detalle del prestamo
     *
     * @return el valor unitario
     */
    public Double getUnitaryValue() {
        return unitaryValue;
    }

    /**
     * Cambia el valor unitario del detalle del prestamo
     * 
     * @param unitaryValue es el valor unitario
     */
    public void setUnitaryValue(final Double unitaryValue) {
        this.unitaryValue = unitaryValue;
    }

    /**
     * 
     * Obtiene la cantidad de libros
     * 
     * @return la cantidad de libros
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Cambia la cantidad de libros
     * 
     * @param quantity la cantidad
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Agrega una cantidad de libros
     * 
     * @param quantity es la cantidad
     */
    public void addQuantity(final Integer quantity) {
        setQuantity(getQuantity() + quantity);
    }

    /**
     * Obtiene el libro del detalle de prestamo
     * 
     * @return el libro
     */
    public Book getBook() {
        return book;
    }

    /**
     * Cambia el libro del detalle de prestamo
     * 
     * @param book el libro
     */
    public void setBook(final Book book) {
        this.book = book;
    }

    /**
     * Determina si el detalle del préstamo tiene un libro por medio de su isbn
     * 
     * @param isbn es el isbn del libro
     * @return true si lo tiene
     */
    public boolean hasIsbn(final String isbn) {
        return book.hasIsbn(isbn);
    }

    /**
     * Determina si el detalle del préstamo existe o no dependiendo de que su
     * cantidad
     * {@code quantity}, código {@code code}, valor unitario {@code unitaryValue} y
     * libro {@code book} existan y no sean null
     * 
     * @return true si existe
     */
    public boolean getExists() {
        return getCode() != null && getUnitaryValue() != null && getQuantity() != null && getBook() != null
                && getBook().getExists();
    }

    /**
     * Determina si el detalle del préstamo tiene la cantidad entre 2 valores
     * 
     * @param min es el valor mínimo
     * @param max es el valor máximo
     * @return
     */
    public boolean hasQuantityBetween(Integer min, Integer max) {
        return getQuantity() >= min && getQuantity() <= max;
    }

}
