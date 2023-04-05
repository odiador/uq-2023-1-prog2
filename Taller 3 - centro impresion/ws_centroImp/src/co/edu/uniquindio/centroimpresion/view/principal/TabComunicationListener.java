package co.edu.uniquindio.centroimpresion.view.principal;

import co.edu.uniquindio.centroimpresion.model.centro.TipoAccion;

public interface TabComunicationListener {
	/**
	 * Este metodo se activa especialmente para la comunicación de la ventana
	 * principal entre el panel izquierdo que tiene todas sus opciones y el
	 * panel derecho que tiene todas las pestañas del Centro de Impresion
	 *
	 * @param source
	 */
	public void movementPerformed(TipoAccion source);
}
