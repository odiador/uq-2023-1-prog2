package co.edu.uniquindio.p2.empresaenergia.model;

import co.edu.uniquindio.p2.empresaenergia.exceptions.FacturaException;
import co.edu.uniquindio.p2.empresaenergia.exceptions.NullException;

public interface FacturaManagement {
	public void registrarFactura(Factura factura) throws NullException, FacturaException;

	public boolean validarFactura(String codigo);

	public Factura buscarFactura(String codigo);
}
