package co.edu.uniquindio.p2.tradersenterprise.model;

import java.time.LocalDate;

public class Transaction implements Comparable<Transaction> {
	private String id;
	private double value = -1d;
	private LocalDate date;

	/**
	 * Is the constructor of the class Transaction
	 * 
	 * @param id
	 * @param value
	 * @param date
	 */
	public Transaction(String id, double value, LocalDate date) {
		this.id = id;
		this.value = value;
		this.date = date;

	}

	/**
	 * @return the id of the transaction
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id of the transaction to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the value of the transaction
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value the value of the transaction to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date of the transaction to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public int compareTo(Transaction transaction) {
		return new Double(transaction.getValue() - value).intValue();
	}

	/**
	 * Determines if a transaction is in a specific year
	 * 
	 * @param year the year
	 * @return
	 */
	public boolean isInYear(int year) {
		return date.getYear() == year;
	}

	/**
	 * Determines if the transaction has all fields filled
	 * 
	 * @return
	 */
	public boolean hasAllFields() {
		return doIdExists() && date != null && value >= 0;
	}

	/**
	 * Determines if the id of the transaction exists
	 * 
	 * @return
	 */
	public boolean doIdExists() {
		return id != null && !id.isEmpty();
	}

	/**
	 * Determines if the Transaction has an id specified
	 * 
	 * @param id
	 * @return
	 */
	public boolean hasId(String id) {
		return this.id.equals(id);
	}

	@Override
	public String toString() {
		return String.format("Transaction [id=%s, value=%s, date=%s]", id, value, date);
	}

}
