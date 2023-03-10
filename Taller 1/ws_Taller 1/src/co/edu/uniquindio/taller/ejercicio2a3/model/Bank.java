package co.edu.uniquindio.taller.ejercicio2a3.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private String code;
    private List<BankAccount> bankAccountList;
    private List<Client> clientList;

    /**
     * Es el constructor del banco sin parámetros
     */
    public Bank() {
    }

    /**
     * Es el constructor del banco
     * 
     * @param name
     * @param code
     */
    public Bank(String name, String code) {
        this.name = name;
        this.code = code;
        this.bankAccountList = new ArrayList<BankAccount>();
        this.clientList = new ArrayList<Client>();
    }

    /**
     * Agrega una nueva cuenta de banco, muestra un error en caso de que ya se
     * encuentre alguna con el mismo numero de cuenta {@code accountNumber}
     * 
     * @param name
     * @param lastName
     * @param accountNumber
     * @param accountType
     * @throws Exception
     */
    public void addBankAccount(final String name, final String lastName, final String accountNumber,
            final AccountType accountType) throws Exception {
        if (validateBankAccount(accountNumber)) {
            throw new Exception("La cuenta ya existe, no se puede agregar");
        }
        BankAccount account = new BankAccount(accountNumber, accountType);

        bankAccountList.add(account);
    }

    /**
     * Busca la cuenta de banco a partir de su numero de cuenta
     * {@code accountNumber}, retorna una cuenta con constructor vacío si no se
     * encuentra
     * 
     * @param accountNumber
     * @return
     */
    public BankAccount searchBankAccount(String accountNumber) {
        BankAccount account = new BankAccount();
        for (BankAccount eachAccount : bankAccountList) {
            if (eachAccount.getAccountNumber().equals(accountNumber)) {
                account = eachAccount;
                break;
            }
        }
        return account;
    }

    /**
     * Valida si la cuenta de banco se encuentra o no a partir del número de cuenta
     * {@code accountNumber}
     * 
     * @param accountNumber
     * @return
     */
    public boolean validateBankAccount(String accountNumber) {
        return searchBankAccount(accountNumber).getExists();
    }

    /**
     * Envía dinero desde una cuenta enviadora a una que recibe el dinero
     * 
     * @param senderAccountNumber
     * @param recieverAccountNumber
     * @param quantity
     * @throws Exception
     */
    public void sendBalance(String senderAccountNumber, String recieverAccountNumber, Double quantity)
            throws Exception {
        BankAccount senderAccount = searchBankAccount(senderAccountNumber);
        BankAccount recieverAccount = searchBankAccount(recieverAccountNumber);
        senderAccount.sendBalance(recieverAccount, quantity);
    }

    /**
     * Obtiene el nombre del banco
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Cambia el nombre del banco
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el código del banco
     * 
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * Cambia el código del banco
     * 
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Obtiene la lista de cuentas de banco del banco
     * 
     * @return
     */
    public List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    /**
     * cambia la lista de cuentas de banco del banco
     * 
     * @param bankAccountList
     */
    public void setBankAccountList(List<BankAccount> bankAccountList) {
        this.bankAccountList = bankAccountList;
    }

    /**
     * Obtiene la lista de clientes
     * 
     * @return
     */
    public List<Client> getClientList() {
        return clientList;
    }

    /**
     * Cambia la lista de clientes
     * 
     * @param clientList
     */
    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
