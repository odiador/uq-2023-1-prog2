package co.edu.uniquindio.parcial1.model;

public class LendingDetail {
    private String code;
    private Double subTotal;
    private Integer quantity;
    private Book book;

    /**
     * Es el constructor del detalle del préstamo
     * 
     * @param code
     * @param subTotal
     * @param quantity
     */
    public LendingDetail(String code, Double subTotal, Integer quantity, Book book) {
        this.code = code;
        this.subTotal = subTotal;
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
     * Cambia el codigo de detallle de prestamo
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Obtiene el subTotal del prestamo
     *
     * @return el subTotal del prestamo
     */
    public Double getSubTotal() {
        return subTotal;
    }

    /**
     * Cambia el subTotal del prestamo
     * 
     * @param subTotal
     */
    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
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
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Determina si el detalle del préstamo tiene un libro por medio de su isbn
     * 
     * @param isbn es el isbn del libro
     * @return true si lo tiene
     */
    public boolean hasIsbn(String isbn) {
        return book.hasIsbn(isbn);
    }

}
