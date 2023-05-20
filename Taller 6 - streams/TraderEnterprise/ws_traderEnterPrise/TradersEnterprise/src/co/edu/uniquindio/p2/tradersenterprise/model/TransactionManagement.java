package co.edu.uniquindio.p2.tradersenterprise.model;

import co.edu.uniquindio.p2.tradersenterprise.exceptions.NullException;
import co.edu.uniquindio.p2.tradersenterprise.exceptions.TransactionException;

public interface TransactionManagement {

	public void addTransaction(Transaction transaction) throws NullException, TransactionException;

	public void removeTransaction(Transaction transaction) throws NullException, TransactionException;

	public void updateTransaction(Transaction transaction) throws NullException, TransactionException;

	public Transaction searchTransaction(String id);

	public boolean validateTransaction(String id);
}
