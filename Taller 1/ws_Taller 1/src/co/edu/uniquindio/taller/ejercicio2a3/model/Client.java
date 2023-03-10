package co.edu.uniquindio.taller.ejercicio2a3.model;

import java.util.List;

public class Client {

    private String name;

    private String lastName;
    private List<BankAccount> bankAccountList;

    /**
     * Es el constructor del cliente
     * 
     * @param name
     * @param lastName
     */
    public Client(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    /**
     * Es el constructor del cliente sin parámetros
     */
    public Client() {
    }

    /**
     * Obtiene el apellido del cliente
     * 
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Cambia el apellido del cliente
     * 
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el nombre del cliente
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Cambia el nombre del cliente
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la lista de cuentas de banco del cliente
     * 
     * @return
     */
    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    /**
     * Obtiene la lista de cuentas de banco del cliente
     * 
     * @param bankAccountList
     */
    public void setBankAccountList(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }

    /**
     * Determina si el nombre existe (no es null ni está vacía la cadena)
     * 
     * @return
     */
    public boolean getNameExists() {
        return !(getName() == null || getName().trim().isEmpty());
    }

    /**
     * Determina si el apellido existe (no es null ni está vacía la cadena)
     * 
     * @return
     */
    public boolean getLastNameExists() {
        return !(getLastName() == null || getLastName().trim().isEmpty());
    }

    /**
     * Determina si un cliente existe a partir de saber si existen sus nombres y
     * apellidos
     * 
     * @see {@link #getLastNameExists()}
     *      <li>{@link #getNameExists()}
     * @return
     */
    public boolean getExists() {
        return getNameExists() && getLastNameExists();
    }

}
