package co.edu.uniquindio.p2.tradersenterprise.model;

import java.util.ArrayList;
import java.util.List;

public class Trader extends Person {

	private String city;
	private List<Transaction> transactionList;

	/**
	 * Is the constructor of the class {@link Trader}
	 * 
	 * @param id
	 * @param name
	 * @param city
	 */
	public Trader(String id, String name, String city) {
		super(id, name);
		this.city = city;
		this.transactionList = new ArrayList<Transaction>();
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Determines if the trader has a city or not
	 * 
	 * @param city
	 * @return
	 */
	public boolean hasCity(String city) {
		return this.city.equals(city);
	}

	/**
	 * @return the transactionList
	 */
	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	/**
	 * @param transactionList the transactionList to set
	 */
	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	@Override
	public boolean hasAllFields() {
		return super.hasAllFields() && city != null && !city.isEmpty();
	}

	public boolean hasId(String id) {
		return this.id.equals(id);
	}

	@Override
	public String toString() {
		return String.format("Trader [id=%s, name=%s, city=%s, transactionList=%s]", id, name, city, transactionList);
	}

}
