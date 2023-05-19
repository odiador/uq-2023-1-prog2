package co.edu.uniquindio.p2.empresaenergia.exceptions;

public class FacturaException extends Exception {
	/**
	 * Es el constructor de la clase FacturaException
	 * 
	 * @param msg
	 */
	public FacturaException(String msg) {
		super(msg);
	}

	/**
	 * Es el constructor de la clase FacturaException sin parámetros, tiene de mensaje:
	 * "Hay un error relacionado a la factura"
	 */
	public FacturaException() {
		super("Hay un error relacionado a la factura");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
