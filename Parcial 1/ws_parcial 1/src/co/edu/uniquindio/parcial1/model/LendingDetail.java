package co.edu.uniquindio.parcial1.model;

public class LendingDetail {
    private String code;
    private Double subTotal;
    private Integer quantity;

    /**
     * Es el constructor del detalle del préstamo
     * 
     * @param code
     * @param subTotal
     * @param quantity
     */
    public LendingDetail(String code, Double subTotal, Integer quantity) {
        this.code = code;
        this.subTotal = subTotal;
        this.quantity = quantity;
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
}
