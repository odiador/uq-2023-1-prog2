package co.edu.uniquindio.taller.ejercicio2a3.model;

import java.util.List;

public class Client {

    private String name;

    private String lastName;
    private List<BankAccount> bankAccountList;

    private String code;

    /**
     * Es el constructor del cliente
     * 
     * @param name
     * @param lastName
     */
    public Client(final String name, final String lastName, final String code) {
        this.name = name;
        this.lastName = lastName;
        this.code = code;
    }

    /**
     * Obtiene el código del cliente
     * 
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * Cambia el código del cliente
     * 
     * @param code
     */
    public void setCode(final String code) {
        this.code = code;
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
    public void setLastName(final String lastName) {
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
    public void setName(final String name) {
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
    public void setBankAccountList(final List<BankAccount> bankAccountList) {
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
     * Determina si el código existe (no es null ni está vacía la cadena)
     * 
     * @return
     */
    public boolean getCodeExists() {
        return !(getCode() == null || getCode().trim().isEmpty());
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
        return getNameExists() && getLastNameExists() && getCodeExists();
    }

    @Override
    public String toString() {
        return "Client [name=" + name + ", lastName=" + lastName + ", bankAccountList=" + bankAccountList + ", code="
                + code + "]";
    }
}