package co.edu.uniquindio.p2.tradersenterprise.model;

import co.edu.uniquindio.p2.tradersenterprise.exceptions.NullException;
import co.edu.uniquindio.p2.tradersenterprise.exceptions.TraderException;

public interface TraderManagement {

	public void addTrader(Trader trader) throws NullException, TraderException;

	public void removeTrader(Trader trader) throws NullException, TraderException;

	public void updateTrader(Trader trader) throws NullException, TraderException;

	public Trader searchTrader(String id);

	public boolean validateTrader(String id);
}
