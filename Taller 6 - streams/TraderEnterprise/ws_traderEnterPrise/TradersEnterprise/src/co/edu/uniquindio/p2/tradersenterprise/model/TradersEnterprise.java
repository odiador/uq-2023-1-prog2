package co.edu.uniquindio.p2.tradersenterprise.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import co.edu.uniquindio.p2.tradersenterprise.exceptions.NullException;
import co.edu.uniquindio.p2.tradersenterprise.exceptions.TraderException;
import co.edu.uniquindio.p2.tradersenterprise.exceptions.TransactionException;

public class TradersEnterprise implements TraderManagement, TransactionManagement {

	private String name;
	private List<Trader> tradersList;
	private List<Transaction> transactionList;

	/**
	 * Is the constructor of the trader's enterprise
	 * 
	 * @param name
	 */
	public TradersEnterprise(String name) {
		this.name = name;
		this.tradersList = new ArrayList<Trader>();
		this.transactionList = new ArrayList<Transaction>();
	}

	@Override
	public void addTrader(Trader trader) throws NullException, TraderException {
		if (trader == null)
			throw new NullException("The trader sent is null");
		if (!trader.hasAllFields())
			throw new TraderException("The trader has some fields empty");
		if (validateTrader(trader.getId()))
			throw new TraderException("The trader already exist in the list");
		tradersList.add(trader);
	}

	@Override
	public void removeTrader(Trader trader) throws NullException, TraderException {
		if (trader == null)
			throw new NullException("The trader sent is null");
		if (!trader.doIdExists())
			throw new TraderException("The trader sent has no id");
		if (!validateTrader(trader.getId()))
			throw new TraderException("The trader doesn't exist in the list");
		tradersList.remove(trader);
	}

	@Override
	public void updateTrader(Trader trader) throws NullException, TraderException {
		if (trader == null)
			throw new NullException("The trader sent is null");
		if (!trader.doIdExists())
			throw new TraderException("The trader sent has no id");
		int index = tradersList.indexOf(trader);
		if (index == -1)
			throw new TraderException("The trader doesn't exist in the list");
		tradersList.set(index, trader);
	}

	@Override
	public Trader searchTrader(String id) {
		return tradersList.stream().filter(trader -> trader.hasId(id)).findFirst().orElse(null);
	}

	@Override
	public boolean validateTrader(String id) {
		return searchTrader(id) != null;
	}

	@Override
	public void addTransaction(Transaction transaction) throws NullException, TransactionException {
		if (transaction == null)
			throw new NullException("The transaction sent is null");
		if (!transaction.hasAllFields())
			throw new TransactionException("The transaction has some fields empty");
		if (validateTrader(transaction.getId()))
			throw new TransactionException("The trader already exist in the list");
		transactionList.add(transaction);

	}

	@Override
	public void removeTransaction(Transaction transaction) throws NullException, TransactionException {
		if (transaction == null)
			throw new NullException("The transaction sent is null");
		if (!transaction.doIdExists())
			throw new TransactionException("The transaction sent has no id");
		if (!validateTrader(transaction.getId()))
			throw new TransactionException("The trader doesn't exists in the list");
		transactionList.remove(transaction);
	}

	@Override
	public void updateTransaction(Transaction transaction) throws NullException, TransactionException {
		if (transaction == null)
			throw new NullException("The transaction sent is null");
		if (!transaction.hasAllFields())
			throw new TransactionException("The transaction has some fields empty");
		int index = transactionList.indexOf(transaction);
		if (index == -1)
			throw new TransactionException("The trader doesn't exists in the list");
		transactionList.set(index, transaction);
	}

	@Override
	public Transaction searchTransaction(String id) {
		return transactionList.stream().filter(transaction -> transaction.hasId(id)).findFirst().orElse(null);
	}

	@Override
	public boolean validateTransaction(String id) {
		return searchTransaction(id) != null;
	}

	/**
	 * Finds all transactions in an specific year and sorts them by value (small to
	 * high)
	 * 
	 * @param year
	 * @return a list of transactions
	 */
	public ArrayList<Transaction> getTransactionYearSorted(int year) {
		ArrayList<Transaction> result = transactionList.stream().filter(transaction -> transaction.isInYear(year))
				.sorted().collect(Collectors.toCollection(ArrayList::new));
		return result;

	}

	/**
	 * Gets all the unique cities where the traders work
	 * 
	 * @return
	 */
	public ArrayList<String> getUniqueCitiesTraders() {
		ArrayList<String> list = new ArrayList<String>();
		tradersList.forEach(trader -> {
			String city = trader.getCity();
			if (!list.contains(city))
				list.add(city);
		});
		return list;
	}

	/**
	 * Finds all traders from a city and sort them by name
	 * 
	 * @param city
	 * @return
	 */
	public ArrayList<Trader> getTradersCitySorted(String city) {
		ArrayList<Trader> result = tradersList.stream().filter(trader -> trader.hasCity(city)).sorted()
				.collect(Collectors.toCollection(ArrayList::new));
		return result;
	}

	/**
	 * @return a string of all trader's names sorted alphabetically
	 */
	public String getTradersNamesSorted() {
		StringBuilder stringBuilder = new StringBuilder();
		tradersList.stream().map(Trader::getName).sorted().forEach(text -> {
			stringBuilder.append(text);
			stringBuilder.append(" ");
		});
		return stringBuilder.toString();
	}

	/**
	 * Determinates if a trader is based in a city
	 * 
	 * @param city
	 * @return
	 */
	public boolean validateIfThereIsATraderInCity(String city) {
		return tradersList.stream().map(Trader::getCity).filter(text -> text.equals(city)).findFirst().isPresent();
	}

	/**
	 * Gets the transactions of the traders that lives in a city
	 * 
	 * @param city
	 * @return
	 */
	public ArrayList<Transaction> getTransactionsTraderLiving(String city) {
		ArrayList<Transaction> result = new ArrayList<Transaction>();
		tradersList.stream().filter(trader -> trader.hasCity(city))
				.forEach(trader -> result.addAll(trader.getTransactionList()));
		return result;
	}

	/**
	 * Gets the highest value of all the transactions
	 * 
	 * @param city
	 * @return the highest value
	 */
	public Double getMaxValueTransactions(String city) {
		Optional<Double> optional = transactionList.stream().map(Transaction::getValue)
				.max((o1, o2) -> ((Double) (o1 - o2)).intValue());
		return optional.orElse(null);
	}

	/**
	 * Gets the min value of all the transactions
	 * 
	 * @param city
	 * @return the min value
	 */
	public Double getMinValueTransactions(String city) {
		Optional<Double> optional = transactionList.stream().map(Transaction::getValue)
				.max((o1, o2) -> ((Double) (o2 - o1)).intValue());
		return optional.orElse(null);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the tradersList
	 */
	public List<Trader> getTradersList() {
		return tradersList;
	}

	/**
	 * @param tradersList the tradersList to set
	 */
	public void setTradersList(List<Trader> tradersList) {
		this.tradersList = tradersList;
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
	public String toString() {
		return String.format("TradersEnterprise [name=%s, tradersList=%s, transactionList=%s]", name, tradersList,
				transactionList);
	}
}
