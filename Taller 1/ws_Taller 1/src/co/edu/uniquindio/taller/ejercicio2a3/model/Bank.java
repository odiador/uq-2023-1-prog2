package co.edu.uniquindio.taller.ejercicio2a3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bank {
    private String name;
    private String code;
    private List<BankAccount> bankAccountList = new ArrayList<BankAccount>();
    private List<Client> clientList = new ArrayList<Client>();

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
    public Bank(final String name, final String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * Agrega una nueva cuenta de banco, muestra un error en caso de que ya se
     * encuentre alguna con el mismo numero de cuenta {@code accountNumber}
     * 
     * @param accountNumber
     * @param accountType
     * @throws Exception
     */
    public String addBankAccount(final String accountNumber,
            final AccountType accountType) throws BankException {
        if (validateBankAccount(accountNumber)) {
            throw new BankException("La cuenta ya existe, no se puede agregar");
        }
        final BankAccount account = new BankAccount(accountNumber, accountType);

        bankAccountList.add(account);
        return "La cuenta fue agregada exitosamente (" + accountNumber + ")";
    }

    /**
     * Elimina una cuenta de banco, a partir de un numero de cuenta
     * {@code accountNumber}, muestra un error en caso de que la cuenta no exista
     * 
     * @param accountNumber es el numero de cuenta
     * @return "La cuenta ha sido eliminada con éxito"
     * @throws BankException en caso de que no exista
     */
    public String removeBankAccount(final String accountNumber) throws BankException {
        if (!validateBankAccount(accountNumber)) {
            throw new BankException("La cuenta no existe, no se puede eliminar");
        }
        setBankAccountList(bankAccountList.stream().filter(e -> !e.getAccountNumber().equals(accountNumber))
                .collect(Collectors.toList()));
        return "La cuenta ha sido eliminada con éxito";
    }

    /**
     * Busca la cuenta de banco a partir de su numero de cuenta
     * {@code accountNumber}, retorna una cuenta con constructor vacío si no se
     * encuentra
     * 
     * @param accountNumber
     * @return
     */
    public BankAccount searchBankAccount(final String accountNumber) {
        BankAccount account = new BankAccount();
        for (final BankAccount eachAccount : bankAccountList) {
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
    public boolean validateBankAccount(final String accountNumber) {
        return searchBankAccount(accountNumber).getExists();
    }

    /**
     * Agrega un cliente a partir de su nombre, apellido y código; muestra un error
     * cuando el cliente ya existe
     * 
     * @param name     es el nombre del cliente
     * @param lastName es el apellido del cliente
     * @param code     es el código del cliente
     * @return "El cliente fue agregado de manera exitosa"
     * @throws BankException cuando el cliente ya existe
     */
    public String addClient(final String name, final String lastName, final String code) throws BankException {
        if (validateClient(code)) {
            throw new BankException("El cliente ya existe");
        }
        clientList.add(new Client(name, lastName, code));
        return "El cliente fue agregado de manera exitosa";
    }

    /**
     * Elimina un cliente a partir de su código, muestra un error en caso de que el
     * cliente no exista
     * 
     * @param code es el código del cliente
     * @return "El cliente fue eliminado de manera exitosa"
     * @throws BankException en caso de que el cliente no exista
     */
    public String removeClient(final String code) throws BankException {
        Client client = searchClient(code);
        if (!client.getExists()) {
            throw new BankException("El cliente no existe");
        }
        clientList.remove(client);
        return "El cliente fue eliminado de manera exitosa";
    }

    /**
     * Busca un cliente por medio de su código {@code code}, si no lo encuentra
     * retorna un cliente sin parámetros
     * 
     * @param code
     * @return
     */
    public Client searchClient(final String code) {
        return clientList.stream().filter(client -> code.equals(client.getCode())).findFirst().orElse(new Client());
    }

    /**
     * Valida si un cliente está en la lista a partir de un código {@code code}
     * 
     * @param code es el código del cliente
     * @return true si existe
     */
    public boolean validateClient(final String code) {
        return searchClient(code).getExists();
    }

    /**
     * Envía dinero desde una cuenta enviadora a una que recibe el dinero
     * 
     * @param senderAccountNumber
     * @param recieverAccountNumber
     * @param quantity
     * @throws BankException
     */
    public void sendBalance(final String senderAccountNumber, final String recieverAccountNumber, final Double quantity)
            throws BankException {
        final BankAccount senderAccount = searchBankAccount(senderAccountNumber);
        final BankAccount recieverAccount = searchBankAccount(recieverAccountNumber);
        senderAccount.sendBalance(recieverAccount, quantity);
    }

    /**
     * Compara el dinero de una cuenta a otra, muestra un error en caso de que
     * alguna de las cuentas no exista
     * 
     * @param senderAccountNumber
     * @param recieverAccountNumber
     * @param quantity
     * @return
     * @throws BankException
     */
    public boolean compareBalance(final String senderAccountNumber, final String recieverAccountNumber,
            final Double quantity)
            throws BankException {
        final BankAccount senderAccount = searchBankAccount(senderAccountNumber);
        final BankAccount recieverAccount = searchBankAccount(recieverAccountNumber);
        return senderAccount.compareBalanceTo(recieverAccount);
    }

    /**
     * Saca dinero de una cuenta con un código {@code code} determinado, muestra un
     * error si no se encuentra la cuenta de banco o si la cantidad a quitar es
     * mayor a la cantidad que se tiene
     * 
     * @param code
     * @param quantity
     * @throws BankException
     */
    public void withdrawBalance(final String code,
            final Double quantity)
            throws BankException {
        final BankAccount accountToWithdraw = searchBankAccount(code);
        accountToWithdraw.withDrawBalance(quantity);
    }

    /**
     * Agrega dinero a una cuenta con un código {@code code} determinado, muestra un
     * error si no se encuentra la cuenta de banco
     * 
     * @param code
     * @param quantity
     * @return
     * @throws BankException
     */
    public String consignBalance(final String code, final Double quantity) throws BankException {
        return searchBankAccount(code).consignBalance(quantity);
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
    public void setName(final String name) {
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
    public void setCode(final String code) {
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
    public void setBankAccountList(final List<BankAccount> bankAccountList) {
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
    public void setClientList(final List<Client> clientList) {
        this.clientList = clientList;
    }

    /**
     * Determina si un banco existe o no dependiendo de que su nombre y código no
     * sean null
     * 
     * @return true si ninguno es null
     */
    public boolean getExists() {
        return getName() != null && getCode() != null;
    }

    @Override
    public String toString() {
        return getExists() ? "Bank [name=" + name + ", code=" + code + "]" : "Bank [?]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bank other = (Bank) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

}
