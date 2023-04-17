package co.edu.uniquindio.centroimpresion.services;

import co.edu.uniquindio.centroimpresion.model.TipoAccion;

public interface TabComunicationListener {
	/**
	 * Este metodo se activa especialmente para la comunicaci�n de la ventana
	 * principal entre el panel izquierdo que tiene todas sus opciones y el
	 * panel derecho que tiene todas las pesta�as del Centro de Impresion
	 *
	 * @param source
	 */
	public void movementPerformed(TipoAccion source);
}
